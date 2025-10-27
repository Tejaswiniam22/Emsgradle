package com.vamsi.EmployeeManagementSystem.Controller;

import com.vamsi.EmployeeManagementSystem.Model.Employee;
import com.vamsi.EmployeeManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public String addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return "Employee added Successfully";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id){
        return employeeService.getEmployeeById(id);

    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee employee){
      return  employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") long id){
       return employeeService.deleteEmployeeById(id);
    }


    @GetMapping("/employees/department/{dept}")
    public List<Employee> getEmployeesByDepartment(@PathVariable("dept") String department){
        return employeeService.getEmployeesByDepartment(department);

    }

    @GetMapping("/employees/salary/{min}")
    public List<Employee> getEmployeesByMinSalary(@PathVariable("min") double minSalary){
        return employeeService.getEmployeesByMinSalary(minSalary);

    }

    @GetMapping("/employees/search/{name}")
    public List<Employee> searchEmployeesByName(@PathVariable("name") String name){
        return employeeService.searchEmployeesByName(name);

    }

    @GetMapping("/employees/emails")
    public List<String> getAllEmployeeEmails(){
        return employeeService.getAllEmployeeEmails();

    }

    @GetMapping("/employees/average-salary")
    public double getAverageSalary(){
        return employeeService.getAverargeSalary();
    }
}
