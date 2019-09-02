package com.onecard.system.supermarket.controller;

import com.onecard.system.supermarket.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/statistics")
    public Map statistics(String name, String code, String startTime, String endTime, Integer page, Integer limit) {
        Map<String, Object> result = new HashMap<>();
        Page goodsPage = reportService.Statistics(name, code,startTime,endTime, page, limit);
        result.put("totalCount", goodsPage.getTotalPages());
        result.put("result", goodsPage.getContent());
        return result;
    }
}
