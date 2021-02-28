package com.example.spring_application2.repository;

import com.example.spring_application2.entity.Department;
import com.example.spring_application2.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    @Query(value="SELECT * from Employee e INNER JOIN Department d on e.id=d.id",nativeQuery = true)
    List<Employee> getExperienced(Long departmentId);

}
