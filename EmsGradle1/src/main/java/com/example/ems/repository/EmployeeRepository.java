package com.example.ems.repository;

import com.example.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.department = :dept")
    List<Employee> findByDepartment(@Param("dept") String dept);

    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary")
    List<Employee> findBySalaryGreaterThan(@Param("minSalary") Double minSalary);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> searchByName(@Param("name") String name);

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double getAverageSalary();

    @Query("SELECT e.email FROM Employee e")
    List<String> findAllEmails();

    Employee findByEmail(String email);
}
