package com.tr.springboot.rest.example.person.service;


import com.tr.springboot.rest.example.person.dto.RequestDto;
import com.tr.springboot.rest.example.person.entity.Employee;
import com.tr.springboot.rest.example.person.entity.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployerResource {

    @Autowired
    private IEmployerRepository employerRepository;
    @GetMapping("/employers")
    public List<Employer> retrieveAllPersons(@RequestBody RequestDto requestDto) {
        List<Employer> employers= employerRepository.findByType(requestDto);
         return employers;
    }
}
