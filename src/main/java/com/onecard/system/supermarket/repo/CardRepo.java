package com.onecard.system.supermarket.repo;

import com.onecard.system.supermarket.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CardRepo extends JpaRepository<Card, Integer>, JpaSpecificationExecutor<Card> {
}