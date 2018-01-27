/*
 * 类文件名:  IMerchProviderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.account.api;

import java.math.BigDecimal;
import java.util.List;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.ReceiveDetailInfo;
import com.alycloud.modules.entity.WithdrawalDetailInfo;
import com.alycloud.modules.search.MerchInfo4Search;
import com.alycloud.modules.vo.IdentifyAuthenVO;
import com.alycloud.modules.vo.LivingIdentifyVO;
import com.alycloud.modules.vo.RegistUserVO;
import com.alycloud.modules.vo.UpgradePayDataVO;

/**
 * 商户账户接口服务
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IMerchService
{
    /**
     * 
     * 查询具体某个商户信息
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	MerchInfo getByLoginName(String loginName,String merchMobile);
	
	/**
     * 
     * 查询商户信息，用于登录
     * 
     * @author   罗根恒
     * @version  V001Z0001
     * @date     2017年10月16日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
	MerchInfo getByLoginName(String loginName);
	
	/**
	 * 
	 * @param merchno
	 * @return
	 */
	String getMerchMaxId(String merchno);
	
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
	int addMerchInfo(RequestBean<RegistUserVO> reqData,MerchUser refer);
	
	/**
	 * 查询商户累计收款
	 */
	BigDecimal getSuccTranAmt(String merchno);
	
	/**
	 * 查询商户可提现金额
	 */
	BigDecimal getAvailAmt(String merchno);
	
	/**
	 * 查询商户收款明细
	 */
	List<ReceiveDetailInfo> getTranDetail(String merchno,String startDate,String endDate);
	
	/**
	 * 查询商户可提现金额
	 */
	List<WithdrawalDetailInfo> getWithdrawalDetail(String merchno,String startDate,String endDate);
	
	/**
	 * 获取对应状态下的商户信息
	 * @param username
	 * @param status
	 * @return
	 */
	List<MerchInfo> getMerchInfoByStatus(String merchno,String status,String gradeType,String queryParam);
	
	/**
	 * 新增待审核商户信息
	 * @param changeMerchInfoBean
	 * @return
	 */
	SingleResult<String> addChangeMerchInfo(RequestBean<IdentifyAuthenVO> reqData) throws ApiException;
	
	
	/**
	 * 根据商户号获取商户
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param merchno
	 * @return
	 */
	MerchInfo getByMerchno(String merchno);

	/**
	 * 根据商户id获取商户
	 * @author Moyq5
	 * @date 2017年10月27日
	 * @param id
	 * @return
	 */
	MerchInfo getById(Integer id);
	

	/**
	 * 升级支付
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param data
	 * @return
	 */
	String upgradePay(String merchno, UpgradePayDataVO data) throws Exception;
	
	/**
	 * 升级，检查推荐人数、交易金额，如果满足条件则进行升级
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param userId
	 * @throws Exception 
	 */
	void upgradeInviter(Integer userId)throws ApiException;

	/**
	 * 更新商户信息
	 * @author Moyq5
	 * @date 2017年11月2日
	 * @param data
	 */
	void mod(MerchInfo data);

	/**
	 * 修改HXTC渠道商户费率
	 * @param merchno
	 * @param settleType  费率类型 d0  d1
	 * @param modifyRate 修改后的费率
	 * @return
	 */
	int modifyHXTCMerchFee(MerchInfo merchInfo);
	
	/**
	 * 修改御付商户费率
	 * @param merchInfo
	 * @return
	 */
	int modifyYUFUMerchFee(MerchInfo merchInfo);
	
	/**
	 * 查询商户列表信息
	 * @author Moyq5
	 * @date 2017年11月5日
	 */
	List<MerchInfo> listByPage(MerchInfo4Search data);
	
	/**
	 * 查询单笔提现订单详情
	 * @param orderNo
	 * @return
	 * @author Horanluo
	 */
	WithdrawalDetailInfo queryWithdrawalOrderDetail(String orderNo);
	
	/**
	 * 查询单笔收款订单详情
	 * @param orderNo  订单号
	 * @param payType  支付方式
	 * @return
	 * @author Horanluo
	 */
	ReceiveDetailInfo queryReceiveOrderDetail(String orderNo,String payType);
	
	/**
	 * 查询各状态下收款明细
	 * @param merchno
	 * @param startDate
	 * @param endDate
	 * @param tranStatus  交易状态 0未付款、1付款成功、2付款失败
	 * @return
	 */
	List<ReceiveDetailInfo> queryReceiveDetailByStatus(String merchno,String startDate,
			String endDate,String tranStatus);
	
	/**
	 * 查询各状态下提现明细
	 * @param merchno
	 * @param startDate
	 * @param endDate
	 * @param tranStatus  交易状态 1-处理中 2-交易成功 3-交易失败
	 * @return
	 */
	List<WithdrawalDetailInfo> queryWithdrawalDetailByStatus(String merchno,String startDate,
			String endDate,String tranStatus);

	void upgradeByGradeOrderId(Integer gradeOrderId) throws Exception;
	
	/**
	 * 更新待审核商户信息
	 * @param changeMerchInfoBean
	 * @return
	 */
	SingleResult<String> updateChangeMerchInfo(RequestBean<LivingIdentifyVO> reqData) throws ApiException;
	
	/**
	 * 根据实名认证结果批量下载文件
	 * @author Horanluo
	 * @date 2017年11月27日
	 * @return
	 */
	List<MerchInfo> getByMerchInfo(MerchInfo merchInfo);
}
