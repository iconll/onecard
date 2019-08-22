package com.onecard.system.suppermarket.controller;

import com.onecard.system.suppermarket.entity.Card;
import com.onecard.system.suppermarket.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

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
