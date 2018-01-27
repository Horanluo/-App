package com.alycloud.pay.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.Channel;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.search.Channel4Search;
import com.alycloud.modules.vo.PayTypeDataVO;
import com.alycloud.modules.vo.PayTypeResultVO;
import com.alycloud.pay.api.IChannelService;
import com.alycloud.pay.mapper.ChannelMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 渠道
 * @author Moyq5
 * @date 2017年10月21日
 */
@RestController
@RequestMapping("/channel")
@Api(value = "渠道接口")
@Slf4j
public class ChannelController {
	
	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private IChannelService channelService;
	
	/**
	 * 获取渠道信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取渠道列表信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "获取渠道列表信息")
	public ResultBean<List<Channel>> listByPage(@RequestBody RequestBean<Channel4Search> reqBody) {
		List<Channel> channelList = channelMapper.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(channelList);
	}


	/**
	 * 根据支付方式获取交易渠道(扫码)
	 * @author Moyq5 
	 * @date 2017年10月20日
	 */
	@ApiOperation(notes = "调用 /qrcodePayType方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取扫码交易渠道列表")
	@PostMapping(value = "/qrcodePayType")
	@SystemControllerLog(description = "获取扫码交易渠道信息")
	public ResultBean<List<PayTypeResultVO>> qrcodePayType(@RequestBody RequestBean<PayTypeDataVO> reqBody) throws Exception {
		if(StrUtil.isEmpty(reqBody.getMerchno())||StrUtil.isEmpty(reqBody.getData().getPayType())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		PayTypeDataVO data = reqBody.getData();
		QrcodePayType payType = QrcodePayType.values()[data.getPayType()];
		List<PayTypeResultVO> payTypeList = channelService.getPayTypeResult2(reqBody.getMerchno(), payType);
		return RestBeanGenerator.genSuccessResult(payTypeList);
	}


	/**
	 * 根据支付方式获取交易渠道(快捷)
	 * @author Moyq5 
	 * @date 2017年10月21日
	 */
	@ApiOperation(notes = "调用 /fastPayType方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取快捷交易渠道列表")
	@PostMapping(value = "/fastPayType2")
	@SystemControllerLog(description = "获取快捷交易渠道列表")
	public ResultBean<List<PayTypeResultVO>> fastPayType2(@RequestBody RequestBean<String> reqBody) throws Exception {
		List<PayTypeResultVO> payTypeList = channelService.getPayTypeResult(reqBody.getMerchno());
		return RestBeanGenerator.genSuccessResult(payTypeList);
	}
	
	/**
     * 根据支付方式获取交易渠道(快捷)
     * @author 曾云龙
     * @date 2017年10月21日
     */
	@ApiOperation(notes = "调用 /fastPayType方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "获取快捷交易渠道列表")
    @PostMapping(value = "/fastPayType")
    @SystemControllerLog(description = "获取快捷交易渠道列表")
    public ResultBean<List<PayTypeResultVO>> fastPayType(@RequestBody RequestBean<String> reqBody) throws Exception {
		if(StrUtil.isEmpty(reqBody.getMerchno())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
//        if(StringUtils.isEmpty(reqBody.getMerchno()))
//        {
//            return RestBeanGenerator.genErrorResult("商户编号不能为空");
//        }
	    List<PayTypeResultVO> payTypeList = channelService.listAllChannelByType(reqBody.getMerchno(),QrcodePayType.FAST);
        return RestBeanGenerator.genSuccessResult(payTypeList);
    }

	/**
	 * 平安通道支付回调处理
	 * @author Moyq5 
	 * @date 2017年11月11日
	 */
	@ApiOperation(notes = "调用 /notify/pingan方法", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "平安通道支付回调处理")
	@RequestMapping(value = "/notify/pingan")
	//@SystemControllerLog(description = "平安通道支付回调处理")
	public @ResponseBody String notifyByPingan(HttpServletRequest req) throws Exception {
		log.info("平安通道支付回调处理>>>>>>>>>>",req);
		return channelService.notifyByPingan(req);
	}

	/**
	 * 御付（扫码）通道支付回调处理
	 * @author Moyq5 
	 * @date 2017年11月11日
	 */
	@ApiOperation(notes = "调用 /notify/yufuQrcode方法", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "御付（扫码）通道支付回调处理")
	@RequestMapping(value = "/notify/yufuQrcode")
	//@SystemControllerLog(description = "御付（扫码）通道支付回调处理")
	public @ResponseBody String notifyByYufuQrcode(HttpServletRequest req) throws Exception {
		log.info("御付（扫码）通道支付回调处理>>>>>>>>>>",req);
		return channelService.notifyByYufuQrcode(req);
	}

	/**
	 * 御付（快捷）通道支付回调处理
	 * @author Moyq5 
	 * @date 2017年11月11日
	 */
	@ApiOperation(notes = "调用 /notify/yufuFast方法", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "御付（快捷）通道支付回调处理")
	@RequestMapping(value = "/notify/yufuFast")
	//@SystemControllerLog(description = "御付（快捷）通道支付回调处理")
	public @ResponseBody String notifyByYufuFast(HttpServletRequest req) throws Exception {
		log.info("御付（快捷）通道支付回调处理>>>>>>>>>>",req);
		return channelService.notifyByYufuFast(req);
	}

}
