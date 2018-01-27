/*
 * 类文件名:  FastTransBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月6日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.quickpay;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付流水
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月6日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastTransBean implements Serializable
{
    private static final long serialVersionUID = 1695812924557676618L;
    
    private Integer id;// 
    private String merchno;// 商户号
    private String merchName;// 商户名称
    private String agentno;// 代理商编号
    private String agentName;// 代理商名称
    private String branchno;// 机构号
    private String branchName;// 机构名称
    private String orderno;// 系统订单号（我们系统自己生成的单号）
    private Integer channelType;// 渠道类型：0易联支付
    private String channelOrderno;// 渠道订单号（上游交易渠道生成的单号）
    private BigDecimal amount;// 交易金额（元）
    private BigDecimal merchFee;// 商户手续费（元）
    private BigDecimal channelFee;// 渠道手续费（元）
    private BigDecimal branchFee;// 机构手续费（元）
    private Integer status;// 订单付款状态：0未付款、1付款成功、2付款失败
    private String descr;// 付款状态描述
    private String trueName;// 付款人姓名
    private String idCard;// 付款人身份证号
    private String bankCard;// 付款人银行卡号
    private String bankName;// 付款人银行卡银行名称
    private String mobile;// 付款人手机号
    private Integer settleType;// 结算方式：0－T0，1－T1
    private String addTime;// 创建时间：yyyy-MM-dd HH:mm:ss
    private Integer settleStatus;// 结算状态：0未结算，1结算成功，2结算失败
    private String settleDescr;// 结算状态描述
    private String settleTime;// 结算时间：yyyy-MM-dd HH:mm:ss
    private String settleOrderno;// 代付订单号（代付渠道生成的单号）
    private String payerRemark;// 付款人备注信息
    private Integer liquidateType;// 清算类型：0平台清算，1渠道清算
}
