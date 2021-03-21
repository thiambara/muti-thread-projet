package com.multi.backend.controllers;

import java.util.List;

import com.multi.backend.models.Absence;
import com.multi.backend.models.Employe;
import com.multi.backend.models.Pointage;
// import com.multi.backend.models.Service;
import com.multi.backend.services.ServiceEmploye;
// import com.multi.backend.services.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.CrossOrigin;

// @CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employes")
public class EmployeController {
    @Autowired
    private ServiceEmploye serviceEmploye;

    @Autowired
    // private ServiceService serviceService;

    @GetMapping("")
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employe = this.serviceEmploye.getAllEmployes();
        return new ResponseEntity<List<Employe>>(employe, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable("id") Long id) {

        Employe employe = this.serviceEmploye.getEmployeById(id);
        return new ResponseEntity<Employe>(employe, HttpStatus.OK);
    }

    @GetMapping("/pointages/{id}")
    public ResponseEntity<List<Pointage>> getEmployePointages(@PathVariable("id") Long id) {

        List<Pointage> pointages = this.serviceEmploye.getEmployePointages(id);
        return new ResponseEntity<List<Pointage>>(pointages, HttpStatus.OK);
    }

    @GetMapping("/absences/{id}")
    public ResponseEntity<List<Absence>> getEmployeAbsences(@PathVariable("id") Long id) {

        List<Absence> absences = this.serviceEmploye.getEmployeAbsences(id);
        return new ResponseEntity<List<Absence>>(absences, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Employe> addEmploye(@RequestBody Employe employe) {
        employe = this.serviceEmploye.addEmploye(employe);
        return new ResponseEntity<Employe>(employe, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable("id") Long id, @RequestBody Employe employe) {
        employe.setId(id);
        Employe updatedEmploye = this.serviceEmploye.updateEmploye(employe);
        return new ResponseEntity<Employe>(updatedEmploye, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Employe> deleteEmploye(@PathVariable("id") Long id) {
        Employe employe = this.serviceEmploye.getEmployeById(id);
        this.serviceEmploye.deleteEmploye(employe.getId());
        return new ResponseEntity<Employe>(employe, HttpStatus.OK);
    }

}
