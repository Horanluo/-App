package com.alycloud.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 发送post请求
 * @author Horanluo
 */
public class HttpPostUtil {

	public static String postReq(String gateway,String jsonParam)throws Exception {
		
		String xmlText = "";
		CloseableHttpClient httpclient = HttpClients.custom().build();
		try {
			 HttpPost httpPost = new HttpPost(gateway); 
             httpPost.addHeader("charset", "UTF-8");
			 StringEntity stentity = new StringEntity(jsonParam.toString(),"utf-8");//解决中文乱码问题    
			 stentity.setContentEncoding("UTF-8");    
			 stentity.setContentType("application/json");
			 httpPost.setEntity(stentity);
		     CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						xmlText = xmlText + text;
					}
				}
				
				EntityUtils.consume(entity);
			} finally {
				
				response.close();
			}
		} finally {
			
			httpclient.close();
	    }
	    return xmlText;
 	}
}
