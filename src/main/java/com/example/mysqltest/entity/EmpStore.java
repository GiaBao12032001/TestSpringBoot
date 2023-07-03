package com.example.mysqltest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@IdClass(EmployeeKey.class)
public class EmpStore {
    @Id
    Long employee_Id;
    @Id
    Long store_Id;
    private Store store;
}