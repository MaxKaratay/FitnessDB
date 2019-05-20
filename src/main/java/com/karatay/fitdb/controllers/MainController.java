package com.karatay.fitdb.controllers;

import com.karatay.fitdb.domain.Discipline;
import com.karatay.fitdb.domain.Instruct;
import com.karatay.fitdb.domain.Subscription;
import com.karatay.fitdb.repository.DisciplineRepository;
import com.karatay.fitdb.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class MainController {
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping
    public String index(@RequestParam(name = "name", required = false, defaultValue = "visitor") String name, Model model) {
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/disciplines")
    public String allDisciplines(Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

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
