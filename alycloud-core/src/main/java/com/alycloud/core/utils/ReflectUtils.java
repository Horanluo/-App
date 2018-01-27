/*
 * 类文件名:  ReflectUtils.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用java反射机制运行时进行Map和POJO的互相转换
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
public class ReflectUtils
{
    /** 
     * 将泛型形参给出的类中设置的属性值转换为Map形式的键值对 
     * t一般是pojo类 
     *  
     * @param params 
     * @param t 
     */  
    public static <T extends Object> Map<String, String> convertToMaps(T t) {  
        Map<String, String> params = new HashMap<String, String>();
        DecimalFormat formater = new DecimalFormat("###0.0000");
        DecimalFormat formater2 = new DecimalFormat("###0.00");
        if( t == null)  
            return params;  
          
        Class<?> clazz = t.getClass() ;  
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                Field[] fields = clazz.getDeclaredFields() ;  
                  
                for (int j = 0; j < fields.length; j++) { // 遍历所有属性  
                    String name = fields[j].getName(); // 获取属性的名字  
                    Object value = null;  
                      
                    if(log.isDebugEnabled())  
                        log.debug(ReflectUtils.class + "method flushParams attribute name:" + name + "  ");  
                          
                    if(!"serialVersionUID".equals(name)){  
                        Method method = t.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));  
                        value = method.invoke(t);  
  
                        if(log.isDebugEnabled())  
                            log.debug(ReflectUtils.class + "attribute value:" + value);  
                        
                        if(value != null) 
                        {
                            if(value instanceof List)  
                            {
                                Object[] objs = ((List)value).toArray();
                                List<Object> list = new ArrayList<Object>();
                                for(Object obj : objs)
                                {
                                    Map<String, String> para =convertToMaps(obj);
                                    list.add(para);
                                }
                                
                                params.put(name, JSONUtils.obj2json(list));  
                            }else if(value instanceof String)
                            {
                                params.put(name, value.toString());  
                            }else if(value instanceof BigDecimal){
                                params.put(name, formater.format(value));  
                            }else if(value instanceof Double){
                                params.put(name, formater2.format(value));  
                            }else
                            {
                                params.put(name, value.toString());
                            }
                        }
                            
                    }  
                }  
            } catch (Exception e) {}   
        }
        return params;
    }  
      
    /** 
     * 将Map形式的键值对中的值转换为泛型形参给出的类中的属性值 
     * t一般代表pojo类 
     *  
     * @param t 
     * @param params 
     */  
    public static <T extends Object> void flushObject(T t, Map<String, Object> params) {  
        if(params == null || t == null)  
            return;  
          
        Class<?> clazz = t.getClass() ;  
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                Field[] fields = clazz.getDeclaredFields() ;  
                  
                for(int i = 0 ; i< fields.length;i++){  
                    String name = fields[i].getName(); // 获取属性的名字  
                      
                    if(log.isDebugEnabled())  
                        log.debug(ReflectUtils.class + "method flushObject attribute name:" + name + "  ");  
                      
                    Object value = params.get(name);  
                    if(value != null && !"".equals(value)){  
                        //注意下面这句，不设置true的话，不能修改private类型变量的值  
                        fields[i].setAccessible(true);  
                        fields[i].set(t, value);  
                    }  
                }  
            }catch(Exception e){}  
        }  
    }   
}
