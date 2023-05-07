package com.example.studentinstitution.controller;

import com.example.studentinstitution.dto.StudentDto;
import com.example.studentinstitution.entity.Student;
import com.example.studentinstitution.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class StudentController {

    @Autowired
    private StudentRepo repo;

    @PostMapping("/add")
    public ResponseEntity<Student> addDetails(@RequestBody @Valid StudentDto stud){
        Student student = new Student(0,stud.getName(),stud.getCourses());
        return ResponseEntity.ok().body(repo.save(student));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Student>> getAllDetails(){
        return ResponseEntity.ok().body(repo.findAll());
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Student> getDetailsById(@PathVariable int id){
        Optional<Student> stud = repo.findById(id);
        if(stud.isPresent()){
            return ResponseEntity.ok().body(repo.findById(id).orElse(null));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Student> updateById(@RequestBody Student student, @PathVariable int id){
        Student studnt = null;
        Optional<Student> stud = repo.findById(id);
        if(stud.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            studnt = stud.get();
            studnt.setId(id);
            studnt.setName(student.getName());
            studnt.setCourses(student.getCourses());
            return ResponseEntity.ok().body(repo.save(studnt));
        }
    }

    @PatchMapping("/updatesome/{id}")
    public ResponseEntity<Student> patchDetails(@RequestBody Student student,@PathVariable int id){
        Optional<Student> s= repo.findById(id);
        Student stud = s.get();
        stud.setId(id);
        if(student.getName()!=null){
            stud.setName(student.getName());
        }
        if(student.getCourses()!=null){
            stud.setCourses(student.getCourses());
        }
        return ResponseEntity.ok().body(repo.save(stud));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteDetails(@PathVariable int id){
        Optional<Student> del = repo.findById(id);
        if(del.isPresent()){
            repo.deleteById(id);
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }
    }
}
