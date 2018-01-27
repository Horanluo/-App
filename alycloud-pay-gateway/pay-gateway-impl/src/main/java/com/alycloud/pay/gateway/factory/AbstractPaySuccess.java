/*
 * 类文件名:  AbstractPaySuccess.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月18日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.factory;

/**
 * 付款成功业务处理
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月18日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractPaySuccess
{
    public synchronized void execute(String orderno) throws Exception {
        process(orderno);
    }
    
    protected abstract void process(String orderno) throws Exception;
}
