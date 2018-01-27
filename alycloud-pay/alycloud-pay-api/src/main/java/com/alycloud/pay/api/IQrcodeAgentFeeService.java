package com.alycloud.pay.api;

import java.util.List;

import com.alycloud.modules.entity.QrcodeAgentFee;
import com.alycloud.modules.search.QrcodeAgentFee4Search;

/**
 * 代理商二维码费率
 * @author Moyq5
 * @date 2017年10月29日
 */
public interface IQrcodeAgentFeeService {

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param data
	 */
	void batchAdd(List<QrcodeAgentFee> data);

	/**
	 * 删除指定代理商的费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param agentno
	 */
	void delByAgentno(String agentno);

	/**
	 * 获取代理商二维码费率
	 * @author Moyq5
	 * @date 2017年11月7日
	 */
	List<QrcodeAgentFee> list(QrcodeAgentFee4Search data);

}
