package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Discipline;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DisciplineRepository extends CrudRepository<Discipline, Long> {
    Iterable<Discipline> findAllByOrderByName();
    Optional<Discipline> findByName(String name);
}
