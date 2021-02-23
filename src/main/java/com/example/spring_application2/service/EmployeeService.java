package com.example.spring_application2.service;

import com.example.spring_application2.dto.EmployeeRequestDTO;
import com.example.spring_application2.dto.EmployeeResponseDTO;

public interface EmployeeService {
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDto);
    EmployeeResponseDTO getEmployeeById(Long id);
    EmployeeResponseDTO updateEmployeeById(Long id,EmployeeRequestDTO employeeRequestDTO);

    EmployeeResponseDTO deleteEmployeeById(Long id);
}
