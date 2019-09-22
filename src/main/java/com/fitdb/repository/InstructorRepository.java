package com.fitdb.repository;

import com.fitdb.domain.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
    Optional<Instructor> findByFirstNameAndLastNameAndPhoneNumber(String firstName, String lastName, String phoneNumber);
    Optional<Instructor> findByFirstNameAndLastNameAndPatronymic(String firstName, String lastName, String patronymic);
    Iterable<Instructor> findAllByFirstNameAndLastName(String firstName, String lastName);
    Iterable<Instructor> findAllByFirstName(String firstName);
    Iterable<Instructor> findAllByLastName(String lastName);
}
