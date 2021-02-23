package com.example.spring_application2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {
    private long id;
    private String name;
    private String departmentName;
}
