package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.GoodsType;
import com.onecard.system.suppermarket.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "商品类型-列表查询")
    public BaseResponse list(String name, Integer pageNo, Integer pageSize) {
        return goodsTypeService.findByName(name, new PageRequest(pageNo-1, pageSize));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "商品类型-保存")
    public BaseResponse save(GoodsType goodsType){
        return goodsTypeService.save(goodsType);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "商品类型-删除")
    public BaseResponse delete(Integer id){
        return goodsTypeService.delete(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "商品类型-根据ID查询")
    public BaseResponse get(Integer id){
        return goodsTypeService.get(id);
    }

    @PostMapping("/getTree")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "商品类型-根据ID获取树形数据")
    public BaseResponse getTree(Integer id){
        return goodsTypeService.getTree(id);
    }

}
