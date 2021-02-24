package com.example.spring_application2.service.impl;

import com.example.spring_application2.dto.DepartmentRequestDTO;
import com.example.spring_application2.dto.DepartmentResponseDTO;
import com.example.spring_application2.entity.Department;
import com.example.spring_application2.entity.Employee;
import com.example.spring_application2.repository.DepartmentRepository;
import com.example.spring_application2.repository.EmployeeRepository;
import com.example.spring_application2.service.DepartmentService;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
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
    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO){
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

        //update department
        department.setName(departmentRequestDTO.getName());
        Department saveDepartment = departmentRepository.save(department);

        //append departmentCode to employee code


//        if(departmentId!=null){
//            throw new RuntimeException("My Error");
//        }
        employeeList.forEach(employee -> {
            employee.setCode(departmentRequestDTO.getDepartmentCode());

        });
        employeeRepository.saveAll(employeeList);

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(saveDepartment,responseDTO);
        return responseDTO;
    }
}
