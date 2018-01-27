/*
 * 类文件名:  AbstractYufuClient.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月13日
 * 功能版本:  V001Z0001
 */
package com.alycloud.channel.support.yufu;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.alycloud.channel.utils.yufu.YufuMD5;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/**
 * 接口API操作类抽象类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月13日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Slf4j
public abstract class AbstractYufuClient<D, R extends AbstractYufuResultEntity> implements IYufuClient<D, R>
{
    private static final String CHARSET = "UTF-8"; 
    
    @Autowired
    protected YufuContext context;
    
//    private Class<R> resultClass;
    
//    protected AbstractYufuClient(Class<R> resultClass)
//    {
//        this.resultClass = resultClass;
//    }

    @ServiceLogAnnotation(moduleName="御付请求")
    public R post(D data,Class<R> resultClass)
    {
        R resResult = null;
        try {
            HttpClient client = context.getHttpClient();
            String reqBody = getReqBody(data);
            String resBody = client.post(context.getConfig().getServicePath(), reqBody);
            String sign = sign(resBody+"");
            
            resResult = JSONUtils.json2pojo(resBody, resultClass);
            if (!sign.equals(resResult.getSign())) {
                throw new Exception("签名失败");
            }
        } catch (Exception e) {
            
            log.error("接口调用失败", e);
            try {
                resResult = resultClass.newInstance();
                resResult.setResultCode("9999");
                resResult.setResultMessage(e.getMessage());
            } catch (Exception e1) {
                log.error("接口调用失败", e1);
            }
        }
        return resResult;
    }
    
    protected String getReqBody(D data) throws Exception {
        String jsonString = JSONUtils.obj2json(data);
        Map<String, String> dataMap = getSortedFields(jsonString);
        String sign = sign(dataMap);
        dataMap = getSortedFields(jsonString, false);
        dataMap.put("sign", sign);
        return toQueryString(dataMap);
    }
    
    protected String sign(String jsonString) throws Exception {
        Map<String, String> dataMap = getSortedFields(jsonString);
        return sign(dataMap);
    }
    
    private String sign(Map<String, String> dataMap) throws Exception {
        List<String> ignoreFields = new ArrayList<String>();
        ignoreFields.add("sign");
        String queryString = toQueryString(dataMap, ignoreFields);
        queryString = queryString + context.getConfig().getKey();
        log.debug("签名内容：{}", queryString);
        return YufuMD5.MD5Encode(queryString).toUpperCase();
    }
    
    public static String toQueryString(Map<String, String> data,
        List<String> ignoreFields, boolean ignoreNull)
        throws JsonParseException, IOException {
    StringBuffer sb = new StringBuffer();

    for (Map.Entry entry : data.entrySet()) {
        try {
            String fieldName = (String) entry.getKey();
            if ((null == ignoreFields)
                    || (!(ignoreFields.contains(fieldName))))
                ;
            String value = (String) entry.getValue();
            if ((!(ignoreNull))
                    || ((null != value) && (!(value.contentEquals("null"))) && (!(value
                            .isEmpty()))))
                ;
            sb.append(new StringBuilder().append(fieldName).append("=")
                    .append(value).append("&").toString());
        } catch (Exception e) {
            log.warn("数据转换失败：{}", e.getMessage());
        }
    }
    if (data.size() > 0) {
        sb.delete(sb.length() - 1, sb.length());
    }
    log.debug("组装结果：{}", sb.toString());
    return sb.toString();
}
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @throws IOException 
     * @throws JsonParseException 
     * @date     2017年9月19日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    protected String sign(Map<String, String> dataMap, boolean isPay, String key) throws Exception
    {
        List ignoreFields = new ArrayList();
        ignoreFields.add("sign");
        String queryString = toQueryString(dataMap, ignoreFields, !(isPay));
        queryString = new StringBuilder().append(queryString)
                .append((isPay) ? "" : "&key=").append(key).toString();
        log.debug("签名内容：{}", queryString);
        return YufuMD5.MD5Encode(queryString).toUpperCase();
    }
    
    protected static String toQueryString(Map<String, String> data) throws Exception {
        return toQueryString(data, null);
    }
    
    private static String toQueryString(Map<String, String> data, List<String> ignoreFields) throws JsonParseException, IOException {
        StringBuffer sb = new StringBuffer();
        String value;
        String fieldName;
        for(Map.Entry<String, String> entry : data.entrySet()) {
            try {
                fieldName = entry.getKey();
                if (null != ignoreFields && ignoreFields.contains(fieldName)) {
                    continue;
                }
                value = entry.getValue();
                if (null == value || value.contentEquals("null") || value.isEmpty()) {
                    continue;
                }
                sb.append(fieldName + "=" + value + "&");
            } catch(Exception e) {
                log.warn("数据转换失败：{}", e.getMessage());
            }
        }
        if (data.size() > 0) {
            sb.delete(sb.length()-1, sb.length());// 去掉最后的“&”
        }
        log.debug("组装结果：{}", sb.toString());
        return sb.toString();
    }
    
    protected static Map<String, String> getSortedFields(String jsonString) throws JsonParseException, IOException {
        return getSortedFields(jsonString, null, false);
    }
    
    protected static Map<String, String> getSortedFields(String jsonString, Boolean encodeUrl) throws JsonParseException, IOException {
        return getSortedFields(jsonString, null, encodeUrl);
    }
    
    private static Map<String, String> getSortedFields(String jsonString, List<String> ignoreFields, Boolean encodeUrl) throws JsonParseException, IOException {
        Map<String, String> fields = new TreeMap<String, String>();
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jp = jsonFactory.createParser(jsonString);
        jp.nextToken();
        while (jp.nextToken() != JsonToken.END_OBJECT) {
            String fieldName = jp.getCurrentName();
            jp.nextToken();
            if (null == ignoreFields || !ignoreFields.contains(fieldName)) {
                String value = jp.getValueAsString();
                if (null != value) {
                    if (null != encodeUrl && encodeUrl) {
                        value = URLEncoder.encode(value, CHARSET);
                    }
                    fields.put(fieldName, value);
                }
                
            }
        }
        jp.close();
        return fields;
    }
}
