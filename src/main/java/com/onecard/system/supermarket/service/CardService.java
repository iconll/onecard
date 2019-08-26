package com.onecard.system.supermarket.service;

import com.onecard.system.supermarket.entity.Card;
import com.onecard.system.supermarket.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class CardService {
    @Autowired
    private CardRepo cardRepo;


    public Card findById(Integer id) {
        return cardRepo.getOne(id);
    }
}
