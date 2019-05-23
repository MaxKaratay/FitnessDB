package com.karatay.fitdb.service;

import com.karatay.fitdb.domain.Admin;
import com.karatay.fitdb.domain.Client;
import com.karatay.fitdb.domain.Instructor;
import com.karatay.fitdb.repository.AdminRepository;
import com.karatay.fitdb.repository.ClientRepository;
import com.karatay.fitdb.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Admin> admin =  adminRepository.findByName(s);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            String[] fio = s.split(" ");
            if (fio.length != 3) {
                throw new UsernameNotFoundException("Wrong user name!");
            }
            Optional<Instructor> instructor = instructorRepository.findByFirstNameAndLastNameAndPatronymic(fio[0], fio[1], fio[2]);
            if (instructor.isPresent()) {
                return instructor.get();
            } else {
                Optional<Client> client = clientRepository.findByFirstNameAndLastNameAndPatronymic(fio[0], fio[1], fio[2]);
                if (client.isPresent()) {
                    return client.get();
                } else {
                    throw new UsernameNotFoundException("User not found!");
                }
            }
        }
    }
}
