package com.example.spring_application2.service.impl;

import com.example.spring_application2.dto.DepartmentRequestDTO;
import com.example.spring_application2.dto.DepartmentResponseDTO;
import com.example.spring_application2.entity.Department;
import com.example.spring_application2.repository.DepartmentRepository;
import com.example.spring_application2.service.DepartmentService;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO){
        Department department = new Department();
        BeanUtils.copyProperties(departmentRequestDTO,department);

        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment,responseDTO);
        return responseDTO;

    }
    @Override
    public Department getDepartmentById(Long id){
        return departmentRepository.findById(id).get();
    }

}
