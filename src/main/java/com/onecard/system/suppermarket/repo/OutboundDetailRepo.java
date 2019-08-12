package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.OutboundDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OutboundDetailRepo extends JpaRepository<OutboundDetail, Integer>, JpaSpecificationExecutor<OutboundDetail> {

    List<OutboundDetail> findByOutboundId(Integer outboundId);

    Page<OutboundDetail> getByOutboundId(Integer outboundId, Pageable pageable);

}
