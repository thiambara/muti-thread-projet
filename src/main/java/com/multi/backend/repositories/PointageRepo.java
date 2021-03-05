package com.multi.backend.repositories;

import com.multi.backend.models.Pointage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointageRepo extends JpaRepository<Pointage, Long> {

}
