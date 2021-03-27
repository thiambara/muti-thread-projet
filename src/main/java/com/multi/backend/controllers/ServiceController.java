package com.multi.backend.controllers;

import java.util.List;

// import com.multi.backend.models.Employe;
// import com.multi.backend.models.Employe;
import com.multi.backend.models.Service;
// import com.multi.backend.services.ServiceEmploye;
import com.multi.backend.services.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @Autowired
    // private ServiceEmploye serviceEmploye;

    @GetMapping("")
    public ResponseEntity<List<Service>> getAllServices() {
        List<Service> service = this.serviceService.getAllServices();
        return new ResponseEntity<List<Service>>(service, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable("id") Long id) {
        Service service = this.serviceService.getServiceById(id);
        return new ResponseEntity<Service>(service, HttpStatus.OK);
    }

    // @GetMapping("/employes/{id}")
    // public ResponseEntity<List<Employe>> getEmployeAbsence(@PathVariable("id")
    // Long id) {
    // List<Employe> employes = this.serviceService.getServiceEmployes(id);
    // return new ResponseEntity<List<Employe>>(employes, HttpStatus.OK);
    // }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Service> addService(@RequestBody Service service) {
        service = this.serviceService.addService(service);
        return new ResponseEntity<Service>(service, HttpStatus.OK);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Service> updateService(@PathVariable("id") Long id, @RequestBody Service service) {
        service.setId(id);
        Service updatedService = this.serviceService.updateService(service);
        return new ResponseEntity<Service>(updatedService, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Service> deleteService(@PathVariable("id") Long id) {
        Service service = this.serviceService.getServiceById(id);
        this.serviceService.deleteService(service.getId());
        return new ResponseEntity<Service>(service, HttpStatus.OK);
    }

}
