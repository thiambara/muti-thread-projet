package com.multi.backend.services;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.multi.backend.models.Employe;
import com.multi.backend.models.Service;
import com.multi.backend.repositories.ServiceRepo;

import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;

    @Autowired
    private Conv<Service> conv;

    public List<Service> getAllServices() {
        return this.serviceRepo.findAll();
    }

    public Service getServiceById(Long id) {
        if(id == null){
            throw new NullPointerException("id service not provied");
        }
        return this.serviceRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("service with id " + id + " not found"));
    }

    public Service updateService(Service service) {
        Service registeredService = this.getServiceById(service.getId());
        List<String> fields = Arrays.asList("name", "description");
        registeredService = this.conv.pour(registeredService, service,fields);

        return this.serviceRepo.save(registeredService);
    }

    public Service addService(Service service) {
        return this.serviceRepo.save(service);
    }

    public void deleteService(Long id) {
        this.serviceRepo.deleteById(id);
    }

    public List<Employe> getServiceEmployes(Long id){
        Service service = this.getServiceById(id);
        return service.getEmployes();
    }
}
