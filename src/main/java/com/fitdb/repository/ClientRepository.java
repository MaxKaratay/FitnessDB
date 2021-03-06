package com.fitdb.repository;

import com.fitdb.domain.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
    Optional<Client> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);
    Iterable<Client> findAllByFirstNameAndLastName(String firstName, String lastName);
    Iterable<Client> findAllByFirstName(String firstName);
    Iterable<Client> findAllByLastName(String lastName);
}
