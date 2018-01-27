package com.alycloud.core.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

public class IdentifyAuthenUtil {

	protected static Logger logger = LoggerFactory.getLogger(IdentifyAuthenUtil.class);
	private static final String CHASET_UTF_8 = "UTF-8";
    
	/**
     * 生成请求header
     */
    public static JSONObject getRequestHeader(String session_id,String pub_key,
    		String security_key) throws IOException {
        JSONObject header = new JSONObject();
        if (session_id != null && !session_id.equals("")) {
            header.put("session_id", session_id);
        }
        String sign_time = getStringDate(new Date());
        String partner_order_id = UUID.randomUUID().toString();
        String sign = getMD5Sign(pub_key, partner_order_id, sign_time, security_key);
        header.put("partner_order_id", partner_order_id);
        header.put("sign", sign);
        header.put("sign_time", sign_time);
        return header;
    }
    
    /**
     * 格式化日期字符串 yyyyMMddHHmmss
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }
    
    /**
     * 生成md5签名
     */
    public static String getMD5Sign(String pub_key, String partner_order_id, String sign_time, String security_key) throws UnsupportedEncodingException {
        String signStr = String.format("pub_key=%s|partner_order_id=%s|sign_time=%s|security_key=%s", pub_key, partner_order_id, sign_time, security_key);
        System.out.println("测试输入签名signField：" + signStr);
        return MD5.MD5Encrpytion(signStr.getBytes("UTF-8"));
    }
    
    /**
     * 从测试目录（src//test//resources//idcard）获取测试base64数据
     */
    public static String getFileBase64Str(String fileName) throws IOException {
        String filePath = System.getProperty("user.dir") + "//src//test//resources//idcard//" + fileName;
        System.out.println("测试文件：" + filePath);
        File file = new File(filePath);
        byte[] front = FileUtils.readFileToByteArray(file);
        return Base64.getEncoder().encodeToString(front);
    }
    
    /**
     * Http请求
     */
    public static JSONObject doHttpRequest(String url, JSONObject reqJson) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //设置传入参数
        StringEntity entity = new StringEntity(reqJson.toJSONString(), CHASET_UTF_8);
        entity.setContentEncoding(CHASET_UTF_8);
        entity.setContentType("application/json");
        System.out.println(url);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity he = resp.getEntity();
            String respContent = EntityUtils.toString(he, CHASET_UTF_8);
            return (JSONObject) JSONObject.parse(respContent);
        }
        return null;
    }
    
    /**
     * 下载身份实名认证照片
     */
    public static boolean download(String urlStr,String filename){
    	try {
    		URL url = new URL(urlStr);
        	File file = new File("/home/temp/"+filename);
        	if (!file.getParentFile().exists()) {
        		file.getParentFile().mkdirs();
        	}
			FileUtils.copyURLToFile(url, file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 启动线程下载身份验证图片
     * @param info
     * @param frontUrl  身份证正面照
     * @param backUrl  身份证反面照
     * @param handHeldUrl  手持身份证照
     * @param livingPhotoUrl  活体照
     * @param videoUrl  视频
     * @param idcardimg_localpath  身份证件照下载路径
     * @param apiUrl  系统文件访问路径
     * @return
     * @throws Exception
     */
    public static Map<Object,Object> loadIdCardFile(String merchno,String frontUrl,
    		String backUrl,String livingPhotoUrl,String videoUrl,String imgLocalpath,String apiUrl) throws Exception {
		final String ext = ".jpg";
		final String video = ".mp4";
		String localPath = imgLocalpath;
		String dir = new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + "/";
		boolean hasOneLoaded = false;
		Map<Object,Object> map = new HashMap<Object,Object>();
		
		//ChangeMerchInfo destInfo = info;
		String front="";
		String back="";
		String livingPhoto="";
		String videourl="";
		try {
			//String imgIdCard1 = info.getImgIdCard1();// 身份证正面照
			if (!StringUtils.isEmpty(frontUrl)) {
				URL source = new URL(frontUrl);
				String path = dir + CommonUtil.mkRandomStr(15) + ext;
				File file = new File(localPath + merchno + "/" + path);
				file.getParentFile().mkdirs();
				FileUtils.copyURLToFile(source, file);
				//destInfo.setImgIdCard1(apiUrl + path);
				front = apiUrl + merchno+"/"+path;
				hasOneLoaded = true;
			}
		} catch (Exception e) {
			logger.warn("身份证正面照下载失败:imgIdCard1", e);
			hasOneLoaded = false;
		}
		
		try {
			//String imgIdCard2 = info.getImgIdCard2();// 身份证反面照
			if (!StringUtils.isEmpty(backUrl)) {
				URL source = new URL(backUrl);
				String path = dir + CommonUtil.mkRandomStr(15) + ext;
				File file = new File(localPath + merchno + "/" + path);
				file.getParentFile().mkdirs();
				FileUtils.copyURLToFile(source, file);
				//destInfo.setImgIdCard2(apiUrl + path);
				back = apiUrl + merchno+"/"+path;
				hasOneLoaded = true;
			} 
		} catch (Exception e) {
			logger.warn("身份证反面照下载失败:imgIdCard2", e);
			hasOneLoaded = false;
		}
		
		try {
			if (!StringUtils.isEmpty(livingPhotoUrl)) {
				URL source = new URL(livingPhotoUrl);
				String path = dir + CommonUtil.mkRandomStr(15) + ext;
				File file = new File(localPath + merchno + "/" + path);
				file.getParentFile().mkdirs();
				FileUtils.copyURLToFile(source, file);
				livingPhoto = apiUrl + merchno+"/"+path;
				hasOneLoaded = true;
			}
		} catch (Exception e) {
			logger.warn("活体照下载失败:livingPhoto", e);
			hasOneLoaded = false;
		}
		
		try {
			if (!StringUtils.isEmpty(videoUrl)) {
				URL source = new URL(videoUrl);
				String path = dir + CommonUtil.mkRandomStr(15) + video;
				File file = new File(localPath + merchno + "/" + path);
				file.getParentFile().mkdirs();
				FileUtils.copyURLToFile(source, file);
				videourl = apiUrl + merchno+"/"+path;
				hasOneLoaded = true;
			}
		} catch (Exception e) {
			logger.warn("活体视频下载失败:video", e);
			hasOneLoaded = false;
		}
		
		map.put("hasOneLoaded", hasOneLoaded);
		//map.put("destInfo", destInfo);
		map.put("front", front);
		map.put("back", back);
		map.put("livingPhoto", livingPhoto);
		map.put("videourl", videourl);
		return map;
	}
}
