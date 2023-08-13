package com.tr.springboot.rest.example.student;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
class MultithreadingDemo implements Runnable {

    private final IStudentRepository studentRepository;
    private Student student;
    public MultithreadingDemo(Student s,IStudentRepository studentRepository){
        this.student=s;
        this.studentRepository=studentRepository;
    }
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                    "Thread " + Thread.currentThread().getId()
                            + " is running");
            List<Student> students=new ArrayList<>();
            for(int i=0;i<10;i++){
                Student s=new Student();
                s.setName(student.getName()+Thread.currentThread().getId()+".."+i);
students.add(s);
            }
           studentRepository.saveAll(students);
            //studentRepository.save(student);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
@RestController
public class StudentResource {

    @Autowired
    private IStudentRepository studentRepository;

    @GetMapping("/students")
    public List<Student> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public Student retrieveStudent(@PathVariable long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new StudentNotFoundException("id-" + id);

        return student.get();
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        //boolean yeniKayit=student.getId()==null;
        List<Student> students=new ArrayList<>();
        for(int i=0;i<100;i++){
            Student s=new Student();
            s.setName(student.getName()+i+"..");

            Thread object
                    = new Thread(new MultithreadingDemo(s,studentRepository));
            object.start();
        }
       /// studentRepository.saveAll(students);
        Student savedStudent = studentRepository.save(student);


        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedStudent.getId())
                .toUri();

        return ResponseEntity.created(location)
                .build();

    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isEmpty())
            return ResponseEntity.notFound().build();

        student.setId(id);

        studentRepository.save(student);

        return ResponseEntity.noContent()
                .build();
    }
}
