package com.onecard.system.suppermarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.Inbound;
import com.onecard.system.suppermarket.service.InboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inbound")
public class InboundController {

    @Autowired
    InboundService inboundService;

    @PostMapping("/addAll")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "入库-提交入库[details参数传json字符串]")
    public BaseResponse addAll(Inbound inbound, String details){
        return inboundService.addAll(inbound, JSONArray.parseArray(details));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "入库-提交入库[details参数传json字符串]")
    public BaseResponse save(Integer id,String no, String purchaseTime,Integer isSettle,Integer merchantId,Integer supplierId){
        return inboundService.save(id,no,purchaseTime,isSettle,supplierId);
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "入库-查询")
    public BaseResponse list(String startTime, String endTime, String no, Integer isSettle,
                             Integer merchantId, String supplierName, Integer pageNo, Integer pageSize){
        return inboundService.list(startTime, endTime, no, isSettle, supplierName, pageNo-1, pageSize);
    }

}
