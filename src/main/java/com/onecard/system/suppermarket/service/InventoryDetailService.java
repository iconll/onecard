package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonErrorResponse;
import com.kmut.retail.entity.Goods;
import com.kmut.retail.entity.Inventory;
import com.kmut.retail.entity.InventoryDetail;
import com.kmut.retail.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor=Exception.class)
public class InventoryDetailService extends BaseService {

    @Autowired
    InventoryDetailRepo inventoryDetailRepo;
    @Autowired
    MerchantUserRepo merchantUserRepo;
    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    InventoryRepo inventoryRepo;

    public BaseResponse findByInventoryId(Integer id, Integer pageNo, Integer pageSize){
        Page page = inventoryDetailRepo.findByInventoryId(id, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse save(Integer id, Integer goodsId, Integer num, Integer inventoryId, Integer merchantUserId) {
        InventoryDetail inventoryDetail = new InventoryDetail();
        Goods goods = goodsRepo.findOne(goodsId);
        Integer diff = goods.getNum() - num;
        Integer oldDiff = 0;
        if(id != null){
            inventoryDetail = inventoryDetailRepo.findOne(id);
            oldDiff = inventoryDetail.getDiff();
        }else{
            inventoryDetail.setMerchantUser(merchantUserRepo.findOne(merchantUserId));
        }
        Inventory inventory=inventoryRepo.findOne(inventoryId);
        inventory.setDiff(inventory.getDiff()+diff-oldDiff);
        if(inventory.getDiff() == 0){
            inventory.setStatus(0);
        }else{
            inventory.setStatus(1);
        }
        inventoryRepo.save(inventory);

        inventoryDetail.setGoods(goods);
        inventoryDetail.setDueNum(goods.getNum());
        inventoryDetail.setRealNum(num);
        inventoryDetail.setDiff(goods.getNum() - num);
        inventoryDetail.setInventory(inventoryRepo.findOne(inventoryId));
        inventoryDetail = inventoryDetailRepo.save(inventoryDetail);
        return returnSave(inventoryDetail,true);
    }

    public BaseResponse delete(Integer id, Integer merchantUserId) {
        InventoryDetail inventoryDetail = inventoryDetailRepo.findOne(id);
        if(inventoryDetail.getMerchantUser().getId().equals(merchantUserId)){
            inventoryDetailRepo.delete(id);
            Inventory inventory=inventoryRepo.findOne(inventoryDetail.getInventory().getId());
            inventory.setDiff(inventory.getDiff()-inventoryDetail.getDiff());
            if(inventory.getDiff() != 0){
                inventory.setStatus(1);
            }
            inventoryRepo.save(inventory);
            return returnGet(inventory,true);
        }else{
            return new CommonErrorResponse("只能编辑自己录入的信息");
        }

    }
}
