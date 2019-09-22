package com.fitdb.repository;

import com.fitdb.domain.Time;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TimeRepository extends CrudRepository<Time, Long> {
    Optional<Time> findByStartAndFinish(String start, String finish);
}
