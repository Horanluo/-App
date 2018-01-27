package com.alycloud.account.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.AgentVirtualCard;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchRelate;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.MerchVirtualCard;
import com.alycloud.modules.entity.QrcodeMerchFee;
import com.alycloud.modules.entity.ReceiveDetailInfo;
import com.alycloud.modules.entity.WithdrawalDetailInfo;
import com.alycloud.modules.search.MerchInfo4Search;


/**
 * 商户相关的操作类
 * @author Moyq5
 * @date 2017年5月23日
 */
public interface MerchInfoMapper {
	
	public int mod(MerchInfo merch);
	
	public void add(MerchInfo merch, MerchUser user, List<MerchRelate> relates, List<QrcodeMerchFee> qrcodeFees);

	public List<MerchInfo> listByPage(MerchInfo4Search merchInfo4s);

	public MerchInfo getByMerchno(String merchno);

	public void modAccount(MerchInfo merch, List<MerchVirtualCard> merchCards, AgentInfo agent,
			AgentVirtualCard agentCard);


	public MerchInfo getById(Integer id);

	/**
     * 
     * 根
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月25日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	MerchInfo getByLoginName(Map<String,String> userParams);
    
    /**
     * 
     * 新增商户信息
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	int addMerchInfo(MerchInfo merch);
	
	/**
     * 
     * 查询商户累计收款
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	BigDecimal getSuccTranAmt(Map<String,String> tranParams);
	
	/**
     * 
     * 查询商户可提现金额
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月20日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	BigDecimal getAvailAmt(Map<String,String> tranParams);
	
	/**
	 * 查询商户收款明细
	 * @param loginName
	 * @param merchno
	 * @return
	 */
	List<ReceiveDetailInfo> getTranDetail(Map<String,String> tranParams);
	
	/**
	 * 查询商户提现明细
	 * @param tranParams
	 * @return
	 */
	List<WithdrawalDetailInfo> getWithdrawalDetail(Map<String,String> tranParams);
	
	/**
	 * 获取状态下的商户信息
	 * @param username
	 * @param status
	 * @return
	 */
	List<MerchInfo> getMerchInfoByStatus(Map<String,String> merchParams);
	
	/**
	 * 修改商户信息
	 * @param merch
	 * @return
	 */
	int updateMerchInfo(MerchInfo merch);

	/**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月26日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    String getBankByCardno(String cardno);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    String getBankIDByBankName(String bankType);
    
    /**
	 * 修改商户费率
	 * @param merchno
	 * @param settleType  费率类型 d0  d1
	 * @param modifyRate 修改后的费率
	 * @return
	 */
	int modifyHXTCMerchFee(MerchInfo merchInfo);
	
	String getMerchMaxId(String merchno);
	
	/**
	 * 查询商户时间段收款总金额
	 * @param merchno
	 * @return
	 */
	List<BigDecimal> getTranTotal(Map<String,String> tranParams);
	
	/**
	 * 查询单笔提现订单详情
	 * @param orderNo
	 * @return
	 * @author Horanluo
	 */
	WithdrawalDetailInfo queryWithdrawalOrderDetail(String orderNo);
	
	/**
	 * 查询单笔收款订单详情
	 * @param orderNo
	 * @return
	 * @author Horanluo
	 */
	ReceiveDetailInfo queryReceiveOrderDetail(Map<String,String> queryParam);
	
	/**
	 * 修改御付商户费率
	 * @param merchInfo
	 * @return
	 */
	int modifyYUFUMerchFee(MerchInfo merchInfo);
	
	/**
	 * 查询各状态下收款明细
	 * @param queryParams
	 * @return
	 */
	List<ReceiveDetailInfo> queryReceiveDetailByStatus(Map<String,String> queryParams);
	
	/**
	 * 查询各状态下提现明细
	 * @param queryParams
	 * @return
	 */
	List<WithdrawalDetailInfo> queryWithdrawalDetailByStatus(Map<String,String> queryParams);
	
	//仅做测试
//	/**
//     * 查询所有商户信息
//     */
    List<MerchInfo> getAllMerch();
	List<MerchInfo> getByMerchInfo(MerchInfo merchInfo);
	
	Integer batUpdMerchInfo(Map<String,String> map);
}
