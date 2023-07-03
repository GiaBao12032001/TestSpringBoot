package com.example.mysqltest.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class EmployeeKey implements Serializable {
    private final Long employeeId;
    private final Long storeId;

    public EmployeeKey(Long employeeId, Long storeId) {
        this.storeId = storeId;
        this.employeeId = employeeId;
    }
}
