package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.OutboundBack;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OutboundBackRepo extends JpaRepository<OutboundBack, Integer> {

    @Query("from OutboundBack ob where ob.goods.name like %?2%")
    Page<OutboundBack> findByGoodsName(Pageable pageable, String goodsName);

}
