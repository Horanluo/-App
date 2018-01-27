///*
// * 类文件名:  HfbankOrderSchedule.java
// * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
// * 功能描述:  <描述>
// * 类创建人:  曾云龙
// * 创建时间:  2017年8月17日
// * 功能版本:  V001Z0001
// */
//package com.alycloud.pay.gateway.schedule;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.alycloud.pay.gateway.api.IQrcodeChannelService;
//import com.alycloud.pay.gateway.api.IQrcodeMerchService;
//import com.alycloud.pay.gateway.api.IQrcodeOrderService;
//import com.alycloud.pay.gateway.api.IQrcodeQueryService;
//import com.alycloud.pay.gateway.api.IQrcodeSuccessService;
//import com.alycloud.pay.gateway.api.IQrcodeTransService;
//import com.alycloud.pay.gateway.inter.QrcodeIC;
//import com.alycloud.pay.gateway.mapper.MerchMapper;
//import com.alycloud.modules.merch.MerchBean;
//import com.alycloud.pay.gateway.models.qrcode.QrcodeChannelBean;
//import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchBean;
//import com.alycloud.pay.gateway.models.qrcode.QrcodeOrderBean;
//import com.alycloud.pay.gateway.models.qrcode.QrcodeTrans;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 恒丰银行订单查询
// * @author   曾云龙
// * @version  V001Z0001
// * @date     2017年8月17日
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Slf4j
//@Component
//@Configuration
//public class HfbankOrderSchedule
//{
//    
//    @Autowired 
//    private IQrcodeOrderService orderService;
//    
//    /**
//     * 渠道的业务处理对象
//     */
//    @Autowired
//    protected IQrcodeChannelService channelService;
//    
//    @Autowired
//    private IQrcodeMerchService qrcodeMerchService;
//    
//    @Autowired
//    private IQrcodeQueryService queryService; //二维码订单查询
//    
//    @Autowired
//    private IQrcodeTransService qrcodeTransService; //二维码交易查询
//    
//    @Autowired
//    private IQrcodeSuccessService qrcodeSuccessService;
//    
//    @Autowired
//    private MerchMapper merchMapper;
//    
//    
//    //每隔5秒查询下订单情况
////    @Scheduled(cron="0 0/1 8-20 * * ?") 
//    @Scheduled(cron="0/5 * * * * ?")
//    public void executeUploadTask() 
//    {
//        //1.查询下单成功的支付订单
//        //2.向通道查询订单状态
//        //3.支付成功处理结果
//        
//        log.info("################################################################");
//        log.info("###                     二维码交易自动查询");
//        log.info("################################################################");
////        String delayTime = conf.getInitParameter("interval");
//        int hour = 3;
//        while (true) {
//            try {
//                runTask(hour);
//            } catch (Exception e) {
//                log.error("自动查询失败", e);
//            } finally {
//                try {
////                    long time = Long.parseLong(delayTime);
//                    long time = 5000;
//                    log.info("自动休眠[" + time + "]毫秒");
//                    Thread.sleep(time);
//                } catch (Exception e) {
//                }
//            }
//        }
//        
//    }
//    
//    /**
//     * 执行任务
//     * 
//     * @throws Exception
//     */
//    protected void runTask(int hour) throws Exception {
//        log.info("查询最近20分钟内下单成功,但是尚未支付，尚未查询的订单信息");
//        int query_minute = 2000;
//        List<QrcodeOrderBean> data = orderService.listNotPay(query_minute);
//        int count = data.size();
//        if(count == 0){
//            log.warn("找不到尚未支付的订单信息");
//            return;
//        }
//        log.info("查找到[" + count + "]笔订单");
//        for(int i = 0; i < count; i++){
//            QrcodeOrderBean order = data.get(i);
//            log.info("[" + (i + 1) + "/" + count + "]开始处理订单信息:\r\n" + order);
//            String orderno = order.getOrderno();
//            try{
//                /** 查找交易路由 **/
//                log.info("[" + (i + 1) + "/" + count + "]根据渠道编码[" + order.getChannelCode() + "]获取渠道信息");
//                QrcodeChannelBean channel = channelService.getByChannelCode(order.getChannelCode());
//                log.info(channel.toString());
//                log.info("[" + (i + 1) + "/" + count + "]根据渠道编码[" + order.getChannelCode() + "]和渠道商户号[" + order.getChannelMerchno() + "]获取渠道商户信息");
//                QrcodeMerchBean qrcodeMerch = qrcodeMerchService.getByMerchno(order.getChannelCode(), order.getChannelMerchno());
//                log.info(qrcodeMerch.toString());
//                QrcodeTrans trans = qrcodeTransService.getByOrderno(order.getOrderno());
//                MerchBean merch = merchMapper.getByMerchno(order.getMerchno());
//                qrcodeMerch.setHfbankT1MerchNo(merch.getHfbankT1MerchNo());
//                qrcodeMerch.setHfbankD0MerchNo(merch.getHfbankD0MerchNo());
//    
//                /** 执行二维码查询交易 **/
//                Map<String, String> result = queryService.query(order, channel, qrcodeMerch);
//                String respCode = result.get("respCode");
////                if (order.getMerchno().equals("138440154110001")) {
////                    respCode = "1";
////                }
//                if ("1".equals(respCode)) {
//                    //PaySuccessFactory.getService(SysTransType.QRCODE).execute(orderno);
//                    trans = qrcodeSuccessService.buildQrcodeTrans(order, result.get("channelOrderno"), result.get("channelTraceno"));
//                    qrcodeSuccessService.process(order, trans, "客户端二维码查询,系统自动充值");
//                    orderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_SUCCESS, "支付成功");
//                    String bankCardType = result.get("bankCardType");
//                    if (null == bankCardType)
//                    {
//                        bankCardType = "";
//                    }
//                    orderService.updateBankCardType(order.getOrderno(), bankCardType);
//                } else if ("0".equals(respCode)) {
//                    orderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_NOT_PAY, "尚未支付");
//                    orderService.updateQueryInfo(order.getOrderno());
//                } else {
//                    orderService.updateStatusInfo(order.getOrderno(), QrcodeIC.ORDER_STATUS_FAILURE, "支付失败");
//                    orderService.updateQueryInfo(order.getOrderno());
//                }
//            }catch(Exception e){
//                log.error("处理订单[" + order.getOrderno()+ "]失败", e);
//            }
//        }
//    }
//}
