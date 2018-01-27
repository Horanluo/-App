/*
 * 类文件名:  MerchProviderController.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.FastOrder;
import com.alycloud.modules.entity.ReportMark;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.search.FastOrder4Search;
import com.alycloud.modules.vo.FastPayDataVO;
import com.alycloud.modules.vo.FastPayResultVO;
import com.alycloud.modules.vo.ReportMerchFeeVO;
import com.alycloud.pay.api.IChannelSubmerchInfoService;
import com.alycloud.pay.api.IFastOrderService;
import com.alycloud.pay.feign.MerchInfoFeign;
import com.alycloud.pay.mapper.FastOrderMapper;
import com.alycloud.pay.support.channel.api.ChannelApi;
import com.alycloud.pay.support.channel.api.ChannelApiFactory;
import com.alycloud.pay.support.channel.api.ChannelFastOrderResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 快捷交易订单
 * 
 * @author Moyq5
 * @date 2017年10月17日
 */
@RestController
@RequestMapping("/fastOrder")
@Api(value = "快捷支付接口")
@Slf4j
public class FastOrderController
{
    
    @Autowired
    private IFastOrderService fastOrderService;
    
    @Autowired
    private MerchInfoFeign merchInfoFeign;
    
    @Autowired
    private IChannelSubmerchInfoService submerchInfoService;
    
    @Value("${platform.pay.baseUrl}")
    private String baseUrl = "http://localhost:8082";
    
    @Autowired
	private FastOrderMapper fastOrderMapper;
    
