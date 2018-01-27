/*
 * 类文件名:  ChannelFeeServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IChannelFeeService;
import com.alycloud.pay.gateway.mapper.ChannelFeeMapper;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class ChannelFeeServiceImpl implements IChannelFeeService
{
    @Autowired
    private ChannelFeeMapper channelFeeMapper;
    
    /**
     * 获取二维码的费率信息
     * 
     * @param merchno
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param settleType
     *            结算类型(1-T+0 2-T+1)
     * @return
     * @throws Exception
     */
    @Override
    public BigDecimal getQrcodeRate(String channelCode, int payType, int scanType, int settleType)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("channelCode", channelCode);
        param.put("payType", payType);
        param.put("scanType", scanType);
        param.put("settleType", settleType);
        return channelFeeMapper.getQrcodeRate(param);
    }
}
