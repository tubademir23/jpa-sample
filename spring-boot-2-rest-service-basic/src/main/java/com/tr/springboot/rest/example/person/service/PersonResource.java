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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonResource {

    @Qualifier("IPersonRepository")
    @Autowired
    private IPersonRepository personRepository;
    @Autowired
    private IEmployerRepository employerRepository;
    @Autowired
    private IEmployeeRepository employeeRepository;
    @GetMapping("/persons")
    public List<Person> retrieveAllPersons() {
        List<Person> resultEmployee=personRepository.findAllEmployee();
         return resultEmployee;
    }
private IPersonRepository getPersonRepositoryFromType(String type){
    if(type.equals("employee")) {
        return employeeRepository;
    }else{
        return employerRepository;
    }
}
    @GetMapping("/person/{type}")
    public List<Person> retrieveAllPersonsByType(@PathVariable String type,@RequestBody RequestDto requestDto) {
     //   List<Person> result=new ArrayList<>();
        IPersonRepository personRepositoryLcl=getPersonRepositoryFromType(type);
        List<Person> result= personRepositoryLcl.findByType(requestDto);
     /*   if(type.equals("employee")) {
            List<Employee> employees= employeeRepository.findByType(requestDto);
            result.addAll(employees);
        }else{
            List<Employer> employers=  employerRepository.findByType(requestDto);
            result.addAll(employers);
        }*/
         return result;
    }

    @GetMapping("/persons/{id}")
    public Person retrievePerson(@PathVariable long id) {
        Optional<Person> person = personRepository.findById(id);
        System.out.println(person.getClass());

        return person.get();
    }

    @DeleteMapping("/persons/{id}/{type}")
    public void deletePerson(@PathVariable Long id,@PathVariable Integer type) throws ClassNotFoundException {
        Person p=Person.getObjectFromType(type);
        p.setId(id);
        personRepository.delete(p);
        System.out.println(p);
    }
    @PostMapping("/person")
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        Person p;
        if(person.getType().equals(Constants.PersonType.Employee)){
            p=new Employee("havlsan","employee");
        }else{
            p=new Employer("employer");

        }
        personRepository.save(p);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();
        return ResponseEntity.created(location)
                .build();
    }
    @PostMapping("/employee")
    public Long createEmployee(@RequestBody Employee person) {
        personRepository.save(person);
        return person.getId();
    }
    @PostMapping("/employer")
    public Long createEmployer(@RequestBody Employer person) {
          personRepository.save(person);
        return person.getId();
    }
    @PostMapping("/persons")
    public ResponseEntity<Object> createPerson(@RequestBody List<Person> personsReq) {
        //boolean yeniKayit=person.getId()==null;
        List<Person> persons=new ArrayList<>();
        for(Person personReq:personsReq) {
            Person p;
            if(personReq.getType().equals(Constants.PersonType.Employee)){
                p=new Employee("havlsan","employee");
            }else{
                p=new Employer("employer");
                persons.add(p);
            }
            for (int i = 0; i < 100; i++) {
                p.guncelle(i);
                personRepository.save(p);
            }
        }
       /// personRepository.saveAll(persons);
       Person savedPerson = (Person)personRepository.save(persons.get(0));


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();

        return ResponseEntity.created(location)
                .build();

    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person, @PathVariable long id) {

        Optional<Person> personOptional = personRepository.findById(id);

        if (personOptional.isEmpty())
            return ResponseEntity.notFound().build();

        person.setId(id);

        personRepository.save(person);

        return ResponseEntity.noContent()
                .build();
    }
}
