package com.multi.backend.repositories;

import com.multi.backend.models.Employe;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepo extends JpaRepository<Employe, Long> {
    Optional<Employe[]> findByEmail(String email);

}
