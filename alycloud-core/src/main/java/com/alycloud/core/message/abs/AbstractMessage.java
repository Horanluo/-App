package com.alycloud.core.message.abs;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.message.Message;
import com.alycloud.core.message.bean.MsgTransBean;
import com.alycloud.core.modules.SingleResult;

/**
 * 
 * 短信发送的抽象类
 * 
 * @author 李安国
 * @date 2016-11-14 下午6:19:29
 * @project posp-api
 */
public abstract class AbstractMessage implements Message {
	/**
	 * 短信的发送地址
	 */
	private String msgUrl;
	private URLConnection conn = null;
	private OutputStream os = null;
	private OutputStreamWriter osw = null;
	private InputStream is = null;
	private InputStreamReader isr = null;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	protected Log logger = LogFactory.getLog(AbstractMessage.class);

	protected AbstractMessage(String msgUrl) {
		super();
		this.msgUrl = msgUrl;
	}

	/**
	 * 获取短信的内容
	 * 
	 * @param templateId
	 *            模板ID
	 * @param param
	 *            参数列表
	 * @return
	 */
	protected abstract String getContent(String mobile, int msgType, String param) throws Exception;

	/**
	 * 解析短信通道返回的结果
	 * 
	 * @param result
	 * @return
	 */
	protected abstract boolean parseResult(String result);

	/**
	 * 获取通讯的编码
	 * 
	 * @return
	 */
	protected abstract String encoding();

	/**
	 * 根据类型获取模板ID
	 * 
	 * @param msgType
	 * @return
	 */
	protected abstract String getTemplateId(int msgType);

	private MsgTransBean buildMsgBean(String mobile, int msgType, String param) throws Exception {
		MsgTransBean bean = new MsgTransBean();
		Date date = new Date();
		bean.setSendDate(dateFormat.format(date));
		bean.setSendTime(timeFormat.format(date));
		bean.setMobile(mobile);
		bean.setMsgType(msgType);
		bean.setTempId(getTemplateId(msgType));
		bean.setParamValue(param);
		//dao.insertMsg(bean);
		return bean;
	}

	/**
	 * 发送短信信息
	 * 
	 * @param mobile
	 *            手机号码
	 * @param msgType
	 *            发送短信的类型
	 * @param templateId
	 *            模板ID
	 * @param param
	 *            参数列表
	 */
	public SingleResult<String> send(String mobile, int msgType, String param) {
		MsgTransBean bean = null;
		SingleResult<String> singleResult = new SingleResult<String>();
		try {
			bean = buildMsgBean(mobile, msgType, param);
			initConn();
			String content = getContent(mobile, msgType, param);
			send(content);
			String result = receive();
			boolean flag = parseResult(result);
			if (flag) {
				singleResult.setSuccess(true);
				singleResult.setResult(param);
			} else {
				singleResult.setSuccess(false);
				singleResult.setErrorCode(JSONObject.parseObject(result).getString("respCode"));
				singleResult.setErrorHint(JSONObject.parseObject(result).getString("respMsg"));
			}
			return singleResult;
		} catch (Exception e) {
			logger.error("短信发送失败", e);
			bean.setRespCode("02");
			singleResult.setErrorCode(ResCode.API_ERROE_CODE_0023.getErrorCode());
			singleResult.setErrorHint(ResCode.API_ERROE_CODE_0023.getErrorMes());
			singleResult.setSuccess(false);
			return singleResult;
		} finally {
			close(bean);
		}
	}

	/**
	 * 创建短信发送的连接
	 * 
	 * @throws Exception
	 */
	private void initConn() throws Exception {
		try {
			logger.info("连接短信服务器[" + msgUrl + "]");
			URL url = new URL(msgUrl);
			conn = url.openConnection();
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			osw = new OutputStreamWriter(os, encoding());
			logger.info("连接成功");
		} catch (Exception e) {
			throw new Exception("创建连接失败", e);
		}
	}

	/**
	 * 发送短信信息
	 * 
	 * @param content
	 * @throws Exception
	 */
	private void send(String content) throws Exception {
		try {
			logger.info("发送数据:\r\n" + content);
			osw.write(content);
			osw.flush();
			logger.info("发送成功");
		} catch (Exception e) {
			throw new Exception("发送短信失败", e);
		}
	}

	/**
	 * 接收服务器返回的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	private String receive() throws Exception {
		try {
			String line = null;
			logger.info("开始接收数据");
			StringBuffer sb = new StringBuffer();
			is = conn.getInputStream();
			isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				sb.append(line + "\r\n");
			}
			String result = sb.toString();
			logger.info("接收成功:\r\n" + result);
			return result;
		} catch (Exception e) {
			throw new Exception("短信数据接收失败", e);
		}
	}

	/**
	 * 关闭连接
	 */
	private void close(MsgTransBean bean) {
		try {
			//dao.updateMsg(bean);
			if (isr != null) {
				isr.close();
			}
			if (is != null) {
				is.close();
			}
			if (osw != null) {
				osw.close();
			}
			if (os != null) {
				os.close();
			}
		} catch (Exception e) {
			logger.error("连接关闭失败", e);
		}
	}

}
