package com.example.mysqltest.employeeservice;

import com.example.mysqltest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee save(Employee employee);

    void deleteById(Long id);

    Employee updateById(Employee employee, Long id);

    Employee findById(Long id);

    double findSalaryById(Employee employee);
}
