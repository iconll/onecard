package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.Outbound;
import com.onecard.system.suppermarket.service.OutboundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outbound")
public class OutboundController {

    @Autowired
    OutboundService outboundService;

    @PostMapping("/out")
    @AComment(comment = "入库-提交入库[details参数传json字符串]")
    public BaseResponse outbound(Outbound outbound, String details){
        return outboundService.outbound(outbound, details);
    }

    @PostMapping("/list")
    @AComment(comment = "入库-查询")
    public BaseResponse list(String startTime, String endTime, String no, Integer type,Integer payment,
                             String clientName, Integer pageNo, Integer pageSize){
        return outboundService.list(startTime, endTime, no, type, payment, clientName, pageNo-1, pageSize);
    }

    @PostMapping("/save")
    @AComment(comment = "出库-保存")
    public BaseResponse save(Integer id,String no,Integer type, Integer payment,Integer clientId,Integer merchantId){
        return outboundService.save(id,no,type,payment,clientId);
    }

}
