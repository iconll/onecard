package com.onecard.system.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.onecard.system.common.base.service.GenericService;
import com.onecard.system.common.base.dao.GenericDao;

import com.onecard.system.sys.entity.JzKyedInfo;
import com.onecard.system.sys.entity.QueryJzKyedInfo;
import com.onecard.system.sys.dao.JzKyedInfoDao;

/**
 *@author gly
 **/
@Service("jzKyedInfoService")
@Transactional(rollbackFor={Exception.class})
public class JzKyedInfoService extends GenericService<JzKyedInfo, QueryJzKyedInfo> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private JzKyedInfoDao jzKyedInfoDao;
	@Override
	protected GenericDao<JzKyedInfo, QueryJzKyedInfo> getDao() {
		return jzKyedInfoDao;
	}
}