    @ApiOperation(notes = "调用 /fastPay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "快捷支付")
    @PostMapping(value = "/fastPay")
    @SystemControllerLog(description = "快捷支付")
    public ResultBean<FastPayResultVO> fastPay(@Validated @RequestBody RequestBean<FastPayDataVO> reqBody)
        throws Exception
    {
    	if(StrUtil.isEmpty(reqBody.getMerchno())||StrUtil.isEmpty(reqBody.getData().getAmount())
    			||StrUtil.isEmpty(reqBody.getData().getChannelType())||StrUtil.isEmpty(reqBody.getData().getAccountNo())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
        String merchno = reqBody.getMerchno();
        FastPayDataVO data = reqBody.getData();
        
        // 选择渠道，选择渠道进行支付时，后台检查商户渠道业务开通状态：未开通的，申请开通，
        // 返回开通结果，开通成功的直接进就下一步，如果未成功，提示大概成功时间，并结束支付过程；
        final SysChannelType channelType = SysChannelType.values()[data.getChannelType()];
        ChannelSubmerchInfo info = submerchInfoService.searchByChannelCode(merchno, channelType.getCode());
        int result = 0;
        if (info == null)
        {
            // res 大于1 说明是存量客户，则将历史数据复制到渠道子商户信息表
            int res = submerchInfoService.copytoChannelSubmerchInfo(merchno, channelType.getCode());
            
            // 渠道为空，默认为商户开通收款渠道
            if (res < 1)
            {
                result = submerchInfoService.addChannelSubMerchInfoByGradeInfo(merchno, channelType.getCode());
            }
            
            if (result > 0)
            {
                RequestBean<ReportMerchFeeVO> reqData = new RequestBean<ReportMerchFeeVO>();
                reqData.setMerchno(merchno);
                ReportMerchFeeVO vo = new ReportMerchFeeVO();
                vo.setChannelCode(channelType.getCode());
                reqData.setData(vo);
                ResultBean<ReportMark> mk = merchInfoFeign.reportMerchFee(reqData);
                if (mk.getRespCode().equals("1") && mk.getData().getReportMark().equals("0"))
                {
                    return RestBeanGenerator.genErrorResult("通道审核中，预计T+1开通业务；请尝试其他通道。");
                }
                else if (mk.getRespCode().equals("0"))
                {
                    log.error("申请通道" + channelType.getCode() + " 发生错误， errorMessage: " + mk.getMessage());
                    return RestBeanGenerator.genErrorResult("该支付通道暂不可用，请尝试其他通道");
                }
            }
            
        }
        // TODO 完善资料之后如果没有向上游进件，进行进件
        if (info != null && !StrUtil.isEmpty(info.getAduitStatus()) && info.getAduitStatus().equals("0"))
        {
            return RestBeanGenerator.genErrorResult("通道审核中，预计T+1开通业务；请尝试其他通道。");
        }else if (info != null && StrUtil.isEmpty(info.getAduitStatus()))
        {
            return RestBeanGenerator.genErrorResult("该支付通道不可用，请联系客服。");
        }
        
        FastOrder order = fastOrderService.buildOrder(merchno, data);
        
        ChannelApi channelApi = ChannelApiFactory.getChannelApi(order);
        ChannelFastOrderResult apiResult = channelApi.fastPay();
        
        FastPayResultVO vo = new FastPayResultVO();
        if (!apiResult.isSuccess()) {
//        	vo.setRespCode(apiResult.getErrorCode());
        	vo.setRespMes("该通道在维护");
        	//vo.setSuccess(false);
        	fastOrderMapper.del(order);
        	return RestBeanGenerator.genErrorResult(vo, "");
        }
//        vo.setRespCode(apiResult.getErrorCode());
//    	vo.setRespMes(apiResult.getErrorMes());
//    	vo.setSuccess(true);
        vo.setContent(apiResult.getPayHtml());
        
        order.setChannelOrderno(apiResult.getChannelOrderNo());
        
        fastOrderMapper.mod(order);
        return RestBeanGenerator.genSuccessResult(vo);
    }
    
    @ApiOperation(notes = "调用 /pay方法", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE, value = "快捷支付")
    @GetMapping(value = "/pay")
    @SystemControllerLog(description = "快捷支付")
    public String pay(String orderno)
        throws Exception
    {
        
        FastOrder4Search order4s = new FastOrder4Search();
        order4s.setOrderno(orderno);
        List<FastOrder> orderList = fastOrderService.listByPage(order4s);
        if (orderList.size() == 0)
        {
            throw new Exception("订单不存在:" + orderno);
        }
        FastOrder order = orderList.get(0);
        
        ChannelApi channelApi = ChannelApiFactory.getChannelApi(order);
        ChannelFastOrderResult apiResult = channelApi.fastPay();
        return apiResult.getPayHtml();
    }
    
    /**
     * @author Moyq5
     * @date 2017年10月19日
     */
    @ApiOperation(notes = "调用 /add方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "添加快捷支付订单")
    @PostMapping(value = "/add")
    @SystemControllerLog(description = "添加快捷支付订单")
    public ResultBean<FastOrder> add(@RequestBody RequestBean<FastOrder> requestData)
    {
        fastOrderService.add(requestData.getData());
        return RestBeanGenerator.genSuccessResult(requestData.getData());
    }
    
    /**
     * @author Moyq5
     * @date 2017年11月9日
     */
    @ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "分页查询快捷订单")
    @PostMapping(value = "/listByPage")
    @SystemControllerLog(description = "分页查询快捷订单")
    public ResultBean<List<FastOrder>> listByPage(@RequestBody RequestBean<FastOrder4Search> requestData)
    {
        FastOrder4Search order4s = requestData.getData();
        if (null == order4s)
        {
            order4s = new FastOrder4Search();
        }
        List<FastOrder> list = fastOrderService.listByPage(order4s);
        return RestBeanGenerator.genSuccessResult(list);
    }
    
    /**
     * 支付成功处理
     * 
     * @author Moyq5
     * @date 2017年11月9日
     */
    @ApiOperation(notes = "调用 /paySuccess方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "快捷支付成功处理")
    @PostMapping(value = "/paySuccess")
    @SystemControllerLog(description = "快捷支付成功处理")
    public ResultBean<?> paySuccess(@RequestBody RequestBean<String> requestData)
    {
        fastOrderService.paySuccess(requestData.getData());
        return RestBeanGenerator.genSuccessResult();
    }
    
    /**
     * 检查支付状态
     * 
     * @author Moyq5
     * @throws Exception
     * @date 2017年11月12日
     */
    @ApiOperation(notes = "调用 /checkPayStatus方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "检查快捷交易支付状态")
    @PostMapping(value = "/checkPayStatus")
    public ResultBean<?> checkPayStatus(@RequestBody RequestBean<String> requestData)
        throws Exception
    {
        fastOrderService.checkPayStatus(requestData.getData());
        return RestBeanGenerator.genSuccessResult();
    }
}
