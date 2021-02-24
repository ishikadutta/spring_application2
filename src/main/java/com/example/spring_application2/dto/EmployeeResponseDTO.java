package com.example.spring_application2.dto;

import com.example.spring_application2.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {
    private long id;
    private String name;
    private DepartmentResponseDTO department;

    public void setDepartmentFromEntity(Department departmentFromEntity){
        DepartmentResponseDTO departmentResponseDTO= new DepartmentResponseDTO();
        departmentResponseDTO.setId(departmentFromEntity.getId());
        departmentResponseDTO.setName(departmentFromEntity.getName());
        this.department = departmentResponseDTO;
    }
}
