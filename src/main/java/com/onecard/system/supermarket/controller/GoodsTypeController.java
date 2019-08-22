package com.onecard.system.suppermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.onecard.system.suppermarket.entity.GoodsType;
import com.onecard.system.suppermarket.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;

    @PostMapping("/list")
    @AComment(comment = "商品类型-列表查询")
    @ResponseBody
    public Map list(String name, Integer page, Integer limit) {
        Map<String,Object> result = new HashMap<>();
        BaseJsonResponse response = (BaseJsonResponse)goodsTypeService.findByName(name, new PageRequest(page-1, limit));
        Page pg = (Page) response.getDataResult();
        result.put("totalCount",pg.getTotalPages());
        result.put("result",pg.getContent());
        return result;
    }

    @PostMapping("/save")
    @AComment(comment = "商品类型-保存")
    public BaseResponse save(GoodsType goodsType){
        return goodsTypeService.save(goodsType);
    }

    @GetMapping("/delete")
    @AComment(comment = "商品类型-删除")
    public BaseResponse delete(Integer id){
        return goodsTypeService.delete(id);
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

    /**
     * 功能描述：直接跳转到更新数据的页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatePage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePage(Integer id, Model model) throws Exception{
        BaseJsonResponse response = (BaseJsonResponse)goodsTypeService.get(id);
        GoodsType goodsType = (GoodsType)response.getDataResult();
        model.addAttribute("entity",goodsType);
        return "supermarket/goodstype/update";
    }

    /** 跳转到添加对象页面
     * @throws Exception
     */
    @RequestMapping(value="/addPage")
    public String addPage() throws Exception{
        return "supermarket/goodstype/add";
    }

}
