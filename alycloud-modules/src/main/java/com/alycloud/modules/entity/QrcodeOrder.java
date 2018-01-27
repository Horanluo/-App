package com.alycloud.modules.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alycloud.modules.enums.QrcodeOrderNotifyStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二维码订单
 * 
 * @author Moyq5
 * @date 2017年6月8日
 */
@SuppressWarnings("serial")
@JsonInclude(value=Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrcodeOrder implements Serializable {

	private Integer id;
	private String branchno;// 机构号
	private String branchName;// 机构名称
	private String agentno;// 代理商号
	private String agentName;// 代理商名称
	private String merchno;// 商户号
	private String merchName;// 商户名称
	private String transDate;// 交易日期
	private String transTime;// 交易时间
	private Integer payType;// 支付类型
	private Integer scanType;// 扫码方式
	private String traceno;// 商户流水号
	private String orderno;// 订单号
	private BigDecimal amount;// 交易金额
	private String notifyUrl;// 通知地址
	/**
	 * @see {@link QrcodeOrderNotifyStatus}
	 */
	private Integer notifyStatus;// 通知状态
	private String notifyDesc;// 通知结果描述
	private Integer notifyCount;// 累计通知次数
	private String notifyTime;// 最后一次通知时间
	private String returnUrl;// 返回地址
	private Integer settleType;// 结算方式(1-T+0结算 2-T+1结算)
	private Integer status;// 订单状态(0-下单成功 1-支付成功 2-支付失败)
	private String statusDesc;// 状态说明
	private String openId;// 用户OpenId（公众号支付时）
	private String appId;// APPID（商家APP直接调起微信或者支付宝支付时，商家APP的appId）
	private String channelCode;// 渠道编码
	private String channelMerchno;// 渠道商户号
	private String channelUrl;// 通道方交易地址
	private String channelOrderno;// 渠道单号
	private BigDecimal merchFee;// 商户手续费
	private BigDecimal channelFee;// 渠道手续费
	private BigDecimal branchFee;// 机构手续费
	private String certno;// 身份证号
	private String mobile;// 手机号
	private String accountno;// 结算卡号
	private String accountName;// 结算户名
	private String bankno;// 联行号
	private String bankType;// 银行类别
	private String bankName;// 支行名称
	private String bankCardType;// 银行卡类型
	private Integer queryCount;// 付款状态查询次数
	private String lastQueryTime;// 上次付款状态查询时间
	private BigDecimal paymentFee;// 代付费（渠道手续费中已经包含该项）
	private String payerRemark;// 付款人备注
	private Integer payerType;// 付款人类型：0非注册用户，1商户，2代理商
	private String payer;// 付款人：商户号、代理商号
	private Integer payerBiz;// 付款原因：0正常交易，1商户升级，2代理升级
	private Integer liquidateType;// 清算类型：0平台清算，1渠道清算
	private BigDecimal fee;
	private BigDecimal receiveFee;
	private BigDecimal rate;
}
