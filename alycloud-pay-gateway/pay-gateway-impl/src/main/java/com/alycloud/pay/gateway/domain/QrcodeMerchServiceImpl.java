/*
 * 类文件名:  QrcodeMerchServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IQrcodeMerchService;
import com.alycloud.pay.gateway.mapper.QrcodeMerchMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;

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
public class QrcodeMerchServiceImpl implements IQrcodeMerchService
{
    @Autowired
    private QrcodeMerchMapper qrcodeMerchMapper;

    /**
     * 根据渠道编码获取渠道信息
     * 
     * @param channelCode
     *            渠道编码
     * @return
     * @throws Exception
     */
    @Override
    public QrcodeMerchBean getByMerchno(String channelCode, String merchno)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("channelCode", channelCode);
        param.put("channelMerchno", merchno);
        return qrcodeMerchMapper.getByMerchno(param);
    }
    
}
