/*
 * 类文件名:  JSONUtils.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月4日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSON转换常用类
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年9月4日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class JSONUtils
{
    private final static ObjectMapper objectMapper = new ObjectMapper();
    
    static{
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }
    
    private JSONUtils()
    {
        
    }
    
    public static ObjectMapper getInstance()
    {
        
        return objectMapper;
    }
    
    /**
     * javaBean,list,array convert to json string
     */
    public static String obj2json(Object obj)
        throws Exception
    {
        return objectMapper.writeValueAsString(obj);
    }
    
    /**
     * json string convert to javaBean
     */
    public static <T> T json2pojo(String jsonStr, Class<T> clazz)
        throws Exception
    {
        return objectMapper.readValue(jsonStr, clazz);
    }
    
    /**
     * json string convert to map
     */
    public static <T> Map<String, Object> json2map(String jsonStr)
        throws Exception
    {
        return objectMapper.readValue(jsonStr, Map.class);
    }
    
    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
        throws Exception
    {
        Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>()
        {
        });
        Map<String, T> result = new HashMap<String, T>();
        for (Entry<String, Map<String, Object>> entry : map.entrySet())
        {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }
    
    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
        throws Exception
    {
        List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>()
        {
        });
        List<T> result = new ArrayList<T>();
        for (Map<String, Object> map : list)
        {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }
    
    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz)
    {
        return objectMapper.convertValue(map, clazz);
    }
    
    /**
     * 将string转为map后转为json
     */
    public static String strToJson(String livingType,String livingPhotoUrl,String videoType,String videoUrl,String jsonStr){
    	Map<String, String> map = new HashMap<String, String>();;
    	if(!StrUtil.isEmpty(jsonStr)){
    		map = JSONObject.parseObject(jsonStr,Map.class);  
    	}
    	map.put(livingType, livingPhotoUrl);
		map.put(videoType, videoUrl);
		JSONArray array_test = new JSONArray();
		array_test.add(map);
		String json = JSON.toJSONString(map,true);
    	return json;
    }
//    public static void main(String[] args) {
//    	Map<String, String> map = new HashMap<String, String>();
//    	map.put("frontUrl", "1234");
//		map.put("backUrl", "52678");
//		JSONArray array_test = new JSONArray();
//		array_test.add(map);
//		String json = JSON.toJSONString(map,true); 
//	    System.out.println(json); 
//	    System.out.println(strToJson("aaaa","bbbb",json));
//	}
}
