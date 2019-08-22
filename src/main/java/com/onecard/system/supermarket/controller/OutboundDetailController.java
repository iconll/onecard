package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.service.OutboundDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/outboundDetail")
public class OutboundDetailController {

    @Autowired
    OutboundDetailService outboundDetailService;

    @GetMapping("/findByOutboundId")
    @AComment(comment = "出库明细-根据出库ID查询明细")
    public BaseResponse findByOutboundId(Integer outboundId, Integer pageNo, Integer pageSize){
        return outboundDetailService.findByOutboundId(outboundId, pageNo-1, pageSize);
    }

    @PostMapping("/list")
    @AComment(comment = "产品出库明细-查询")
    public BaseResponse list(String startTime, String endTime,Integer goodsId,String goodsName,String goodsCode,String goodsType,Integer payment,
                             Integer outboundId,Integer type,Integer pageNo, Integer pageSize){
        return outboundDetailService.list(startTime, endTime,goodsId,goodsName,goodsCode,goodsType,payment,outboundId,type, pageNo-1, pageSize);
    }

    @PostMapping("/save")
    @AComment(comment = "产品出库明细-保存")
    public BaseResponse save(Integer id,Integer goodsId,Integer num,Double price,Integer outboundId,Integer merchantUserId){
        return outboundDetailService.save(id, goodsId,num,price,outboundId,merchantUserId);
    }

    @PostMapping("/delete")
    @AComment(comment = "产品出库明细-删除")
    public BaseResponse save(Integer id){
        return outboundDetailService.delete(id);
    }

    @PostMapping("/getByGoods")
    @AComment(comment = "产品出库统计")
    public BaseResponse getByGoods(String startTime, String endTime, String goodsName,String goodsCode,String goodsType,
                                   Integer pageNo, Integer pageSize){
        return outboundDetailService.findByGroupGoods(startTime, endTime, goodsName,goodsCode,goodsType, pageNo-1, pageSize);
    }

}
