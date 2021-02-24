package com.example.spring_application2.repository;

import com.example.spring_application2.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
