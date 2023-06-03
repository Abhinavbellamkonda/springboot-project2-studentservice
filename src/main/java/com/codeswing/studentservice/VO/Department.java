package com.codeswing.studentservice.VO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;

}
