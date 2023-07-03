package com.example.mysqltest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @JsonProperty(value = "id")
    private Long id;
    private String name;
    private int age;
    private String address;
    private double salary;
    private Long storeId;
}
