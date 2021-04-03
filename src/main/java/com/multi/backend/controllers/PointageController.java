package com.multi.backend.controllers;

import java.util.List;

import com.multi.backend.models.Pointage;
import com.multi.backend.services.ServicePointage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/api/pointages")
public class PointageController {
    @Autowired
    private ServicePointage servicePointage;

    @GetMapping("")
    public ResponseEntity<List<Pointage>> getAllPointages() {
        List<Pointage> pointage = this.servicePointage.getAllPointages();
        return new ResponseEntity<List<Pointage>>(pointage, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable("id") Long id) {

        Pointage pointage = this.servicePointage.getPointageById(id);
        return new ResponseEntity<Pointage>(pointage, HttpStatus.OK);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERVISEUR')")
    public ResponseEntity<Pointage> addPointage(@RequestBody Pointage pointage) {
        pointage = this.servicePointage.addPointage(pointage);
        return new ResponseEntity<Pointage>(pointage, HttpStatus.OK);
    }

    // @PutMapping("{id}")
    // public ResponseEntity<Pointage> updatePointage(@PathVariable("id") Long id,
    // @RequestBody Pointage pointage) {
    // pointage.setId(id);
    // Pointage updatedPointage = this.servicePointage.updatePointage(pointage);
    // return new ResponseEntity<Pointage>(updatedPointage, HttpStatus.OK);

    // }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Pointage> deletePointage(@PathVariable("id") Long id) {
        Pointage pointage = this.servicePointage.getPointageById(id);
        this.servicePointage.deletePointage(pointage.getId());
        return new ResponseEntity<Pointage>(pointage, HttpStatus.OK);
    }

    @GetMapping("/last-pointage/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERVISEUR')")
    public ResponseEntity<Pointage> getLastEmployePointage(@PathVariable("id") Long employeId) {
        return new ResponseEntity<Pointage>(this.servicePointage.lastEmployePointage(employeId), HttpStatus.OK);
    }

    

    

}
