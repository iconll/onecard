package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 供应商Repo
 */
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

    Page<Supplier> findByNameLike(String name, Pageable pageable);

}
