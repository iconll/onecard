package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.kmut.retail.entity.InboundBack;
import com.kmut.retail.service.InboundBackService;
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
    public BaseResponse list(Integer pageNo, Integer pageSize, String goodsName,Integer inboundId,Integer merchantId){
        return inboundBackService.list(pageNo, pageSize, goodsName,inboundId,merchantId);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "产品入库退货明细-保存")
    public BaseResponse save(Integer id,Integer goodsId,Integer num,Double price,Integer inboundId,Integer merchantUserId){
        return inboundBackService.save(id, goodsId,num,price,inboundId,merchantUserId);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "产品入库退货明细-删除")
    public BaseResponse save(Integer id,Integer merchantUserId){
        return inboundBackService.delete(id, merchantUserId);
    }


    @PostMapping("/cancel")
    @AComment(comment = "入库退货-撤销")
    @PreAuthorize("retail")
    public BaseResponse cancel(Integer backId){
        return  inboundBackService.cancel(backId);
    }

}
