package com.example.spring_application2.controller;

import com.example.spring_application2.dto.DepartmentRequestDTO;
import com.example.spring_application2.dto.DepartmentResponseDTO;
//import com.example.spring_application2.dto.EmployeeResponseDTO;
import com.example.spring_application2.entity.Department;
import org.springframework.beans.BeanUtils;
import com.example.spring_application2.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentRequestDTO departmentRequestDTO){
    return departmentService.createDepartment(departmentRequestDTO);
}
@GetMapping("/{id}")
public Department getDepartment(@PathVariable("id") Long id){
    return departmentService.getDepartmentById(id);
}
@PutMapping("/{id}")
    public DepartmentResponseDTO updateDepartment(@PathVariable("id") Long departmentId,@RequestBody DepartmentRequestDTO departmentRequestDTO){
    return departmentService.updateDepartment(departmentId,departmentRequestDTO);
}
//@GetMapping("/{id}")
//    public List<EmployeeResponseDTO> getMostExperiencedInDepartment(@PathVariable("id") Long departmentId){
//    return departmentService.getMostExperiencedInDepartment(departmentId);
//}


}
