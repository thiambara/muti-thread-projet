package com.multi.backend.repositories;

import com.multi.backend.models.Absence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepo extends JpaRepository<Absence, Long> {

}
