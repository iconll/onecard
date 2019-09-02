package com.onecard.system.supermarket.controller.apis;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.supermarket.form.GoodsTypeForm;
import com.onecard.system.supermarket.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apis/goodsType")
public class GoodsTypeApi {

    @Autowired
    GoodsTypeService goodsTypeService;

    @PostMapping("/list")
    @AComment(comment = "商品类型-列表查询")
    public Map list(String name, Integer page, Integer limit) {
        Map<String,Object> result = new HashMap<>();
        BaseJsonResponse response = (BaseJsonResponse)goodsTypeService.findByName(name, new PageRequest(page-1, limit));
        result.put("totalCount",response.getTotalRows());
        result.put("result",response.getDataResult());
        return result;
    }

    @PostMapping("/save")
    @AComment(comment = "商品类型-保存")
    public BaseResponse save(GoodsTypeForm form){
        return goodsTypeService.save(form);
    }

    @PostMapping("/delete")
    @AComment(comment = "商品类型-删除")
    public BaseResponse delete(String json){
        return goodsTypeService.delete(json);
    }

    @GetMapping("/get")
    @AComment(comment = "商品类型-根据ID查询")
    public BaseResponse get(Integer id){
        return goodsTypeService.get(id);
    }

    @PostMapping("/getTree")
    @AComment(comment = "商品类型-根据ID获取树形数据")
    public BaseResponse getTree(Integer id){
        return goodsTypeService.getTree(id);
    }

}
