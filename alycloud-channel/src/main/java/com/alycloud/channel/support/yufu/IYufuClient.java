/*
 * 类文件名:  YufuClient.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;

/**
 * 接口API操作类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface IYufuClient<D, R extends AbstractYufuResultEntity>
{
    String getKey();

    R post(D data, Class<R> resultClass);
}
