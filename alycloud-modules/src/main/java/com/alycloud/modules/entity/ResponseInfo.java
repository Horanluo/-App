/*
 * 类文件名:  ResponseBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   罗根恒
 * @version  V001Z0001
 * @date     2017年10月17日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ResponseInfo implements Serializable
{
    /**
     * 返回的地址
     */
    protected String mapping; 
    /**
     * 响应码
     */
    protected String respCode;
    /**
     * 响应内容
     */
    protected String message;
    
    /**
     * 返回数据签名
     */
    private String sign;
    
    public ResponseInfo(String respCode, String message) {
        super();
        this.respCode = respCode;
        this.message = message;
    }
}
