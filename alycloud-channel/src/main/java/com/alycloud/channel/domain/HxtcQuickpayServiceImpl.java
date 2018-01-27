/*
 * 类文件名:  HxtcQuickpayServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.utils.DateUtils;
import org.mortbay.log.Log;
import org.springframework.stereotype.Service;
import org.xiajinsuo.epay.sdk.HttpUtils;
import org.xiajinsuo.epay.sdk.RRParams;
import org.xiajinsuo.epay.sdk.ResponseDataWrapper;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.utils.hxtc.HxtcConfig;
import com.alycloud.channel.utils.hxtc.MypaysRsaDataEncryptUtil;
import com.alycloud.channel.utils.hxtc.MypaysRsaDataEncryptUtil_JF;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.enums.SysChannelType;

/**
 * 汇享天成快捷接口实现
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年9月7日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class HxtcQuickpayServiceImpl implements IQuickpayService
{
    /**
     * 重载方法
     * 
     * @return
     */
    @Override
    public String getChannelCode()
    {
        return SysChannelType.HXTC.getCode();
    }
    
    /**
     * 重载方法
     * 
     * @return
     */
    @ServiceLogAnnotation(moduleName = "汇享天成商户注册")
    public <T, R> T registerChannelSubmerch(R businessReq)
    {
    	Log.info("进件参数:"+businessReq.toString());
        RRParams requestData =
            RRParams.newBuilder()
                .setAppId(HxtcConfig.appId)
                .setClientTransId(generateTransId())
                .setTransTimestamp(System.currentTimeMillis())
                .setTransType(HxtcConfig.transType)
                .build();
        ResponseDataWrapper rdw =
            HttpUtils.post(HxtcConfig.REQUEST_URL,
                requestData,
                (Map)businessReq,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
        Map<String, Object> result = new HashMap<>();
        result.put("respCode", rdw.getRespCode());
        result.put("respMsg", rdw.getRespMsg());
        result.put("sign", rdw.getSign());
        result.put("data", rdw.getResponseData());
        return (T)result;
    }
    
    /**
     * 重载方法
     * 
     * @return
     */
    @ServiceLogAnnotation(moduleName = "汇享天成商户注册")
    public <T, R> T registerChannelJFSubmerch(R businessReq)
    {
    	Log.info("进件参数:"+businessReq.toString());
        RRParams requestData =
            RRParams.newBuilder()
                .setAppId(HxtcConfig.jf_appId)
                .setClientTransId(generateTransId())
                .setTransTimestamp(System.currentTimeMillis())
                .setTransType(HxtcConfig.transType)
                .build();
        ResponseDataWrapper rdw =
            HttpUtils.post(HxtcConfig.REQUEST_URL,
                requestData,
                (Map)businessReq,
                MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPri,
                MypaysRsaDataEncryptUtil_JF.jf_rsaDataEncryptPub);
        Map<String, Object> result = new HashMap<>();
        result.put("respCode", rdw.getRespCode());
        result.put("respMsg", rdw.getRespMsg());
        result.put("sign", rdw.getSign());
        result.put("data", rdw.getResponseData());
        return (T)result;
    }
    /**
     * 重载方法
     * @param entity
     * @return
     */
    @Override
    public <T, R> T queryChannelSubmerch(R entity)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * 重载方法
     * 
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
     */
    @ServiceLogAnnotation(moduleName="发起汇享天成支付订单")
    public <T,R> T pay(R data, String orderNo)
    {
        Map<String, String> businessReq = (Map<String, String>)data;
        RRParams requestData =
            RRParams.newBuilder()
                .setAppId(HxtcConfig.appId)
                .setClientTransId(orderNo)
                .setTransTimestamp(System.currentTimeMillis())
                .setTransType(HxtcConfig.transType)
                .build();
        ResponseDataWrapper rdw =
            HttpUtils.post(HxtcConfig.REQUEST_URL,
                requestData,
                businessReq,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
        
        Map<String, String> result = new HashMap<String, String>();
        if (rdw.getRespCode().equals("000000"))
        {
            result = rdw.getResponseData();
            result.put("channelOrderno", rdw.getServerTransId());
            result.put("responseCode", "0");
            result.put("respMsg", rdw.getRespMsg());
            result.put("pageContent", result.get("page_content"));
            System.out.println(result);
        }
        else
        {
            result.put("responseCode", "1");
            result.put("respMsg", rdw.getRespMsg());
            result.put("pageContent", rdw.getRespMsg());
        }
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
     */
    @Override
    public <T, R> T billQuery(R data)
    {
        Map<String, String> businessReq = (Map<String, String>)data;
        RRParams requestData =
            RRParams.newBuilder()
                .setAppId(HxtcConfig.appId)
                .setClientTransId(generateTransId())
                .setTransTimestamp(System.currentTimeMillis())
                .setTransType(HxtcConfig.transType)
                .build();
        ResponseDataWrapper rdw =
            HttpUtils.post(HxtcConfig.REQUEST_URL,
                requestData,
                businessReq,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
        
        return (T)rdw.getData();
        
    }
    
    /**
     * 构建交易流水号 14位
     *
     * @return
     */
    static String generateTransId()
    {
        String time = DateUtils.formatDate(new Date(), "yyyyMMdd");
        String nanoTime = System.nanoTime() + "";
        return String.format("%s%s", time, nanoTime.substring(nanoTime.length() - 6));
    }
}
