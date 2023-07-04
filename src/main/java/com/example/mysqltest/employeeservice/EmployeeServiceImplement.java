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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements EmployeeService {

    private static final String NOT_FOUND = "Employee Not Found.";
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmpStoreRepository empStoreRepository;
    @Autowired
    private StoreRepository storeRepository;

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
        EmpStore empStore = new EmpStore();
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
            if (employee.getStoreId() == storeRepository.findById(employee.getStoreId()).get().getId()) {
                empStore.setEmployeeId(employee.getId());
                empStore.setStoreId(storeRepository.findById(employee.getStoreId()).get().getId());
            }
            employeeRepository.deleteById(id);
            e.setId(employee.getId());
            e.setName(employee.getName());
            e.setAge(employee.getAge());
            e.setAddress(employee.getAddress());
            e.setSalary(employee.getSalary());
            save(e);
            employee.setStoreId(storeRepository.findById(employee.getStoreId()).get().getId());
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
    public List<EmployeeDto> findEmployeeByStoreId(Long id) {
        List<EmployeeDto> employeeDto = new ArrayList<>();
        Optional<Store> value = storeRepository.findById(id);
        if (value.isPresent()) {
            List<EmpStore> empStoreList = empStoreRepository.findAllByStoreId(value.get().getId());
            for (EmpStore empStore : empStoreList) {
                employeeDto.add(convertToDTOWithStoreId(
                        employeeRepository.findById(empStore.getEmployeeId()).get()
                        , empStore));
            }
            return employeeDto;
        } else {
            throw new ObjectNotFoundException("This Store does not exist", id);
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
