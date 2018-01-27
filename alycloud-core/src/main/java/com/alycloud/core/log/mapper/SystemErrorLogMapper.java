/*
 * 类文件名:  SystemErrorLogMapper.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月3日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.log.mapper;

import com.alycloud.modules.log.SystemErrorLogBean;

/**
 * 业务错误日志
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月3日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SystemErrorLogMapper
{

    /**
     * 异常日志持久化
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月4日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    int insert(SystemErrorLogBean entity);
    
}
