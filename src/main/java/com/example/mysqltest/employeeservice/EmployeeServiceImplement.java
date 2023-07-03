package com.example.mysqltest.employeeservice;

import com.example.mysqltest.dto.EmployeeDto;
import com.example.mysqltest.entity.EmpStore;
import com.example.mysqltest.entity.Employee;
import com.example.mysqltest.entity.Store;
import com.example.mysqltest.exception.ApplicationException;
import com.example.mysqltest.repository.EmpStoreRepository;
import com.example.mysqltest.repository.EmployeeRepository;
import com.example.mysqltest.repository.StoreRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplement implements EmployeeService {

    private static final String NOT_FOUND = "Employee Not Found.";
    @Autowired
    private static EmployeeRepository employeeRepository;
    @Autowired
    private static EmpStoreRepository empStoreRepository;
    @Autowired
    private static StoreRepository storeRepository;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(this::convertToDTO).toList();
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
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Employee> value = employeeRepository.findById(id);
        if (value.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public EmployeeDto updateById(EmployeeDto employee, Long id) {
        Employee e = new Employee();
        Optional<Employee> value = employeeRepository.findById(id);
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
            employeeRepository.deleteById(id);
            e.setId(employee.getId());
            e.setName(employee.getName());
            e.setAge(employee.getAge());
            e.setAddress(employee.getAddress());
            e.setSalary(employee.getSalary());
            save(e);
            return employee;
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public EmployeeDto findById(Long id) {
        Optional<Employee> value = employeeRepository.findById(id);
        if (value.isPresent()) {
            return convertToDTO(value.get());
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public double findSalaryById(Long id) {
        Optional<Employee> value = employeeRepository.findById(id);
        if (value.isPresent()) {
            Employee employee = value.get();
            return employee.getSalary() + employee.getBonus() - employee.getPenalty();
        } else {
            throw new ObjectNotFoundException(NOT_FOUND, id);
        }
    }

    @Override
    public EmployeeDto findEmployeeByStoreId(Long id) {
        Optional<Store> value = storeRepository.findById(id);
        if (value.isPresent()) {
            Optional<EmpStore> empStoreValue = empStoreRepository.findById(value.get().getId());
            if (empStoreValue.isPresent()) {
                EmpStore empStore = empStoreValue.get();
                Optional<Employee> employeeValue = employeeRepository.findById(empStore.getEmployeeId());
                if (employeeValue.isPresent()) {
                    Employee employee = employeeValue.get();
                    return convertToDTOWithStoreId(employee, empStore);
                } else {
                    throw new ObjectNotFoundException(NOT_FOUND, id);
                }
            } else {
                throw new ObjectNotFoundException(NOT_FOUND, id);
            }
        } else {
            throw new ObjectNotFoundException("That Store doesn't exist", id);
        }
    }

    public EmployeeDto convertToDTO(Employee employee) {
        EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setSalary(employee.getSalary());
        return employeeDTO;
    }

    public EmployeeDto convertToDTOWithStoreId(Employee employee, EmpStore empStore) {
        EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setStoreId(empStore.getStoreId());
        return employeeDTO;
    }
}
