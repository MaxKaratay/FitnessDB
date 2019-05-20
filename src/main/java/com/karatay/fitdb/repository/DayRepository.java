package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Day;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DayRepository extends CrudRepository<Day, Long> {
    Optional<Day> findByName(String name);
}
