/*
 * 类文件名:  YufuQuickpayServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.domain;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.factory.yufu.YufuMerchApiFactory;
import com.alycloud.channel.support.yufu.YufuMerchApplyCilent;
import com.alycloud.channel.support.yufu.YufuMerchQueryClient;
import com.alycloud.channel.support.yufu.YufuPayClient;
import com.alycloud.channel.utils.yufu.YufuApiType;
import com.alycloud.channel.utils.yufu.YufuMerchApiType;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.enums.SysChannelType;
import com.alycloud.modules.channel.yufu.AbstractYufuEntity;
import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;
import com.alycloud.modules.channel.yufu.YufuMerchBean;
import com.alycloud.modules.channel.yufu.YufuMerchQueryBean;
import com.alycloud.modules.channel.yufu.YufuMerchQueryResultBean;
import com.alycloud.modules.channel.yufu.YufuPayBean;
import com.alycloud.modules.channel.yufu.YufuPayResultBean;

/**
 * 御付支付通道接口
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
@Slf4j
public class YufuQuickpayServiceImpl implements IQuickpayService
{
    @Autowired
    private AbstractYufuEntity config;
    
    public String getChannelCode()
    {
        return SysChannelType.YUFU.getCode();
    }

    @ServiceLogAnnotation(moduleName="御付子商户注册")
    public <T,R> T registerChannelSubmerch(R data)
    {
        YufuMerchApplyCilent client = YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_APPLY.name());
        YufuMerchBean res = (YufuMerchBean)data;
        res.setBranchNo(config.getBranchNo());
        AbstractYufuResultEntity result = client.post(res, AbstractYufuResultEntity.class);
        return (T)result;
    }
    
    /**
     * 重载方法
     * @param entity
     * @return
     */
    @ServiceLogAnnotation(moduleName="向御付发起子商户查询服务")
    public <T, R> T queryChannelSubmerch(R data)
    {
        YufuMerchQueryClient client = YufuMerchApiFactory.getClient(YufuMerchApiType.MERCH_QUERY.name());
        YufuMerchQueryBean res = (YufuMerchQueryBean)data;
        res.setBranchNo(config.getBranchNo());
        YufuMerchQueryResultBean result = client.post(res, YufuMerchQueryResultBean.class);
        return (T)result;
    }

    /**
     * 重载方法
     * @return
     */
    @Override
    public <T> T updateChannelSubmerch()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 重载方法
     * @param payRequestParams
     * @param orderNo
     * @return
     */
    @Override
    public <T, R> T  pay(R data, String orderNo)
    {
        YufuPayClient client = YufuMerchApiFactory.getClient(YufuApiType.PAY.name());
        YufuPayBean res = (YufuPayBean)data;
        YufuPayResultBean result = client.post(res, YufuPayResultBean.class);
        
        return (T)result;
    }

    /**
     * 重载方法
     */
    @Override
    public void orderQuery()
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * 重载方法
     */
    @Override
    public void notifys()
    {
        // TODO Auto-generated method stub
        
    }


    /**
     * 重载方法
     * @param data
     * @return
     */
    @Override
    public <T, R> T billQuery(R data)
    {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public <T, R> T registerChannelJFSubmerch(R entity) {
		// TODO Auto-generated method stub
		return null;
	}
    
}
