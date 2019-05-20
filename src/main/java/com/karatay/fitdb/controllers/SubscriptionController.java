package com.karatay.fitdb.controllers;

import com.karatay.fitdb.domain.*;
import com.karatay.fitdb.repository.ScheduleRepository;
import com.karatay.fitdb.repository.VisitsRepository;
import com.karatay.fitdb.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping
    public String subscriptionList(@RequestParam(required = false, name = "client", defaultValue = "") String clientId,
                                   @RequestParam(required = false, name = "instruct", defaultValue = "") String instructId,
                                   Model model) {
        if (clientId != null && !clientId.isEmpty()) {
            Client client = subscriptionService.getClientRepository().findById(Long.parseLong(clientId)).get();
            Instruct instruct = subscriptionService.getScheduleService().getInstructService()
                    .getInstructRepository().findById(Long.parseLong(instructId)).get();
            model.addAttribute("_client", client);
            if (subscriptionService.freeTrainingPlace(instruct)) {// check free places
                model.addAttribute("_ins", instruct);
            } else {
                String error = instruct.getInstructor().getFirstName() + " " + instruct.getInstructor().getLastName()
                        + " " + instruct.getInstructor().getPatronymic() + " have not free places!";
                model.addAttribute("trainingOverflow", error);
            }

//            Set<Schedule> schedules = subscriptionService.schedulesByInstructWithOutCrossing(instruct, client);
//            if (schedules.isEmpty()) { // check if client can training in instruct time
//                model.addAttribute("scheduleEngaged", "Can`t find free schedule in your subscription list !");
//            } else {
//                model.addAttribute("schedules", schedules);
//            }
            model.addAttribute("schedules", subscriptionService.getScheduleService().getScheduleRepository().findAllByInstruct(instruct));
        }
        Iterable<Subscription> subscriptions = subscriptionService.getSubscriptionRepository().findAll();
        model.addAttribute("subscriptions", subscriptions);
        model.addAttribute("instructs", subscriptionService.getScheduleService().getInstructService().getInstructRepository().findAll());
        model.addAttribute("clients", subscriptionService.getClientRepository().findAll());
        return "subscriptions";
    }

    @PostMapping
    public String addSubscription(@RequestParam(name = "client") Client client,
                                  @RequestParam(name = "instruct") Instruct instruct,
                                  @RequestParam Map<String, String> form) {
        Subscription subscription = subscriptionService.getSubscriptionRepository()
                .save(new Subscription(client, instruct, java.sql.Date.valueOf(LocalDate.now().plusMonths(1))));//add a month to date of creating subs. to take duration end
        VisitsRepository vr = subscriptionService.getVisitsRepository();
        ScheduleRepository sr = subscriptionService.getScheduleService().getScheduleRepository();
        for (String key : form.keySet()) {
            if (SubscriptionService.isNumeric(key)) {
                vr.save(new Visits(subscription, sr.findById(Long.parseLong(key)).get()));
            }
        }
        return "redirect:/subscriptions";
    }

    @GetMapping("{subscription}")
    public String editSubscription(@PathVariable Subscription subscription, Model model) {
        model.addAttribute("subscription", subscription);
        List<Schedule> scheduleList = StreamSupport.stream(subscriptionService.getVisitsRepository()
                .findAllBySubscription(subscription).spliterator(), false)
                .map(Visits::getSchedule).collect(Collectors.toList());
        model.addAttribute("scheduleByClient", scheduleList);
        model.addAttribute("allSchedule", subscriptionService.getScheduleService().getScheduleRepository().findAllByInstruct(subscription.getInstruct()));
        return "editSubscription";
    }

    @PostMapping("{subscription}")
    public String changeSubscription(@PathVariable Subscription subscription,
                                     @RequestParam Map<String, String> form) {
        List<Schedule> allByInstruct = StreamSupport.stream(subscriptionService.getScheduleService()
                .getScheduleRepository().findAllByInstruct(subscription.getInstruct()).spliterator(), false)
                .collect(Collectors.toList());
        List<Visits> remove = new ArrayList<>();
        for (Schedule s : allByInstruct) {
            for (Visits v : subscription.getVisits()) {
                if (v.getSchedule().equals(s)) {
                    remove.add(v);
                }
            }
        }
        subscription.getVisits().removeAll(remove);
        for (String key : form.keySet()) {
            Visits v = subscriptionService.getVisitsRepository()
                    .save(new Visits(subscription, subscriptionService.getScheduleService().getScheduleRepository().findById(Long.parseLong(key)).get()));
            subscription.addVisit(v);
        }
        subscriptionService.getSubscriptionRepository().save(subscription);
        return "redirect:/subscriptions";
    }


    @GetMapping("/delete/{subscription}")
    public String deleteSubscription(@PathVariable Subscription subscription) {
        subscriptionService.getSubscriptionRepository().delete(subscription);
        return "redirect:/subscriptions";
    }

}
