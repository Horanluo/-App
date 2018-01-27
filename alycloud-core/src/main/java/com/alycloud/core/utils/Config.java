package com.alycloud.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import org.apache.commons.logging.Log;

public class Config {

	private static String path = null;
	
	private static Properties prop;
	
	/**
	 * ��־��¼��Ϣ
	 */
	protected static Log logger = LogUtil.getApiLog();
	
	static{
		ClassLoader loader = Config.class.getClassLoader();
		path = loader.getResource("conf.xml").getPath();
		FileInputStream fis = null;
		try {
			path = URLDecoder.decode(path, "UTF-8");
			File file = new File(path);  
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.loadFromXML(fis);
		} catch (Exception e) {
			logger.error("��ʼ�������ļ�����", e);
		}finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) { }
			}
		}
	}
	
	/**
	 * ��ȡ�ַ�����ʽ������
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		return prop.getProperty(key);
	}
	
	/**
	 * ��ȡ����ֵ������
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key){
		return Boolean.parseBoolean(prop.getProperty(key));
	}
	
	/**
	 * ��ȡ��ֵ���͵�����
	 * @param key
	 * @return
	 */
	public static int getInt(String key){
		return Integer.parseInt(prop.getProperty(key));
	}
	
	
}
