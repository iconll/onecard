package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.OutboundBack;
import com.onecard.system.suppermarket.service.OutboundBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outboundBack")
public class OutboundBackController {

    @Autowired
    OutboundBackService outboundBackService;

    @PostMapping("/back")
    @AComment(comment = "出库退货-退货")
    public BaseResponse back(Integer outboundDetailId, OutboundBack back){
        return outboundBackService.back(outboundDetailId, back);
    }

    @PostMapping("/list")
    @AComment(comment = "出库退货-列表")
    public BaseResponse list(Integer pageNo, Integer pageSize, String goodsName){
        return outboundBackService.list(pageNo, pageSize, goodsName);
    }


    @PostMapping("/cancel")
    @AComment(comment = "出库退货-撤销")
    public BaseResponse cancel(Integer backId){
        return  outboundBackService.cancel(backId);
    }

}
