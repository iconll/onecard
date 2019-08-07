package com.onecard.system.sys.dao;

import com.onecard.system.common.base.dao.GenericDao;

import com.onecard.system.sys.entity.FqtKyedInfo;
import com.onecard.system.sys.entity.QueryFqtKyedInfo;

/**
 *@author gly
 **/
public interface FqtKyedInfoDao extends GenericDao<FqtKyedInfo, QueryFqtKyedInfo> {

    /**
     * 根据基础数据id查询最后一次导入的剩余可用额度
     * @param fqtBaseInfoId 分期通基础数据ID
     * @return 最后一次导入的剩余可用额度
     */
    String findLastSurplusMoneyByFqtBaseInfoId(int fqtBaseInfoId);
}