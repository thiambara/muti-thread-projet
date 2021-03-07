package com.multi.backend.controllers;

import java.util.List;

import com.multi.backend.models.Absence;
import com.multi.backend.services.ServiceAbsence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/absences")
public class AbsenceController {
    @Autowired
    private ServiceAbsence serviceAbsence;

    @GetMapping("")
    public ResponseEntity<List<Absence>> getAllAbsences() {
        List<Absence> absence = this.serviceAbsence.getAllAbsences();
        return new ResponseEntity<List<Absence>>(absence, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable("id") Long id) {

        Absence absence = this.serviceAbsence.getAbsenceById(id);
        return new ResponseEntity<Absence>(absence, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Absence> addAbsence(@RequestBody Absence absence) {
        absence = this.serviceAbsence.addAbsence(absence);
        return new ResponseEntity<Absence>(absence, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Absence> updateAbsence(@PathVariable("id") Long id, @RequestBody Absence absence) {
        absence.setId(id);
        Absence updatedAbsence = this.serviceAbsence.updateAbsence(absence);
        return new ResponseEntity<Absence>(updatedAbsence, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Absence> deleteAbsence(@PathVariable("id") Long id) {
        Absence absence = this.serviceAbsence.getAbsenceById(id);
        this.serviceAbsence.deleteAbsence(absence.getId());
        return new ResponseEntity<Absence>(absence, HttpStatus.OK);
    }

}
