package com.tr.springboot.rest.example.person.service;


import com.tr.springboot.rest.example.person.dto.RequestDto;
import com.tr.springboot.rest.example.person.entity.Employee;
import com.tr.springboot.rest.example.person.entity.Employer;
import com.tr.springboot.rest.example.person.entity.Person;
import commons.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeResource {

    @Autowired
    private IEmployeeRepository employeeRepository;
    @GetMapping("/employees")
    public List<Employee> retrieveAllPersons(@RequestBody RequestDto requestDto) {
        List<Employee> employees= employeeRepository.findByType(requestDto);
         return employees;
    }
}
