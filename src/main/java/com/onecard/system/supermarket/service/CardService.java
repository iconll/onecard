package com.onecard.system.supermarket.service;

import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.entity.Card;
import com.onecard.system.supermarket.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CardService {
    @Autowired
    private CardRepo cardRepo;


    public Card findById(Integer id) {
        if (id != null) {
            return cardRepo.getOne(id);
        } else {
            return null;
        }

    }

    public Page findList(String cardNo, Integer type, Integer page, Integer limit) {
        Page<Card> Cardpage = cardRepo.findAll(new Specification<Card>() {
            @Override
            public Predicate toPredicate(Root<Card> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(cardNo)) {
                    list.add(criteriaBuilder.like(root.get("cardNo").as(String.class), "%" + cardNo + "%"));
                }

                if (type != null) {
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), type));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(page, limit));
        return Cardpage;
    }

    public Card save(Integer id, Integer type, Integer stutes, String cardNo, String balance, String name) {
        Card tempCard = new Card();
        if (id != null) {
            tempCard = cardRepo.findOne(id);
            tempCard.setType(type);
        }
        tempCard.setCardNo(cardNo);
        tempCard.setBalance(Double.parseDouble(balance));
        tempCard.setName(name);
        tempCard.setStatus(stutes);
        tempCard = cardRepo.save(tempCard);
        return tempCard;
    }
}
