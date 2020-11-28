package com.alayon.springsecuritydemo.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "jair alayon"),
            new Student(2, "maria jones"),
            new Student(3, "ana smith")
    );

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream().filter(student -> student.getStudentId().equals(studentId))
                        .findFirst()
                        .orElseThrow(()-> new IllegalStateException("Student " + studentId + " does not exist"));
    }
}

