package com.onecard.system.supermarket.controller;

import com.huaying.framework.annotation.AComment;
import com.onecard.system.supermarket.entity.Card;
import com.onecard.system.supermarket.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping("/list")
    @ResponseBody
    @AComment(comment = "卡-列表查询")
    public Map list(String cardNo,Integer type, Integer page, Integer limit) {
        Map<String,Object> result = new HashMap<>();
        Page Cardpage = cardService.findList(cardNo,type,page,limit);
        result.put("totalCount",Cardpage.getTotalPages());
        result.put("result",Cardpage.getContent());
        return result;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    @AComment(comment = "卡信息保存")
    public Map save(Integer id,Integer type,Integer stutes,String cardNo,String balance,String name){
        Map<String,Object> result = new HashMap<>();
        Card card = cardService.save(id,type,stutes,cardNo,balance,name);
        result.put("result",card);
        return result;
    }

    @RequestMapping(value = "/addPage")
    public String addPage(){
        return "supermarket/card/add";
    }

    @RequestMapping(value = "/updatePage")
    public String updatePage(Integer id, Model model) throws Exception {
        Card card = cardService.findById(id);
        model.addAttribute("entity", card);
        return "supermarket/card/update";
    }

    @RequestMapping(value = "/detailPage")
    public String detailPage(Integer id, Model model) throws Exception {
        Card card = cardService.findById(id);
        model.addAttribute("entity", card);
        return "supermarket/card/detail";
    }

}
