/*
 * 类文件名:  AbstractEntity.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 公共请求参数
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
@ConfigurationProperties(prefix="yufu.product")
@Component
public class AbstractYufuEntity implements Serializable
{
    /**
     * 机构号，必填
     */
    @JsonProperty("brhId")
    private String branchNo;
    
    /**
     * 接口名称，必填
     */
    private String service;
    /**
     * 流水号，必填，16
     */
    private String serialNo;
    
    /**
     * 服务请求地址
     */
    @JsonIgnore
    private String servicePath;
    
    /**
     * 服务交易密钥
     */
    @JsonIgnore
    private String key;
    
    /**
     * 支付地址
     */
    @JsonIgnore
    private String payPath;
    
    /**
     * 交易密钥
     */
    @JsonIgnore
    private String payKey;
    
    /**
     * 支付业务服务请求地址
     */
    @JsonIgnore
    private String payServerPath;
    
    public AbstractYufuEntity(String service)
    {
        this.service = service;
    }
}
