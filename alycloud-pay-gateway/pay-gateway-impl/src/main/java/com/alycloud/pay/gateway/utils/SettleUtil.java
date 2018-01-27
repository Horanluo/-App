/*
 * 类文件名:  SettleUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.pay.gateway.models.agent.AgentVirtualCard;


/**
 * 转发到settle项目的工具类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
public class SettleUtil
{
    /**
     * 字符编码
     */
    private static final String CHARSET = "GBK";
    
    private static Config config;

    /**
     * 使用公众号推送给商户交易成功信息
     * 
     * @param log
     *            日志对象
     * @param merchno
     *            商户号
     * @param transType
     *            交易类型(1-传统POS 2-MPOS 3-二维码 4-网关 5-快捷)
     * @param bizType
     *            业务类型(1-消费交易 2-撤销或冲正交易)
     * @param orderno
     *            交易单号
     * @param amount
     *            交易金额(以元为单位)
     */
    public static void offcialNotify(String merchno, String transType, String bizType, String orderno, BigDecimal amount) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("merchno=" + merchno);
            sb.append("&transType=" + transType);
            sb.append("&bizType=" + bizType);
            sb.append("&orderno=" + orderno);
            sb.append("&amount=" + amount);
            String url = config.getUrl() + "notifyOffical.do";
            String content = sb.toString();
            log.info("进行公众号通知,通知地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("公众号通知结果:" + result);
        } catch (Exception e) {
            log.error("公众号通知失败", e);
        }
    }

    /**
     * 使用网站推送给商户交易成功信息
     * 
     * @param log
     *            日志对象
     * @param merchno
     *            商户号
     * @param orderno
     *            交易单号
     * @param transType
     *            交易类型(1-银行卡 2-二维码 3-网关 4-快捷)
     * @param bizType
     *            业务类型(1-消费交易 2-撤销或冲正交易)
     * @param notifyType
     *            通知类型(只能为http或https)
     * @param notifyUrl
     *            通知地址
     * @param data
     *            通知数据(格式:key1=val1&key2=val2...&keyn=valn)
     */
    public static boolean merchNotify(String merchno, String orderno, String transType, String bizType, String notifyType, String notifyUrl,
            String data) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("merchno=" + merchno);
            sb.append("&transType=" + transType);
            sb.append("&bizType=" + bizType);
            sb.append("&orderno=" + orderno);
            sb.append("&notifyType=" + notifyType);
            sb.append("&url=" + URLEncoder.encode(notifyUrl, CHARSET));
            sb.append("&data=" + URLEncoder.encode(data, CHARSET));
            String url = config.getUrl() + "notifyMerch.do";
            String content = sb.toString();
            log.info("进行网站通知,通知地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("网站通知结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("网站通知失败", e);
            return false;
        }
    }

    /**
     * 虚拟账户充值
     * 
     * @param log
     *            日志对象
     * @param transType
     *            交易类型(1101-消费,1201-消费撤销,1202-消费冲正,2100-二维码交易,2200-二维码退款,3100-
     *            网关交易,4100-快捷交易)
     * @param bizType
     *            业务类型(1-传统POS 2-手机APP 4-二维码扫码 8-网关支付 16-快捷支付)
     * @param transDate
     *            交易日期(格式:yyyy-MM-dd)
     * @param transTime
     *            交易时间(格式:HH:mm:ss)
     * @param merchno
     *            商户号
     * @param orderno
     *            交易单号
     * @param amount
     *            交易金额
     * @param fee
     *            手续费
     * @param remark
     *            备注信息
     */
    public static boolean merchRecharge(String transType, String bizType, String transDate, String transTime, String merchno, String orderno,
            BigDecimal amount, BigDecimal fee, String remark) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("merchno=" + merchno);
            sb.append("&transType=" + transType);
            sb.append("&bizType=" + bizType);
            sb.append("&transDate=" + transDate);
            sb.append("&transTime=" + transTime);
            sb.append("&orderno=" + orderno);
            sb.append("&amount=" + amount);
            sb.append("&fee=" + fee);
            sb.append("&remark=" + URLEncoder.encode(remark, CHARSET));
            String url = config.getUrl() + "transRecharge.do";
            String content = sb.toString();
            log.info("进行虚拟账户充值,充值地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("虚拟账户充值结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("虚拟账户充值失败", e);
            return false;
        }
    }

    /**
     * 国采T+0代付
     * 
     * @param log
     *            日志对象
     * @param orderno
     *            订单号
     * @param amount
     *            交易金额
     * @param fee
     *            手续费
     * @param accountno
     *            结算卡号
     * @param accountName
     *            结算户名
     * @param mobile
     *            手机号码
     * @param bankno
     *            联行号
     * @param bankName
     *            银行支行名称
     * @param bankType
     *            银行类别
     */
    public static boolean merchPay(String orderno, BigDecimal amount, BigDecimal fee, String accountno, String accountName, String mobile,
            String bankno, String bankName, String bankType, SysTransType transType) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("transType=" + transType.ordinal());
            sb.append("&orderno=" + orderno);
            sb.append("&amount=" + amount);
            sb.append("&fee=" + fee);
            sb.append("&accountno=" + accountno);
            sb.append("&accountName=" + URLEncoder.encode(accountName, CHARSET));
            sb.append("&mobile=" + mobile);
            sb.append("&bankno=" + bankno);
            sb.append("&bankName=" + URLEncoder.encode(bankName, CHARSET));
            sb.append("&bankType=" + URLEncoder.encode(bankType, CHARSET));
            sb.append("&delayTime=60");
            String url = config.getUrl() + "t0Pay.do";
            String content = sb.toString();
            log.info("T+0交易成功,进行代付,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("T+0代付结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("T+0代付失败", e);
            return false;
        }
    }
    
    /**
     * 商户虚拟账户提现
     * @author Moyq5
     * @date 2017年3月14日
     * @param log
     * @param card
     * @return
     */
    public static DrawResult merchDraw(String cardno) {
        DrawResult drawResult = new DrawResult();
        drawResult.setRespCode("9999");
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("cardno=");
            sb.append(URLEncoder.encode(cardno, CHARSET));
            sb.append("&nonstr=");
            sb.append(URLEncoder.encode(noneStr, CHARSET));
            sb.append("&timestamp=");
            sb.append(timestamp);
            
            String signSrc = sb.toString() + "&key=" +  config.getKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "merchVirtualCard.do?m=draw";
            String content = sb.toString();
            log.info("商户虚拟账户提现,进行代付,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("提现代付结果:" + result);
            drawResult = JSONObject.parseObject(result, DrawResult.class);// TODO 没作签名验证
        } catch (Exception e) {
            log.error("提现代付失败", e);
            drawResult.setMessage(e.getMessage());
        }
        return drawResult;
    }
    
    /**
     * 代理商虚拟账户提现
     * @author Moyq5
     * @date 2017年3月20日
     * @param log
     * @param card
     * @return
     */
    public static DrawResult agentDraw(AgentVirtualCard card) {
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("cardno=");
            sb.append(URLEncoder.encode(card.getCardno(), CHARSET));
            sb.append("&nonstr=");
            sb.append(URLEncoder.encode(noneStr, CHARSET));
            sb.append("&timestamp=");
            sb.append(timestamp);
            
            String signSrc = sb.toString() + "&key=" + card.getPayKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "agentVirtualCard.do?m=draw";
            String content = sb.toString();
            log.info("代理商虚拟账户提现,进行代付,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("提现代付结果:" + result);
            return JSONObject.parseObject(result, DrawResult.class);// TODO 没作签名验证
        } catch (Exception e) {
            log.error("提现代付失败", e);
        }
        return null;
    }

    public static boolean merchRecharge(SysTransType transType, String orderno) {
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("nonstr=");
            sb.append(URLEncoder.encode(noneStr, CHARSET));
            sb.append("&orderno=");
            sb.append(URLEncoder.encode(orderno, CHARSET));
            sb.append("&timestamp=");
            sb.append(timestamp);
            sb.append("&transType=");
            sb.append(transType.ordinal());
            
            String signSrc = sb.toString() + "&key=" + config.getKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "merchVirtualCard.do?m=recharge";
            String content = sb.toString();
            log.info("商户虚拟账户充值,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("商户虚拟账户充值结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("商户虚拟账户充值失败", e);
            return false;
        }
    }
    
    /**
     * 商户升级
     * @author Moyq5
     * @date 2017年7月14日
     * @param refno
     */
    public static boolean merchUpgrade(MerchUpgradeType type, String refno) {
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("nonstr=");
            sb.append(noneStr);
            sb.append("&refno=");
            sb.append(refno);
            sb.append("&timestamp=");
            sb.append(timestamp);
            sb.append("&type=");
            sb.append(type.ordinal());
            
            String signSrc = sb.toString() + "&key=" + config.getKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "merchUpgrade.do?m=upgrate";
            String content = sb.toString();
            log.info("商户升级,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("商户升级结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("商户升级失败", e);
            return false;
        }
    }
    
    /**
     * 代理商虚拟账户充值（分润）
     * @author Moyq5
     * @date 2017年3月19日
     */
    public static boolean agentRecharge(String orderno) {
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("nonstr=");
            sb.append(URLEncoder.encode(noneStr, CHARSET));
            sb.append("&refno=");
            sb.append(orderno);
            sb.append("&timestamp=");
            sb.append(timestamp);
            
            String signSrc = sb.toString() + "&key=" + config.getKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "agentVirtualCard.do?m=recharge";
            String content = sb.toString();
            log.info("代理商虚拟账户充值,地址:" + url + ",发送数据:\r\n" + content);
            String result = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("代理商虚拟账户充值结果:" + result);
            return returnStatus(result);
        } catch (Exception e) {
            log.error("代理商虚拟账户充值失败", e);
            return false;
        }
    }
    
    /**
     * 开通代理
     * @author Moyq5
     * @date 2017年3月27日
     */
    public static VipOpenResult agentVipOpen(String orderno) {
        try {
            final String noneStr = CommonUtil.mkRandomStr(15);
            final long timestamp = new Date().getTime();
            StringBuffer sb = new StringBuffer();
            sb.append("nonstr=");
            sb.append(URLEncoder.encode(noneStr, CHARSET));
            sb.append("&refno=");
            sb.append(orderno);
            sb.append("&timestamp=");
            sb.append(timestamp);
            
            String signSrc = sb.toString() + "&key=" + config.getKey();
            log.info("签名数据:" + signSrc);
            final String signature = MD5.getMD5ofStr(signSrc, "GBK");
            
            sb.append("&signature=");
            sb.append(signature);
            
            String url = config.getUrl() + "agentVipTrans.do?m=open";
            String content = sb.toString();
            log.info("代理开通,地址:" + url + ",发送数据:\r\n" + content);
            String resultStr = YzyuePost.receiveBySend(url, content, CHARSET);
            log.info("代理开通结果:" + resultStr);
            return JSONObject.parseObject(resultStr, VipOpenResult.class);// TODO 没作签名验证
        } catch (Exception e) {
            log.error("代理开通失败", e);
        }
        return null;
    }


    /**
     * 验证响应结果
     * @param result
     * @return
     */
    private static boolean returnStatus(String result){
        if(result == null || result.equals("")){
            return false;
        }
        JSONObject obj = JSONObject.parseObject(result);
        String respCode = obj.getString("respCode");
        ///响应51代表通知商户网站失败
        return "00".equals(respCode) || "51".equals(respCode);
    }

    public static class DrawResult {
        private String respCode;
        private String message;
        private String traceno;// 提现流水号
        public String getRespCode() {
            return respCode;
        }
        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getTraceno() {
            return traceno;
        }
        public void setTraceno(String traceno) {
            this.traceno = traceno;
        }
    }
    public static class VipOpenResult {
        private String respCode;
        private String message;
        private String payerOpenId;// 付款人OPENID
        private String payeeOpenId;// 收款人OPENID
        private String payerBranchno;// 付款人机构号
        private String payeeBranchno;// 收款人机构号
        public String getRespCode() {
            return respCode;
        }
        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getPayerOpenId() {
            return payerOpenId;
        }
        public void setPayerOpenId(String payerOpenId) {
            this.payerOpenId = payerOpenId;
        }
        public String getPayeeOpenId() {
            return payeeOpenId;
        }
        public void setPayeeOpenId(String payeeOpenId) {
            this.payeeOpenId = payeeOpenId;
        }
        public String getPayerBranchno() {
            return payerBranchno;
        }
        public void setPayerBranchno(String payerBranchno) {
            this.payerBranchno = payerBranchno;
        }
        public String getPayeeBranchno() {
            return payeeBranchno;
        }
        public void setPayeeBranchno(String payeeBranchno) {
            this.payeeBranchno = payeeBranchno;
        }
    }
    /**
     * 设置 config
     * @param 对config进行赋值
     */
    @Autowired
    public void setConfig(Config config)
    {
        SettleUtil.config = config;
    }
    
    
}
