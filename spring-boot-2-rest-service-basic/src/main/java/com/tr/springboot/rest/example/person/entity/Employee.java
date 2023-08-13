package com.tr.springboot.rest.example.person.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="Employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee extends Person {
    private String company;
    private String employeeName;
//static{this.setPersonType(Constants.PersonType.Employee.getVal());}
    // constructor, getters, setters
    public Employee(String company, String employeeName){
        this.company=company;
        this.employeeName=employeeName;
    }

    public Employee(Long id){
        this.id=id;
    }
    @Override
    public void guncelle(int i) {
        this.company=i+company;
        this.employeeName=i+employeeName;
    }
}
