package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.service.InboundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inboundDetail")
public class InboundDetailController {

    @Autowired
    InboundDetailService inboundDetailService;

    @GetMapping("/findByInboundId")
    @AComment(comment = "入库明细-根据入库ID查询明细")
    public BaseResponse findByInboundId(Integer inboundId, Integer pageNo, Integer pageSize){
        return inboundDetailService.findByInboundId(inboundId, pageNo-1, pageSize);
    }

    @PostMapping("/list")
    @AComment(comment = "产品入库明细-查询")
    public BaseResponse list(String startTime, String endTime,Integer goodsId,String goodsName,String goodsCode,String goodsType,Integer isSettle,
                             Integer inboundId,String supplierName, Integer pageNo, Integer pageSize){
        return inboundDetailService.list(startTime, endTime,goodsId,goodsName,goodsCode,goodsType,isSettle,inboundId, supplierName, pageNo-1, pageSize);
    }

    @PostMapping("/save")
    @AComment(comment = "产品入库明细-保存")
    public BaseResponse save(Integer id,Integer goodsId,Integer num,Double price,Integer inboundId,Integer merchantUserId){
        return inboundDetailService.save(id, goodsId,num,price,inboundId,merchantUserId);
    }

    @PostMapping("/delete")
    @AComment(comment = "产品入库明细-删除")
    public BaseResponse save(Integer id,Integer merchantUserId){
        return inboundDetailService.delete(id, merchantUserId);
    }

    @PostMapping("/getByGoods")
    @AComment(comment = "产品入库统计")
    public BaseResponse getByGoods(String startTime, String endTime, String goodsName,String goodsCode,String goodsType,
                             Integer merchantId, Integer pageNo, Integer pageSize){
        return inboundDetailService.findByGroupGoods(startTime, endTime, goodsName,goodsCode,goodsType,pageNo-1, pageSize);
    }
}
