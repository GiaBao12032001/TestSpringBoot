package com.example.mysqltest.repository;

import com.example.mysqltest.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
