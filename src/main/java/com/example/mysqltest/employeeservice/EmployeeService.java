package com.example.mysqltest.employeeservice;

import com.example.mysqltest.dto.EmployeeDto;
import com.example.mysqltest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    Employee save(Employee employee);

    void deleteById(Long id);

    EmployeeDto updateById(EmployeeDto employee, Long id);

    EmployeeDto findById(Long id);

    double findSalaryById(Long id);

    List<EmployeeDto> findEmployeeByStoreId(Long id);
}
