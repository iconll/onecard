package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.InboundBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 采购退货Repo
 */
public interface InboundBackRepo extends JpaRepository<InboundBack, Integer>, JpaSpecificationExecutor<InboundBack> {

    @Query("from InboundBack ib where ib.goods.name like %?1%")
    Page<InboundBack> findByGoodsName(String goodsName, Pageable pageable);

}
