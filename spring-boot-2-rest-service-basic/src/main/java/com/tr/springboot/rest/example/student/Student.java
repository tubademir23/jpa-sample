package com.tr.springboot.rest.example.student;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@DiscriminatorColumn
public class Student {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator( name="",sequenceName = "student_id_seq")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sid;
    private String name;
    private String passportNumber;
  /*  @Transient
    private transient boolean yeniKayit=true;

    // to display on view
    public boolean getYeniKayit() {
       this.yeniKayit= (id==null);
       return this.yeniKayit;
    }

    public Long getId() {
        if(getYeniKayit()){
            this.sid=id;
        }
        return id;
    }
    public void setSid(Long sid) {
        if(getYeniKayit()){
            this.sid=id;
        }
        else{
            this.sid=sid;
        }
    }




    public Student(Long id, String name, String passportNumber) {
        super();
        this.id = id;
        this.name = name;
        this.passportNumber = passportNumber;
    }


   */
  public Student(String name, String passportNumber, Long sid) {
      super();
      this.name = name;
      this.sid = sid;
      this.passportNumber = passportNumber;

  }public Student() {
        super();
    }
}
