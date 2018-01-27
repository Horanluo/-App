package com.alycloud.pay.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.IdentifyAuthenUtil;
import com.alycloud.core.utils.SmsUtil;
import com.alycloud.modules.vo.DownLoadImgVO;
import com.alycloud.modules.vo.SaveCardVO;
import com.alycloud.pay.api.IUtilService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 工具类
 * @author Horanluo
 * @date 2017年11月20日
 */
@RestController
@RequestMapping("/util")
@Api(value = "工具类")
public class UtilController {

	@Value("${img.localpath}")
    private String imgLocalpath;
	@Value("${file-api-path}")
    private String apiUrl;
	@Autowired
	private IUtilService utilService;
	/**
	 * 发送短信
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月27日
	 */
	@ApiOperation(notes = "调用 /sendSms方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "发送短信服务接口")
	@PostMapping(value = "/sendSms")
	@SystemControllerLog(description = "发送短信服务接口")
	public SingleResult<String> sendSms(@RequestBody String mobile) throws Exception {
		return SmsUtil.send(mobile);
	}
	
	/**
	 * 下载身份实名认证图片
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月27日
	 */
	@ApiOperation(notes = "调用 /loadImg方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "下载身份实名认证图片")
	@PostMapping(value = "/loadImg")
	@SystemControllerLog(description = "下载身份实名认证图片")
	public Map<Object,Object> downLoadImg(@RequestBody RequestBean<DownLoadImgVO> reqData) throws Exception {
		Map<Object,Object> resultMap = IdentifyAuthenUtil.loadIdCardFile(reqData.getMerchno(),reqData.getData().getFrontUrl(),
				reqData.getData().getBackUrl(),reqData.getData().getLivingPhotoUrl(),reqData.getData().getVideoUrl(),imgLocalpath,apiUrl);
		return resultMap;
	}
	
	/**
	 * 银行卡四要素认证
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年11月30日
	 */
	@ApiOperation(notes = "调用 /certifyCardMsg方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "发送短信服务接口")
	@PostMapping(value = "/certifyCardMsg")
	@SystemControllerLog(description = "银行卡四要素认证接口")
	public JSONObject certifyCardMsg(@RequestBody SaveCardVO reqData) throws Exception {
		return utilService.certifyCardMsg(reqData.getIdNo(), reqData.getCardName(), reqData.getCardNo(), reqData.getPhoneNo());
	}
	
	/**
	 * 获取卡信息
	 * @author Horanluo
	 * @throws Exception 
	 * @date 2017年12月01日
	 */
	@ApiOperation(notes = "调用 /getCardDetail方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取卡信息")
	@PostMapping(value = "/getCardDetail")
	@SystemControllerLog(description = "获取卡信息接口")
	public String getCardDetail(@RequestBody String cardNo) throws Exception {
		return utilService.getCardDetail(cardNo);
	}
}
