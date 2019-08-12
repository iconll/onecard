package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.suppermarket.entity.GoodsType;
import com.onecard.system.suppermarket.repo.GoodsTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品类型Service
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class GoodsTypeService extends BaseService{

    @Autowired
    GoodsTypeRepo goodsTypeRepo;

    /**
     * 保存商品类型
     * @param goodsType
     * @return
     */
    public BaseResponse save(GoodsType goodsType){
        if(goodsType.getId()==null){
            goodsType.setCreateTime(new Date());
        }else{
            GoodsType target = goodsTypeRepo.findOne(goodsType.getId());
            goodsType = (GoodsType) po2po(goodsType, target);
        }
        GoodsType gd = goodsTypeRepo.save(goodsType);
        return returnSave(gd, false);
    }

    /**
     * 根据类型名称和商家ID做分页查询
     * @param name
     * @param pageable
     * @return
     */
    public BaseResponse findByName(String name, Pageable pageable){
        Page page = null;
        if(StringUtils.isEmpty(name)){
            page = goodsTypeRepo.findAll(pageable);
        }else{
            page = goodsTypeRepo.findByName(name, pageable);
        }
        return returnList(page, false);
    }

    /**
     * 根据ID查询商品类型
     * @param id
     * @return
     */
    public BaseResponse get(Integer id){
        GoodsType goodsType = goodsTypeRepo.findOne(id);
        return returnGet(goodsType, false);
    }

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    public BaseResponse delete(Integer id){
        goodsTypeRepo.delete(id);
        return new CommonSuccessResponse();
    }

    public BaseResponse getTree(Integer id,Integer merchantId) {
        List<GoodsType> list = goodsTypeRepo.findAll(new Specification<GoodsType>() {
            @Override
            public Predicate toPredicate(Root<GoodsType> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (merchantId != null) {
                    list.add(criteriaBuilder.equal(root.get("merchant").get("id").as(Integer.class), merchantId));
                }

                if (id != null) {
                    list.add(criteriaBuilder.equal(root.get("goodsType").as(GoodsType.class), goodsTypeRepo.getOne(id)));
                }else{
                    list.add(criteriaBuilder.isNull(root.get("goodsType")));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        });
        return returnList(list, true);
    }
}
