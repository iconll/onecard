package com.onecard.system.sys.controller;

import javax.inject.Inject;

import com.onecard.system.sys.entity.BankInfo;
import com.onecard.system.sys.entity.QueryBankInfo;
import com.onecard.system.sys.service.BankInfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.onecard.system.common.base.controller.GenericController;
import com.onecard.system.common.base.service.GenericService;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *@author gly
 **/
@Controller
@RequestMapping("/bankInfo")
public class BankInfoController extends GenericController<BankInfo, QueryBankInfo> {
	@Inject
	private BankInfoService bankInfoService;
	@Override
	protected GenericService<BankInfo, QueryBankInfo> getService() {
		return bankInfoService;
	}

	/**
	 * 获取所有网点信息
	 * @return
	 */
	@RequestMapping( value = "/findAll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<BankInfo> findAll(){

		return bankInfoService.findAllBankInfo();
	}
}