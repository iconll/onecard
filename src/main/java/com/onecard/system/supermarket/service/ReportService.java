package com.onecard.system.supermarket.service;

import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.PoToJson;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.entity.Goods;
import com.onecard.system.supermarket.entity.InboundDetail;
import com.onecard.system.supermarket.repo.GoodsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReportService {
    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    private EntityManager entityManager;

    public Page Statistics(String name, String code, String startTime, String endTime, Integer page, Integer limit) {
        Page<Goods> goodsPage = goodsRepo.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(name)) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
            }
            if (StringUtils.isNotBlank(code)) {
                list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
            }
            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, new PageRequest(page-1, limit));
        List<Goods> goodsList = goodsPage.getContent();
        for(int i=0;i<goodsList.size();i++){
            Goods goods = goodsList.get(i);
            JSONObject json = PoToJson.getInstance().po2Json(goods,false);
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();//配合后面的multiselect
            Root<InboundDetail> root = criteriaQuery.from(InboundDetail.class);//具体实体的Root
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.equal(root.get("goods"),goods));
            if (StringUtils.isNotBlank(startTime)) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
            }

            if (StringUtils.isNotBlank(endTime)) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
            }
            Predicate[] p = new Predicate[list.size()];
            Predicate predicatesWhere = criteriaBuilder.and(list.toArray(p));
            Expression<Integer> sumNum = criteriaBuilder.sum(root.get("num").as(Integer.class));
            Expression<Double> sumPrice = criteriaBuilder.sum(root.get("sumPrice").as(Double.class));
            criteriaQuery.multiselect(sumNum.alias("sumNum"), sumPrice.alias("sumPrice")).where(predicatesWhere);
            Tuple tuples = entityManager.createQuery(criteriaQuery).getSingleResult();
            json.put("inboundNum",tuples.get(0));
            json.put("inboundPrice",tuples.get(1));
        }
        return goodsPage;
    }
}
