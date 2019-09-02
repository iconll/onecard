package com.onecard.system.supermarket.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaying.framework.response.BaseJsonResponse;
import com.onecard.system.supermarket.entity.Goods;
import com.onecard.system.supermarket.entity.GoodsType;
import com.onecard.system.supermarket.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 功能描述：直接跳转到更新数据的页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatePage",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePage(Integer id, Model model) throws Exception{
        BaseJsonResponse response = (BaseJsonResponse)goodsTypeService.get(id);
        GoodsType goodsType = JSONObject.parseObject(response.getDataResult().toString(), GoodsType.class);
        model.addAttribute("entity",goodsType);
        response = (BaseJsonResponse)goodsTypeService.getAll();
        JSONArray ja = (JSONArray)response.getDataResult();
        model.addAttribute("goodsTypeList", ja);
        return "supermarket/goodstype/update";
    }

    /** 跳转到添加对象页面
     * @throws Exception
     */
    @RequestMapping(value="/addPage")
    public String addPage(Model model) throws Exception{
        BaseJsonResponse response = (BaseJsonResponse)goodsTypeService.getAll();
        JSONArray ja = (JSONArray)response.getDataResult();
        model.addAttribute("goodsTypeList", ja);
        return "supermarket/goodstype/add";
    }

}
