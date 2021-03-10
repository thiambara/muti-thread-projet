package com.multi.backend.services;

import java.util.Arrays;
import java.util.List;

import com.multi.backend.models.Employe;
import com.multi.backend.repositories.EmployeRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceEmploye {
    @Autowired
    private EmployeRepo employeRepo;
    @Autowired
    private Conv<Employe> conv;

    public Employe getEmployeById(Long id) {
        if(id == null){
            throw new NullPointerException("id employe not provied");
        }
        return this.employeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("employe with id " + id + " not found"));
    }

    public Employe addEmploye(Employe employe) {
        employe.generateMatricule();
        return this.employeRepo.save(employe);
    }

    public Employe updateEmploye(Employe employe) {
        Employe registeredEmploye = this.getEmployeById(employe.getId());
        List<String> fields = Arrays.asList("firstName", "lastName", "email","post", "address", "bithDate");
        registeredEmploye = this.conv.pour(registeredEmploye, employe,fields);
        return this.employeRepo.save(registeredEmploye);
    }

    public List<Employe> getAllEmployes() {
        return this.employeRepo.findAll();
    }

    public void deleteEmploye(Long id) {
        this.employeRepo.deleteById(id);
    }

}
