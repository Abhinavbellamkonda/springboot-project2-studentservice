package com.codeswing.studentservice.service;


import com.codeswing.studentservice.VO.Department;
import com.codeswing.studentservice.VO.StudentDepartmentVO;
import com.codeswing.studentservice.entity.Student;
import com.codeswing.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailSenderService mailSenderService;

    public Student saveStudent(Student student) {
    return studentRepository.save(student);
    }

    public String getStudentById(Long studentId) {

        StudentDepartmentVO studentDepartmentVO = new StudentDepartmentVO();
        Student student = studentRepository.findById(studentId).get();
        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE/department/getbyid/" + student.getDepartmentId()
                , Department.class
        );
        studentDepartmentVO.setStudent(student);
        studentDepartmentVO.setDepartment(department);
        return "Successfully established communication with department api";

    }

/*    public String getStudentById(Long studentId) {

        StudentDepartmentVO studentDepartmentVO = new StudentDepartmentVO();
        Student student = studentRepository.findById(studentId).get();
        Department department = restTemplate.getForObject(
                "http://localhost:8080/department/getbyid/" + student.getDepartmentId()
                , Department.class
        );

        StringBuffer buffer = new StringBuffer();
        buffer.append("please find details below ");
        buffer.append("/n");
        buffer.append(student.getStudentId()+ " " + student.getFirstName()+ " "
        +student.getLastName()+ ".");
        buffer.append("/n");
        buffer.append(department.getDepartmentId()+ " "+ department.getDepartmentAddress()+
                " " + department.getDepartmentCode());

        mailSenderService.sendMail("b.abhi249@gmail.com","Test",buffer.toString());
        return " Student details along with department";

    }*/


}
