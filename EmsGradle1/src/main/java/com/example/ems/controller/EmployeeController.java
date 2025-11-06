package com.example.ems.controller;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest req) {
        Employee emp = new Employee(req.getFirstName(), req.getLastName(), req.getEmail(), req.getDepartment(), req.getSalary());
        Employee saved = service.createEmployee(emp);
        return ResponseEntity.created(URI.create("/employees/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody EmployeeRequest req) {
        Employee emp = new Employee(req.getFirstName(), req.getLastName(), req.getEmail(), req.getDepartment(), req.getSalary());
        return service.updateEmployee(id, emp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/department/{dept}")
    public List<Employee> byDepartment(@PathVariable String dept) {
        return service.getEmployeesByDepartment(dept);
    }

    @GetMapping("/salary/{min}")
    public List<Employee> bySalary(@PathVariable Double min) {
        return service.getEmployeesWithSalaryGreaterThan(min);
    }

    @GetMapping("/search/{name}")
    public List<Employee> searchByName(@PathVariable String name) {
        return service.searchEmployeesByName(name);
    }

    @GetMapping("/emails")
    public List<String> emails() {
        return service.getAllEmails();
    }

    @GetMapping("/average-salary")
    public Double averageSalary() {
        return service.getAverageSalary();
    }
}
