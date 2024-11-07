package com.employee.service.impl;

import com.employee.constants.EmployeeConstants;
import com.employee.exception.EmployeeException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository){
        this.repository = repository;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Optional<Employee> employeeExits = repository.findById(employee.getEmpId());

        if(employeeExits.isPresent()){
            throw new EmployeeException(EmployeeConstants.EMPLOYEE_EXISTS,HttpStatus.CONFLICT);
        }else
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        if(employees.isEmpty()){
            throw new EmployeeException(EmployeeConstants.EMPLOYEE_DATA_NOT_FOUND, HttpStatus.NO_CONTENT);
        }
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = repository
                .findById(id)
                .orElseThrow(
                        () -> new EmployeeException(EmployeeConstants.EMPLOYEE_NOT_FOUND, HttpStatus.NOT_FOUND));
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employeeExits = repository
                .findById(employee.getEmpId())
                .orElseThrow(
                        () -> new EmployeeException(EmployeeConstants.EMPLOYEE_NOT_FOUND,HttpStatus.NOT_FOUND));
        employeeExits.setName(employee.getName());
        employeeExits.setAge(employee.getAge());
        employeeExits.setEmail(employee.getEmail());
        Employee updatedEmployee = repository.save(employeeExits);
        return updatedEmployee;
    }

    @Override
    public Employee deleteEmployee(int id) {
        Employee employee = repository
                .findById(id)
                .orElseThrow(
                        () -> new EmployeeException(EmployeeConstants.EMPLOYEE_NOT_FOUND,HttpStatus.NOT_FOUND));
        repository.deleteById(id);
        return employee;
    }
}
