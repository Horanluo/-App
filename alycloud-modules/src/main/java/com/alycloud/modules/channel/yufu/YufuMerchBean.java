/*
 * 类文件名:  YufuMerchBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.alycloud.modules.channel.yufu.jackson.converter.StringSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 御付商户信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class YufuMerchBean extends AbstractYufuEntity
{
    /**
     * 商户名称，必填，全局唯一性判重
     */
    @JsonProperty("merName")
    private String merchName;
    
    /**
     * 商户简称，必填
     */
    @JsonProperty("merShortName")
    private String merchShortName;
    
    /**
     * 商户地址，必填
     */
    @JsonProperty("merAddr")
    private String merchAddress;
    
    /**
     * 开户类型，必填，1
     */
    private Integer openType;
    
    /**
     * 省份（城市编码），必填，快捷可默认填写29
     */
    private Integer province;
    
    /**
     * 城市（城市编码），必填，快捷可默认填写：2904
     */
    private Integer city;
    
    /**
     * 身份证号，必填
     */
    private String idCard;
    
    /**
     * 账户类型，0-民生对公、1-民生对私、2-非民生对公、3-非民生对私(快捷可默认填写：1)<br>
     * 必填
     */
    private Integer accountType;
    
    /**
     * 账号，必填
     */
    private String accountNo;
    
    /**
     * 账户名，必填
     */
    private String accountName;
    
    /**
     * 开户行号，必填，快捷可默认填写：310290000013
     */
    private String bankCode;
    
    /**
     * 开户行名，必填，联行号，精确到总行和bankCode传递一样的值（快捷可默认填写：310290000013）
     */
    private String bankName;
    
    /**
     * 联行号，精确到支行网点非民生对公必填
     */
    @JsonProperty("openBranch")
    private String bankBranchName;
    
    /**
     * 联系人，必填
     */
    @JsonProperty("merConsacts")
    private String merchContacts;
    
    /**
     * 联系人电话，必填，全局唯一性判重
     */
    private String phone;
    
    /**
     * 支付服务
     */
    @JsonSerialize(using = StringSerializer.class)
    private List<YufuMerchPayServiceBean> payServices;
    
    /**
     * 商户开通结果通知的url，必填
     */
    private String notifyUrl;
    
    public YufuMerchBean()
    {
        super("MER_APPLY2");
    }
   
}
