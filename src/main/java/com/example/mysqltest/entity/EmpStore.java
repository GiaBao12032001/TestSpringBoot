package com.example.mysqltest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "`emp_store`")
@Data
public class EmpStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "`employee_id`")
    Long employeeId;
    @Column(name = "`store_id`")
    Long storeId;
}
