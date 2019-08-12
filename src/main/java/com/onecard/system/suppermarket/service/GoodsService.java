package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseJsonResponse;
import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonErrorResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.suppermarket.entity.Goods;
import com.onecard.system.suppermarket.repo.GoodsRepo;
import com.onecard.system.suppermarket.repo.GoodsTypeRepo;
import com.onecard.system.suppermarket.repo.UserRepo;
import com.onecard.system.suppermarket.vo.StockVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@Service
@Transactional(rollbackFor=Exception.class)
public class GoodsService extends BaseService{

    @Autowired
    GoodsRepo goodsRepo;
    @Autowired
    GoodsTypeRepo goodsTypeRepo;
    @Autowired
    UserRepo userRepo;

    /**
     * 保存商品
     * @param goods
     * @return
     */
    public BaseResponse save(Goods goods, Integer goodsTypeId, Integer userId){
        if(goods.getId()==null){
            goods.setCreateTime(new Date());
            goods.setUser(userRepo.getOne(userId));
            goods.setGoodsType(goodsTypeRepo.getOne(goodsTypeId));
            goods.setNum(0);
            goods.setSumPrice(0.00);
            //检查Code是否重复
            List<Goods> list = goodsRepo.queryByCode(goods.getCode());
            if(list!=null && list.size()>0){
                return new CommonErrorResponse("商品编码重复，不允许保存！");
            }
        }else{
            Goods goodsTarget = goodsRepo.findOne(goods.getId());
            goods.setGoodsType(goodsTypeRepo.getOne(goodsTypeId));
            goods = (Goods) po2po(goods, goodsTarget);
        }
        Goods g = goodsRepo.save(goods);
        return returnSave(g, false);
    }

    /**
     * 根据ID查询商品
     * @param id
     * @return
     */
    public BaseResponse get(Integer id){
        Goods goods = goodsRepo.findOne(id);
        return returnGet(goods, true);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    public BaseResponse delete(Integer id){
        goodsRepo.delete(id);
        return new CommonSuccessResponse();
    }

    /**
     * 商品列表查询
     * @param name
     * @param code
     * @param goodsTypeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public BaseResponse list(String name, String code, Integer goodsTypeId, Integer pageNo, Integer pageSize){
        Page<Goods> page = goodsRepo.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                if (StringUtils.isNotBlank(name)) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }

                if (StringUtils.isNotBlank(code)) {
                    list.add(criteriaBuilder.like(root.get("code").as(String.class), "%" + code + "%"));
                }

                if (goodsTypeId != null) {
                    list.add(criteriaBuilder.equal(root.get("goodsType").get("id").as(Integer.class), goodsTypeId));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, new PageRequest(pageNo, pageSize));
        return returnList(page, true);
    }

    public BaseResponse queryByCode(String code){
        List<Goods> list = goodsRepo.queryByCode(code);
        if(list!=null && list.size()>0){
            return returnGet(list.get(0), true);
        }else{
            return new CommonErrorResponse("商品编码错误，查无此商品！");
        }
    }

    /**
     * 库存查询
     * @param name
     * @param code
     * @param pageable
     * @return
     */
    public BaseResponse getStock(String name, String code, Pageable pageable){
        Page page = null;
        if((StringUtils.isNotEmpty(code)) || (StringUtils.isNotEmpty(code) && StringUtils.isNotEmpty(name))){
            page = goodsRepo.getStockByCode(code, pageable);
        }else if(StringUtils.isEmpty(code) && StringUtils.isEmpty(name)){
            page = goodsRepo.getStock(pageable);
        }else if(StringUtils.isNotEmpty(name)){
            page = goodsRepo.getStockByName(name, pageable);
        }else{
            return new CommonErrorResponse("参数异常，请联系程序员小哥哥！");
        }

        StockVo vo = new StockVo();
        for (Object object : page.getContent()) {
            Object[] obj = (Object[]) object;
            vo.setId(Integer.parseInt(obj[0].toString()));
            vo.setName(obj[1].toString());
            vo.setCode(obj[2].toString());
            vo.setUnit(obj[3].toString());
            vo.setSpecs(obj[4].toString());
            vo.setInNum(Integer.parseInt(obj[5].toString()));
            vo.setInBackNum(Integer.parseInt(obj[6].toString()));
            vo.setOutNum(Integer.parseInt(obj[7].toString()));
            vo.setOutBackNum(Integer.parseInt(obj[8].toString()));
            vo.setRealNum(Integer.parseInt(obj[9].toString()));
        }

        BaseJsonResponse baseResponse = new BaseJsonResponse();
        baseResponse.setDataResult(vo);
        baseResponse.setTotalPage(page.getTotalPages());
        baseResponse.setTotalRows(page.getTotalElements());

        return baseResponse;
    }

}
