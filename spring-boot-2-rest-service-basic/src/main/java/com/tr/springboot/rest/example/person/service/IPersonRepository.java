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

   // @Query(value =" select t.* from #{#entityName} t where row_number() over(partition by person_type order by )=1",nativeQuery = true)
  //  List<T> findByType(@Param("requestDto") RequestDto requestDto);
    @Query(value =" select t.* from (select row_number() over (partition by person_type order by id) idsno," +
            "       row_number() over (partition by person_type order by company) companyno, *" +
            "       from #{#entityName} t where t.person_type in :#{#requestDto.personTip}) t where (:#{#requestDto.idFilter}=-1 or idsno=1)" +
            "and  (:#{#requestDto.nameFilter}=-1 or companyno=1)",nativeQuery = true)
    List<T> findByType(@Param("requestDto") RequestDto requestDto);


}
