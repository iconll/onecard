package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.GoodsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 商品分类repo
 */
public interface GoodsTypeRepo extends JpaRepository<GoodsType, Integer>, JpaSpecificationExecutor<GoodsType> {

    @Query("from GoodsType g where g.name like %?1%")
    Page<GoodsType> findByName(String name, Pageable pageable);

}
