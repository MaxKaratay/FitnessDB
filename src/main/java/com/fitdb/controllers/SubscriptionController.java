package com.fitdb.controllers;

import com.fitdb.domain.Client;
import com.fitdb.domain.Instruct;
import com.fitdb.domain.Schedule;
import com.fitdb.domain.Subscription;
import com.fitdb.domain.Visits;
import com.fitdb.repository.ScheduleRepository;
import com.fitdb.repository.VisitsRepository;
import com.fitdb.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
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
