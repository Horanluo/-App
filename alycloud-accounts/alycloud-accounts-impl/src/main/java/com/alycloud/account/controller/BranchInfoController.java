package com.alycloud.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alycloud.account.mapper.BranchInfoMapper;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.modules.entity.BranchInfo;

/**
 * 机构信息
 * @author Moyq5
 * @date 2017年10月18日
 */
@RequestMapping(value="/branchInfo")
@RestController
public class BranchInfoController {

	@Autowired
	private BranchInfoMapper branchInfoMapper;
	
	/**
	 * 根据机构号获取机构信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@RequestMapping(value="/getByBranchno")
	public ResultBean<BranchInfo> getByBranchno(@RequestBody RequestBean<String> reqData) throws Exception{
		BranchInfo branch = branchInfoMapper.getByBranchno(reqData.getData());
        return RestBeanGenerator.genSuccessResult(branch);
	}
	
}
