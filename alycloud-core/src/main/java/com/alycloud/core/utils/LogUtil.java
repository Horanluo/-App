package com.alycloud.core.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	private static String LOG_TAG_API = "apiLog";
	
	private static String LOG_TAG_QRCODE = "qrcodeLog";

	private static String LOG_TAG_ONLINE = "onlineLog";
	
	private static String LOG_TAG_REG = "regLog";
	
	private static String LOG_TAG_QUICK = "quickLog";
	
	private static String LOG_TAG_SETTLE = "settleLog";
	
	/**
	 * ��ȡ�ӿ�ǰ�õ���־��¼
	 * @return
	 */
	public static Log getApiLog(){
		return LogFactory.getLog(LOG_TAG_API);
	}	
	
	/**
	 * ��ȡ��ά�뽻�׵���־��¼
	 * @return
	 */
	public static Log getQrcodeLog(){
		return LogFactory.getLog(LOG_TAG_QRCODE);
	}	
	
	/**
	 * ��ȡ���֧�����׵���־��¼
	 * @return
	 */
	public static Logger getQuickLog(){
		return LoggerFactory.getLogger(LOG_TAG_QUICK);
	}
	
	/**
	 * ��ȡ��ά�뽻�׵���־��¼
	 * @return
	 */
	public static Log getQuickLog2(){
		return LogFactory.getLog(LOG_TAG_QUICK);
	}	
	
	/**
	 * ��ȡ���Ͻ��׵���־��¼
	 * @return
	 */
	public static Log getOnlineLog(){
		return LogFactory.getLog(LOG_TAG_ONLINE);
	}
	
	/**
	 * ��ȡ�û�ע�����־��¼
	 * @return
	 */
	public static Log getRegLog(){
		return LogFactory.getLog(LOG_TAG_REG);
	}
	
	/**
	 * ��ȡ�������־��¼
	 * @return
	 */
	public static Log getSettleLog(){
		return LogFactory.getLog(LOG_TAG_SETTLE);
	}	
	
}
