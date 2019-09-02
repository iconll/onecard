package com.onecard.system.supermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.supermarket.entity.Inventory;
import com.onecard.system.supermarket.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/addALL")
    @AComment(comment = "盘点-保存[details参数传json字符串]")
    public BaseResponse addALL(Inventory inventory, String details){
        return inventoryService.addALL(inventory, details);
    }

    @PostMapping("/save")
    @AComment(comment = "盘点-保存[details参数传json字符串]")
    public BaseResponse save(Integer id,String no,Integer diff,Integer status,Integer state,String startTime,String schedule){
        return inventoryService.save(id, no,diff,status,state,startTime,schedule);
    }

    @PostMapping("/list")
    @AComment(comment = "盘点-查询")
    public BaseResponse list(String startTime, String endTime, Integer status, Integer state,String no,
                             Integer pageNo, Integer pageSize){
        return inventoryService.list(startTime, endTime, status, state,no, pageNo-1, pageSize);
    }

}
