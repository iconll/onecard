package com.onecard.system.supermarket.service;

import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.utils.PoToJson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor=Exception.class)
public class BaseService<T> {

    public Object po2po(Object src, Object target){
        BeanUtils.copyProperties(src,target,getNullProperties(src));
        return target;
    }

    /**
     * 将为空的properties给找出来,然后返回出来
     * @param src
     * @return
     */
    private static String[] getNullProperties(Object src){
        BeanWrapper srcBean=new BeanWrapperImpl(src);
        PropertyDescriptor[] pds=srcBean.getPropertyDescriptors();
        Set<String> emptyName=new HashSet<>();
        for(PropertyDescriptor p:pds){
            Object srcValue=srcBean.getPropertyValue(p.getName());
            if(srcValue==null) emptyName.add(p.getName());
        }
        String[] result=new String[emptyName.size()];
        return emptyName.toArray(result);
    }

    /**
     * list通用返回
     * @param page
     * @param isLazy 是否加载子类
     * @return
     */
    public BaseResponse returnList(Page page, boolean isLazy){
        BaseJsonResponse baseResponse = new BaseJsonResponse();
        baseResponse.setDataResult(PoToJson.getInstance().po2JsonList(page.getContent(), isLazy));
        baseResponse.setTotalPage(page.getTotalPages());
        baseResponse.setTotalRows(page.getTotalElements());
        return baseResponse;
    }

    public BaseResponse returnList(List list, boolean isLazy){
        BaseJsonResponse baseResponse = new BaseJsonResponse();
        baseResponse.setDataResult(PoToJson.getInstance().po2JsonList(list, isLazy));
        return baseResponse;
    }

    public BaseResponse returnSave(T t, boolean isLazy){
        return returnGet(t, isLazy);
    }

    /**
     * get通用返回
     * @param t
     * @param isLazy 是否加载子类
     * @return
     */
    public BaseResponse returnGet(T t, boolean isLazy){
        BaseJsonResponse response = new BaseJsonResponse();
        response.setDataResult(PoToJson.getInstance().po2Json(t, isLazy));
        return response;
    }

}
