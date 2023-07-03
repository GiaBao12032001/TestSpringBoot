package com.example.mysqltest.repository;

import com.example.mysqltest.dto.EmployeeDto;
import com.example.mysqltest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
