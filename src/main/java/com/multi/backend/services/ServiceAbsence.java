package com.multi.backend.services;

import java.util.Arrays;
import java.util.List;

import com.multi.backend.models.Absence;
// import com.multi.backend.models.Employe;
// import com.multi.backend.models.Service;
import com.multi.backend.repositories.AbsenceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceAbsence {
    @Autowired
    private AbsenceRepo absenceRepo;
    @Autowired
    private Conv<Absence> conv;

    public Absence getAbsenceById(Long id) {
        if(id == null){
            throw new NullPointerException("id absence not provied");
        }
        return this.absenceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("absence with id " + id + " not found"));
    }

    public Absence addAbsence(Absence service) {
        return this.absenceRepo.save(service);
    }

    public Absence updateAbsence(Absence absence) {
        Absence registeredAbsence = this.getAbsenceById(absence.getId());
        List<String> fields = Arrays.asList("dateFom","dateTo", "justificatif");
        registeredAbsence = this.conv.pour(registeredAbsence, absence,fields);
        return this.absenceRepo.save(registeredAbsence);
    }

    public List<Absence> getAllAbsences() {
        return this.absenceRepo.findAll();
    }

    public void deleteAbsence(Long id) {
        this.absenceRepo.deleteById(id);
    }

    
}
