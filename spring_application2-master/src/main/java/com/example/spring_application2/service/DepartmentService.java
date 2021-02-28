package com.example.spring_application2.service;

import com.example.spring_application2.dto.DepartmentRequestDTO;
import com.example.spring_application2.dto.DepartmentResponseDTO;
import com.example.spring_application2.entity.Department;
import com.sun.prism.shader.DrawEllipse_RadialGradient_PAD_Loader;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
   // public DepartmentResponseDTO getEmployeeById(Long id);
    //public DepartmentResponseDTO updateEmployeeById(Long id, DepartmentRequestDTO departmentRequestDTO);
    Department getDepartmentById(Long id);
    DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO);


    List<DepartmentResponseDTO> getDepartmentWithMaxExperience();
}
