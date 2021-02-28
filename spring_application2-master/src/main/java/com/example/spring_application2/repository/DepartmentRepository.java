package com.example.spring_application2.repository;

import com.example.spring_application2.entity.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
  //  @Query(value="select * from department where EXISTS (select max(employee.years_of_experience) as years_of_experience, employee.department_id from employee where EXISTS (select sum(employee.years_of_experience) as years_of_experience, employee.department_id from employee group by employee.department_id))",nativeQuery = true)
    @Query(value="select * from department where id in(select id from department where (select max(sum) from (select sum(years_of_experience) from employee e1 group by e1.department_id) s1) = (select sum(years_of_experience) from employee e2 where e2.department_id = department.id))",nativeQuery = true)
    List<Department> findDepartmentWithMaxExperience();
}
