/*
 * 类文件名:  QrcodeTrans.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models.qrcode;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 交易信息对象
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QrcodeTrans implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1309683434792622562L;
    
    private Integer id; // 
    private String branchno; // 机构编码
    private String branchName; // 机构名称
    private String agentno;// 代理商号
    private String agentName;// 代理商名称
    private String merchno; // 商户编号
    private String merchName; // 商户名称
    private String transDate; // 交易日期
    private String transTime; // 交易时间
    private String orderno; // 商户订单号
    private BigDecimal transAmount; // 交易金额
    private String barCode; // 二维码信息
    private String partnerId; // 渠道商户号
    private String termno; // 渠道终端号
    private String channelOrderno; // 渠道订单号
    private Integer payType; // 支付方式(1-支付宝 2-微信)
    private BigDecimal discountAmount; // 折扣金额
    private BigDecimal rebackAmount; // 退款金额
    private Integer status; // 订单状态
    private String remark; // 备注
    private Integer checkStatus; // 检查状态 0-未检查
    private String checkTime; // 检查时间
    private BigDecimal totalFee; // 商户手续费
    private BigDecimal channelFee; // 渠道费率
    private BigDecimal branchFee; // 平台费率
    private String traceno; // 系统流水号
    private String backupOrderno; // 备份订单号 渠道流水号
    private String channelCode; // 渠道编码
    private Integer scanType; // 扫码类型(1-主扫 2-被扫)
    private Integer interType; // 接口类型(1-手机APP 2-传统POS 3-接口接入)
    private String mobileSeller; // 卖家手机号
    private String mobileBuyer; // 买家手机号
    private String notifyUrl; // 通知地址
    private String returnUrl; // 返回地址
    private Integer settleType; // 结算类型(1-T+0 2-T+1)
    private String certno; // 身份证号
    private String accountno; // 结算卡号
    private String accountName; // 结算户名
    private BigDecimal t0AddFee; // T+0手续费
    private Integer payStatus; // 付款类型 0-处理中 1-付款成功 2-付款失败
    private String payDesc; // 付款说明
    private String mobile; // 手机号
    private String bankno; // 联行号
    private String bankName; // 银行名称
    private String bankType; // 银行类型
    private Integer liquidator; // 清算方(1-平台清算 2-虚拟账户)
    private Integer notifyStatus; // 通知状态 1-无需通知 2-通知成功 3-通知失败
    private Integer rechargeStatus; // 充值状态 1-无需充值 2-充值成功 3-充值失败
    private Integer paymentStatus; // 代付状态 1-无需代付 2-代付成功 3-代付失败
    private BigDecimal paymentFee;// 代付费（渠道手续费中已经包括该项费用）
    private String bankCardType;
    private String payerRemark;// 付款人备注
    private Integer payerType;// 付款人类型：0非注册用户，1商户，2代理商
    private String payer;// 付款人：商户号、代理商号
    private Integer payerBiz;// 付款原因：0正常交易，1商户升级，2代理升级
}
