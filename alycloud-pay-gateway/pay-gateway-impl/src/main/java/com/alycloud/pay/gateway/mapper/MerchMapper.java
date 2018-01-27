/*
 * 类文件名:  MerchMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.pay.gateway.models.MerchRelateBean;
import com.alycloud.pay.gateway.models.MerchUserBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchFeeBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MerchMapper
{

    /**
     * 生成商户编号
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    String getMerchMaxId(@Param("merchno") String prefix);

    /**
     * 判断商户的全称是否存在
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int validFullName(String merchName);
    
    /**
     * 创建商户基本信息
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int registerMerch(MerchInfo merch);

    /**
     * 插入商户关系
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insertMerchRelate(List<MerchRelateBean> relate);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    MerchUserBean getByLoginName(Map<String, String> userParams);

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月23日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int batchAddQrcodeChannelFee(List<QrcodeMerchFeeBean> list);
    
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
    MerchInfo getByMerchno(String merchno);

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
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    List<MerchInfo> searchByAgentno(@Param("agentno")String agentno);
    
}
