package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.Inbound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InboundRepo extends JpaRepository<Inbound, Integer>, JpaSpecificationExecutor<Inbound> {

}
