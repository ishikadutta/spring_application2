package com.example.spring_application2.service.impl;

import com.example.spring_application2.dto.EmployeeRequestDTO;
import com.example.spring_application2.dto.EmployeeResponseDTO;
import com.example.spring_application2.entity.Employee;
import com.example.spring_application2.repository.EmployeeRepository;
import com.example.spring_application2.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeRequestDTO,employee);
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee,responseDTO);

        return responseDTO;
    }
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            return responseDTO;
        }
       return null;
    }
    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id, EmployeeRequestDTO employeeRequestDTO){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            //update employee
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDTO.getName());
            employeeFromDb.setDepartmentName((employeeRequestDTO.getDepartmentName()));

            //save to db
            Employee saveEmployee = employeeRepository.save(employeeFromDb);

            //copy from employee to response dto
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(saveEmployee,responseDTO);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            // Employee employeeFromDb = employeeOptional.get();
            employeeRepository.deleteById(id);
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            return responseDTO;
        }
        return null;
        //return null;
    }
}
