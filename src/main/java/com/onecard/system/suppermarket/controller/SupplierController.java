package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.Supplier;
import com.onecard.system.suppermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "供应商-列表查询")
    public BaseResponse list(String name, Integer pageNo, Integer pageSize) {
        return supplierService.list(name,pageNo-1, pageSize);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "供应商-保存")
    public BaseResponse save(Supplier supplier, Integer userId){
        return supplierService.save(supplier,userId);
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "供应商-删除")
    public BaseResponse delete(Integer id){
        return supplierService.delete(id);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('retail')")
    @AComment(comment = "供应商-根据ID查询")
    public BaseResponse get(Integer id){
        return supplierService.query(id);
    }

}
