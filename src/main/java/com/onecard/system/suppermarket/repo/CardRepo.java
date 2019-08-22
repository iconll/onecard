package com.onecard.system.suppermarket.repo;

import com.onecard.system.suppermarket.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardRepo extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {
}
