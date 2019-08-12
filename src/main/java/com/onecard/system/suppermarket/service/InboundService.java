package com.onecard.system.suppermarket.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.suppermarket.entity.Goods;
import com.onecard.system.suppermarket.entity.Inbound;
import com.onecard.system.suppermarket.entity.InboundDetail;
import com.onecard.system.suppermarket.repo.GoodsRepo;
import com.onecard.system.suppermarket.repo.InboundDetailRepo;
import com.onecard.system.suppermarket.repo.InboundRepo;
import com.onecard.system.suppermarket.repo.SupplierRepo;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class InboundService extends BaseService {

    @Autowired
    InboundRepo inboundRepo;

    @Autowired
    InboundDetailRepo inboundDetailRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @Autowired
    SupplierRepo supplierRepo;

    /**
     * 入库
     * @param inbound
     * @param details
     * @return
     */
    public BaseResponse addAll(Inbound inbound, JSONArray details){
        //根据明细算出总数，总金额
        Double sumPrice = 0.00;
        Integer num = 0;
        for (Object o:details) {
            InboundDetail d = JSONObject.toJavaObject((JSONObject)o, InboundDetail.class);
            num += d.getNum();
            sumPrice += d.getPrice()*d.getNum();
        }

        //先保存入库单
        if(inbound.getId()==null){
            //单号=IN+日期+4位商品总数（不足补零）+ 4位随机数
            int rand = (int)((Math.random()*9+1)*1000);
            String no = "IN" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + String.format("%04d", num) + rand;
            inbound.setCreateTime(new Date());
            inbound.setNo(no);
        }else{
            //如果是更新，移除所有明细
            List<InboundDetail> delList = inboundDetailRepo.getByInboundId(inbound.getId());
            inboundDetailRepo.delete(delList);
            Inbound target = inboundRepo.findOne(inbound.getId());
            inbound = (Inbound) po2po(inbound, target);
        }
        inbound.setNum(num);
        inbound.setSumPrice(sumPrice);
        Inbound result = inboundRepo.save(inbound);

        //将入库单ID放入明细list并算出明细总价
        List<InboundDetail> list = new ArrayList<>();
        for (Object o:details) {
            Goods goods = goodsRepo.findOne(((JSONObject) o).getInteger("goods.id"));
            InboundDetail d = JSONObject.toJavaObject((JSONObject)o, InboundDetail.class);
            d.setGoods(goods);
            d.setInbound(result);
            d.setSumPrice(d.getPrice()*d.getNum());
            list.add(d);

            //更新商品库存并计算出商品总价值
            goods.setNum(goods.getNum()+d.getNum());
            goods.setSumPrice(goods.getPrice()*goods.getNum());
            goodsRepo.save(goods);
        }

        //再保存入库明细
        inboundDetailRepo.save(list);

        return new CommonSuccessResponse();
    }

    /**
     * 列表查询
     * @param startTime
     * @param endTime
     * @param no
     * @param isSettle
     * @param supplierName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResponse list(String startTime, String endTime, String no, Integer isSettle, String supplierName, Integer pageNo, Integer pageSize){
        Page<Inbound> page = inboundRepo.findAll(new Specification<Inbound>() {
            @Override
            public Predicate toPredicate(Root<Inbound> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(startTime)) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(startTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (StringUtils.isNotBlank(endTime)) {
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(endTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (isSettle != null) {
                    list.add(criteriaBuilder.equal(root.get("isSettle").as(Integer.class), isSettle));
                }

                if (StringUtils.isNotBlank(no)) {
                    list.add(criteriaBuilder.like(root.get("no").as(String.class), "%" + no + "%"));
                }

                if (StringUtils.isNotBlank(supplierName)) {
                    list.add(criteriaBuilder.like(root.get("supplier").get("name").as(String.class), "%" + supplierName + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse save(Integer id,String no, String purchaseTime,Integer isSettle, Integer supplierId) {
        Inbound inbound = new Inbound();
        if(id!=null){
            inbound = inboundRepo.findOne(id);
        }else{
            inbound.setCreateTime(new Date());
            inbound.setNum(0);
            inbound.setSumPrice(0.00);
        }
        inbound.setNo(no);
        inbound.setPurchaseTime(DateUtil.parseDate(purchaseTime, "yyyy-MM-dd"));
        inbound.setIsSettle(isSettle);
        inbound.setSupplier(supplierRepo.findOne(supplierId));
        inbound=inboundRepo.save(inbound);

        return returnSave(inbound,true);
    }
}
