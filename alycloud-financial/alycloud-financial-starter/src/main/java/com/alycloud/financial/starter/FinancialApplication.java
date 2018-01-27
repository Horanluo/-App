package com.alycloud.financial.starter;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Moyq5
 * @date 2017年10月20日
 */
@SpringBootApplication(scanBasePackages={"com.alycloud.modules","com.alycloud.core", "com.alycloud.financial"})
@EnableTransactionManagement
@MapperScan({"com.alycloud.core.log.mapper","com.alycloud.financial.mapper"})
@EnableAspectJAutoProxy
@EnableEurekaClient
@EnableFeignClients(basePackages = ("com.alycloud.financial"))
@EnableCircuitBreaker
@EnableSwagger2
public class FinancialApplication  extends SpringBootServletInitializer
{
    static Logger logger = LoggerFactory.getLogger(FinancialApplication.class);
    
    public static void main(String[] args)
    {
        SpringApplication.run(FinancialApplication.class, args);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public PlatformTransactionManager txManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FinancialApplication.class);
    }
    
    @Bean
    public Module jacksonAfterBurnerModule() {
        return new AfterburnerModule();
    }

}
