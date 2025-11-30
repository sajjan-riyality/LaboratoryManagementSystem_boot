package com.lab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByCategory(String category);

    List<Inventory> findByQuantityLessThan(Integer qty);
}
