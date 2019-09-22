package com.fitdb.controllers;

import com.fitdb.domain.Instructor;
import com.fitdb.repository.DisciplineRepository;
import com.fitdb.repository.InstructorRepository;
import com.fitdb.service.InstructService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructService instructService;
    @Autowired
    private DisciplineRepository disciplineRepository;

    @GetMapping
    public String allInstructors(@RequestParam(required = false, defaultValue = "") String firstNameFilter,
                                 @RequestParam(required = false, defaultValue = "") String lastNameFilter,
                                 Model model) {
        Iterable<Instructor> instructors;
        boolean fName = firstNameFilter != null && !firstNameFilter.isEmpty();
        boolean lName = lastNameFilter != null && !lastNameFilter.isEmpty();
        if (fName && lName) {
            instructors = instructorRepository.findAllByFirstNameAndLastName(firstNameFilter, lastNameFilter);
        } else if (fName || lName) {
            instructors = fName ? instructorRepository.findAllByFirstName(firstNameFilter)
                    : instructorRepository.findAllByLastName(lastNameFilter);
        } else {
            instructors = instructorRepository.findAll();
        }
        model.addAttribute("instructors", instructors);
        model.addAttribute("firstNameFilter", firstNameFilter);
        model.addAttribute("lastNameFilter", lastNameFilter);
        return "instructors";
    }

    @PostMapping
    public String addInstructors(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName,
                                 @RequestParam(name = "patronymic") String patronymic,
                                 @RequestParam(name = "phoneNumber") String phoneNumber,
                                 @RequestParam(name = "password") String password,
                                 @RequestParam(name = "age", required = false, defaultValue = "0") int age,
                                 Model model) {
        if (!instructorRepository.findByFirstNameAndLastNameAndPhoneNumber(firstName, lastName, phoneNumber).isPresent()) {
            Instructor instructor = new Instructor(firstName, lastName, patronymic, phoneNumber, password, age);
            instructorRepository.save(instructor);
        }
        model.addAttribute("instructors", instructorRepository.findAll());
        return "instructors";
    }

    @GetMapping("{instructor}")
    public String editInstructor(@PathVariable Instructor instructor, Model model) {
        model.addAttribute("instructor", instructor);
        model.addAttribute("disciplines", disciplineRepository.findAll());
        model.addAttribute("ins_dis", instructService.findAllDisciplineByInstructor(instructor));
        return "editInstructor";
    }

    @PostMapping("{instructor}")
    public String saveChanges(@PathVariable Instructor instructor,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "lastName") String lastName,
                              @RequestParam(name = "patronymic") String patronymic,
                              @RequestParam(name = "phoneNumber") String phoneNumber,
                              @RequestParam(name = "age", required = false, defaultValue = "0") int age) {
        instructor.setFirstName(firstName);
        instructor.setLastName(lastName);
        instructor.setPatronymic(patronymic);
        instructor.setPhoneNumber(phoneNumber);
        instructor.setAge(age);
        instructorRepository.save(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/delete/{instructor}")
    public String delete(@PathVariable Instructor instructor) {
        instructorRepository.delete(instructor);
        return "redirect:/instructors";
    }
}
