/*
 * 类文件名:  LogAnnotation.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志注解
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月4日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface ServiceLogAnnotation
{
   String moduleName() default ""; 
}
