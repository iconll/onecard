package com.onecard.system.sys.dao;

import com.onecard.system.common.base.dao.GenericDao;

import com.onecard.system.sys.entity.BankInfo;
import com.onecard.system.sys.entity.QueryBankInfo;

import java.util.List;

/**
 *@author gly
 **/
public interface BankInfoDao extends GenericDao<BankInfo, QueryBankInfo> {
    /**
     * 加载所有网点信息
     * @return List<BankInfo>
     */
    List<BankInfo> findAllBankInfo();
    /**
     * 根据网点代码查询对应的网点信息
     * @param bankCode 网点代码
     * @return 网点信息
     */
    BankInfo getBankInfoByBankCode(String bankCode);
}