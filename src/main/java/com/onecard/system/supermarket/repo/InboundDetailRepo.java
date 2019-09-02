package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.InboundDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface InboundDetailRepo extends JpaRepository<InboundDetail, Integer>, JpaSpecificationExecutor<InboundDetail> {

    List<InboundDetail> getByInboundId(Integer inboundId);

    Page<InboundDetail> findByInboundId(Integer inboundId, Pageable pageable);

}
