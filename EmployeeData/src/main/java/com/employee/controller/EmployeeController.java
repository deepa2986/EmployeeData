package com.employee.controller;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employeeData")
public class EmployeeController {

    EmployeeService service;

    EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping("/saveEmployee")
    ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        Employee save = service.addEmployee(employee);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/viewEmployee")
    ResponseEntity<List<Employee>> viewAllEmployee(){
        List<Employee> employees = service.getAllEmployees();
        return new ResponseEntity<>(employees,HttpStatus.OK);
    }

    @GetMapping("/searchEmployee/{id}")
    ResponseEntity<Employee> searchEmployeeById(@PathVariable int id){
        Employee employee = service.getEmployeeById(id);
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<Employee> deleteEmployeeById(@PathVariable int id){
        Employee deletedEmployee = service.deleteEmployee(id);
        return new ResponseEntity<>(deletedEmployee,HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee updatedEmployee = service.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee,HttpStatus.OK);
    }
}
