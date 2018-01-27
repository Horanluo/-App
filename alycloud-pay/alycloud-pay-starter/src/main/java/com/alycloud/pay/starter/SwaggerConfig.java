/*
 * 类文件名:  SwaggerConfig.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.starter;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;  
import org.springframework.context.EnvironmentAware;  
import org.springframework.context.annotation.Bean;  
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  
import org.springframework.core.env.Environment;  
import org.springframework.util.StopWatch;  
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;  
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;  

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;  
import springfox.documentation.service.ApiInfo;  
import springfox.documentation.service.Contact;  
import springfox.documentation.spi.DocumentationType;  
import springfox.documentation.spring.web.plugins.Docket;  
import springfox.documentation.swagger2.annotations.EnableSwagger2;  
  
import static springfox.documentation.builders.PathSelectors.*;  

/**
 * Swagger配置类
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月22日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.alycloud.pay")
@Slf4j
public class SwaggerConfig extends WebMvcConfigurerAdapter implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;
    
    @Value("${title:易商云-内部支付接口}")
    private String title;
    
    @Value("${description:支付系统接口，包括商户进件、一码付、快捷支付等接口}")
    private String description;
    
    @Value("${version:1.0.0}")
    private String version;
    
    @Value("${termsOfServiceUrl:http://www.esyto.com}")
    private String termsOfServiceUrl;
    
    @Value("${contact.name:莫勇强}")
    private String contactName;
    
    @Value("${contact.url:http://www.esyto.com}")
    private String contactUrl;
    
    @Value("${contact.email:mo_yq5@163.com}")
    private String contactEmail;
    
    @Value("${license:易商云版权所有}")
    private String license;
    
    @Value("${licenseUrl:http://www.esyto.com}")
    private String licenseUrl;
    
    /**
     * 静态资源映射
     * 
     * @param registry
     *            静态资源注册器
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
    
    /**
     * 这里是从配置文件里读相关的字段
     * @param arg0
     */
    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,
            "swagger.");
    }
    
    /**
     * 最重要的就是这里，定义了/test/.*开头的rest接口都分在了test分组里，可以通过/v2/api-docs?group=test得到定义的json
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    @Bean
    public Docket swaggerSpringfoxDocket4KAD() {//
        log.debug("Starting Swagger");
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2)
                .groupName("cloudplatformApi")
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.any())
                .paths(regex("/channel/qrcodePayType|/channel/fastPayType|/qrcodeOrder/qrcodePay|/fastOrder/fastPay")) // and by paths
                .build();
        watch.stop();
        log.debug("Started Swagger in {} ms", watch.getTotalTimeMillis());
        return swaggerSpringMvcPlugin;
    }

    /**
     * 
     * 这里是生成文档基本信息的地方
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年7月22日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
            .description(description)
            .version(version)
            .termsOfServiceUrl(termsOfServiceUrl)
            .contact(new Contact(contactName,
                contactUrl,
                contactEmail))
             .license(license)
             .licenseUrl(licenseUrl)
             .build();

//        return new ApiInfoBuilder().title(propertyResolver.getProperty("title"))
//            .description(propertyResolver.getProperty("description"))
//            .version(propertyResolver.getProperty("version"))
//            .termsOfServiceUrl(propertyResolver.getProperty("termsOfServiceUrl"))
//            .contact(new Contact(propertyResolver.getProperty("contact.name"),
//                propertyResolver.getProperty("contact.url"),
//                propertyResolver.getProperty("contact.email")))
//             .license(propertyResolver.getProperty("license"))
//             .licenseUrl(propertyResolver.getProperty("licenseUrl"))
//             .build();

    }

}
