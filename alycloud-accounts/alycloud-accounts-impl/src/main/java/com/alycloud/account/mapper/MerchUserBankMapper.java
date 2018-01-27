package com.alycloud.account.mapper;

import java.util.List;
import java.util.Map;
import com.alycloud.modules.entity.MerchUserBank;
import com.alycloud.modules.search.MerchUserBank4Search;


/**
 * 商户银行列表的操作类
 * @author Moyq5
 * @date 2017年10月23日
 */
public interface MerchUserBankMapper {

	public List<MerchUserBank> listByPage(MerchUserBank4Search bank4s);

	/**
     * 
     * 查询账户绑卡列表
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月31日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	List<MerchUserBank> queryUserBankList(Map<String,String> userBankParams);
	
	/**
     * 
     * 绑定银行卡
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int addMerchUserBank(MerchUserBank mubb);
	
	/**
     * 
     * 编辑银行卡
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int modifyMerchUserBank(Map<String,String> params);
}
