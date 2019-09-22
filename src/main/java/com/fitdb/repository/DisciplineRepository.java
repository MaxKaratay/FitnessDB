package com.fitdb.repository;

import com.fitdb.domain.Discipline;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
    Iterable<Discipline> findAllByOrderByName();
    Optional<Discipline> findByName(String name);
}
