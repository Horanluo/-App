/*
 * 类文件名:  IMerchProviderService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.api;

import com.alycloud.pay.gateway.dto.MerchDTO;
import com.alycloud.pay.gateway.models.ResponseBean;

/**
 * 商户对外提供接口服务
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月21日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IMerchProviderService
{
    /**
     * 
     * 商户注册
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    ResponseBean merchRegister(MerchDTO merchDTO,String key) throws Exception;
    
    /**
     * 商户资料修改
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    void merchChange();
    
    /**
     * 二维码支付接口
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月21日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    void qrcodeActivePay();
}
