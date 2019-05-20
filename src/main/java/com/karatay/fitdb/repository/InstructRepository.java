package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Discipline;
import com.karatay.fitdb.domain.Instruct;
import com.karatay.fitdb.domain.Instructor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InstructRepository extends CrudRepository<Instruct, Long> {
    Iterable<Instruct> findAllByDiscipline(Discipline discipline);
    Iterable<Instruct> findAllByInstructor(Instructor instructor);
    Optional<Instruct> findByDisciplineAndInstructor(Discipline discipline, Instructor instructor);
}
