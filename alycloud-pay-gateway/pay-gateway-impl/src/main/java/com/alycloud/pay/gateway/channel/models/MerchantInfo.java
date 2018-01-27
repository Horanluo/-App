/*
 * 类文件名:  MerchantInfo.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月23日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
public class MerchantInfo extends HfbankBaseInfo
{
    /**
     * 商户名称
     */
    private String merchantName;
    
    /**
     * 商户简称
     */
    private String merchantShortName;
    /**
     * 负责人
     */
    private String opName;
      
    /**
     * 负责人电话  
     */
    private String opPhone;
    /**
     * 客服电话
     */
    private String customerTelephone;
    /**
     * 省份
     */
    private String proName;
    /**
     * 城市
     */
    private String cityName;
    /**
     * 区县
     */
    private String areaName;
       
    /**
     * 地址
     */
    private String address;
    
    /**
     * 电话
     */
    private String telephone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 经营类目 
     */
    private String categoryWx;
    /**
     * 经营类目
     */
    private String categoryZfb;
    
    /**
     * 银行类型
     */
    private String bankType;
    
    /**
     * 银行名称
     */
    private String bankName;
    
    /**
     * 银行卡号
     */
    private String bankCardNo;
    
    /**
     * 户名
     */
    private String bankAccName;
    
    /**
     * 帐户类型
     */
    private String accountType;
    
    /**
     * 营业执照类型
     */
    private String businessLicenseType;
    
    /**
     * 营业执照编号
     */
    private String businessLicense;
        
    /**
     * 受理商户负责人信息
     */
    private String legalPersonType;    
        
    /**
     * 证件类型
     */
    private String certificateType;   
      
    /**
     * 证件号码
     */
    private String certificateNo;    
      
    /**
     * 支付类型列表  
     */
    private String payTypes; 
    
    /**
     * 商户经营类型 
     */
    private String merchantOperationType; 
    
    /**
     * 备注说明
     */
    private String rmk; 
    
}
