package com.codeswing.studentservice.controller;

import com.codeswing.studentservice.VO.StudentDepartmentVO;
import com.codeswing.studentservice.entity.Student;
import com.codeswing.studentservice.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public Student save(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getbyid/{studentId}")
    @CircuitBreaker(name = "student-service",fallbackMethod = "fallbackMethod")
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> getStudentById(@PathVariable("studentId") Long studentId){
        return CompletableFuture.supplyAsync(()->studentService.getStudentById(studentId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<String> fallbackMethod(@PathVariable("studentId") Long studentId,RuntimeException ex){
        return CompletableFuture.supplyAsync(()->"This is taking longer than expected");
    }
/*
 //This method sends email code is perfectly alright.
 //I don't want to mess with my gmail account so, I have commented it

 @GetMapping("/getbyid/{studentId}")
    public String getStudentById(@PathVariable("studentId") Long studentId){
        studentService.getStudentById(studentId);
        return "Student details along with department detail";
    }*/

}
