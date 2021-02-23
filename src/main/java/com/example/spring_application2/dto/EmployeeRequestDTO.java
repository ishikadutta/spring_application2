package com.example.spring_application2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDTO {
    private long id;
    private String name;
    private String departmentName;
}
