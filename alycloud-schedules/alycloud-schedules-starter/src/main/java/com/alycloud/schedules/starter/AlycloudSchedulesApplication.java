/*
 * 类文件名:  PayGatewayApplication.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.schedules.starter;

import javax.sql.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   Horanluo
 * @version  V001Z0001
 * @date     2017年11月3日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@SpringBootApplication(scanBasePackages={"com.alycloud.modules","com.alycloud.core","com.alycloud.channel",
		"com.alycloud.schedules"})
@EnableTransactionManagement
@MapperScan({"com.alycloud.schedules.mapper", "com.alycloud.core.log.mapper"})
@EnableScheduling
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients(basePackages = ("com.alycloud.schedules"))
public class AlycloudSchedulesApplication  extends SpringBootServletInitializer
{
    
    public static void main(String[] args)
    {
        SpringApplication.run(AlycloudSchedulesApplication.class, args);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AlycloudSchedulesApplication.class);
    }
    
    @Bean
    public Module jacksonAfterBurnerModule() {
        return new AfterburnerModule();
    }
}
