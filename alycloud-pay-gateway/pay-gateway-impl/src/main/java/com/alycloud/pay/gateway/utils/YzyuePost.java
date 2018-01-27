/*
 * 类文件名:  YzyuePost.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年8月15日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP/HTTPS传输数据
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年8月15日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class YzyuePost
{
    /**
     * 连接对象
     */
    private static CloseableHttpClient client = null;

    /**
     * 日志记录对象
     */
    protected static Logger logger = LoggerFactory.getLogger(YzyuePost.class);

    static {
        try {
            RegistryBuilder<ConnectionSocketFactory> builder = RegistryBuilder.create();
            /** 构建HTTP传输协议 **/
            ConnectionSocketFactory plain = PlainConnectionSocketFactory.getSocketFactory();
            builder.register("http", plain);
            /** 构建HTTPS传输协议 **/
            SslMaterial material = new SslMaterial();
            SSLContextBuilder buidler = new SSLContextBuilder();
            buidler.loadTrustMaterial(null, material);
            SSLContext sslContext = buidler.build();
            SSLConnectionSocketFactory ssl = new SSLConnectionSocketFactory(sslContext);
            builder.register("https", ssl);
            Registry<ConnectionSocketFactory> registry = builder.build();

            PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager(registry);
            manager.setMaxTotal(100);// 客户端总并行链接最大数
            manager.setDefaultMaxPerRoute(100);// 每个主机的最大并行链接数

            HttpClientBuilder clientBuilder = HttpClients.custom();
            clientBuilder.setConnectionManager(manager);
            client = clientBuilder.build();
        } catch (Exception e) {
            logger.error("创建HTTP/HTTPS连接池失败", e);
        }
    }

    /***
     * 发送数据并接收返回
     * 
     * @param message
     *            需要发送的数据。格式：a=1&b=2
     * @return
     * @throws Exception
     */
    public static String receiveBySend(String url, String message, String charset) throws Exception {
        InputStream is = null;
        HttpPost httpPost = null;
        CloseableHttpResponse response = null;
        try {
            logger.info("远程服务器地址:" + url);
            httpPost = new HttpPost(url);
            if (!StrUtil.isEmpty(message)) {
                logger.info("向远程服务器发送数据:\r\n" + message);
                List<NameValuePair> nvps = buildParam(message);
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
            }
            response = client.execute(httpPost);
            if (response != null && response.getEntity() != null) {
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
                String result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                logger.info("远程服务器响应数据:\r\n" + result);
                return result;
            } else {
                logger.error("远程服务器响应超时");
                throw new Exception("远程服务器响应超时");
            }
        } catch (Exception e) {
            logger.error("向远程服务器通讯失败", e);
            throw new Exception("向远程服务器通讯失败");
        } finally {
            if (is != null) {
                is.close();
            }
            if (response != null) {
                response.close();
            }
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
        }
    }

    /**
     * 构造HTTP形式POST的请求数据
     * 
     * @param param
     * @return
     */
    private static List<NameValuePair> buildParam(String message) {
        String[] data = message.split("&");
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (int i = 0; i < data.length; i++) {
            String str = data[i];
            int index = str.indexOf("=");
            String key = str.substring(0, index);
            String value = str.substring(index + 1);
            list.add(new BasicNameValuePair(key, value));
        }
        return list;
    }
}
