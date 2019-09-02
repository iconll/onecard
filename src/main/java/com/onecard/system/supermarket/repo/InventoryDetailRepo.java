package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.InventoryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryDetailRepo extends JpaRepository<InventoryDetail, Integer> {

    List<InventoryDetail> findByInventoryId(Integer inventoryId);

    Page<InventoryDetail> findByInventoryId(Integer inventoryId, Pageable pageable);

}
