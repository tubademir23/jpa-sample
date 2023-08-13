package com.tr.springboot.rest.example.person.service;

import com.tr.springboot.rest.example.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

//bu çalışıyor
    @Query(value =
            " select new com.tr.springboot.rest.example.person.entity.Person(e.id, e.personType) FROM Employee e ")
    List<Person> findAllEmployee();


}
