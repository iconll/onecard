package com.onecard.system.supermarket.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.PoToJson;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.entity.Goods;
import com.onecard.system.supermarket.entity.Outbound;
import com.onecard.system.supermarket.entity.OutboundDetail;
import com.onecard.system.supermarket.repo.GoodsRepo;
import com.onecard.system.supermarket.repo.OutboundDetailRepo;
import com.onecard.system.supermarket.repo.OutboundRepo;
import com.onecard.system.supermarket.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class OutboundDetailService extends BaseService {

    @Autowired
    OutboundDetailRepo outboundDetailRepo;
    @Autowired
    OutboundRepo outboundRepo;
    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private EntityManager entityManager;

    public BaseResponse findByOutboundId(Integer id, Integer pageNo, Integer pageSize) {
        Page page = outboundDetailRepo.getByOutboundId(id, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse list(String startTime, String endTime, Integer goodsId, String goodsName, String goodsCode, String goodsType, Integer payment, Integer outboundId, Integer type, Integer pageNo, Integer pageSize) {
        Page<OutboundDetail> page = outboundDetailRepo.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(startTime)) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("outbound").get("outTime").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
            }

            if (StringUtils.isNotBlank(endTime)) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("outbound").get("outTime").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
            }
            if (outboundId != null) {
                list.add(criteriaBuilder.equal(root.get("outbound").get("id").as(Integer.class), outboundId));
            }
            if (payment != null && payment != -1) {
                list.add(criteriaBuilder.equal(root.get("outbound").get("payment").as(Integer.class), payment));
            }
            if (type != null && type != -1) {
                list.add(criteriaBuilder.equal(root.get("outbound").get("type").as(Integer.class), type));
            }
            if (goodsId != null) {
                list.add(criteriaBuilder.equal(root.get("goods").get("id").as(String.class), goodsId));
            }
            if (StringUtils.isNotBlank(goodsName)) {
                list.add(criteriaBuilder.like(root.get("goods").get("name").as(String.class), "%" + goodsName + "%"));
            }
            if (StringUtils.isNotBlank(goodsCode)) {
                list.add(criteriaBuilder.like(root.get("goods").get("code").as(String.class), "%" + goodsCode + "%"));
            }
            if (StringUtils.isNotBlank(goodsType)) {
                list.add(criteriaBuilder.like(root.get("goods").get("goodsType").get("name").as(String.class), "%" + goodsType + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse findByGroupGoods(String startTime, String endTime, String goodsName, String goodsCode, String goodsType,Integer pageNo, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();//配合后面的multiselect
        Root<OutboundDetail> root = criteriaQuery.from(OutboundDetail.class);//具体实体的Root
        Expression<Long> sumNum = criteriaBuilder.sum(root.get("num").as(Long.class));
        Expression<Double> sumPrice = criteriaBuilder.sum(root.get("sumPrice").as(Double.class));
        Expression<Goods> goods = root.get("goods").as(Goods.class);
        List<Predicate> list = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime)) {
            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("outbound").get("outTime").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
        }

        if (StringUtils.isNotBlank(endTime)) {
            list.add(criteriaBuilder.lessThanOrEqualTo(root.get("outbound").get("outTime").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
        }
        if (StringUtils.isNotBlank(goodsName)) {
            list.add(criteriaBuilder.like(root.get("goods").get("name").as(String.class), "%" + goodsName + "%"));
        }
        if (StringUtils.isNotBlank(goodsCode)) {
            list.add(criteriaBuilder.like(root.get("goods").get("code").as(String.class), "%" + goodsCode + "%"));
        }
        if (StringUtils.isNotBlank(goodsType)) {
            list.add(criteriaBuilder.like(root.get("goods").get("goodsType").get("name").as(String.class), "%" + goodsType + "%"));
        }
        Predicate[] p = new Predicate[list.size()];
        Integer start = pageNo*pageSize;
        criteriaQuery.multiselect(sumNum.alias("sumNum"), sumPrice.alias("sumPrice"), goods).where(criteriaBuilder.and(list.toArray(p))).groupBy(root.get("goods"));
        List<Tuple> tuples = entityManager.createQuery(criteriaQuery).setFirstResult(start).setMaxResults(pageSize).getResultList();
        Integer totalPage = (int) Math.ceil(tuples.size() / pageSize);
        JSONArray result = new JSONArray();
        for (Tuple tuple : tuples) {
            JSONObject json = new JSONObject();
            json.put("sumNum", Integer.valueOf(tuple.get(0).toString()));
            json.put("sumPrice", Double.valueOf(tuple.get(1).toString()));
            json.put("goods", PoToJson.getInstance().po2Json(tuple.get(2), true));
            result.add(json);
        }

        BaseJsonResponse response = new BaseJsonResponse();
        response.setDataResult(result);
        response.setTotalPage(totalPage);
        return response;
    }

    public BaseResponse save(Integer id, Integer goodsId, Integer num, Double price, Integer outboundId, Integer userId) {
        OutboundDetail outboundDetail = new OutboundDetail();
        Outbound outbound=outboundRepo.findOne(outboundId);
        Goods goods=goodsRepo.getOne(goodsId);
        Double sumPrice=num*price;
        Integer sumnum=num;
        if(id!=null){
            outboundDetail = outboundDetailRepo.getOne(id);
            sumnum=sumnum-outboundDetail.getNum();
            sumPrice=sumPrice-outboundDetail.getSumPrice();
        }
        outboundDetail.setGoods(goods);
        outboundDetail.setNum(num);
        outboundDetail.setPrice(price);
        outboundDetail.setSumPrice(num*price);
        outboundDetail.setOutbound(outbound);
        outboundDetail.setUser(userRepo.findOne(userId));

        goods.setNum(goods.getNum()-sumnum);
        goods.setSumPrice(goods.getSumPrice()-sumPrice);
        goodsRepo.save(goods);

        outbound.setNum(outbound.getNum()+sumnum);
        outbound.setSumPrice(outbound.getSumPrice()+sumPrice);
        outboundRepo.save(outbound);

        outboundDetail=outboundDetailRepo.save(outboundDetail);
        return returnSave(outboundDetail, true);
    }

    public BaseResponse delete(Integer id) {
        OutboundDetail outboundDetail = outboundDetailRepo.findOne(id);
        outboundDetailRepo.delete(id);
        Outbound outbound=outboundRepo.findOne(outboundDetail.getOutbound().getId());
        Goods goods=goodsRepo.getOne(outboundDetail.getGoods().getId());
        goods.setNum(goods.getNum()+outboundDetail.getNum());
        goods.setSumPrice(goods.getSumPrice()+outboundDetail.getSumPrice());
        goodsRepo.save(goods);

        outbound.setNum(outbound.getNum()-outboundDetail.getNum());
        outbound.setSumPrice(outbound.getSumPrice()-outboundDetail.getSumPrice());
        outboundRepo.save(outbound);
        return returnGet(outbound,true);
    }
}
