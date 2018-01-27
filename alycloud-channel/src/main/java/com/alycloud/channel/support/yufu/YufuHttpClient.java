/*
 * 类文件名:  YufuHttpClient.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 御付http调度实现类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
@Component
public class YufuHttpClient implements HttpClient
{
    private static final String CHARSET = "UTF-8";
    
    public String post(String serverPath, String postBody) {
        log.debug("请求地址：{}", serverPath);
        log.debug("请求内容：{}", postBody);
        Header header = new BasicHeader("Content-Type", 
                "application/x-www-form-urlencoded; charset=" + CHARSET);
        HttpUriRequest httpUriRequest = RequestBuilder
                .post()
                .setHeader(header)
                .setUri(serverPath)
                .setEntity(new StringEntity(postBody, CHARSET))
                .build();
        CloseableHttpClient httpClient = HttpClientFactory.createHttpClient();
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(httpUriRequest);
            int status = response.getStatusLine().getStatusCode();
            log.debug("远程接口返回状态：{}", response.getStatusLine());
            if (status == 200) {
                String resBody = EntityUtils.toString(response.getEntity(), CHARSET);
                log.debug("响应内容：{}", resBody);
                return resBody;
            } 
        } catch (Exception e) {
            log.error("远程接口调用失败", e);
        } 
        return null;
    }
}
