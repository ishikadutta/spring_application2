package com.example.spring_application2.service.impl;

import com.example.spring_application2.dto.DepartmentResponseDTO;
import com.example.spring_application2.dto.EmployeeRequestDTO;
import com.example.spring_application2.dto.EmployeeResponseDTO;
import com.example.spring_application2.entity.Department;
import com.example.spring_application2.entity.Employee;
import com.example.spring_application2.repository.DepartmentRepository;
import com.example.spring_application2.repository.EmployeeRepository;
import com.example.spring_application2.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeRequestDTO,employee);
        //fetch department from db
        Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
        if(departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }
        else{
            Department department = new Department();
            department.setName(employeeRequestDTO.getDepartment().getName());
            employee.setDepartment(department);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee,responseDTO);

//        DepartmentResponseDTO departmentResponseDTO= new DepartmentResponseDTO();
//        departmentResponseDTO.setId(savedEmployee.getDepartment().getId());
//        departmentResponseDTO.setName(savedEmployee.getDepartment().getName());
        responseDTO.setDepartmentFromEntity(savedEmployee.getDepartment());

        return responseDTO;
    }
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);

           responseDTO.setDepartmentFromEntity(employeeOptional.get().getDepartment());
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
          //  employeeFromDb.setDepartmentName((employeeRequestDTO.getDepartmentName()));

            //fetch deparment from db
            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }
            else{
                Department department = new Department();
                department.setName(employeeRequestDTO.getDepartment().getName());
                employeeFromDb.setDepartment(department);
            }
            //save to db
            Employee saveEmployee = employeeRepository.save(employeeFromDb);

            //copy from employee to response dto
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(saveEmployee,responseDTO);
            responseDTO.setDepartmentFromEntity(saveEmployee.getDepartment());

            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            Employee employeeFromDb = employeeOptional.get();
            employeeRepository.deleteById(id);

            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);

            responseDTO.setDepartmentFromEntity(employeeFromDb.getDepartment());
            return responseDTO;
        }
        return null;
        //return null;
    }
    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId){
        Department department = departmentRepository.findById(departmentId).get();

      // List<Employee> employeeList = employeeRepository.findByDepartment(department);
     //   List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

       // List<Employee> employeeList1 = employeeRepository.getEmployeeListByDepartmentId(departmentId);
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);
       List<EmployeeResponseDTO> employeeResponseDTOSList = new ArrayList<>();
       for(Employee employee: employeeList){
           EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
           BeanUtils.copyProperties(employee,responseDTO);
           responseDTO.setDepartmentFromEntity(employee.getDepartment());
           employeeResponseDTOSList.add(responseDTO);

       }
       return employeeResponseDTOSList;
    }

    @Override
    public List<EmployeeResponseDTO> getMostExperienced() {
        //  List<Employee> employeeList = employeeRepository.findAllUsers(Sort.by("yearsOfExperience"));
//        int length = employeeList.size();
//        int maxYear = employeeList.get(length - 1).getYearsOfExperience();
//        List<Employee> listOfMaxExperience = new ArrayList<>();
//        for(Employee employee: employeeList){
//            int experience = employee.getYearsOfExperience();
//            if(experience== maxYear){
//                listOfMaxExperience.add(employee);
//            }
//        }
//        List<EmployeeResponseDTO> employeeResponseDTOSList = new ArrayList<>();
//        for(Employee employee:listOfMaxExperience){
//            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
//            BeanUtils.copyProperties(employee,responseDTO);
//            responseDTO.setDepartmentFromEntity(employee.getDepartment());
//            employeeResponseDTOSList.add(responseDTO);
//        }

        List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findMostExperiencedEmployee();
        for(Employee employee: employeeList){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee,responseDTO);
            responseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOS.add(responseDTO);
        }

        return employeeResponseDTOS;
    }

    @Override
    public List<EmployeeResponseDTO> getMostExperiencedInDepartment(Long departmentId){
        List<EmployeeResponseDTO> employeeResponseDTOS = new ArrayList<>();
       // List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);
        List<Employee> employeeList = employeeRepository.findMostExperiencedEmployeeByDepartment(departmentId);


        for(Employee employee: employeeList){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee,responseDTO);
            responseDTO.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOS.add(responseDTO);
        }
        return employeeResponseDTOS;
    }
}
