package com.alycloud.financial.mapper;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.entity.MerchVirtualTrans;

/**
 * 商户虚拟账户流水的持久层
 * @author Moyq5
 * @date 2017年3月14日
 */
public interface MerchVirtualTransMapper {

	public int add(MerchVirtualTrans trans);

	/**
	 * 根据平台自动产生的订单号获取交易信息
	 * 
	 * @param refno
	 * @return
	 * @throws Exception
	 */
	public MerchVirtualTrans getByTraceno(String traceno);
	
	/**
	 * 根据订单号更新代付状态
	 * @param trans
	 * @throws Exception
	 */
	public int updatePayStatus(MerchVirtualTrans trans);
	
	/**
	 * 根据订单号更新提现状态
	 * @param trans
	 * @throws Exception
	 */
	public int updateTransStatus(MerchVirtualTrans trans);
	
	public List<MerchVirtualTrans> listByPage(Map<String, Object> param, Integer page);

	public int countNotSuccess(String merchno);

	public int modByTraceno(MerchVirtualTrans trans4Mod);

	public String genRefno();

	/**
	 * @author Horanluo  重写
	 * 2018/01/23
	 */
	public List<MerchVirtualTrans> getResultList(MerchVirtualTrans trans);
	
	/**
	 * @author Horanluo  重写
	 * 2018/01/23
	 */
	public List<MerchVirtualTrans> getBatchList(MerchVirtualTrans trans);
	
	/**
	 * @author Horanluo  重写
	 * 2018/01/23
	 */
	public List<MerchVirtualTrans> getInfoByBatchno(String batchno);
}
