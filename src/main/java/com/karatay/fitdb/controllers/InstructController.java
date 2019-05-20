package com.karatay.fitdb.controllers;

import com.karatay.fitdb.domain.Discipline;
import com.karatay.fitdb.domain.Instruct;
import com.karatay.fitdb.domain.Instructor;
import com.karatay.fitdb.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/instructs")
public class InstructController {
    @Autowired
    InstructService instructService;

    @GetMapping
    public String allInstructs(Model model) {
        Map<Discipline, Map<Instructor, Integer>> instructs = instructService.getInstructListByDiscipline();
        model.addAttribute("instructs", instructs);
        model.addAttribute("disciplines", instructService.getDisciplineRepository().findAll());
        model.addAttribute("instructors", instructService.getInstructorRepository().findAll());
        return "instructs";
    }

    @PostMapping
    public String addInstruct(@RequestParam(name = "discipline") String discipline,
                              @RequestParam(name = "instructor") String instructor,
                              @RequestParam(name = "price", defaultValue = "0") int price) {
        String[] fio = instructor.split(" ");
        instructService.getInstructRepository()
                .save(new Instruct(instructService.getInstructorRepository()
                        .findByFirstNameAndLastNameAndPatronymic(fio[0], fio[1], fio[2]).get(),
                        instructService.getDisciplineRepository().findByName(discipline).get(), price));
        return "redirect:/instructs";
    }

    @GetMapping("{discipline}")
    public String editInstruct(@PathVariable Discipline discipline, Model model) {
        model.addAttribute("discipline", discipline);
        model.addAttribute("ins_price", instructService.findAllByDiscipline(discipline));
        //instructService;
        return "editInstruct";
    }

    @PostMapping("{discipline}")
    public String saveChanges(@PathVariable Discipline discipline,
                              @RequestParam Map<String, String> form) {
        for (String key : form.keySet()) {
            String[] fio = key.split(" ");
            Instructor instructor = instructService.getInstructorRepository()
                    .findByFirstNameAndLastNameAndPatronymic(fio[0], fio[1], fio[2]).get();
            Instruct instruct = instructService.getInstructRepository()
                    .findByDisciplineAndInstructor(discipline, instructor).get();
            instruct.setPrice(Integer.parseInt(form.get(key)));
            instructService.getInstructRepository().save(instruct);
        }
        return "redirect:/instructs";
    }

    @GetMapping("{discipline}/delete/{instructor}")
    public String deleteInstruct(@PathVariable Discipline discipline,
                                 @PathVariable Instructor instructor) {
        instructService.getInstructRepository()
                .delete(instructService.getInstructRepository()
                        .findByDisciplineAndInstructor(discipline, instructor).get());
        return "redirect:/instructs/{discipline}";
    }

}
