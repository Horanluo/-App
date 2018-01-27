/*
 * 类文件名:  AbstractYufuResultEntity.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应参数
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value =Include.NON_NULL)
public class AbstractYufuResultEntity implements Serializable
{
    /**
     * 流水号，和请求参数一致，必填，16
     */
    private String serialNo;
    
    //################# 交易操作参数 begin ########################
    @JsonProperty("serial_no")
    private String serialNo2;
    
    /**
     * 应答返回码（“0000”,执行成功,其他，失败）， 必填
     */
    @JsonProperty("resp_code")
    private String respCode;
    
    /**
     * 应答返回提示信息， 必填
     */
    @JsonProperty("resp_msg")
    private String respMsg;
    
  //################# 交易操作参数 end ########################
    
    /**
     * 签名， 必填
     */
    private String sign;
    /**
     * 应答返回码（“0000”,执行成功,其他，失败）， 必填
     */
    private String resultCode;
    /**
     * 应答返回提示信息， 必填
     */
    private String resultMessage;
}
