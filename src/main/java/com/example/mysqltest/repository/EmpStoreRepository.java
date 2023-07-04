package com.example.mysqltest.repository;

import com.example.mysqltest.entity.EmpStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpStoreRepository extends JpaRepository<EmpStore, Long> {

    List<EmpStore> findAllByStoreId(Long storeId);
}
