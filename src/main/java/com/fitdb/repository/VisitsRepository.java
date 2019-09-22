package com.fitdb.repository;

import com.fitdb.domain.Subscription;
import com.fitdb.domain.Visits;
import org.springframework.data.repository.CrudRepository;

public interface VisitsRepository extends CrudRepository<Visits, Long> {
    Iterable<Visits> findAllBySubscription(Subscription subscription);
}
