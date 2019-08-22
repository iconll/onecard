package com.onecard.system.suppermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.suppermarket.entity.Goods;
import com.onecard.system.suppermarket.entity.Inbound;
import com.onecard.system.suppermarket.entity.InboundBack;
import com.onecard.system.suppermarket.entity.InboundDetail;
import com.onecard.system.suppermarket.repo.GoodsRepo;
import com.onecard.system.suppermarket.repo.InboundBackRepo;
import com.onecard.system.suppermarket.repo.InboundDetailRepo;
import com.onecard.system.suppermarket.repo.InboundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class InboundBackService extends BaseService {

    @Autowired
    InboundBackRepo inboundBackRepo;

    @Autowired
    InboundRepo inboundRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @Autowired
    InboundDetailRepo inboundDetailRepo;

    /**
     * 退库
     * @param inboundDetailId
     * @param back
     * @return
     */
    public BaseResponse back(Integer inboundDetailId, InboundBack back){
        //更新库存明细，入库单，商品的数量以及总价
        InboundDetail inboundDetail=inboundDetailRepo.findOne(inboundDetailId);
        Inbound inbound=inboundRepo.findOne(inboundDetail.getInbound().getId());
        Goods goods=goodsRepo.getOne(inboundDetail.getGoods().getId());
        if(inboundDetail.getNum()-back.getNum()==0){
            inboundDetailRepo.delete(inboundDetail);
        }else{
            inboundDetail.setNum(inboundDetail.getNum()-back.getNum());
            inboundDetail.setSumPrice(inboundDetail.getSumPrice()-back.getSumPrice());
            inboundDetailRepo.save(inboundDetail);
        }
        goods.setNum(goods.getNum()-inboundDetail.getNum());
        goods.setSumPrice(goods.getSumPrice()-inboundDetail.getSumPrice());
        goodsRepo.save(goods);
        inbound.setNum(inbound.getNum()-inboundDetail.getNum());
        inbound.setSumPrice(inbound.getSumPrice()-inboundDetail.getSumPrice());
        inboundRepo.save(inbound);

        //记录
        back.setInbound(inbound);
        inboundBackRepo.save(back);

        return returnGet(inbound,true);
    }

    /**
     * 退库明细
     * @param pageNo
     * @param pageSize
     * @param goodsName
     * @return
     */
    public BaseResponse list(Integer pageNo, Integer pageSize, String goodsName){
        Page page = null;
        if(StringUtils.isNotEmpty(goodsName)){
            page = inboundBackRepo.findByGoodsName(goodsName, new PageRequest(pageNo, pageSize));
        }else{
            page = inboundBackRepo.findAll(new PageRequest(pageNo, pageSize));
        }
        return returnList(page, true);
    }

    /**
     * 撤销退库
     * @param backId
     * @return
     */
    public BaseResponse cancel(Integer backId){
        InboundBack back = inboundBackRepo.findOne(backId);

        //更新库存明细，入库单，商品的数量以及总价
        InboundDetail inboundDetail=inboundDetailRepo.findOne(back.getInboundDetailId());
        if(inboundDetail==null){
            inboundDetail = new InboundDetail();
            inboundDetail.setGoods(back.getGoods());
            inboundDetail.setUser(back.getUser());
            inboundDetail.setInbound(back.getInbound());
            inboundDetail.setNum(back.getNum());
            inboundDetail.setSumPrice(back.getSumPrice());
        }else{
            inboundDetail.setNum(inboundDetail.getNum()+back.getNum());
            inboundDetail.setSumPrice(inboundDetail.getSumPrice()+back.getSumPrice());
        }
        inboundDetailRepo.save(inboundDetail);

        Inbound inbound=inboundRepo.findOne(inboundDetail.getInbound().getId());
        Goods goods=goodsRepo.getOne(inboundDetail.getGoods().getId());
        goods.setNum(goods.getNum()+back.getNum());
        goods.setSumPrice(goods.getSumPrice()+back.getSumPrice());
        goodsRepo.save(goods);
        inbound.setNum(inbound.getNum()+back.getNum());
        inbound.setSumPrice(inbound.getSumPrice()+back.getSumPrice());
        inboundRepo.save(inbound);
        inboundBackRepo.delete(back);
        return new CommonSuccessResponse();
    }

}
