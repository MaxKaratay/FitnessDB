package com.fitdb.repository;

import com.fitdb.domain.Client;
import com.fitdb.domain.Instruct;
import com.fitdb.domain.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    int countSubscriptionByInstruct(Instruct instruct);
    Iterable<Subscription> findAllByClient(Client client);
    Iterable<Subscription> findAllByDurationEndGreaterThanEqual(Date date);
    Iterable<Subscription> findAllByDurationEndGreaterThanEqualAndClient(Date date, Client client);
}
