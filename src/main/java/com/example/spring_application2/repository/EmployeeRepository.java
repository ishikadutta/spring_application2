package com.example.spring_application2.repository;

import com.example.spring_application2.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
