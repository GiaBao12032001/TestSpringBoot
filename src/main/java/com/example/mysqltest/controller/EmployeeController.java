package com.example.mysqltest.controller;

import com.example.mysqltest.dto.EmployeeDto;
import com.example.mysqltest.employeeservice.EmployeeService;
import com.example.mysqltest.entity.Employee;
import com.example.mysqltest.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeService service, EmployeeRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/employee/")
    public List<EmployeeDto> getEmployees() {
        return service.findAll();
    }

    @GetMapping("/employee/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/employee/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @DeleteMapping("/employee/delete/{id}")
    public void removeEmployee(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping("/employee/update/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employee) {
        return service.updateById(employee, id);
    }

    @GetMapping("/employee/salary/{id}")
    public double findSalaryById(@PathVariable Long id) {
        return service.findSalaryById(id);
    }

    @GetMapping("/employee/store/{id}")
    public List<EmployeeDto> findEmployeeByStoreId(@PathVariable Long id) {
        return service.findEmployeeByStoreId(id);
    }
}
