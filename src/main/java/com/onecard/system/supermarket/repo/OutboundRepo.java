package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.Outbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OutboundRepo extends JpaRepository<Outbound, Integer>, JpaSpecificationExecutor<Outbound> {

}
