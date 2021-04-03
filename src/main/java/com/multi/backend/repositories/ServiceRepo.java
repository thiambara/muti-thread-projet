package com.multi.backend.repositories;

import java.util.Optional;

import com.multi.backend.models.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Service, Long> {
    Optional<Service[]> findByName(String name);
}
