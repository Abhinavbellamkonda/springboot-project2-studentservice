package com.codeswing.studentservice.VO;

import com.codeswing.studentservice.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDepartmentVO {

    private Student student;

    private Department department;
}
