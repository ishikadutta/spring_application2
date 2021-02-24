package com.example.spring_application2.repository;

import com.example.spring_application2.entity.Department;
import com.example.spring_application2.entity.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByDepartment(Department department);
    List<Employee> findByDepartment_Id(Long departmentId);

    @Query("SELECT e FROM Employee e where e.department.id=?1 AND e.name= ?2")
 //   @Query("FROM Employee e WHERE e.department.id= ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    @Query("SELECT e FROM Employee e where e.department.id= :departmentId")
        //   @Query("FROM Employee e WHERE e.department.id= ?1")
    List<Employee> getEmployeeListByDepartmentIdParam(@Param("departmentId") Long departmentId);

    @Query(value = "SELECT * FROM employee e WHERE e.department_id= ?1", nativeQuery =true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);

    @Query(value="SELECT e FROM Employee e")
    List<Employee> findAllUsers(Sort sort);


}
