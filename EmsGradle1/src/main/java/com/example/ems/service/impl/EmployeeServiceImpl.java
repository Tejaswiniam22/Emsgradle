package com.example.ems.service.impl;

import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        if (repo.findByEmail(employee.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        return repo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));

        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());
        return repo.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesByDepartment(String dept) {
        return repo.findByDepartment(dept);
    }

    @Override
    public List<Employee> getEmployeesWithSalaryGreaterThan(Double minSalary) {
        return repo.findBySalaryGreaterThan(minSalary);
    }

    @Override
    public List<Employee> searchEmployeesByName(String name) {
        return repo.searchByName(name);
    }

    @Override
    public Double getAverageSalary() {
        return repo.getAverageSalary();
    }

    @Override
    public List<String> getAllEmails() {
        return repo.findAllEmails();
    }
}
