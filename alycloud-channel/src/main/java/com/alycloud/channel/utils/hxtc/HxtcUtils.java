/*
 * 类文件名:  HxtcUtils.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.utils.hxtc;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.utils.DateUtils;
import org.xiajinsuo.epay.sdk.HttpUtils;
import org.xiajinsuo.epay.sdk.RRParams;
import org.xiajinsuo.epay.sdk.ResponseDataWrapper;
import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.SystemCardBin;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年8月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HxtcUtils
{
    private static DecimalFormat df = new DecimalFormat("0.00");
    
    public static Map<String, String> responsePayBuilder(FastOrder order, ChannelSubmerchInfo sumerchInfo, SystemCardBin cardBin, String notifyUrl, String returnUrl) throws Exception {
    	MerchInfo merch = new MerchInfo();
    	merch.setMobile(order.getMobile());
    	merch.setAccountName(order.getTrueName());
    	merch.setIdentityCard(order.getIdCard());
    	merch.setAccountno(order.getBankCard());
    	ChannelSubmerchInfoBean submerch = new ChannelSubmerchInfoBean();
    	submerch.setD0payRate(sumerchInfo.getD0payRate());
    	submerch.setPayFeeD0(sumerchInfo.getPayFeeD0());
    	submerch.setChannelSubmerchno(sumerchInfo.getChannelSubmerchno());
    	return responsePayBuilder(merch, order.getAmount(), order.getBankCard(),
    	        order.getOrderno(), submerch, cardBin.getBankName(), notifyUrl, returnUrl);
    }
    
    public static Map<String, String> responsePayBuilder(MerchInfo bean, BigDecimal amount, String payBankno,
        String orderNo, ChannelSubmerchInfoBean channelSubmerchInfo, String bankType, String backNotifyUrl, String callbackUrl)
        throws Exception
    {
        Map<String, String> businessReq = new HashMap<String, String>();
        businessReq.put("mobile", bean.getMobile());
        businessReq.put("family_name", bean.getAccountName());
        businessReq.put("id_card", bean.getIdentityCard());
        businessReq.put("pay_bank_no", payBankno);
        
        businessReq.put("trans_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        businessReq.put("trans_date", DateUtils.formatDate(new Date(), "yyyyMMdd"));
        BigDecimal amountFen = amount.multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        businessReq.put("trans_amount", amountFen.toString());// 单位为分
        BigDecimal fastPayRateT0 = channelSubmerchInfo.getD0payRate();
        BigDecimal fastPayRateT0Bai = fastPayRateT0.multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 手续费计算
        BigDecimal feeD0 = amountFen.multiply(fastPayRateT0).setScale(0, BigDecimal.ROUND_UP);
        
        // 提现费转换成分counterFee
        BigDecimal counterFeeToFen =
            channelSubmerchInfo.getPayFeeD0().multiply(new BigDecimal(100)).setScale(0, BigDecimal.ROUND_HALF_UP);
        businessReq.put("rate_t0", String.valueOf(fastPayRateT0Bai));// 汇享天成手续费是按照百分比计算的
        // 提现费
//        if (bean.getMerchno().equals("001440154110006"))
//        {
//            counterFeeToFen = new BigDecimal(0);
//        }
        
        businessReq.put("counter_fee_t0", counterFeeToFen.toString());
        
        businessReq.put("operation_fee", String.valueOf(feeD0));
        // businessReq.put("pay_amount", String.valueOf(amountFen.subtract(feeD0).setScale(0,
        // BigDecimal.ROUND_HALF_UP)));
        businessReq.put("pay_amount",
            String.valueOf(amountFen.subtract(feeD0).subtract(counterFeeToFen).setScale(0, BigDecimal.ROUND_FLOOR)));
        
        // 结算账户
        // String bankType = merchDao.getBankByCardno(bean.getAccountno());
        // String frontNotifyUrl = baseUrl + "fastNotify.do?m=hxtcNofity&merchno="+bean.getMerchno();
        // frontNotifyUrl = URLEncoder.encode(frontNotifyUrl, "UTF-8");
        businessReq.put("payee_bank_name", bankType);
        businessReq.put("payee_bank_no", bean.getAccountno());
        businessReq.put("back_notify_url", backNotifyUrl);
        //businessReq.put("back_notify_url", "http://cp.esyto.com:8081/pay/cloudplatform/api/quickPay/notify/hxtc");
        // businessReq.put("front_notify_url", frontNotifyUrl);
        businessReq.put("front_notify_url", callbackUrl);
        businessReq.put("memo", "测试");
        businessReq.put("method", "pay");
        // businessReq.put("payee_bank_id", "308584000013");
        businessReq.put("third_merchant_code", channelSubmerchInfo.getChannelSubmerchno());
//        RRParams requestData =
//            RRParams.newBuilder()
//                .setAppId(HxtcConfig.appId)
//                .setClientTransId(orderNo)
//                .setTransTimestamp(System.currentTimeMillis())
//                .setTransType(HxtcConfig.transType)
//                .build();
//        ResponseDataWrapper rdw =
//            HttpUtils.post(HxtcConfig.REQUEST_URL,
//                requestData,
//                businessReq,
//                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
//                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
//        Map<String, String> result = new HashMap<String, String>();
//        if (rdw.getRespCode().equals("000000"))
//        {
//            result = rdw.getResponseData();
//            result.put("channelOrderno", rdw.getServerTransId());
//            result.put("responseCode", "0");
//            result.put("respMsg", rdw.getRespMsg());
//            result.put("pageContent", result.get("page_content"));
//            System.out.println(result);
//        }
//        else
//        {
//            result.put("responseCode", "1");
//            result.put("respMsg", rdw.getRespMsg());
//            result.put("pageContent", rdw.getRespMsg());
//        }
//        return result;
        return businessReq;
    }
    
    public static Map<String, String> quickPayOrderQuery(String orderno)
    {
        Map<String, String> businessReq = new HashMap();
        businessReq.put("method", "pay_qry");
        businessReq.put("orig_tran_id", orderno);
        RRParams requestData =
            RRParams.newBuilder()
                .setAppId(HxtcConfig.appId)
                .setClientTransId(generateTransId())
                .setTransTimestamp(System.currentTimeMillis())
                .setTransType(HxtcConfig.transType)
                .build();
        ResponseDataWrapper rdw =
            HttpUtils.post(HxtcConfig.REQUEST_URL,
                requestData,
                businessReq,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPri,
                MypaysRsaDataEncryptUtil.rsaDataEncryptPub);
        Map<String, String> result = new HashMap<String, String>();
        if (rdw.getRespCode().equals("000000"))
        {
            result = rdw.getResponseData();
            return result;
        }
        else
        {
            result.put("resp_code", "ERROR_CODE");
        }
        return result;
    }
    
    /**
     * 构建交易流水号 14位
     *
     * @return
     */
    static String generateTransId()
    {
        String time = DateUtils.formatDate(new Date(), "yyyyMMdd");
        String nanoTime = System.nanoTime() + "";
        return String.format("%s%s", time, nanoTime.substring(nanoTime.length() - 6));
    }
    
    /**
     * 构建汇享天成子商户注册
     * 
     * @author 曾云龙
     * @version V001Z0001
     * @date 2017年9月7日
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static Map<String, String> builderRegisterRequestParams(MerchInfo bean, String bankId,ChannelSubmerchInfo newRecord)
    {
        Map<String, String> businessReq = new HashMap<String, String>();
        if("HXTC".equals(newRecord.getChannelCode())){
        	businessReq.put("merchant_code", "YSYZWZ" + bean.getMerchno());
        	businessReq.put("channel_flag", "channel_no_jf");
        }
        
        if("HXTC_JF".equals(newRecord.getChannelCode())){
        	businessReq.put("merchant_code", "YSYZWZ" + bean.getMerchno()+"A");
        	businessReq.put("channel_flag", "channel_jf");
        }
        
        businessReq.put("merchant_name", bean.getMerchName());
        businessReq.put("merchant_province", bean.getProvince());
        businessReq.put("merchant_city", bean.getCity());
        businessReq.put("merchant_address", bean.getAddress());
        businessReq.put("family_name", bean.getAccountName());
        businessReq.put("id_card", bean.getIdentityCard());
        businessReq.put("mobile", bean.getMobile());
        businessReq.put("trans_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        businessReq.put("memo", "购买商品");
        businessReq.put("payee_bank_id", bankId);
        businessReq.put("payee_bank_no", bean.getAccountno());
        businessReq.put("payee_bank_name", bean.getBankName());
        businessReq.put("payee_branch_name", "");
        businessReq.put("payee_branch_code", "");
        businessReq.put("payee_bank_province", "");
        businessReq.put("payee_bank_city", "");
        
        businessReq.put("rate_t0", df.format(newRecord.getD0payRate().multiply(new BigDecimal(100))));
        businessReq.put("counter_fee_t0", newRecord.getPayFeeD0().multiply(new BigDecimal("100")).setScale(0).toPlainString());
        businessReq.put("rate_t1", df.format(newRecord.getT1payRate().multiply(new BigDecimal(100))));
        businessReq.put("counter_fee_t1", "0");
        businessReq.put("merchant_oper_flag", "A");
        businessReq.put("method", "register");
        
        return businessReq;
    }
    
    public static Map<String, String> builderRegisterRequestParams(MerchInfo bean, String bankId,double t0Fee)
    {
        Map<String, String> businessReq = new HashMap<String, String>();
        businessReq.put("merchant_code", "YSY-ZWZ" + bean.getMerchno());
        businessReq.put("merchant_name", bean.getMerchName());
        businessReq.put("merchant_province", bean.getProvince());
        businessReq.put("merchant_city", bean.getCity());
        businessReq.put("merchant_address", bean.getAddress());
        businessReq.put("family_name", bean.getAccountName());
        businessReq.put("id_card", bean.getIdentityCard());
        businessReq.put("mobile", bean.getMobile());
        businessReq.put("trans_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        businessReq.put("memo", "购买商品");
        businessReq.put("payee_bank_id", bankId);
        businessReq.put("payee_bank_no", bean.getAccountno());
        businessReq.put("payee_bank_name", bean.getBankName());
        businessReq.put("payee_branch_name", "");
        businessReq.put("payee_branch_code", "");
        businessReq.put("payee_bank_province", "");
        businessReq.put("payee_bank_city", "");
        
        businessReq.put("rate_t0", df.format(bean.getFastpayRateT0()));
        businessReq.put("counter_fee_t0", new BigDecimal(t0Fee).setScale(0).toPlainString());
        businessReq.put("rate_t1", df.format(bean.getFastpayRateT1()));
        businessReq.put("counter_fee_t1", "0");
        businessReq.put("merchant_oper_flag", "A");
        businessReq.put("method", "register");
        return businessReq;
    }
    
    /**
     * 构建汇享天成子商户注册
     * 
     * @author 曾云龙
     * @version V001Z0001
     * @date 2017年9月7日
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static Map<String, String> builderModifyRequestParams(MerchInfo bean, String bankId, ChannelSubmerchInfo newRecord)
    {
        Map<String, String> businessReq = new HashMap<String, String>();
        if("HXTC".equals(newRecord.getChannelCode())){
        	businessReq.put("merchant_code", newRecord.getChannelSubmerchno());
        	businessReq.put("channel_flag", "channel_no_jf");
        }
        
        if("HXTC_JF".equals(newRecord.getChannelCode())){
        	businessReq.put("merchant_code", newRecord.getChannelSubmerchno());
        	businessReq.put("channel_flag", "channel_jf");
        }
        //businessReq.put("merchant_code", "YSY-ZWZ" + bean.getMerchno());
        businessReq.put("merchant_name", bean.getMerchName());
        businessReq.put("merchant_province", bean.getProvince());
        businessReq.put("merchant_city", bean.getCity());
        businessReq.put("merchant_address", bean.getAddress());
        businessReq.put("family_name", bean.getAccountName());
        businessReq.put("id_card", bean.getIdentityCard());
        businessReq.put("mobile", bean.getMobile());
        businessReq.put("trans_time", DateUtils.formatDate(new Date(), "yyyyMMddHHmmss"));
        businessReq.put("memo", "购买商品");
        businessReq.put("payee_bank_id", bankId);
        businessReq.put("payee_bank_no", bean.getAccountno());
        businessReq.put("payee_bank_name", bean.getBankName());
        businessReq.put("payee_branch_name", "");
        businessReq.put("payee_branch_code", "");
        businessReq.put("payee_bank_province", "");
        businessReq.put("payee_bank_city", "");
        
        businessReq.put("rate_t0", df.format(newRecord.getD0payRate().multiply(new BigDecimal(100))));
        businessReq.put("counter_fee_t0", newRecord.getPayFeeD0().multiply(new BigDecimal("100")).setScale(0).toPlainString());
        businessReq.put("rate_t1", df.format(newRecord.getT1payRate().multiply(new BigDecimal(100))));
        businessReq.put("counter_fee_t1", "0");
        businessReq.put("merchant_oper_flag", "M04");
        businessReq.put("method", "register");
        return businessReq;
    }
    
    /**
     * 对账单查询
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月29日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static Map<String, String> quickPayBillQuery(String date)
    {
        Map<String, String> businessReq = new HashMap();
        businessReq.put("method", "BILL");
        businessReq.put("trans_date", date); //订单日期，YYYYMMDD
        
        return businessReq;
    }
}
