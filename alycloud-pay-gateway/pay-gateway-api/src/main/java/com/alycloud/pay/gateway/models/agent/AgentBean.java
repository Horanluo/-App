/*
 * 类文件名:  AgentBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.agent;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 代理商实体
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月23日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentBean implements Serializable
{
    private static final long serialVersionUID = -125522309226055488L;
    
    private int id;
    /**
     * 代理商编号(4位地区代码+6位顺序号)
     */
    private String agentno;
    /**
     * 代理商简称
     */
    private String agentName;
    /**
     * 代理商全称
     */
    private String fullName;
    /**
     * 代理商在运营系统的分成比例(范围:0-100)
     */
    private int divide;
    /**
     * 法人姓名
     */
    private String legalName;
    /**
     * 法人身份证
     */
    private String identityCard;
    /**
     * 联系人
     */
    private String linkMan;
    /**
     * 联系人电子邮箱
     */
    private String email;
    /**
     * 联系人固定电话
     */
    private String telephone;
    /**
     * 联系人手机号码
     */
    private String mobile;
    /**
     * 加盟费
     */
    private BigDecimal memberFee;
    /**
     * 保证金
     */
    private BigDecimal assuranceFee;
    /**
     * 地区代码
     */
    private String areaCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 上级代理商  （运营平台为1000000000）
     */
    private String superAgent;
    /**
     * 开通状态(1-未开通  2-已开通)
     */
    private int status;
    /**
     * 开通时间
     */
    private String openTime;
    /**
     * 审核状态(1-待审核 2-审核拒绝 3-审核通过)
     */
    private int auditStatus;
    /**
     * 审核对象  （运营平台为1000000000）
     */
    private String auditAgentno;
    /**
     * 收款账号
     */
    private String accountno;
    /**
     * 帐户类型 1-对私 2-对公
     */
    private int accountType;
    /**
     * 帐户户名
     */
    private String accountName;
    /**
     * 开户行行号
     */
    private String bankno;
    /**
     * 开户行行名
     */
    private String bankName;
    /**
     * 身份证照片地址
     */
    private String identityCardImg;
    /**
     * 合同文件地址
     */
    private String contractImg;
    /**
     * 其它文件地址
     */
    private String otherFile;
    /**
     * 录入时间
     */
    private String addTime;
    /**
     * 上级代理[动态]
     */
    private String superList;
    /**
     * 代理商级别[动态]
     */
    private int agentLevel;
    /**
     * 代理商类型
     */
    private Integer agentType;
    /**
     * 机构编码
     */
    private String branchno;
    /**
     * 一级代理商编码
     */
    private String agentnoFirst;
    /**
     * 业务开通类型
     */
    private Integer bizType;
    /**
     * 银行卡照片地址
     */
    private String bankCardImg;
    /**
     * 提现费
     */
    private BigDecimal withdrawalFee;
    
    private String bizType1;//是否有传统POS
    private String bizType2;
    private String bizType4;
    private String bizType8;
    private String bizType16;//二维码费率
    private String bizType32;//线上费率
    /**
     * T0分成比例
     */
    private int t0Divide;
        
    private BigDecimal posDebitRate; //借记卡pos费率
    private BigDecimal posDebitFixed; //借记卡pos封顶
    private BigDecimal posCreditRate; //贷记卡pos费率
    private BigDecimal appDebitRate; //借记卡app费率
    private BigDecimal appDebitFixed; //借记卡app封顶
    private BigDecimal appCreditRate; //贷记卡app费率
    private BigDecimal posT0Rate; //pos t+0 增量费率
    private BigDecimal appT0Rate; //app t+0增量费率
    private BigDecimal onlineRate; //线上费率
    
    private BigDecimal fastPayRateT1;//快捷支付T+1费率
    private BigDecimal fastPayRateT0;//快捷支付T+0费率
}
