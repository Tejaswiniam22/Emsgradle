package com.example.ems.service;

import com.example.ems.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Long id);
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);

    List<Employee> getEmployeesByDepartment(String dept);
    List<Employee> getEmployeesWithSalaryGreaterThan(Double minSalary);
    List<Employee> searchEmployeesByName(String name);
    Double getAverageSalary();
    List<String> getAllEmails();
}
