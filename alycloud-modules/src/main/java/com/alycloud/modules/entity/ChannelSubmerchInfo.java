package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 渠道商户信息
 * @author Moyq5
 * @date 2017年10月20日
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ChannelSubmerchInfo implements Serializable {

	private int id;
    private String branchno;// 机构号
    private String agentno;// 代理商号
    private String merchno;// 商户号
    private Date createdate;// 渠道商户号申请时间
    private String channelCode;// 渠道编码
    private String channelName;// 渠道名称
    private String channelSubmerchno;// 渠道分配的上游子商户号
    private String payType;// 支付类型,QUICKPAY： 快捷支付，WEIXI,ALIPAY
    private String aduitStatus;// 0: 待审核,1：审核通过,2: 审核拒绝
    private BigDecimal payFeeD0;// D0提现手续费
    private BigDecimal payFeeT1;// T1提现费
    private BigDecimal d0payRate;// D0支付费率
    private BigDecimal t1payRate;// T1支付费率
    private BigDecimal amountMin; //单笔最小金额
    private BigDecimal amountMax; //单笔最大金额
    private String payMethod;// 支付方式，WEIXIN,ALIPAY,QUICKPAY
    private String remark;// 备注
    private Integer modifyFeeType; //只能修改的费率类型
    private String yufuOneCodeUrl; //（渠道参数）一码付地址
    private String yufuKjKey; //（渠道参数）快捷支付key
    private String yufuTermId; //（渠道参数）终端号
}
