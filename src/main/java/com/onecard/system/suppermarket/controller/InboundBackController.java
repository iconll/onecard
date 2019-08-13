package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.InboundBack;
import com.onecard.system.suppermarket.service.InboundBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inboundBack")
public class InboundBackController {

    @Autowired
    InboundBackService inboundBackService;

    @PostMapping("/back")
    @AComment(comment = "入库退货-退货")
    @PreAuthorize("retail")
    public BaseResponse back(Integer inboundDetailId, InboundBack back){
        return inboundBackService.back(inboundDetailId, back);
    }

    @PostMapping("/list")
    @AComment(comment = "入库退货-列表")
    @PreAuthorize("retail")
    public BaseResponse list(Integer pageNo, Integer pageSize, String goodsName){
        return inboundBackService.list(pageNo, pageSize, goodsName);
    }

    @PostMapping("/cancel")
    @AComment(comment = "入库退货-撤销")
    @PreAuthorize("retail")
    public BaseResponse cancel(Integer backId){
        return  inboundBackService.cancel(backId);
    }

}
