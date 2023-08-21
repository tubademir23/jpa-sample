package com.tr.springboot.rest.example.person.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Employer")
@NoArgsConstructor
@Getter
@Setter
public class Employer extends Person {

    private String employerName;
    private String company;

    public Employer( String employerName){

        this.employerName=employerName;
    }
    public Employer(Long id){
        this.id=id;
    }
    @Override
    public void guncelle(int i) {
        this.employerName=i+employerName;
    }
    // constructor, getters, setters
}
