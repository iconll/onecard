package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InboundRepo extends JpaRepository<Inbound, Integer>, JpaSpecificationExecutor<Inbound> {

}
