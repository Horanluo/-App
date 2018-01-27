/*
 * 类文件名:  AbstractHfbank.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.channel.qrcode.hfbank;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.alycloud.pay.gateway.channel.util.HfbankPayChannelEnum;
import com.alycloud.pay.gateway.channel.util.HfbankServiceCode;
import com.alycloud.pay.gateway.channel.util.HttpsClientUtil;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.core.qrcode.AbstractQrcodePay;
import com.alycloud.pay.gateway.exception.YzyueException;
import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 恒丰银行二维码支付接口
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月27日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractHfbank extends AbstractQrcodePay
{
    /**
     * 编码
     */
    protected static final String CHARSET = "UTF-8";
    
    static String requestUrl = "https://apihf.onepaypass.com/aps/cloudplatform/api/trade.html";
    
    /**
     * 交易查询的构造方法
     * 
     * @param channel 渠道信息
     * @param merch 渠道商户信息
     */
    protected AbstractHfbank(QrcodeChannelBean channel, QrcodeMerchBean merch)
    {
        super(channel, merch);
    }
    
    /**
     * T+1支付交易的构造方法
     * 
     * @param channel 渠道信息
     * @param merch 渠道商户信息
     * @param merchName 商户名称
     * @param payType 商户名称
     */
    public AbstractHfbank(QrcodeChannelBean channel, QrcodeMerchBean merch, String merchName, int payType)
    {
        super(channel, merch, merchName, payType);
    }
    
    /**
     * 二维码公众号支付
     * 
     * @param settleType 结算类型(1-T+0结算 2-T+1结算)
     * @param orderno 订单号
     * @param amount 交易金额
     * @param openId 用户的OPEN-ID
     * @return
     * @throws Exception
     */
    public Map<String, String> officalPay(int settleType, String orderno, long amount, String openId)
        throws YzyueException, Exception
    {
        return officalPay(settleType, orderno, amount);
    }
    
    /**
     * 判断是否支持T+0交易
     * 
     * @return
     */
    public boolean supportT0()
    {
        return true;
    }
    
    /**
     * 判断是否支持信用卡
     * 
     * @return
     */
    public boolean noCredit()
    {
        return true;
    }
    
    /**
     * 判断是否需要OpenId
     * 
     * @return
     */
    public boolean needOpenId()
    {
        return false;
    }
    
    /**
     * 构造支付的请求信息
     * 
     * @param orderno
     * @param amount
     * @param settleType
     * @return
     */
    protected Map<String, String> buildPayRequest(String orderno, long amount, int settleType)
        throws YzyueException
    {
        
        String service = "";
//        if (1 == payType)
//        {
//            // 支付宝
//            service = HfbankPayChannelEnum.ALIPAY_QR.getValue();
//        }
//        else if (2 == payType)
//        {
//            // 微信扫码支付
//            // service = "SMZF004";
//            // 微信公众号支付模式
//            service = HfbankPayChannelEnum.WEIXIN_PUBQR.getValue();
//        }
//        else
//        {
//            throw new YzyueException("05", "支付方式有误");
//        }
        
        if(channel.getChannel() == null)
        {
            throw new YzyueException("05", "支付方式有误");
        }
        
        service = channel.getChannel();
        String key = channel.getKeyMd5();
        
//        String requestUrl = channel.getUrlRequest();
        
        // 代理商号-mchId
        String mchId = merch.getChannelMerchno();
        
        // 商户号-subMchId
        // String subMchId = "000100003000000003";
//        String subMchId = "000100004000000010";
        //默认T1账户
        String subMchId = merch.getHfbankT1MerchNo();
        if(settleType == 1)
        {
            subMchId = merch.getHfbankD0MerchNo();
        }
        
        // 商品描述-body
        String body = merch.getMerchName() + "收款";
        
        // 交易金额-amount
        // String amount = "";
        
        // 附加数据-description
        String description = "";
        
        // 货币类型-currency
        String currency = "CNY";
        
        // 订单支付时间-timePaid
        String timePaid = "";
        
        // 订单失效时间-timeExpire
        String timeExpire = "";
        
        // 商户订单号-outTradeNo
        String outTradeNo = orderno;
        
        // 商品的标题-subject
        // 京东支付、网关支付、快捷支付、QQ钱包为必填
        String subject = "联想笔记本电脑";
        
        // 获取参数
        Map<String, String> param = new HashMap<String, String>();
        param.put("tradeType", HfbankServiceCode.TRADETYPE_SUBMIT);
        param.put("version", HfbankServiceCode.VERSION);
        param.put("mchId", mchId);
        
        param.put("channel", channel.getChannel());
        
        // 终端类型
        // param.put("terminalType", value);
        
        // 操作员
        // param.put("cashierNo", value);
        // param.put("subject", subject);
        param.put("subMchId", subMchId);
        
        param.put("body", body);
        param.put("outTradeNo", outTradeNo);
        
        // 分转成元
        String money = new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2).toString();
        param.put("amount", money);
        // param.put("description", description);
        param.put("currency", currency);
        
        // param.put("timePaid", value);
        // param.put("timeExpire", value);
        
        // param.put("sign", value);
        
        // 过滤空值或null
        Map<String, String> filterMap = SignUtil.paraFilter(param);
        // String channel = filterMap.get("channel");
        
        // 拼接
        String toSign = SignUtil.createLinkString(filterMap);
        
        // 生成签名sign
        String sign = SignUtil.genSign(key, toSign);
        filterMap.put("sign", sign.toUpperCase());
        return filterMap;
    }
    
    /**
     * 构造查询的请求信息
     * 
     * @param orderno
     * @return
     */
    protected Map<String, String> buildQueryRequest(String orderno, int settleType)
    {
        //生成签名的key
        String key = channel.getKeyMd5();
        
        // 代理商号-mchId
        String mchId = merch.getChannelMerchno();
        
        // 商户号-subMchId
        // String subMchId = "000100003000000003";
//        String subMchId = "000100004000000010";
        //默认T1账户
        String subMchId = merch.getHfbankT1MerchNo();
        if(settleType == 1)
        {
            subMchId = merch.getHfbankD0MerchNo();
        }
        
        
        // 商户订单号-outTradeNo
        String outTradeNo = orderno;
        
        // 获取参数
        Map<String, String> param = new HashMap<String, String>();
        param.put("tradeType", HfbankServiceCode.TRADETYPE_ORDER_QUERY);
        param.put("version", HfbankServiceCode.VERSION);
        param.put("mchId", mchId);
        param.put("subMchId", subMchId);
        param.put("outTradeNo", outTradeNo);
        param.put("queryType", String.valueOf(merch.getQueryType()));
        
//        Map<String, String> map = RequestUtils.getRequestMapValue(param);
        
        //过滤空值或null
        Map<String, String> filterMap = SignUtil.paraFilter(param);
        
        //拼接
        String toSign = SignUtil.createLinkString(filterMap);
        
        //生成签名sign
        String sign = SignUtil.genSign(key, toSign);
        filterMap.put("sign", sign);

        
        return filterMap;
    }
    
    /**
     * 构造查询的请求信息
     * 
     * @param orderno
     * @return
     */
    protected Map<String, String> buildRefundOrderRequest(String orderno,long amount, int settleType)
    {
        //生成签名的key
        String key = channel.getKeyMd5();
        
        // 代理商号-mchId
        String mchId = merch.getChannelMerchno();
        
        //默认T1账户 ,商户号-subMchId
        String subMchId = merch.getHfbankT1MerchNo();
        if(settleType == 1)
        {
            subMchId = merch.getHfbankD0MerchNo();
        }
        
        
        // 商户订单号-outTradeNo
        String outTradeNo = orderno;
        
        // 获取参数
        Map<String, String> param = new HashMap<String, String>();
        param.put("tradeType", HfbankServiceCode.TRADETYPE_REFUND_APPLY);
        param.put("version", HfbankServiceCode.VERSION);
        param.put("mchId", mchId);
        param.put("subMchId", subMchId);
        param.put("channel", channel.getChannel());
        // 分转成元
        String money = new BigDecimal(amount).divide(new BigDecimal(100)).setScale(2).toString();
        param.put("outTradeNo", outTradeNo);
        param.put("outRefundNo", outTradeNo);
        param.put("amount",  money);
        param.put("description", "商户退款");
        param.put("callbackUrl", "http://api.esyto.com/hfbank/refundOrder/callbackUrl");
        param.put("notifyUrl", "http://api.esyto.com/hfbank/refundOrder/notifyUrl");
        
//        Map<String, String> map = RequestUtils.getRequestMapValue(param);
        
        //过滤空值或null
        Map<String, String> filterMap = SignUtil.paraFilter(param);
        
        //拼接
        String toSign = SignUtil.createLinkString(filterMap);
        
        //生成签名sign
        String sign = SignUtil.genSign(key, toSign);
        filterMap.put("sign", sign);

        
        return filterMap;
    }
    
    /**
     * 向江苏电子发起数据请求
     * 
     * @param param
     * @return
     * @throws YzyueException
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws Exception
     */
    public Map<String, String> receiveBySend(Map<String, String> param)
        throws YzyueException, JsonParseException, JsonMappingException, IOException
    {
        // 转为json串
        ObjectMapper mapper = new ObjectMapper();
        
        String postStr = mapper.writeValueAsString(param);
        // String requestUrl = channel.getUrlRequest();
        
        // 发送请求
        String returnStr = HttpsClientUtil.sendRequest(requestUrl, postStr, "application/json");
        
        // //解析返回串
        // Map<String, String> returnMap = (Map<String, String>)JSON.parse(returnStr);
        //
        // //验签
        // if(SignUtil.validSign(returnMap, key)){
        // String returnCode = returnMap.get("returnCode");
        // String resultCode = returnMap.get("resultCode");
        // if(returnCode.equals("0")&&resultCode.equals("0")){
        // //根据不同渠道类型做处理
        // request.setAttribute("channel", channel);
        // if(channel.equals("wxPubQR") || channel.equals("alipayQR") || channel.equals("jdQR") ){
        // //展示二维码，请商户调用第三方库将code_url生成二维码图片
        // request.setAttribute("codeUrl", returnMap.get("codeUrl"));
        // }else if(channel.equals("wxApp")){
        // //请商户调用sdk控件发起支付
        // request.setAttribute("payCode", returnMap.get("payCode"));
        // }else if(channel.equals("wxMicro") || channel.equals("alipayMicro") || channel.equals("jdMicro")){
        // //支付成功后的处理，更新订单状态等
        // }else {
        // String payCode = returnMap.get("payCode");
        // if(StringUtils.indexOf(payCode, "http") == 0){
        // //request.setAttribute("payCode", returnMap.get("payCode"));
        // return "redirect:" + returnMap.get("payCode");
        // }else{
        // request.setAttribute("payCode", payCode);
        // }
        // }
        // }else{
        // request.setAttribute("resultCode", returnMap.get("resultCode"));
        // request.setAttribute("errCodeDes", returnMap.get("errCodeDes"));
        // request.setAttribute("returnMsg", returnMap.get("returnMsg"));
        // }
        // }else {
        // request.setAttribute("errorMsg", "验签错误");
        // return "errorPage";
        // }
        //
        // String reqMsg = JszdMsgUtils.getReqMsg(param);
        //
        // String respMsg = HttpClient.sendPost(channel.getUrlRequest(), reqMsg);
        return (Map<String, String>)mapper.readValue(returnStr, Map.class);
    }
    
    /**
     * 使用 Map按key进行排序
     * 
     * @param map
     * @return
     */
    // public static Map<String, String> sortMapByKey(Map<String, String> map) {
    // if (map == null || map.isEmpty()) {
    // return null;
    // }
    // Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
    // sortMap.putAll(map);
    // return sortMap;
    // }
    public Map<String, String> request(String method, Map<String, Object> data, String orderno)
        throws Exception
    {
        // logger.info("华付通请求地址:" + channel.getUrlRequest());
        // logger.info("华付通数据明文:" + FormatUtil.formatMap(data));
        // HftUtil util = new HftUtil(channel.getKeyRsa(), channel.getMerchRsaKey());
        //
        // /** 创建报文体信息 **/
        // Map<String, Object> bodyParam = new HashMap<String, Object>();
        // bodyParam.put("body", data);
        // String bodyInfo = JSON.toJSONString(bodyParam);
        //
        // String aesKey = HftUtil.rand(16);
        // String signData = new String(Base64.encodeBase64(util.signature(bodyInfo.getBytes(CHARSET))), CHARSET);//数据签名
        // String encrtptKey = new String(Base64.encodeBase64(util.encryptByRSA(aesKey)), CHARSET);//秘钥密文
        // Map<String, Object> headParam = buildHeadParam(method, orderno, signData, encrtptKey);
        //
        // String encryptData = new String(Base64.encodeBase64((util.encryptByAES(bodyInfo, aesKey))), CHARSET);
        // String result = buildRequestJsonPost(headParam, encryptData);
        // logger.info("华付通响应数据:\r\n" + result);
        // Map<String, String> res = new HashMap<String, String>();
        // JSONObject resultObj = JSONObject.parseObject(result);
        // JSONObject headObj = resultObj.getJSONObject("head");
        // String respCode = headObj.getString("respCode");
        // if("000000".equals(respCode)){
        // respCode = "00";
        // }
        // res.put("respCode", respCode);
        // res.put("message", headObj.getString("respDesc"));
        // String encryptKey = headObj.getString("encryptKey");
        // String content = resultObj.getString("body");
        // if (content != null && encryptKey != null) {
        // byte[] a = Base64.decodeBase64(encryptKey.getBytes(CHARSET));
        // byte[] b = util.decryptByRSA(a);
        // byte[] c = Base64.decodeBase64(content.getBytes(CHARSET));
        // byte[] d = util.decryptByAES(c, b);
        // String plainBody = new String(d, CHARSET);
        // logger.info("响应报文明文:\r\n" + plainBody);
        // JSONObject bodyObj = JSONObject.parseObject(plainBody);
        // if(bodyObj.containsKey("hfMerchantNo")){
        // res.put("channelMerchno", bodyObj.getString("hfMerchantNo"));
        // }
        // if(bodyObj.containsKey("payStr")){
        // res.put("barCode", bodyObj.getString("payStr"));
        // }
        // /** 交易查询的时候用到 **/
        // if(bodyObj.containsKey("orderNo")){
        // res.put("channelOrderno", bodyObj.getString("orderNo"));
        // }
        // /** 查询余额的时候用到 **/
        // if(bodyObj.containsKey("balance")){
        // res.put("balance", bodyObj.getString("balance"));
        // }
        // if(bodyObj.containsKey("wxBalalce")){
        // res.put("WXSMZF", bodyObj.getString("wxBalalce"));
        // }
        // if(bodyObj.containsKey("wxgzhBalance")){
        // res.put("WXGZHZF", bodyObj.getString("wxgzhBalance"));
        // }
        // if(bodyObj.containsKey("zfbBalance")){
        // res.put("ZFBSMZF", bodyObj.getString("zfbBalance"));
        // }
        // if(bodyObj.containsKey("orderStatus")){
        // String orderStatus = bodyObj.getString("orderStatus");
        // if("02".equals(orderStatus)){
        // res.put("respCode", "00");
        // }else if("00".equals(orderStatus) || "01".equals(orderStatus)){
        // res.put("respCode", "10");
        // res.put("message", "待支付");
        // }else{
        // res.put("respCode", orderStatus);
        // }
        // }
        // }
        // return res;
        return null;
    }
}
