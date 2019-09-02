package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InventoryRepo extends JpaRepository<Inventory, Integer>, JpaSpecificationExecutor<Inventory> {
}
