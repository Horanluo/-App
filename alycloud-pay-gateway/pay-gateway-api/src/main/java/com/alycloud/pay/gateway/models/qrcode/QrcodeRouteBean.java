/*
 * 类文件名:  QrcodeRouteBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码渠道路由结果
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeRouteBean implements Serializable
{
    private static final long serialVersionUID = -3618569393522885433L;
    
    private int id;
    /**
     * 路由名称
     */
    private String routeName;
    /**
     * 路由渠道编码
     */
    private String channelCode;
    /**
     * 路由渠道商户编号
     */
    private String merchno;
    /**
     * 路由渠道商户名称
     */
    private String merchName;
}
