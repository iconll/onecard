package com.onecard.system.common.config;

import com.onecard.system.common.config.date.DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 类描述：springMVC的配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("*","http://localhost:8001/")//Vue 项目的服务地址和端口号 可用*号代替
                .allowedOrigins("*")//Vue 项目的服务地址和端口号 可用*号代替
                .allowCredentials(true)
                .allowedMethods("GET", "POST")//, "DELETE", "PUT"
                .maxAge(3600);
    }

    //日期转化器配置
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

    /**
     * 重写方法描述：实现在url中输入相应的地址的时候直接跳转到某个地址
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/updatePwd").setViewName("updatePwd");
        // 跳转到菜单管理页面
        registry.addViewController("/treeList").setViewName("sys/tree/treeList");
        // 跳转到角色管理页面
        registry.addViewController("/userRoleList").setViewName("sys/role/roleList");
        // 组织架构页面
        registry.addViewController("/groupList").setViewName("sys/orggroup/groupList");
        // 管理管理页面
        registry.addViewController("/userList").setViewName("sys/user/userList");
        // 数据字典页面
        registry.addViewController("/dictList").setViewName("sys/dict/dictList");
        // 跳转到支行管理页面
        registry.addViewController("/bankInfoList").setViewName("sys/bankInfo/bankInfoList");

        // 跳转到管理分类
        registry.addViewController("/goodstype").setViewName("supermarket/goodstype/GoodsType");
        // 跳转到商品信息
        registry.addViewController("/goods").setViewName("supermarket/goods/Goods");
        // 跳转到入库管理
        registry.addViewController("/storageManagement").setViewName("supermarket/storageManagement/StorageManagement");
        // 跳转到退货管理
        registry.addViewController("/returnManagement").setViewName("supermarket/returnManagement/ReturnManagement");
        // 跳转到供应商管理
        registry.addViewController("/supplierManagement").setViewName("supermarket/supplierManagement/SupplierManagement");
        // 跳转到盘盈盘亏
        registry.addViewController("/profitAndLoss").setViewName("supermarket/profitAndLoss/ProfitAndLoss");

        //分期通
        //跳转到分期通基础数据管理页面
        registry.addViewController("/fqtBaseInfoList").setViewName("sys/fqtBaseInfo/fqtBaseInfoList");
        //跳转到分期通额度失效预警页面
        registry.addViewController("/fqtEdsxyjList").setViewName("sys/fqtBaseInfo/fqtEdsxyjList");
        //跳转到分期通基础数据统计页面
        registry.addViewController("/fqtStatisticsList").setViewName("sys/fqtBaseInfo/fqtStatisticsList");

    }
}
