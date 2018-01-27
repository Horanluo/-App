package com.alycloud.account.mapper;

import java.util.List;
import java.util.Map;

import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;

import feign.Param;

public interface ChannelSubMerchInfoMapper {
    
	/**
	 * 查询商户渠道费率，结算，单笔限额
	 * @param merchno  商户号
	 * @param payType  支付方式
	 * @return  商户费率,结算,单笔限额列表
	 */
	List<ChannelSubmerchInfo> getChannelMerchInfoList(Map<String,String> queryParams);
	
	/**
	 * 更新商户费率
	 * @param queryParams
	 * @return 
	 */
	int updateMerchFee(Map<String,String> updateParams);
	
	/**
	 * 新增商户费率
	 */
	int addMerchFee(ChannelSubmerchInfo csmi);
	
	/**
	 * 查询渠道信息
	 * @param merchno  商户号
	 * @param channelCode  渠道号
	 * @return
	 * @author Administrator   罗根恒
	 */
	ChannelSubmerchInfo getChannelInfo(Map<String,String> params);
	
	/**
	 * 更新记录
	 * @param csmi
	 * @return
	 */
	int updateRecord(ChannelSubmerchInfo csmi);
	
	/**
	 * 新增商户费率
	 */
	int addChannelSubMerchInfoList(List<ChannelSubmerchInfo> list);
	
	/**
	 * 删除单条记录
	 */
	int deleteRecord(Integer id);
	
	//仅做测试
//	/**
//     * 插入渠道信息
//     * 
//     * @author   曾云龙
//     * @version  V001Z0001
//     * @date     2017年11月21日
//     * @see  [相关类/方法]
//     * @since  [产品/模块版本]
//     */
    public int addChannelSubMerchInfo(ChannelSubmerchInfo info);
//
//    /**
//     * 查询御付注册信息
//     * 
//     * @author   曾云龙
//     * @version  V001Z0001
//     * @date     2017年11月22日
//     * @see  [相关类/方法]
//     * @since  [产品/模块版本]
//     */
    public YufuChannelMerchBean searchYufuSubmerchInfo(Map<String,String> map);
  //仅做测试
}