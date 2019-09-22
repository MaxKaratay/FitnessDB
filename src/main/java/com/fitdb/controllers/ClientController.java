package com.fitdb.controllers;

import com.fitdb.domain.Client;
import com.fitdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public String clientsList(@RequestParam(required = false, defaultValue = "")String firstNameFilter,
                              @RequestParam(required = false, defaultValue = "")String lastNameFilter,
                              Model model) {
        Iterable<Client> clients;
        boolean fName = firstNameFilter != null && !firstNameFilter.isEmpty();
        boolean lName = lastNameFilter != null && !lastNameFilter.isEmpty();
        if (fName && lName) {
            clients = clientRepository.findAllByFirstNameAndLastName(firstNameFilter, lastNameFilter);
        } else if (fName || lName) {
            clients = (fName ? clientRepository.findAllByFirstName(firstNameFilter) : clientRepository.findAllByLastName(lastNameFilter));
        } else {
            clients = clientRepository.findAll();
        }
        model.addAttribute("clients", clients);
        model.addAttribute("firstNameFilter", firstNameFilter);
        model.addAttribute("lastNameFilter", lastNameFilter);
        return "clients";
    }

    @PostMapping
    public String addClient(@RequestParam(name = "firstName") String firstName,
                            @RequestParam(name = "lastName") String lastName,
                            @RequestParam(name = "patronymic")String patronymic,
                            @RequestParam(name = "phoneNumber") String phoneNumber,
                            @RequestParam(name = "password") String password,
                            @RequestParam(name = "age", required = false, defaultValue = "0") int age,
                            Model model) {
        Optional<Client> isExist = clientRepository.findByFirstNameAndLastNameAndPhoneNumber(firstName,lastName,phoneNumber);
        if (!isExist.isPresent()) {
            Client client = new Client(firstName, lastName, patronymic, phoneNumber, password, age);
            clientRepository.save(client);
        }
        model.addAttribute("clients", clientRepository.findAll());
        return "clients";
    }

    @GetMapping("{client}")
    public String editClient(@PathVariable Client client, Model model) {
        model.addAttribute("client", client);
        return "editClient";
    }

    @PostMapping("{client}")
    public String saveChanges(@PathVariable Client client,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "lastName") String lastName,
                              @RequestParam(name = "patronymic")String patronymic,
                              @RequestParam(name = "phoneNumber") String phoneNumber,
                              @RequestParam(name = "age", required = false, defaultValue = "0") int age ) {
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPatronymic(patronymic);
        client.setPhoneNumber(phoneNumber);
        client.setAge(age);
        clientRepository.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{clientID}")
    public String deleteClient(@PathVariable long clientID){
        clientRepository.deleteById(clientID);
        return "redirect:/clients";
    }
}
