package com.onecard.system.sys.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.onecard.system.common.base.controller.GenericController;
import com.onecard.system.common.base.service.GenericService;

import com.onecard.system.sys.entity.JzKyedInfo;
import com.onecard.system.sys.entity.QueryJzKyedInfo;
import com.onecard.system.sys.service.JzKyedInfoService;

/**
 *@author gly
 **/
@Controller
@RequestMapping("/jzKyedInfo")
public class JzKyedInfoController extends GenericController<JzKyedInfo, QueryJzKyedInfo> {
	@Inject
	private JzKyedInfoService jzKyedInfoService;
	@Override
	protected GenericService<JzKyedInfo, QueryJzKyedInfo> getService() {
		return jzKyedInfoService;
	}
}