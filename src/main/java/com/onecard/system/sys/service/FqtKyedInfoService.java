package com.onecard.system.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.onecard.system.common.base.service.GenericService;
import com.onecard.system.common.base.dao.GenericDao;

import com.onecard.system.sys.entity.FqtKyedInfo;
import com.onecard.system.sys.entity.QueryFqtKyedInfo;
import com.onecard.system.sys.dao.FqtKyedInfoDao;

/**
 *@author gly
 **/
@Service("fqtKyedInfoService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class FqtKyedInfoService extends GenericService<FqtKyedInfo, QueryFqtKyedInfo> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private FqtKyedInfoDao fqtKyedInfoDao;
	@Override
	protected GenericDao<FqtKyedInfo, QueryFqtKyedInfo> getDao() {
		return fqtKyedInfoDao;
	}
}