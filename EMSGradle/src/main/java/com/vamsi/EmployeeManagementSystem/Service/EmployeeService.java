package com.vamsi.EmployeeManagementSystem.Service;

import com.vamsi.EmployeeManagementSystem.Model.Employee;
import com.vamsi.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElse(null);
    }

    public Employee updateEmployee(Employee employeeDetails) {
        return employeeRepository.findById(employeeDetails.getId())
                .map(e-> {
                    e.setFirstName(employeeDetails.getFirstName());
                    e.setLastName(employeeDetails.getLastName());
                    e.setEmail(employeeDetails.getEmail());
                    e.setDepartment(employeeDetails.getDepartment());
                    e.setSalary(employeeDetails.getSalary());
                    return employeeRepository.save(e);
                }).orElseThrow(() -> new RuntimeException("Employee not Found with id "+employeeDetails.getId()));
    }

    public String deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
        return "Employee Deleted Successfully";
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public List<Employee> getEmployeesByMinSalary(double minSalary) {
        return employeeRepository.findBySalaryGreaterThanEqual(minSalary);
    }

    public List<Employee> searchEmployeesByName(String name) {
//        List<Employee> employees=employeeRepository.findAll().stream()
//                .filter(e-> e.getFirstName().equalsIgnoreCase(name) || e.getLastName().equalsIgnoreCase(name))
//                .toList();
//        return employees;
        return employeeRepository.findByFirstNameOrLastName(name);
    }

    public List<String> getAllEmployeeEmails() {
        return employeeRepository.findAllEmail();
    }

    public double getAverargeSalary() {
        return employeeRepository.findAll().stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }
}
