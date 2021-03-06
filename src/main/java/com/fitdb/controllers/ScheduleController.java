package com.fitdb.controllers;

import com.fitdb.domain.Day;
import com.fitdb.domain.Discipline;
import com.fitdb.domain.Instruct;
import com.fitdb.domain.Instructor;
import com.fitdb.domain.Period;
import com.fitdb.domain.Schedule;
import com.fitdb.domain.Time;
import com.fitdb.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @GetMapping
    public String seeSchedule(Model model) {
        model.addAttribute("days", scheduleService.scheduleByDay());
        return "schedules";
    }

    @GetMapping("{day}")
    public String editSchedule(@PathVariable Day day, Model model) {
        model.addAttribute("day", day);
        model.addAttribute("schedule", scheduleService.getScheduleRepository().findAllByDay(day));
        scheduleService.loadResources(model);
        return "editSchedule";
    }

    @PostMapping("{day}")
    public String addSchedule(@PathVariable Day day,
                              @RequestParam(name = "discipline") String dis,
                              @RequestParam(name = "instructor") String ins,
                              @RequestParam(name = "time") String time,
                              Model model) {
        String[] fio = ins.split(" ");
        Instructor instructor = scheduleService.getInstructService()
                .getInstructorRepository().findByFirstNameAndLastNameAndPatronymic(fio[0], fio[1], fio[2]).get();
        Discipline discipline = scheduleService.getInstructService().getDisciplineRepository().findByName(dis).get();
        Optional<Instruct> instruct = scheduleService.getInstructService()
                .getInstructRepository().findByDisciplineAndInstructor(discipline, instructor);
        if (!instruct.isPresent()) {
            model.addAttribute("alert", "Chosen discipline does not instruct by selected instructor");
        } else {
            String[] t = time.split(" - ");
            //Day day1 = scheduleService.getDayRepository().findByName(day).get();
            Time time1 = scheduleService.getTimeRepository().findByStartAndFinish(t[0], t[1]).get();
            Period period = new Period(day, time1);
            scheduleService.getPeriodRepository().save(period);
            scheduleService.getScheduleRepository().save(new Schedule(instruct.get(), period));
        }
        scheduleService.loadResources(model);
        model.addAttribute("day", day);
        model.addAttribute("schedule", scheduleService.getScheduleRepository().findAllByDay(day));
        return "editSchedule";
    }

    @GetMapping("{day}/delete/{schedule}")
    public String deleteSchedule(@PathVariable Schedule schedule,
                                 @PathVariable long day) {
        scheduleService.getScheduleRepository().delete(schedule);
        return "redirect:/schedules/{day}";
    }

    @GetMapping("{day}/return")
    public String returning() {
        return "redirect:/schedules";
    }
}
