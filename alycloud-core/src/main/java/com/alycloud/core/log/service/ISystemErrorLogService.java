/*
 * 类文件名:  ISystemErrorLogService.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月1日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.log.service;

/**
 * 异常日志
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月1日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ISystemErrorLogService<T>
{
    /**
     * 插入异常日志
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月1日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public int insert(T entity);
}
