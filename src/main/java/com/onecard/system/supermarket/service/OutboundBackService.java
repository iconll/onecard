package com.onecard.system.supermarket.service;

import com.huaying.framework.response.BaseResponse;
import com.huaying.framework.response.CommonSuccessResponse;
import com.huaying.framework.utils.StringUtils;
import com.onecard.system.supermarket.entity.Goods;
import com.onecard.system.supermarket.entity.Outbound;
import com.onecard.system.supermarket.entity.OutboundBack;
import com.onecard.system.supermarket.entity.OutboundDetail;
import com.onecard.system.supermarket.repo.GoodsRepo;
import com.onecard.system.supermarket.repo.OutboundBackRepo;
import com.onecard.system.supermarket.repo.OutboundDetailRepo;
import com.onecard.system.supermarket.repo.OutboundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class OutboundBackService extends BaseService {

    @Autowired
    OutboundBackRepo outboundBackRepo;

    @Autowired
    OutboundRepo outboundRepo;

    @Autowired
    GoodsRepo goodsRepo;

    @Autowired
    OutboundDetailRepo outboundDetailRepo;

    /**
     * 退库
     * @param outboundDetailId
     * @param back
     * @return
     */
    public BaseResponse back(Integer outboundDetailId, OutboundBack back){
        //更新库存明细，入库单，商品的数量以及总价
        OutboundDetail outboundDetail=outboundDetailRepo.findOne(outboundDetailId);
        Outbound outbound=outboundRepo.findOne(outboundDetail.getOutbound().getId());
        Goods goods=goodsRepo.getOne(outboundDetail.getGoods().getId());
        if(outboundDetail.getNum()-back.getNum()==0){
            outboundDetailRepo.delete(outboundDetail);
        }else{
            outboundDetail.setNum(outboundDetail.getNum()-back.getNum());
            outboundDetail.setSumPrice(outboundDetail.getSumPrice()-back.getSumPrice());
            outboundDetailRepo.save(outboundDetail);
        }
        goods.setNum(goods.getNum()-outboundDetail.getNum());
        goods.setSumPrice(goods.getSumPrice()-outboundDetail.getSumPrice());
        goodsRepo.save(goods);
        outbound.setNum(outbound.getNum()-outboundDetail.getNum());
        outbound.setSumPrice(outbound.getSumPrice()-outboundDetail.getSumPrice());
        outboundRepo.save(outbound);

        //记录
        back.setOutbound(outbound);
        outboundBackRepo.save(back);
        return returnGet(outbound,true);
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
            page = outboundBackRepo.findByGoodsName(new PageRequest(pageNo, pageSize), goodsName);
        }else{
            page = outboundBackRepo.findAll(new PageRequest(pageNo, pageSize));
        }
        return returnList(page, true);
    }

    /**
     * 撤销退库
     * @param backId
     * @return
     */
    public BaseResponse cancel(Integer backId){
        OutboundBack back = outboundBackRepo.findOne(backId);

        //更新库存明细，入库单，商品的数量以及总价
        OutboundDetail outboundDetail=outboundDetailRepo.findOne(back.getOutboundDetailId());
        if(outboundDetail==null){
            outboundDetail = new OutboundDetail();
            outboundDetail.setGoods(back.getGoods());
            outboundDetail.setUser(back.getUser());
            outboundDetail.setOutbound(back.getOutbound());
            outboundDetail.setNum(back.getNum());
            outboundDetail.setSumPrice(back.getSumPrice());
        } else {
            outboundDetail.setNum(outboundDetail.getNum()+back.getNum());
            outboundDetail.setSumPrice(outboundDetail.getSumPrice()+back.getSumPrice());
        }
        outboundDetailRepo.save(outboundDetail);

        Outbound outbound=outboundRepo.findOne(outboundDetail.getOutbound().getId());
        Goods goods=goodsRepo.getOne(outboundDetail.getGoods().getId());
        goods.setNum(goods.getNum()+back.getNum());
        goods.setSumPrice(goods.getSumPrice()+back.getSumPrice());
        goodsRepo.save(goods);
        outbound.setNum(outbound.getNum()+back.getNum());
        outbound.setSumPrice(outbound.getSumPrice()+back.getSumPrice());
        outboundRepo.save(outbound);
        outboundBackRepo.delete(back);
        return new CommonSuccessResponse();
    }

}
