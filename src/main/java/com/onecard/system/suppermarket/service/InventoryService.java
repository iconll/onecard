package com.onecard.system.suppermarket.service;

import com.alibaba.fastjson.JSONArray;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.DateUtil;
import com.huaying.framework.utils.StringUtils;
import com.kmut.retail.entity.*;
import com.kmut.retail.repo.GoodsRepo;
import com.kmut.retail.repo.InventoryDetailRepo;
import com.kmut.retail.repo.InventoryRepo;
import com.kmut.retail.repo.MerchantRepo;
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
public class InventoryService extends BaseService {

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    InventoryDetailRepo inventoryDetailRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @Autowired
    MerchantRepo merchantRepo;

    /**
     * 盘点
     * @return
     */
    public BaseResponse addALL(Inventory inventory, String details){
        List<InventoryDetail> list = JSONArray.parseArray(details).toJavaList(InventoryDetail.class);
        int diff = 0;
        //算算总差值
        for (InventoryDetail d : list) {
            diff += d.getDiff();
        }

        //保存盘点信息
        if(inventory.getId()==null){
            inventory.setCreateTime(new Date());
            //单号=PD+日期+4位商品总数（不足补零）+ 4位随机数
            int rand = (int)((Math.random()*9+1)*1000);
            String no = "PD" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + String.format("%04d", diff) + rand;
            inventory.setNo(no);
        }else{
            inventory.setUpdateTime(new Date());
            //移除明细
            List<InventoryDetail> delList = inventoryDetailRepo.findByInventoryId(inventory.getId());
            inventoryDetailRepo.delete(delList);
            Inventory target = inventoryRepo.findOne(inventory.getId());
            inventory = (Inventory) po2po(inventory, target);
        }
        inventory.setDiff(diff);
        inventoryRepo.save(inventory);

        //将出库单ID放入明细list并算出明细总价
        List<InventoryDetail> outList = new ArrayList<>();
        for (InventoryDetail d:list) {
            Goods goods = goodsRepo.findOne(d.getGoods().getId());
            d.setGoods(goods);
            d.setInventory(inventory);
            outList.add(d);

            //更新商品库存并计算出商品总价值
            goods.setNum(goods.getNum()+d.getDiff());
            goods.setSumPrice(goods.getPrice()*goods.getNum());
            goodsRepo.save(goods);
        }

        //再保存出库明细
        inventoryDetailRepo.save(outList);

        return new CommonSuccessResponse();
    }

    /**
     * 列表查询
     * @param startTime
     * @param endTime
     * @param status
     * @param state
     * @param merchantId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResponse list(String startTime, String endTime, Integer status, Integer state,String no, Integer merchantId, Integer pageNo, Integer pageSize){
        Page<Inventory> page = inventoryRepo.findAll(new Specification<Inventory>() {
            @Override
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(startTime)) {
                    list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), DateUtil.parseDate(startTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (StringUtils.isNotBlank(endTime)) {
                    list.add(criteriaBuilder.lessThanOrEqualTo(root.get("updateTime").as(Date.class), DateUtil.parseDate(endTime, "yyyy-MM-dd HH:mm:ss")));
                }

                if (merchantId != null) {
                    list.add(criteriaBuilder.equal(root.get("merchant").get("id").as(Integer.class), merchantId));
                }

                if (status != null) {
                    list.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
                }

                if (state != null) {
                    list.add(criteriaBuilder.equal(root.get("state").as(Integer.class), state));
                }

                if (StringUtils.isNotBlank(no)) {
                    list.add(criteriaBuilder.like(root.get("no").as(String.class), '%'+no+'%'));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse save(Integer id, String no, Integer diff, Integer status, Integer state, String startTime, String schedule, Integer merchantId) {
        Inventory inventory=new Inventory();
        if(id!=null){
            inventory=inventoryRepo.findOne(id);
        }else{
            inventory.setCreateTime(new Date());
            inventory.setMerchant(merchantRepo.findOne(merchantId));
        }
        inventory.setNo(no);
        if(diff!=null){
            inventory.setDiff(diff);
        }else{
            inventory.setDiff(0);
        }
        if(status!=null){
            inventory.setStatus(status);
        }else{
            inventory.setStatus(0);
        }
        inventory.setState(state);
        inventory.setStartTime(DateUtil.parseDate(startTime, "yyyy-MM-dd"));
        inventory.setSchedule(schedule);
        inventory.setUpdateTime(new Date());
        inventory=inventoryRepo.save(inventory);

        return returnSave(inventory,true);
    }
}
