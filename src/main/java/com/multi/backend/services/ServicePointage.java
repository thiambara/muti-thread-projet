package com.multi.backend.services;

import java.util.List;

import com.multi.backend.models.Pointage;
import com.multi.backend.repositories.PointageRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServicePointage {
    @Autowired
    private PointageRepo pointageRepo;

    public Pointage getPointageById(Long id) {
        if(id == null){
            throw new NullPointerException("id pointage not provied");
        }
        return this.pointageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("pointage with id " + id + " not found"));
    }

    public Pointage addPointage(Pointage service) {
        return this.pointageRepo.save(service);
    }


    public List<Pointage> getAllPointages() {
        return this.pointageRepo.findAll();
    }

    public void deletePointage(Long id) {
        this.pointageRepo.deleteById(id);
    }

    public Pointage lastEmployePointage(Long employeId){
        return this.pointageRepo.findTopByEmployeIdOrderByCreatedAtDesc(employeId);

    }
}
