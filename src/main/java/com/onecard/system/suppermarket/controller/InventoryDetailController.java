package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.service.InventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventoryDetail")
public class InventoryDetailController {

    @Autowired
    InventoryDetailService inventoryDetailService;

    @GetMapping("/findByInventoryId")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "盘点-根据盘点ID查询明细")
    public BaseResponse findByInventoryId(Integer inventoryId, Integer pageNo, Integer pageSize){
        return inventoryDetailService.findByInventoryId(inventoryId, pageNo-1, pageSize);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "盘点明细-保存")
    public BaseResponse save(Integer id,Integer goodsId,Integer num,Integer inventoryId,Integer merchantUserId){
        return inventoryDetailService.save(id, goodsId,num,inventoryId,merchantUserId);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "盘点明细-删除")
    public BaseResponse save(Integer id,Integer merchantUserId){
        return inventoryDetailService.delete(id, merchantUserId);
    }
}
