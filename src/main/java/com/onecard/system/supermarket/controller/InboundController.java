package com.onecard.system.supermarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.supermarket.entity.Inbound;
import com.onecard.system.supermarket.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inbound")
public class InboundController {

    @Autowired
    InboundService inboundService;

    @PostMapping("/addAll")
    @AComment(comment = "入库-提交入库[details参数传json字符串]")
    public BaseResponse addAll(Inbound inbound, String details){
        return inboundService.addAll(inbound, JSONArray.parseArray(details));
    }

    @PostMapping("/save")
    @AComment(comment = "入库-提交入库[details参数传json字符串]")
    public BaseResponse save(Integer id,String no, String purchaseTime,Integer isSettle,Integer merchantId,Integer supplierId){
        return inboundService.save(id,no,purchaseTime,isSettle,supplierId);
    }

    @PostMapping("/list")
    @AComment(comment = "入库-查询")
    public BaseResponse list(String startTime, String endTime, String no, Integer isSettle,
                             Integer merchantId, String supplierName, Integer pageNo, Integer pageSize){
        return inboundService.list(startTime, endTime, no, isSettle, supplierName, pageNo-1, pageSize);
    }

}
