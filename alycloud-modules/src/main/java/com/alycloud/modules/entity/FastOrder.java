package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 快捷支付订单
 * @author Moyq5
 * @date 2017年4月20日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrder implements Serializable {

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
	private String payerRemark;// 付款人备注
	private Integer liquidateType;// 清算类型：0平台清算，1渠道清算
	
	//下游订单号
    private String outTraceno;// 
    
    /**
     * 后台异步回调地址
     */
    private String  notifyUrl;
    
    
    /**
     * 前台同步回调地址
     */
    private String  callbackUrl;
    
    /**
     * 提现费
     */
    private BigDecimal fee;
    
    /**
     * 收款手续费
     */
    private BigDecimal receiveFee;
    
    /**
     * 收款费率
     */
    private BigDecimal rate;
}
