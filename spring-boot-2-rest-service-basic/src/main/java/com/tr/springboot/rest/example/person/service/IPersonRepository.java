package com.tr.springboot.rest.example.person.service;

import com.tr.springboot.rest.example.person.dto.RequestDto;
import com.tr.springboot.rest.example.person.entity.Person;
import jakarta.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository<T> extends JpaRepository<Person, Long> {

//bu çalışıyor
    @Query(value =
            " select new com.tr.springboot.rest.example.person.entity.Person(e.id, e.personType) FROM Employee e ")
    List<T> findAllEmployee();

    @Query(value =" select t.* from #{#entityName} t",nativeQuery = true)
    List<T> findByType(@Param("requestDto") RequestDto requestDto);


}
