package com.tr.springboot.rest.example.person.service;

import com.tr.springboot.rest.example.person.entity.Employee;
import com.tr.springboot.rest.example.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends IPersonRepository<Employee> {

}
