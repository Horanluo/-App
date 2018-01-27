/*
 * 类文件名:  DateUtil.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DateUtil
{
   /**
    * 日期的默认格式
    */
   public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
   
   /**
    * 时间的默认格式
    */
   public static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
   
   /**
    * 日期时间的默认格式
    */
   public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   
   /**
    * 获取起始日期
    * 
    * @param date
    * @return
    */
   public String getStartDate(String date) {
       return date + " 00:00:00";
   }

   /**
    * 获取截止日期
    * 
    * @param date
    * @return
    */
   public String getEndDate(String date) {
       return date + " 23:59:59";
   }
   
   /**
    * 获取指定格式的时间
    * @param sdf
    * @param date
    * @return
    */
   public static String getDate(SimpleDateFormat sdf, Date date){
       return sdf.format(date);
   }
   
}
