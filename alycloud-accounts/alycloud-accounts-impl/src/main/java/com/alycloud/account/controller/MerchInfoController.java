package com.alycloud.account.controller;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alycloud.account.api.IMerchService;
import com.alycloud.core.annotation.SystemControllerLog;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.utils.RestBeanGenerator;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.search.MerchInfo4Search;
import com.alycloud.modules.vo.MerchInfoVO;
import com.alycloud.modules.vo.UpgradePayDataVO;
import com.alycloud.modules.vo.UpgradePayResultVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 商户信息
 * @author Moyq5
 * @date 2017年10月18日
 */
@RequestMapping(value="/merchInfo")
@RestController
@Slf4j
public class MerchInfoController {

	@Autowired
	private IMerchService merchService;
	

	/**
	 * 更新商户信息
	 * @author Moyq5
	 * @date 2017年11月2日
	 */
	@ApiOperation(notes = "调用 /mod方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "更新商户信息")
	@PostMapping(value = "/mod")
	@SystemControllerLog(description = "更新商户信息")
	public ResultBean<?> mod(@RequestBody RequestBean<MerchInfo> reqData) throws Exception{
		merchService.mod(reqData.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
	/**
	 * 根据商户号获取商户信息
	 * @author Moyq5
	 * @date 2017年10月18日
	 */
	@RequestMapping(value="/getByMerchno")
	public ResultBean<MerchInfo> getByMerchno(@RequestBody RequestBean<String> reqData) throws Exception{
		String merchno = reqData.getMerchno();
		if (StringUtils.isEmpty(merchno)) {
			merchno = reqData.getData();
		}
		MerchInfo merch = merchService.getByMerchno(merchno);
        return RestBeanGenerator.genSuccessResult(merch);
	}
	
	/**
	 * 查询各状态下的商户
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getMerchInfoByStatus")
	public ResultBean<List<MerchInfo>> getMerchInfoByStatus(@RequestBody RequestBean<MerchInfoVO> reqData) throws Exception{
		if(StrUtil.isEmpty(reqData.getMerchno())||StrUtil.isEmpty(reqData.getData().getGradeType())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		List<MerchInfo> merchList = merchService.getMerchInfoByStatus(reqData.getMerchno(), reqData.getData().getStatus(),
				reqData.getData().getGradeType(),reqData.getData().getQueryParam());
		log.info("请求参数:"+reqData.getData().toString());
		if(StrUtil.isEmpty(merchList) || merchList.size()<1){
			return RestBeanGenerator.genResult("99", merchList, null);
		}
		log.warn("商户信息{}", merchList);
		return RestBeanGenerator.genSuccessResult(merchList);
	}
	
	/**
	 * 手机号或姓名搜索商户信息
	 * @param reqData
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value="/getMerchInfoByMobileOrName")
//	public ResultBean<List<MerchInfo>> getMerchInfoByMobileOrName(@RequestBody RequestBean<MerchInfoVO> reqData) throws Exception{
//		if(StrUtil.isEmpty(reqData.getMerchno())||(StrUtil.isEmpty(reqData.getData().getMobile())||StrUtil.isEmpty(reqData.getData().getMerchName()))
//				||StrUtil.isEmpty(reqData.getData().getGradeType())){
//			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
//		}
//		List<MerchInfo> merchList = merchService.getMerchInfoByStatus(reqData.getMerchno(), reqData.getData().getStatus(),
//				reqData.getData().getGradeType(),reqData.getData().getMerchName(),reqData.getData().getMobile());
//		log.info("请求参数:"+reqData.getData().toString());
//		if(StrUtil.isEmpty(merchList) || merchList.size()<1){
//			return RestBeanGenerator.genResult("99", merchList, null);
//		}
//		log.warn("商户信息{}", merchList);
//		return RestBeanGenerator.genSuccessResult(merchList);
//	}
	
	/**
	 * 根据id获取商户信息
	 * @author Moyq5
	 * @date 2017年10月27日
	 */
	@RequestMapping(value="/getById")
	public ResultBean<MerchInfo> getById(@RequestBody RequestBean<Integer> reqData) throws Exception{
		MerchInfo merch = merchService.getById(reqData.getData());
        return RestBeanGenerator.genSuccessResult(merch);
	}
	

	/**
	 * 查询商户列表信息
	 * @author Moyq5
	 * @date 2017年11月5日
	 */
	@ApiOperation(notes = "调用 /listByPage方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "查询商户列表信息")
	@PostMapping(value = "/listByPage")
	@SystemControllerLog(description = "查询商户列表信息")
	public ResultBean<List<MerchInfo>> listByPage(@RequestBody RequestBean<MerchInfo4Search> reqBody) {
		List<MerchInfo> merchList = merchService.listByPage(reqBody.getData());
		return RestBeanGenerator.genSuccessResult(merchList);
	}

	/**
	 * 升级支付(需要授权)
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /upgradePay方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级支付")
	@PostMapping(value = "/upgradePay")
	@SystemControllerLog(description = "升级支付")
	public ResultBean<UpgradePayResultVO> upgradePay(@Validated @RequestBody RequestBean<UpgradePayDataVO> reqBody) throws Exception {
		if(StrUtil.isEmpty(reqBody.getMerchno())||StrUtil.isEmpty(reqBody.getData().getGradeType().toString())
    			||StrUtil.isEmpty(reqBody.getData().getPayType().toString())){
			return RestBeanGenerator.genErrorResult(ResCode.API_ERROE_CODE_0027.getErrorCode(), ResCode.API_ERROE_CODE_0027.getErrorMes());
		}
		String payUrl = merchService.upgradePay(reqBody.getMerchno(), reqBody.getData());
		UpgradePayResultVO vo = new UpgradePayResultVO();
		vo.setContent(payUrl);
		return RestBeanGenerator.genSuccessResult(vo);
	}
	
	/**
	 * 升级
	 * @author Moyq5
	 * @date 2017年10月30日
	 */
	@ApiOperation(notes = "调用 /upgradeInviter方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级")
	@PostMapping(value = "/upgradeInviter")
	@SystemControllerLog(description = "升级")
	public ResultBean<?> upgradeInviter(@Validated @RequestBody RequestBean<Integer> reqBody) throws Exception {
		merchService.upgradeInviter(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	

	/**
	 * 升级
	 * @author Moyq5
	 * @date 2017年11月21日
	 */
	@ApiOperation(notes = "调用 /upgradeByGradeOrderId方法", produces = MediaType.APPLICATION_JSON_VALUE, value = "升级")
	@PostMapping(value = "/upgradeByGradeOrderId")
	@SystemControllerLog(description = "升级")
	public ResultBean<?> upgradeByGradeOrderId(@Validated @RequestBody RequestBean<Integer> reqBody) throws Exception {
		merchService.upgradeByGradeOrderId(reqBody.getData());
		return RestBeanGenerator.genSuccessResult();
	}
	
	/**
	 * 根据实名认证结果批量下载文件
	 * @author Horanluo
	 * @date 2017年11月27日
	 */
	@RequestMapping(value="/getByMerchInfo")
	public ResultBean<List<MerchInfo>> getByMerchInfo(@RequestBody RequestBean<MerchInfo> feignData) throws Exception{
		List<MerchInfo> list = merchService.getByMerchInfo(feignData.getData());
        return RestBeanGenerator.genSuccessResult(list);
	}
}
