/*
 * 类文件名:  AgentVirtualCard.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月16日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.agent;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商虚拟账户
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月16日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentVirtualCard implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3442955138843948051L;
    
    private Integer id;
    private String cardno; // 虚拟账号(10位机构号+8位顺序号)
    private String branchno; // 机构号
    private String agentno; // 代理商编号
    private String validDate; // 有效期
    private Integer bizType; // 业务类别(1-传统POS 2-手机APP 4-二维码扫码 8-网关支付)
    private Integer status; // 账户状态(1-正常 2-已冻结 3-已销户)
    private String rateCode; // 费率类别代码
    private String passwd; // 密码
    private BigDecimal availAmount; // 可用资金
    private BigDecimal transitAmount; // 在途资金
    private String accountno; // 结算账户
    private String accountName; // 账户户名
    private String bankno; // 开户行行号
    private String bankName; // 开户行名称
    private String addTime; // 创建时间
    private String frozenTime; // 上次冻结时间
    private Integer payType; // 代付类型：1-T+0 2-T+1
    private String payKey; // 交易密钥
}
