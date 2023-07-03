package com.example.mysqltest.repository;

import com.example.mysqltest.entity.EmpStore;
import com.example.mysqltest.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpStoreRepository extends JpaRepository<EmpStore, Long> {
}
