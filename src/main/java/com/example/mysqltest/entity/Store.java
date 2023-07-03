package com.example.mysqltest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Store {
    @Id
    @Column
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
}
