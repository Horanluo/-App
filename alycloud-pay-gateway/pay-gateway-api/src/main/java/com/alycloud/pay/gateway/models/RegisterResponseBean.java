/*
 * 类文件名:  RegisterResponseBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月23日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseBean extends ResponseBean
{
    private static final long serialVersionUID = 1574945396558204937L;
    
    /**
     * 商户号
     */
    private String merchno;
    
    /**
     * 终端号
     */
    @JsonIgnore
    private String termno;
    
    /**
     * 商户交易密钥
     */
    private String merchKey;
    
    /**
     * TMK
     */
    @JsonIgnore
    private String tmk;
    
    public RegisterResponseBean(String respCode, String message, String merchno, String termno, String tmk, String merchKey)
    {
        super(respCode, message);
        this.merchno = merchno;
        this.termno = termno;
        this.tmk = tmk;
        this.merchKey = merchKey;
    }
}
