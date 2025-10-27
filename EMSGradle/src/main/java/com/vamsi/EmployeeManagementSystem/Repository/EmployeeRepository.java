package com.vamsi.EmployeeManagementSystem.Repository;

import com.vamsi.EmployeeManagementSystem.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryGreaterThanEqual(double minSalary);

    @Query("select e.email from Employee e")
    List<String> findAllEmail();


    @Query("select e from Employee e where e.firstName =:name or e.lastName = :name")
    List<Employee> findByFirstNameOrLastName(String name);


}
