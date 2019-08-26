package com.onecard.system.supermarket.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.entity.Goods;
import com.onecard.system.supermarket.entity.Outbound;
import com.onecard.system.supermarket.entity.OutboundDetail;
import com.onecard.system.supermarket.repo.GoodsRepo;
import com.onecard.system.supermarket.repo.OutboundDetailRepo;
import com.onecard.system.supermarket.repo.OutboundRepo;
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
@Transactional(rollbackFor = Exception.class)
public class OutboundService extends BaseService {

    @Autowired
    OutboundRepo outboundRepo;

    @Autowired
    OutboundDetailRepo outboundDetailRepo;

    @Autowired
    GoodsRepo goodsRepo;

    /**
     * 出库
     * @param outbound
     * @param details
     * @return
     */
    public BaseResponse outbound(Outbound outbound, String details){
        //算出出库总数，总额
        Integer sumNum = 0;
        Double sumPrice = 0.00;
        for (Object o: JSONArray.parseArray(details)) {
            OutboundDetail detail = JSONObject.toJavaObject((JSONObject)o, OutboundDetail.class);
            sumNum += detail.getNum();
            sumPrice += detail.getPrice();
        }

        //先保存出库单
        if(outbound.getId()==null){
            //单号=OUT+日期+4位商品总数（不足补零）+ 4位随机数
            int rand = (int)((Math.random()*9+1)*1000);
            String no = "OUT" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + String.format("%04d", sumNum) + rand;
            outbound.setOutTime(new Date());
            outbound.setNo(no);
        }else{
            //如果是更新，移除所有明细
            List<OutboundDetail> delList = outboundDetailRepo.findByOutboundId(outbound.getId());
            outboundDetailRepo.delete(delList);
            Outbound target = outboundRepo.findOne(outbound.getId());
            outbound = (Outbound) po2po(outbound, target);
        }
        outbound.setNum(sumNum);
        outbound.setSumPrice(sumPrice);
        Outbound result = outboundRepo.save(outbound);

        //将出库单ID放入明细list并算出明细总价
        List<OutboundDetail> outList = new ArrayList<>();
        for (Object o: JSONArray.parseArray(details)) {
            OutboundDetail d = JSONObject.toJavaObject((JSONObject)o, OutboundDetail.class);
            Goods goods = goodsRepo.findOne(((JSONObject) o).getInteger("goodsId"));
            d.setGoods(goods);
            d.setOutbound(result);
            d.setSumPrice(d.getPrice()*d.getNum());
            outList.add(d);

            //更新商品库存并计算出商品总价值
            goods.setNum(goods.getNum()-d.getNum());
            goods.setSumPrice(goods.getPrice()*goods.getNum());
            goodsRepo.save(goods);
        }

        //再保存出库明细
        outboundDetailRepo.save(outList);

        return new CommonSuccessResponse();
    }

    /**
     * 列表查询
     * @param startTime
     * @param endTime
     * @param no
     * @param type
     * @param clientName
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResponse list(String startTime, String endTime, String no, Integer type,Integer payment, String clientName, Integer pageNo, Integer pageSize){
        Page<Outbound> page = outboundRepo.findAll(new Specification<Outbound>() {
            @Override
            public Predicate toPredicate(Root<Outbound> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(startTime)) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(startTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (StringUtils.isNotBlank(endTime)) {
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(endTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (payment != null) {
                    list.add(criteriaBuilder.equal(root.get("payment").as(Integer.class), payment));
                }

                if (type != null) {
                    list.add(criteriaBuilder.equal(root.get("type").as(Integer.class), type));
                }

                if (StringUtils.isNotBlank(no)) {
                    list.add(criteriaBuilder.like(root.get("no").as(String.class), "%" + no + "%"));
                }

                if (StringUtils.isNotBlank(clientName)) {
                    list.add(criteriaBuilder.like(root.get("client").get("name").as(String.class), "%" + clientName + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse save(Integer id, String no, Integer type, Integer payment, Integer clientId) {
        Outbound outbound = new Outbound();
        if(id!=null){
            outbound = outboundRepo.findOne(id);
        }else{
            outbound.setNum(0);
            outbound.setSumPrice(0.00);
        }
        outbound.setNo(no);
        outbound.setOutTime(new Date());
        outbound.setType(type);
        outbound.setPayment(payment);
        outbound=outboundRepo.save(outbound);

        return returnSave(outbound,true);
    }
}
