package com.onecard.system.suppermarket.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonErrorResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.PoToJson;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.suppermarket.entity.Goods;
import com.onecard.system.suppermarket.entity.Inbound;
import com.onecard.system.suppermarket.entity.InboundDetail;
import com.onecard.system.suppermarket.repo.GoodsRepo;
import com.onecard.system.suppermarket.repo.InboundDetailRepo;
import com.onecard.system.suppermarket.repo.InboundRepo;
import com.onecard.system.suppermarket.repo.UserRepo;
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
public class InboundDetailService extends BaseService {

    @Autowired
    InboundDetailRepo inboundDetailRepo;
    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    InboundRepo inboundRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    private EntityManager entityManager;

    public BaseResponse findByInboundId(Integer id, Integer pageNo, Integer pageSize) {
        Page page = inboundDetailRepo.findByInboundId(id, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse list(String startTime, String endTime, Integer goodsId, String goodsName, String goodsCode, String goodsType, Integer isSettle,Integer inboundId, String supplierName, Integer pageNo, Integer pageSize) {
        Page<InboundDetail> page = inboundDetailRepo.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(startTime)) {
                list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("inbound").get("purchaseTime").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
            }

            if (StringUtils.isNotBlank(endTime)) {
                list.add(criteriaBuilder.lessThanOrEqualTo(root.get("inbound").get("purchaseTime").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
            }

            if(inboundId!=null){
                list.add(criteriaBuilder.equal(root.get("inbound").get("id").as(Integer.class), inboundId));
            }

            if (isSettle != null && isSettle != -1) {
                list.add(criteriaBuilder.equal(root.get("inbound").get("isSettle").as(Integer.class), isSettle));
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
            if (StringUtils.isNotBlank(supplierName)) {
                list.add(criteriaBuilder.like(root.get("inbound").get("supplier").get("name").as(String.class), "%" + supplierName + "%"));
            }

            Predicate[] p = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(p));
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse findByGroupGoods(String startTime, String endTime, String goodsName, String goodsCode, String goodsType, Integer pageNo, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();//配合后面的multiselect
        Root<InboundDetail> root = criteriaQuery.from(InboundDetail.class);//具体实体的Root
        Expression<Long> sumNum = criteriaBuilder.sum(root.get("num").as(Long.class));
        Expression<Double> sumPrice = criteriaBuilder.sum(root.get("sumPrice").as(Double.class));
        Expression<Goods> goods = root.get("goods").as(Goods.class);
        List<Predicate> list = new ArrayList<>();
        if (StringUtils.isNotBlank(startTime)) {
            list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("inbound").get("purchaseTime").as(Date.class), DateUtil.parseDate(startTime + " 00:00:00", "yyyy-MM-dd HH:mm:ss")));
        }

        if (StringUtils.isNotBlank(endTime)) {
            list.add(criteriaBuilder.lessThanOrEqualTo(root.get("inbound").get("purchaseTime").as(Date.class), DateUtil.parseDate(endTime + " 23:59:59", "yyyy-MM-dd HH:mm:ss")));
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

    public BaseResponse save(Integer id, Integer goodsId, Integer num, Double price,Integer inboundId, Integer userId) {
        InboundDetail inboundDetail = new InboundDetail();
        Inbound inbound=inboundRepo.findOne(inboundId);
        Goods goods=goodsRepo.getOne(goodsId);
        Double sumPrice=num*price;
        Integer sumnum=num;
        if(id!=null){
            inboundDetail = inboundDetailRepo.getOne(id);
            sumnum=sumnum-inboundDetail.getNum();
            sumPrice=sumPrice-inboundDetail.getSumPrice();
        }
        inboundDetail.setGoods(goods);
        inboundDetail.setNum(num);
        inboundDetail.setPrice(price);
        inboundDetail.setSumPrice(num*price);
        inboundDetail.setInbound(inbound);
        inboundDetail.setUser(userRepo.findOne(userId));

        goods.setNum(goods.getNum()+sumnum);
        goods.setSumPrice(goods.getSumPrice()+sumPrice);
        goodsRepo.save(goods);

        inbound.setNum(inbound.getNum()+sumnum);
        inbound.setSumPrice(inbound.getSumPrice()+sumPrice);
        inboundRepo.save(inbound);

        inboundDetail=inboundDetailRepo.save(inboundDetail);
        return returnSave(inboundDetail, true);
    }

    public BaseResponse delete(Integer id, Integer userId) {
        InboundDetail inboundDetail=inboundDetailRepo.findOne(id);
        if(inboundDetail.getUser().getId().equals(userId)){
            inboundDetailRepo.delete(id);
            Inbound inbound=inboundRepo.findOne(inboundDetail.getInbound().getId());
            Goods goods=goodsRepo.getOne(inboundDetail.getGoods().getId());
            goods.setNum(goods.getNum()-inboundDetail.getNum());
            goods.setSumPrice(goods.getSumPrice()-inboundDetail.getSumPrice());
            goodsRepo.save(goods);

            inbound.setNum(inbound.getNum()-inboundDetail.getNum());
            inbound.setSumPrice(inbound.getSumPrice()-inboundDetail.getSumPrice());
            inboundRepo.save(inbound);
            return returnGet(inbound,true);
        }else{
            return new CommonErrorResponse("只能编辑自己录入的信息");
        }

    }
}
