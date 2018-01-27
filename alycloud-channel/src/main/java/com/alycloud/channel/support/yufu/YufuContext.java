/*
 * 类文件名:  YufuContext.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alycloud.modules.channel.yufu.AbstractYufuEntity;

/**
 * 御付商户入驻接口－上下文管理类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
@Data
public class YufuContext
{
    @Autowired
    private AbstractYufuEntity config;
    
    @Autowired
    private HttpClient httpClient;
}
