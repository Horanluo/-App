/*
 * 类文件名:  MerchFeeServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月26日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IMerchFeeService;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.mapper.MerchFeeMapper;

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
@Service
public class MerchFeeServiceImpl implements IMerchFeeService
{
    @Autowired
    private MerchFeeMapper merchFeeMapper;

    /**
     * 重载方法
     * @param merchno
     * @param payType
     * @param scanType
     * @param settleType
     * @return
     * @throws YzyueException 
     */
    @Override
    public BigDecimal getQrcodeRate(String merchno, int payType, int scanType, int settleType) throws YzyueException
    {
        BigDecimal rate = merchFeeMapper.getQrcodeRate(merchno, payType, scanType, settleType);
        if(rate == null){
            throw new YzyueException("09", "商户的手续费未设置");
        }
        if(rate.compareTo(BigDecimal.valueOf(0.001)) == -1){
            throw new YzyueException("09", "商户的手续费设置有误");
        }
        return rate;
    }
    
}
