package com.alycloud.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class CommonUtil {
	
	/**
	 * 锟斤拷锟斤拷指锟斤拷锟斤拷锟饺碉拷锟斤拷锟斤拷锟�
	 * @author Moyq5
	 * @date 2017锟斤拷2锟斤拷8锟斤拷
	 * @param length
	 * @return
	 */
	public static String mkRandomStr(int length) {
		final char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer();
	    for(int i = 0; i<length; i++){
	    	sb.append(chars[new Long(Math.round(Math.random() * (chars.length-1))).intValue()]);
	    }
		return sb.toString();
	}
	
	/**
	 * 保存上传文件
	 * @param files 文件
	 * @param UPLOADED_FOLDER  保存目录
	 * @throws IOException
	 */
//    public static Map<String,String> saveUploadedFiles(List<MultipartFile> files,String UPLOADED_FOLDER) throws IOException {
//    	String dir = new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + "/";
//    	File fileDir ;
//    	Map<String,String> map = new HashMap<String,String>();
//        for (MultipartFile file : files) {
//            if (file.isEmpty()) {
//                continue;
//            }
//            byte[] bytes = file.getBytes();
//            fileDir = new File(UPLOADED_FOLDER + dir + file.getOriginalFilename());
//            fileDir.getParentFile().mkdirs();
//            Path path = Paths.get(UPLOADED_FOLDER + dir + file.getOriginalFilename());
//            Files.write(path, bytes);
//            map.put("path", dir + file.getOriginalFilename());
//        }
//        return map;
//    }
    
    /**
	 * 保存上传文件
	 * @param files 文件
	 * @param UPLOADED_FOLDER  保存目录
	 * @throws IOException
	 */
    public static Map<Object,Object> saveUploadedFiles(String base64Img,String UPLOADED_FOLDER) throws IOException {
    	String dir = new SimpleDateFormat("yyyy_MM_dd").format(new Date()) + "/";
    	OutputStream out=null;
    	Map<Object,Object> map = new HashMap<Object,Object>();
    	// 用于设置图片过大，存入临时文件    
        DiskFileItemFactory factory = new DiskFileItemFactory();    
        factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。    
        //factory.setRepository(new File(tempPath)); // 设定存储临时文件的目录。    

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        
//        String[] base64ImgArr = base64Img.split("/");
//        String ext =base64ImgArr[1].substring(0, base64ImgArr[1].indexOf(";"));
//        ext="."+ext;
        //base64Img = base64Img.replaceAll("data:image/"+base64ImgArr[1].substring(0, base64ImgArr[1].indexOf(";"))+";base64,", "");      
        Base64 decoder = new Base64();      
        try {      
           // Base64解码      
           @SuppressWarnings("static-access")
           byte[] b = decoder.decodeBase64(base64Img);      
           for (int i = 0; i < b.length; ++i) {      
               if (b[i] < 0) {// 调整异常数据      
                   b[i] += 256;      
               }      
           }      
           // 生成jpeg图片      
           String ret_fileName = new String(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".jpg");
           File file = new File(UPLOADED_FOLDER + dir + ret_fileName); 
           file.getParentFile().mkdirs();
           out = new FileOutputStream(file);
           out.write(b);
           map.put("path", dir + ret_fileName);
           map.put("uploadFlag", true);
        } catch (Exception e) {      
           e.printStackTrace(); 
           map.put("uploadFlag", false);
        }finally{
        	if(null!=out){
        		out.flush();      
                out.close();
        	}
        }
        return map;
    }
}
