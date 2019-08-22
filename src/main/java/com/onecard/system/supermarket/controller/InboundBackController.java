package com.onecard.system.supermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.supermarket.entity.InboundBack;
import com.onecard.system.supermarket.service.InboundBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inboundBack")
public class InboundBackController {

    @Autowired
    InboundBackService inboundBackService;

    @PostMapping("/back")
    @AComment(comment = "入库退货-退货")
    public BaseResponse back(Integer inboundDetailId, InboundBack back){
        return inboundBackService.back(inboundDetailId, back);
    }

    @PostMapping("/list")
    @AComment(comment = "入库退货-列表")
    public BaseResponse list(Integer pageNo, Integer pageSize, String goodsName){
        return inboundBackService.list(pageNo, pageSize, goodsName);
    }

    @PostMapping("/cancel")
    @AComment(comment = "入库退货-撤销")
    public BaseResponse cancel(Integer backId){
        return  inboundBackService.cancel(backId);
    }

}
