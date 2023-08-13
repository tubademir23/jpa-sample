package com.tr.springboot.rest.example.person.entity;

import commons.Constants;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class Person {
    private static Map<Integer, Class> integerClassMap;
    static{
        integerClassMap =new HashMap<Integer, Class>();
        integerClassMap.put(1, Employee.class);
        integerClassMap.put(2, Employer.class);
    }
    private static Map<Class, Integer> classIntegerMap;
    static{
        classIntegerMap =new HashMap<Class, Integer>();
        classIntegerMap.put(Employee.class,1);
        classIntegerMap.put(Employer.class,2);
    }
    public Person(Long id, Integer personType){
        this.id=id;
        this.personType=personType;
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Id
    protected Long id;
    @Transient
    private Constants.PersonType type;
    private Integer personType;
    public void  guncelle(int i){};
    @PrePersist
    public void prePersist(){
        this.setType(Constants.PersonType.of((Integer)classIntegerMap.get(this.getClass())));
       /* if(this instanceof Employee) {

            this.setType(Constants.PersonType.Employee);
        }else if(this instanceof Employer){

            this.setType(Constants.PersonType.Employer);
        }

        */
    }
    public static Person getObjectFromType(Integer personType)  {
        switch (Objects.requireNonNull(Constants.PersonType.of(personType))) {
            case Employee:
                return new Employee();
            case Employer:
                return new Employer();
            default:
                return new Employee();
        }
    }
    public void setType(Constants.PersonType type) {
        this.setPersonType(type.getVal());
    }
    // constructor, getters, setters
}