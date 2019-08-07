package com.onecard.system.sys.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 外部访问接口
 */
@Controller
@RequestMapping("/PassVisit")
public class PassVisit  {

    @RequestMapping(value = "getStr")
    @CrossOrigin("*")
    public void getStr(PrintWriter out,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String vuestr=request.getParameter("u");
        JSONObject json =new JSONObject();
        json.put("resultMsg","a");
        out = response.getWriter();
        out.write(json.toJSONString());
        out.flush();
        out.close();
    }


}
