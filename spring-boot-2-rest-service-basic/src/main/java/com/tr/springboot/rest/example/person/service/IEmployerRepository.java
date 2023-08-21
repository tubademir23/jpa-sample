package com.tr.springboot.rest.example.person.service;

import com.tr.springboot.rest.example.person.entity.Employee;
import com.tr.springboot.rest.example.person.entity.Employer;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployerRepository extends IPersonRepository<Employer> {

}
