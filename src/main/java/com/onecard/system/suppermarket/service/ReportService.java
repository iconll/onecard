package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.StringUtils;
import com.kmut.retail.entity.GoodsDetailView;
import com.kmut.retail.repo.GoodsDetailViewRepo;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReportService extends BaseService{
    @Autowired
    private GoodsDetailViewRepo goodsDetailViewRepo;

    public BaseResponse goodsDetailList(Integer merchantId, Integer goodsId, Integer type,String startTime, String endTime, Integer pageNo, Integer pageSize) {
        Page<GoodsDetailView> page = goodsDetailViewRepo.findAll(new Specification<GoodsDetailView>() {
            @Override
            public Predicate toPredicate(Root<GoodsDetailView> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(startTime)) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("time").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
                }

                if (StringUtils.isNotBlank(endTime)) {
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("time").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
                }

                if (merchantId != null) {
                    list.add(criteriaBuilder.equal(root.get("merchant").get("id").as(Integer.class), merchantId));
                }

                if (goodsId != null) {
                    list.add(criteriaBuilder.equal(root.get("goods").get("id").as(Integer.class), goodsId));
                }

                if (type != null && type!=0) {
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), type));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }
}
