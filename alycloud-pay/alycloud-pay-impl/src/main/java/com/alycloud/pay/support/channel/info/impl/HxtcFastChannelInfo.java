/*
 * 类文件名:  HxtcquickFastChannelInfo.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月8日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.support.channel.info.impl;

import java.math.BigDecimal;

import com.alycloud.channel.support.config.Config;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysLiquidateType;
import com.alycloud.modules.enums.SysT0RateType;
import com.alycloud.modules.search.FastChannelInfo;


/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月8日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HxtcFastChannelInfo implements FastChannelInfo
{
    @Override
    public BigDecimal getT0Rate() {
        return new BigDecimal(Config.getString("ylpay_quick_rate_t0"));
    }

    @Override
    public BigDecimal getT1Rate() {
        return new BigDecimal(Config.getString("ylpay_quick_rate_t1"));
    }

    @Override
    public BigDecimal getPaymentFee() {
        return new BigDecimal(Config.getString("ylpay_advance_rate"));
    }

    @Override
    public SysChannelType getType() {
        return SysChannelType.HXTC;
    }

    @Override
    public BigDecimal getMinFee() {
        return new BigDecimal(Config.getString("ylpay_quick_min_fee"));
    }

	@Override
	public SysT0RateType getT0RateType() {
		return SysT0RateType.FULL;
	}
	
	@Override
	public SysLiquidateType getLiquidateType() {
		return SysLiquidateType.CHANNEL;
	}
}
