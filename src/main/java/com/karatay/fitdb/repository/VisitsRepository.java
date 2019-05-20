package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Subscription;
import com.karatay.fitdb.domain.Visits;
import org.springframework.data.repository.CrudRepository;

public interface VisitsRepository extends CrudRepository<Visits, Long> {
    Iterable<Visits> findAllBySubscription(Subscription subscription);
}
