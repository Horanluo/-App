/*
 * 类文件名:  YufuPayClient.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.alycloud.channel.utils.yufu.YufuApiType;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.modules.channel.yufu.YufuPayBean;
import com.alycloud.modules.channel.yufu.YufuPayResultBean;

/**
 * 发起客户支付
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
@Slf4j
public class YufuPayClient extends AbstractYufuClient<YufuPayBean, YufuPayResultBean>
{

    public String getKey()
    {
        return YufuApiType.PAY.name();
    }
    
    /**
     * 重载方法
     * @param data
     * @param resultClass
     * @return
     */
    @Override
    public YufuPayResultBean post(YufuPayBean data, Class<YufuPayResultBean> resultClass)
    {
        YufuPayResultBean resResult = null;
        try {
            String reqBody = getReqBody(data);
            String payUrl = context.getConfig().getPayPath() + "?" + reqBody;
//            String resBody = client.post(context.getConfig().getServicePath(), reqBody);
//            String sign = sign(resBody+"");
            
            resResult = new YufuPayResultBean();
            resResult.setPayUrl(payUrl);
            resResult.setRespCode("0000");
            resResult.setRespMsg("支付地址获取成功");
        } catch (Exception e) {
            
            log.error("接口调用失败", e);
            try {
                resResult = resultClass.newInstance();
                resResult.setResultCode("9999");
                resResult.setResultMessage(e.getMessage());
            } catch (Exception e1) {
                log.error("接口调用失败", e1);
            }
        }
        return resResult;
    }
    /**
     * 重载方法
     * @param data
     * @return
     * @throws Exception
     */
    @Override
    protected String getReqBody(YufuPayBean data)
        throws Exception
    {
        String jsonString = JSONUtils.obj2json(data);
        Map<String, String> dataMap = getSortedFields(jsonString);
        String sign = sign(dataMap, true, super.context
            .getConfig().getPayKey());
        dataMap = getSortedFields(jsonString, false);
        dataMap.put("sign", sign);
        return toQueryString(dataMap, null, false);
//        return toQueryString(dataMap);
    }
}
