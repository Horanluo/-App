package com.alycloud.channel.support.config;

import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Config {

//	private static String path = null;
	
	private static Properties prop;
	
	static{
//		ClassLoader loader = Config.class.getClassLoader();
//		path = loader.getResource("conf.xml").getPath();
		Resource resource = new ClassPathResource("conf.xml");
		
//		FileInputStream fis = null;
		try {
//			path = URLDecoder.decode(path, "UTF-8");
//			File file = new File(path);  
//			fis = new FileInputStream(file);
//			BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
			prop = new Properties();
			prop.loadFromXML(resource.getInputStream());
		} catch (Exception e) {
			log.error("初始化配置文件出错", e);
		}finally{
//			if(fis != null){
//				try {
//					fis.close();
//				} catch (IOException e) { }
//			}
		}
	}
	
	/**
	 * 获取字符串格式的数据
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		return prop.getProperty(key);
	}
	
	/**
	 * 获取布尔值的数据
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key){
		return Boolean.parseBoolean(prop.getProperty(key));
	}
	
	/**
	 * 获取数值类型的数据
	 * @param key
	 * @return
	 */
	public static int getInt(String key){
		return Integer.parseInt(prop.getProperty(key));
	}
	
	
}
