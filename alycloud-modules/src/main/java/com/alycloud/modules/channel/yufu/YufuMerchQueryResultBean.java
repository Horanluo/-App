/*
 * 类文件名:  YufuMerchQueryBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alycloud.modules.channel.yufu.enums.MerchState;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 商户审核状态查询接口响应参数对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value =Include.NON_NULL)
public class YufuMerchQueryResultBean extends AbstractYufuResultEntity
{
    /**
     * 状态，必填
     */
    private MerchState state;
    /**
     * 分配的商户号
     */
    @JsonProperty("merId")
    private String merchId;
    
    /**
     * 商户名称
     */
    private String merName;
    
    /**
     * 终端号
     */
    private String termId;
    /**
     * 拒绝原因
     */
    private String remark;
    /**
     * 一码付地址
     */
    private String oneCodeUrl;
    /**
     * 快捷支付key
     */
    @JsonProperty("kj_key")
    private String kjKey;
}
