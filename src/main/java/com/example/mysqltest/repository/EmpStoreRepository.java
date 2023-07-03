package com.example.mysqltest.repository;

import com.example.mysqltest.entity.EmpStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpStoreRepository extends JpaRepository<EmpStore, Long> {
}
