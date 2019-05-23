package com.karatay.fitdb.repository;

import com.karatay.fitdb.domain.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
}
