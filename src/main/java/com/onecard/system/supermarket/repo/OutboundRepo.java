package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutboundRepo extends JpaRepository<Outbound, Integer>, JpaSpecificationExecutor<Outbound> {

}
