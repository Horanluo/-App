package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeAgentFee;
import com.alycloud.modules.search.QrcodeAgentFee4Search;

/**
 * 代理商二维码费率
 * @author Moyq5
 * @date 2017年3月28日
 */
public interface QrcodeAgentFeeMapper {

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param fees
	 */
	public void batchAdd(List<QrcodeAgentFee> fees);

	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param fee4s
	 * @return
	 */
	public List<QrcodeAgentFee> listByPage(QrcodeAgentFee4Search fee4s);

	/**
	 * 删除指定代理商的费率
	 * @author Moyq5
	 * @date 2017年10月29日
	 * @param agentno
	 */
	public void delByAgentno(String agentno);

}
