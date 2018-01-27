/*
 * 类文件名:  BranchFeeServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月28日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IBranchFeeService;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.mapper.BranchFeeMapper;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月28日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class BranchFeeServiceImpl implements IBranchFeeService
{
    @Autowired
    private BranchFeeMapper branchFeeMapper;

    /**
     * 重载方法
     * @param branchno
     * @param payType
     * @param scanType
     * @param settleType
     * @return
     * @throws YzyueException 
     */
    @Override
    public BigDecimal getQrcodeRate(String branchno, int payType, int scanType, int settleType) throws YzyueException
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("branchno", branchno);
        param.put("payType", payType);
        param.put("scanType", scanType);
        param.put("settleType", settleType);
        BigDecimal rate = branchFeeMapper.getQrcodeRate(param);
        if(rate == null){
            throw new YzyueException("09", "机构的手续费未设置");
        }
        if(rate.compareTo(BigDecimal.valueOf(0.001)) == -1){
            throw new YzyueException("09", "机构的手续费设置有误");
        }
        return rate;
    }
    
}
