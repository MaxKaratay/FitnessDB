package com.fitdb.repository;

import com.fitdb.domain.Discipline;
import com.fitdb.domain.Instruct;
import com.fitdb.domain.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructRepository extends CrudRepository<Instruct, Long> {
    Iterable<Instruct> findAllByDiscipline(Discipline discipline);
    Iterable<Instruct> findAllByInstructor(Instructor instructor);
    Optional<Instruct> findByDisciplineAndInstructor(Discipline discipline, Instructor instructor);
}
