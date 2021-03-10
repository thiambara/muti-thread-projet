package com.multi.backend.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "employes")
// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class, 
//   property = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "absences", "pointages"})

public class Employe implements Serializable {
    
    private static final long serialVersionUID = -1503878121631383434L;
    private static final int MATRICULE_LENGTH = 8;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(name = "matricule", unique = true)
    private String matricule;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "bith_date")
    private Date bithDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Service.class)
    @JoinColumn(name = "service_id", referencedColumnName = "id", nullable = false)
    // @JsonManagedReference
    private Service service;

    @Column(name = "post")
    private String post;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Absence> absences;

    @OneToMany(targetEntity = Pointage.class, mappedBy = "employe", cascade = CascadeType.ALL)
    private List<Pointage> pointages;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public void generateMatricule(){
        if (this.matricule==null || this.matricule.trim().isEmpty()) {
            int prefPosition = (int)Math.floor((Math.random()*(4)));
            int sufPosition = prefPosition + (int)Math.floor((Math.random()*(MATRICULE_LENGTH-prefPosition))) +1;
            int i=0;
            String matr ="";
            while(i<=MATRICULE_LENGTH){
                if(i==prefPosition){
                    matr+=this.prefixeM();
                    i++;
                }else if(i==sufPosition){
                    matr += this.suffixeM();
                    i++;
                }else{
                    int n =(int)Math.floor((Math.random()*10));
                    matr += n;
                    i++;
                }
            }
            this.matricule = matr;
        }

    }

    private Character prefixeM(){
        return this.firstName .toUpperCase().charAt(0);
    }
    private Character suffixeM(){
        return this.lastName.toUpperCase().charAt(0);
    }


}