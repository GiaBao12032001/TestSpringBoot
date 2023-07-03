package com.example.mysqltest.entity;

import jakarta.persistence.*;
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
