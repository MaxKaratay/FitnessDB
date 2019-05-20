package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Client;
import com.karatay.fitdb.domain.Instruct;
import com.karatay.fitdb.domain.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    int countSubscriptionByInstruct(Instruct instruct);
    Iterable<Subscription> findAllByClient(Client client);
    Iterable<Subscription> findAllByDurationEndGreaterThanEqual(Date date);
}
