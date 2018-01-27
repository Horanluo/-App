//package com.alycloud.pay.gateway.schedule;
///*
// * 类文件名:  YufuChangeMerchRateSchedule.java
// * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
// * 功能描述:  <描述>
// * 类创建人:  曾云龙
// * 创建时间:  2017年9月21日
// * 功能版本:  V001Z0001
// */
//import java.math.BigDecimal;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.alycloud.channel.api.IQuickpayService;
//import com.alycloud.channel.factory.QuickpayFactory;
//import com.alycloud.channel.utils.hxtc.HxtcUtils;
//import com.alycloud.core.enums.SysChannelType;
//import com.alycloud.core.utils.DateUtil;
//import com.alycloud.core.utils.DateUtils;
//import com.alycloud.core.utils.JSONUtils;
//import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
//import com.alycloud.modules.channel.hfbank.HxtcBillsBean;
//import com.alycloud.modules.channel.hfbank.HxtcBillsResult;
//import com.alycloud.modules.channel.hfbank.HxtcStringUtils;
//import com.alycloud.modules.channel.yufu.AbstractYufuEntity;
//import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;
//import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
//import com.alycloud.modules.channel.yufu.YufuChannelMerchBiz;
//import com.alycloud.modules.channel.yufu.YufuMerchBean;
//import com.alycloud.modules.channel.yufu.YufuMerchPayServiceBean;
//import com.alycloud.modules.channel.yufu.enums.MerchState;
//import com.alycloud.modules.channel.yufu.enums.YufuPayServiceType;
//import com.alycloud.modules.merch.MerchBean;
//import com.alycloud.pay.gateway.api.channel.IChannelSubmerchService;
//import com.alycloud.pay.gateway.mapper.MerchMapper;
//import com.alycloud.pay.gateway.mapper.channel.YufuChannelMerchBizMapper;
//import com.alycloud.pay.gateway.mapper.channel.YufuChannelMerchMapper;
//
///**
// * <一句话功能简述> <功能详细描述>
// * 
// * @author 曾云龙
// * @version V001Z0001
// * @date 2017年9月21日
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Slf4j
//@Component
//@Configuration
//public class HxtcBillSchedule
//{
//    @Autowired
//    private MerchMapper merchMapper;
//    
//    @Autowired
//    private AbstractYufuEntity config;
//    
//    @Autowired
//    private IChannelSubmerchService submerchService;
//    
//    @Autowired
//    private YufuChannelMerchMapper yufuChannelMerchMapper;
//    
//    @Autowired
//    private YufuChannelMerchBizMapper yufuChannelMerchBizMapper;
//    
//    // 每隔5秒查询下订单情况
//    @Scheduled(cron = "1/5 * * * * ?")
//    public void executeUploadTask()
//    {
//        int hour = 3;
//        while (true)
//        {
//            try
//            {
//                runTask(hour);
//            }
//            catch (Exception e)
//            {
//                log.error("自动查询失败", e);
//            }
//            finally
//            {
//                try
//                {
//                    // long time = Long.parseLong(delayTime);
//                    long time = 5000;
//                    Thread.sleep(time);
//                }
//                catch (Exception e)
//                {
//                }
//            }
//        }
//        
//    }
//    
//    /**
//     * <一句话功能简述> <功能详细描述>
//     * 
//     * @author 曾云龙
//     * @version V001Z0001
//     * @throws Exception 
//     * @date 2017年9月21日
//     * @see [相关类/方法]
//     * @since [产品/模块版本]
//     */
//    private void runTask(int hour) throws Exception
//    {
//
//            IQuickpayService quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.HXTC.getCode());
//            Calendar start = Calendar.getInstance();  
//            start.set(2017, 9, 11);  
//            Long startTIme = start.getTimeInMillis();  
//          
//            Calendar end = Calendar.getInstance();  
//            end.set(2017, 9, 11);  
//            Long endTime = end.getTimeInMillis();  
//          
//            Long oneDay = 1000 * 60 * 60 * 24l;  
//            List<HxtcBillsBean> list = new ArrayList<HxtcBillsBean>();
//            Long time = startTIme;  
//            while (time <= endTime) {  
//                Date d = new Date(time);  
//                DateFormat df = new SimpleDateFormat("yyyyMMdd");  
//                System.out.println(df.format(d));  
//                String queryDate = df.format(d);
//                Map<String, String> queryParams = HxtcUtils.quickPayBillQuery(queryDate);
//                String res = quickpayService.billQuery(queryParams);
////                Map<String, Object> mapData = JSONUtils.json2map(res);
//                if(!StringUtils.isEmpty(res) && !res.startsWith("{}"))
//                {
//                    HxtcBillsResult result = JSONUtils.json2pojo(res, HxtcBillsResult.class);
////                    List<String> datas = JSONUtils.json2list(mapData.get("bills").toString(), String.class);
//                    for(String data : result.getBills())
//                    {
//                        String[] tmpDatas = data.split("\\|");
//                        HxtcBillsBean bean = new HxtcBillsBean();
//                         bean.setTransTime(tmpDatas[0]);;
//                         bean.setOrderno(tmpDatas[1]);;
//                         bean.setStatus(tmpDatas[2]);;
//                         bean.setAmount(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[3]));
//                         bean.setD0rate(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[4]));;
//                         bean.setD0payfee(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[5]));;
//                         bean.setT1rate(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[6]));;
//                         bean.setT1payfee(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[7]));;
//                         bean.setTransferCharge(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[8]));;
//                         bean.setActualAmount(HxtcStringUtils.stringConvertToBigDecimal(tmpDatas[9]));;
//                         bean.setCashStatus(tmpDatas[10]);;
//                         list.add(bean);
//                    } 
//                }
//                
//                time += oneDay;  
//            } 
//            
//            submerchService.batchInsertHxtcBills(list);  
//    }
//}
