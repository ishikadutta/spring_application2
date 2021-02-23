package com.example.spring_application2.controller;

import com.example.spring_application2.dto.EmployeeRequestDTO;
import com.example.spring_application2.dto.EmployeeResponseDTO;
import com.example.spring_application2.entity.Employee;
import com.example.spring_application2.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    //POST
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO){

        return employeeService.createEmployee(employeeRequestDTO);

    }

    //GET
    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable("id") Long id){
        return employeeService.getEmployeeById(id);
    }

    //PUT
    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable("id") Long id,@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.updateEmployeeById(id,employeeRequestDTO);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public EmployeeResponseDTO deleteEmployeeById(@PathVariable("id") Long id){
        return employeeService.deleteEmployeeById(id);
    }
}
