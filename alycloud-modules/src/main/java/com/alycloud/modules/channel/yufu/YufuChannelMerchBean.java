/*
 * 类文件名:  YufuChannelMerchBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月14日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.yufu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 御付商户信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月14日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YufuChannelMerchBean
{
    private Long id;
    private String platMerchno;// （平台）商户号
    private String branchId;// （渠道参数）机构号
    private String merchName;// （渠道参数）商户名称
    private String merchShortName;// （渠道参数）商户简称
    private Integer merchLevel;// （渠道参数）商户级别
    private String parentMerchId;// （渠道参数）商户上级商户号
    private Integer openType;// （渠道参数）开启类型：1：商户(对公结算)2：商户(对私结算)3：个体工商户4：个人
    private String gszcName;// （渠道参数）工商注册名
    private String merchAddr;// （渠道参数）商户地址
    private Integer province;// （渠道参数）省份
    private Integer city;// （渠道参数）城市
    private String idCard;// （渠道参数）身份证号
    private String yyzzCode;// （渠道参数）营业执照号
    private String zzjgCode;// （渠道参数）组织机构代码证号
    private String swdjCode;// （渠道参数）税务登记号
    private String khxkCode;// （渠道参数）开户许可证
    private Integer accountType;// （渠道参数）0-民生对公、1-民生对私、2-非民生对公、3-非民生对私
    private String accountNo;// （渠道参数）账号
    private String accountName;// （渠道参数）账户名
    private String bankCode;// （渠道参数）开户行号
    private String bankName;// （渠道参数）开户行名，联行号，精确到总行和bankCode传递一样的值
    private String openBranch;// （渠道参数）开户网点联行号，精确到支行网点非民生对公必填
    private String merchContacts;// （渠道参数）联系人
    private String phone;// （渠道参数）联系人电话，全局唯一性判重
    private String countRole;// （渠道参数）结算主体， 1-本商户
    private Integer state;// （渠道参数）状态0：待审核，1：通过，2：拒绝
    private String merchId;// （渠道参数）商户号
    private String termId;// （渠道参数）终端号
    private String remark;// （渠道参数）拒绝原因
    private String oneCodeUrl;// （渠道参数）一码付地址
    private String kjKey;// （渠道参数）快捷支付key 
}
