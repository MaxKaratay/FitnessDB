package com.karatay.fitdb.controllers;

import com.karatay.fitdb.domain.*;
import com.karatay.fitdb.repository.DisciplineRepository;
import com.karatay.fitdb.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class MainController {
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public String index(@AuthenticationPrincipal RoleMarker user, Model model) {
        if (user.isClient()) {
            model.addAttribute("subs", subscriptionService.getSubscriptionRepository()
                    .findAllByDurationEndGreaterThanEqualAndClient( Date.valueOf(LocalDate.now()),(Client) user));
        }
        if (user.isInstructor()) {
            Iterable<Schedule> allByInstruct_instructor = subscriptionService.getScheduleService().getScheduleRepository()
                    .findAllByInstruct_Instructor((Instructor) user);
            Map<Schedule, Integer> map = new TreeMap<>(Comparator.comparingLong(e-> e.getPeriod().getID()));
            for (Schedule s : allByInstruct_instructor) {
                map.put(s,subscriptionService.getSubscriptionRepository().countSubscriptionByInstruct(s.getInstruct()));
            }
            model.addAttribute("schedules", map);
        }
        return "main";
    }

    @GetMapping("/disciplines")
    public String allDisciplines(Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/reports")
    public String seeReports(Model model) {
        Iterable<Subscription> subscriptions = subscriptionService.getSubscriptionRepository()
                .findAllByDurationEndGreaterThanEqual(Date.valueOf(LocalDate.now()));
        model.addAttribute("subs", subscriptions);
        model.addAttribute("instructs", subscriptionService.reportData(StreamSupport
                .stream(subscriptions.spliterator(), false).collect(Collectors.toSet())));
        int profit = StreamSupport.stream(subscriptions.spliterator(), false)
                .map(Subscription::getInstruct).mapToInt(Instruct::getPrice).sum();
        model.addAttribute("profit", profit);
        return "reports";
    }

}
