///*
// * 类文件名:  DruidConfiguration.java
// * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
// * 功能描述:  <描述>
// * 类创建人:  曾云龙
// * 创建时间:  2017年7月22日
// * 功能版本:  V001Z0001
// */
//package com.alycloud.pay.gateway.datasource;
//
//import java.sql.SQLException;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//
///**
// * Druid初始化数据库池
// * @author   曾云龙
// * @version  V001Z0001
// * @date     2017年7月22日
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Configuration
//@EnableConfigurationProperties({DruidDataSourceProperties.class})
//public class DruidConfiguration
//{
//    @Autowired
//    private DruidDataSourceProperties properties;
//    
//    @Bean
//    @ConditionalOnMissingBean
//    public DataSource druidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setDriverClassName(properties.getDriverClassName());
//        druidDataSource.setUrl(properties.getUrl());
//        druidDataSource.setUsername(properties.getUsername());
//        druidDataSource.setPassword(properties.getPassword());
//        druidDataSource.setInitialSize(properties.getInitialSize());
//        druidDataSource.setMinIdle(properties.getMinIdle());
//        druidDataSource.setMaxActive(properties.getMaxActive());
//        druidDataSource.setMaxWait(properties.getMaxWait());
//        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
//        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
//        druidDataSource.setValidationQuery(properties.getValidationQuery());
//        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
//        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
//        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
//        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
//        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
//        druidDataSource.setConnectionProperties(properties.getConnectionProperties());
//        try {
//            druidDataSource.setFilters(properties.getFilters());
//            druidDataSource.init();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return druidDataSource;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public ServletRegistrationBean druidServlet() {
//
//        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//
//        //添加初始化参数：initParams
//
//        //白名单：
//        //servletRegistrationBean.addInitParameter("allow","127.0.0.1");
//        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
//        //servletRegistrationBean.addInitParameter("deny","192.168.1.73");
//        //登录查看信息的账号密码.
//        servletRegistrationBean.addInitParameter("loginUsername", "admin");
//        servletRegistrationBean.addInitParameter("loginPassword", "admin");
//        //是否能够重置数据.
//        servletRegistrationBean.addInitParameter("resetEnable", "true");
//        return servletRegistrationBean;
//
//    }
//
//    @Bean
//    @ConditionalOnMissingBean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        return filterRegistrationBean;
//    }
//}
