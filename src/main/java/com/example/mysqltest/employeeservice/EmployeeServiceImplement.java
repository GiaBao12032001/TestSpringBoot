package com.example.mysqltest.employeeservice;

import com.example.mysqltest.entity.Employee;
import com.example.mysqltest.exception.ApplicationException;
import com.example.mysqltest.repository.EmployeeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements EmployeeService {

    private static final String NOT_FOUND = "Employee Not Found.";
    private final EmployeeRepository repository;

    public EmployeeServiceImplement(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() == null) {
            throw new ApplicationException(406, "Invalid ID");
        }
        if (employee.getAge() < 18) {
            throw new ApplicationException(406, "Age must be 18+");
        }
        if (employee.getName().equals("") || employee.getName().isEmpty()) {
            throw new ApplicationException(406, "Name must not be null");
        }
        if (employee.getAddress().equals("") || employee.getAddress().isEmpty()) {
            throw new ApplicationException(406, "Address must not be empty");
        }
        return repository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Employee> value = repository.findById(id);
        if (value.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public Employee updateById(Employee employee, Long id) {
        Optional<Employee> value = repository.findById(id);
        if (value.isPresent()) {
            if (employee.getId() == null) {
                throw new ApplicationException(406, "Invalid ID");
            }
            if (employee.getAge() < 18) {
                throw new ApplicationException(406, "Age must be 18+");
            }
            if (employee.getName().equals("") || employee.getName().isEmpty()) {
                throw new ApplicationException(406, "Name must not be null");
            }
            if (employee.getAddress().equals("") || employee.getAddress().isEmpty()) {
                throw new ApplicationException(406, "Address must not be empty");
            }
            repository.deleteById(id);
            return save(employee);
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public Employee findById(Long id) {
        Optional<Employee> value = repository.findById(id);
        if (value.isPresent()) {
            return value.get();
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public double findSalaryById(Employee employee) {
        return employee.getSalary() + employee.getBonus() - employee.getPenalty();
    }
}
