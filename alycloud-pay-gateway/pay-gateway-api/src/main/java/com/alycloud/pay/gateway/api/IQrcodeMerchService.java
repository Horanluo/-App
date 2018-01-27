/*
 * 类文件名:  IQrcodeMerchService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;

/**
 * 渠道商户的业务处理对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IQrcodeMerchService
{

    /**
     * 根据渠道编码获取渠道信息
     * 
     * @param channelCode
     *            渠道编码
     * @return
     * @throws Exception
     */
    QrcodeMerchBean getByMerchno(String channelCode, String merchno);
    
}
