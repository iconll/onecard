package com.onecard.system.suppermarket.vo;


import com.onecard.system.suppermarket.entity.Goods;

/**
 * 库存vo
 */
public class StockVo extends Goods {

    private Integer inNum;
    private Integer inBackNum;
    private Integer outNum;
    private Integer outBackNum;
    private Integer realNum;

    public Integer getInNum() {
        return inNum;
    }

    public void setInNum(Integer inNum) {
        this.inNum = inNum;
    }

    public Integer getInBackNum() {
        return inBackNum;
    }

    public void setInBackNum(Integer inBackNum) {
        this.inBackNum = inBackNum;
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Integer getOutBackNum() {
        return outBackNum;
    }

    public void setOutBackNum(Integer outBackNum) {
        this.outBackNum = outBackNum;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }
}
