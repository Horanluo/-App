/*
 * 类文件名:  DruidDataSourceProperties.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.datasource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Druid相关配置信息
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigurationProperties(prefix = "spring.datasource.druid")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DruidDataSourceProperties
{
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
}
