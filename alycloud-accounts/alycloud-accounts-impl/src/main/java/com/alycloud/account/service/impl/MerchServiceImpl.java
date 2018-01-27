/*
 * 类文件名:  MerchProviderServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.account.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import com.alycloud.account.api.IAgentInfoService;
import com.alycloud.account.api.IChangeMerchInfoService;
import com.alycloud.account.api.IChannelSubMerchInfoService;
import com.alycloud.account.api.IGradeFeeService;
import com.alycloud.account.api.IGradeService;
import com.alycloud.account.api.IMerchService;
import com.alycloud.account.api.IMerchUserService;
import com.alycloud.account.feign.GradeOrderFeign;
import com.alycloud.account.feign.MerchInfoFeign;
import com.alycloud.account.feign.QrcodeOrderFeign;
import com.alycloud.account.feign.SystemParamFeign;
import com.alycloud.account.mapper.AgentInfoMapper;
import com.alycloud.account.mapper.ChangeMerchInfoMapper;
import com.alycloud.account.mapper.ChannelSubMerchInfoMapper;
import com.alycloud.account.mapper.MerchInfoMapper;
import com.alycloud.account.mapper.MerchUserMapper;
import com.alycloud.account.service.upgrade.UpgradeFactory;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.error.ResCode;
import com.alycloud.core.exception.ApiCode;
import com.alycloud.core.exception.ApiException;
import com.alycloud.core.modules.RequestBean;
import com.alycloud.core.modules.ResultBean;
import com.alycloud.core.modules.SingleResult;
import com.alycloud.core.utils.DateFormat;
import com.alycloud.core.utils.JSONUtils;
import com.alycloud.core.utils.StrUtil;
import com.alycloud.modules.entity.AgentInfo;
import com.alycloud.modules.entity.ChangeMerchInfo;
import com.alycloud.modules.entity.ChannelSubmerchInfo;
import com.alycloud.modules.entity.Grade;
import com.alycloud.modules.entity.GradeFee;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.entity.MerchUser;
import com.alycloud.modules.entity.ReceiveDetailInfo;
import com.alycloud.modules.entity.WithdrawalDetailInfo;
import com.alycloud.modules.enums.ChangeMerchInfoAuditStatus;
import com.alycloud.modules.enums.GradeOrderStatus;
import com.alycloud.modules.enums.MerchAuditStatus;
import com.alycloud.modules.enums.MerchJudgStatus;
import com.alycloud.modules.enums.MerchSettleType;
import com.alycloud.modules.enums.MerchStatus;
import com.alycloud.modules.enums.MerchType;
import com.alycloud.modules.enums.QrcodePayType;
import com.alycloud.modules.enums.SysChannelType;
import com.alycloud.modules.enums.SysSettleType;
import com.alycloud.modules.enums.SystemParamCode;
import com.alycloud.modules.search.AgentInfo4Search;
import com.alycloud.modules.search.GradeFee4Search;
import com.alycloud.modules.search.MerchInfo4Search;
import com.alycloud.modules.search.MerchUser4Search;
import com.alycloud.modules.vo.IdentifyAuthenVO;
import com.alycloud.modules.vo.LivingIdentifyVO;
import com.alycloud.modules.vo.QrcodePayDataVO;
import com.alycloud.modules.vo.QrcodePayResultVO;
import com.alycloud.modules.vo.RegistUserVO;
import com.alycloud.modules.vo.UpgradePayDataVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务实现类
 * 
 * @author 曾云龙
 * @version V001Z0001
 * @date 2017年7月21日
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@ConfigurationProperties(prefix="hfbank")
@Slf4j
public class MerchServiceImpl implements IMerchService {
	@Autowired
	private MerchInfoMapper merchInfoMapper;
	@Autowired
	private QrcodeOrderFeign qrcodeOrderFeign;
	@Autowired
	private GradeOrderFeign gradeOrderFeign;
	@Autowired
	private IGradeService gradeService;
	@Autowired
	private MerchInfoFeign merchInfoFeign;
	@Autowired
	private SystemParamFeign systemParamFeign;
	@Autowired 
	private IChangeMerchInfoService changeMerchInfoService;
	@Autowired 
	private IMerchUserService merchUserService;
	@Autowired
	private MerchUserMapper merchUserMapper;
	@Autowired
	private ChannelSubMerchInfoMapper channelSubMerchInfoMapper;
	@Autowired
	private IAgentInfoService agentInfoService;
	@Autowired
	private IGradeFeeService gradeFeeService;
	@Autowired
	private MerchVirtualCardService merchVirtualCardService;
	@Autowired 
	private ChangeMerchInfoMapper changeMerchInfoMapper;
	@Autowired
	private IChannelSubMerchInfoService channelMerchInfoService;
	@Value("${file-api-path}")
    private String apiUrl;
	@Value("${platform.pay.baseUrl}")
	private String baseUrl = "http://localhost:8082";
	@Value("${branchno}")
	private String branchno;
	@Value("${area.code}")
	private String areaCode;
	@Value("${merch.mcc}")
	private String merchMcc;
	@Value("${mpos_app_rate}")
	private String appRate;
	@Value("${mpos_app_fixed}")
	private String appFixed;
	@Value("${mpos_credit_rate}")
	private String creditRate;
	@Value("${mpos_t0_rate}")
	private String toRate;
	@Value("${mpos_biz_type}")
	private String bizType;
	@Resource(name = "transactionManager")
	private PlatformTransactionManager platformTransactionManager;
	@Autowired
	private AgentInfoMapper agentInfoMapper;
	
	@Override
	@ServiceLogAnnotation(moduleName="查询具体某个商户信息")
	public MerchInfo getByLoginName(String loginName,String merchMobile) {
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put("loginName", loginName);
		userParams.put("merchMobile", merchMobile);
		return merchInfoMapper.getByLoginName(userParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="新增商户信息")
	public int addMerchInfo(RequestBean<RegistUserVO> reqData,MerchUser refer) {
		MerchInfo merch = new MerchInfo();
        merch.setMcc(merchMcc);
        merch.setUserId(reqData.getData().getMobile());
		merch.setMobile(reqData.getData().getMobile());
		merch.setMerchType(15);// [Moyyq5] 2016.01.19 商户类型包括MPOS、传统POS、二维码、网关功能
		merch.setMerchKey(StrUtil.mkQrcodeKey());// [Moyq5] 2017.0.19 初始化二维码交易需要的密钥
		merch.setType(MerchType.PERSONAL.ordinal());  //默认个人
		merch.setBizType(Integer.parseInt(bizType));
		merch.setMerchLevel(1);// [Moyq5] 2017.0.20 商户级别
		merch.setTransCtrl(3);// [Moyq5] 2017.0.20 消费、查询余额
		merch.setAuditAgentno("2000440305");
		merch.setBranchno("2000440305");
		merch.setAuditStatus(MerchAuditStatus.UNCOMMITTED.ordinal());
		merch.setJudgStatus(MerchJudgStatus.UNCOMMITTED.ordinal());
		merch.setStatus(MerchStatus.OPENED.getValue());
		merch.setDefSettleType(MerchSettleType.T0.ordinal());
		merch.setAppRate(new BigDecimal(appRate));
		merch.setAppFixed(new BigDecimal(appFixed));
		merch.setAppCredit(new BigDecimal(creditRate));
		merch.setAppT0Rate(new BigDecimal(toRate));
		merch.setSilenceMerch("N");
		merch.setAccountType(1);
		merch.setRealNameAuthStatus(0);
		//没用的数据
		merch.setStripeCardFixed(new BigDecimal(30000));
		merch.setChipCardFixed(new BigDecimal(30000));
		merch.setDebitRate(new BigDecimal(0.0055));
		merch.setDebitFixed(new BigDecimal(0));
		merch.setCreditRate(new BigDecimal(0.0055));
		merch.setCreditFixed(new BigDecimal(0));
		
		//推荐人是员工，直接归属到上上级，上上级也是员工，归平台
		if(1==refer.getUserRank()){
			MerchUser upReferInfo = merchUserMapper.getReferInfoByMerchUser(refer);
			if(upReferInfo.getUserRank()==1){
				merch.setSuperAgent("2000440305");
			}else{
				merch.setSuperAgent(upReferInfo.getAgentno());
			}
		}else{
			AgentInfo4Search agent4s = new AgentInfo4Search();
			agent4s.setMobile(refer.getMobile());
			merch.setSuperAgent(agentInfoService.listByPage(agent4s).get(0).getAgentno());
		}
		String merchno = branchno.substring(0,3)+merchMcc;
		Integer id = Integer.parseInt(getMerchMaxId(merchno))+1;
		String idStr = "" + id;
		merch.setMerchno(merchno+(StrUtil.isEmpty(getMerchMaxId(merchno))?"00000001":"0000000".substring(0, 8-idStr.length())+id));
		merch.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		merch.setOpenTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		merch.setMerchName("");
		merch.setLinkMan("");
		merch.setFullName("");
		merch.setLegalName("");
		merch.setT0Rate(new BigDecimal(0.00)); 
		merch.setIdentityCard("");
		merch.setProvince("广东省");
		merch.setCity("深圳市");
		merch.setAreaCode("440300");
		merch.setAddress("广东省深圳市南山区泰邦大厦");
		
		//获取平台商户等级默认费率
		GradeFee4Search gfs = new GradeFee4Search();
		gfs.setGradeType(1);
		gfs.setPayType(1<<QrcodePayType.FAST.ordinal());
		List<GradeFee> list = gradeFeeService.list(gfs);
		
		//银联快捷（不带积分）
		BigDecimal t0Rate=new BigDecimal("0.0");
		BigDecimal toFee=new BigDecimal("0.0");
		//银联快捷（带积分）
		BigDecimal jf_t0Rate=new BigDecimal("0.0");
		BigDecimal jf_toFee=new BigDecimal("0.0");
		for(GradeFee gf : list){
//			if ((gf.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal())) {
//				t0Rate = gf.getRate();
//				toFee = gf.getFee();
//			}
			if ((gf.getChannelType()==((1<<SysChannelType.YUFU.ordinal())+(1<<SysChannelType.HXTC.ordinal())))&&
					((gf.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal()))) {
				t0Rate = gf.getRate();
				toFee = gf.getFee();
			}
			
			if ((gf.getChannelType()==(1<<SysChannelType.HXTC_JF.ordinal()))&&((gf.getSettleType()&(1<<SysSettleType.T0.ordinal())) == (1<<SysSettleType.T0.ordinal()))) {
				jf_t0Rate = gf.getRate();
				jf_toFee = gf.getFee();
			}
//			if ((gf.getSettleType()&(1<<SysSettleType.T1.ordinal())) == (1<<SysSettleType.T1.ordinal())) {
//				t1Rate = gf.getRate();
//				t1Fee = gf.getFee();
//			}
		}
//		merch.setFastpayRateT0(t0Rate);
//		merch.setFastpayRateT1(t0Rate);
		
		//更新渠道费率信息表
		List<ChannelSubmerchInfo> channellist = new ArrayList<ChannelSubmerchInfo>();
		channellist.add(buildRegistChannelSubmerchInfo(merch,SysChannelType.YUFU.getCode(),SysChannelType.YUFU.getText(),toFee,t0Rate));
		channellist.add(buildRegistChannelSubmerchInfo(merch,SysChannelType.HXTC.getCode(),SysChannelType.HXTC.getText(),toFee,t0Rate));
		channellist.add(buildRegistChannelSubmerchInfo(merch,SysChannelType.HXTC_JF.getCode(),SysChannelType.HXTC_JF.getText(),jf_toFee,jf_t0Rate));
		channelSubMerchInfoMapper.addChannelSubMerchInfoList(channellist);
		//创建商户渠道信息表
		merchInfoMapper.addMerchInfo(merch);
		//创建虚拟账户
		merchVirtualCardService.add4Merch(merch.getMerchno());
		
		return merch.getId();
	}

	private ChannelSubmerchInfo buildRegistChannelSubmerchInfo(MerchInfo merchInfo,String channelCode,String channelName,
			BigDecimal toFee,BigDecimal toRate) {
		
		ChannelSubmerchInfo csmi = new ChannelSubmerchInfo();
		csmi.setBranchno(merchInfo.getBranchno());
		csmi.setAgentno(merchInfo.getSuperAgent());
		csmi.setMerchno(merchInfo.getMerchno());
		csmi.setCreatedate(new Date());
		csmi.setChannelName(channelName);
		csmi.setChannelCode(channelCode);
		csmi.setChannelSubmerchno("");
		csmi.setPayType("QUICKPAY");
		csmi.setAduitStatus("");
		csmi.setPayFeeD0(toFee);
		csmi.setPayFeeT1(new BigDecimal(0));
		csmi.setD0payRate(toRate);
		csmi.setT1payRate(toRate);
		csmi.setRemark("默认等级费率");
		csmi.setPayMethod("QUICKPAY");
		log.info(csmi.toString());
		return csmi;
	}
	
	@Override
	@ServiceLogAnnotation(moduleName="查询累计收款")
	public BigDecimal getSuccTranAmt(String merchno) {
		Map<String,String> tranParams = new HashMap<String,String>();
		tranParams.put("merchno", merchno);
		return merchInfoMapper.getSuccTranAmt(tranParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询可提现金额")
	public BigDecimal getAvailAmt(String merchno) {
		Map<String,String> tranParams = new HashMap<String,String>();
		tranParams.put("merchno", merchno);
		return merchInfoMapper.getAvailAmt(tranParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询商户收款明细")
	public List<ReceiveDetailInfo> getTranDetail(String merchno
			,String startDate,String endDate) {
		Map<String,String> tranParams = new HashMap<String,String>();
		tranParams.put("merchno", merchno);
		tranParams.put("startDate", startDate);
		tranParams.put("endDate", endDate);
		List<ReceiveDetailInfo> tranDetailList = merchInfoMapper.getTranDetail(tranParams);
		return tranDetailList;
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询商户提现明细")
	public List<WithdrawalDetailInfo> getWithdrawalDetail(String merchno, 
			String startDate,String endDate) {
		Map<String,String> tranParams = new HashMap<String,String>();
		tranParams.put("merchno", merchno);
		tranParams.put("startDate", startDate);
		tranParams.put("endDate", endDate);
		return merchInfoMapper.getWithdrawalDetail(tranParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="获取对应状态下的商户信息")
	public List<MerchInfo> getMerchInfoByStatus(String merchno,String status,String gradeType,
			String queryParam) {
		Map<String,String> merchParams = new HashMap<String,String>();
		
		MerchInfo merch = getByMerchno(merchno);
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merch.getId());
		MerchUser merchUser = merchUserMapper.listByPage(user4s).get(0);
		
		merchParams.put("referName", merchUser.getId().toString());
		merchParams.put("status", StrUtil.isEmpty(status)?"":status);
		merchParams.put("userRank", gradeType);
		if(!StringUtils.isEmpty(queryParam)&&queryParam.startsWith("1")){
			merchParams.put("mobile", queryParam);
		}else{
			merchParams.put("merchName", queryParam);
		}
		return merchInfoMapper.getMerchInfoByStatus(merchParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="新增待审核商户信息")
	@Transactional
	public SingleResult<String> addChangeMerchInfo(RequestBean<IdentifyAuthenVO> reqData) throws ApiException{
		String frontUrl = reqData.getData().getFrontUrl();
		String backUrl = reqData.getData().getBackUrl();
		
		SingleResult<String> singleResult = new SingleResult<String>();
		
		MerchInfo merch = getByMerchno(reqData.getMerchno());
		MerchInfo merch4Mod = merch;
		
		if(!StrUtil.isEmpty(changeMerchInfoMapper.getChangeMerchInfo(reqData.getMerchno()))){
			singleResult.setErrorCode(ResCode.API_ERROE_CODE_0025.getErrorCode());
			singleResult.setErrorHint(ResCode.API_ERROE_CODE_0025.getErrorMes());
			singleResult.setSuccess(false);
			return singleResult;
		}
		
		ChangeMerchInfo changeMerchInfo = new ChangeMerchInfo();
		changeMerchInfo.setAgentno(merch.getSuperAgent());
		changeMerchInfo.setFullName("易购个体户"+reqData.getData().getIdName());
		changeMerchInfo.setShortName("易购个体户"+reqData.getData().getIdName());
		changeMerchInfo.setLinkMan(reqData.getData().getIdName());
		changeMerchInfo.setEmail(merch.getEmail());
		changeMerchInfo.setTelephone(merch.getTelephone());
		changeMerchInfo.setAreaCode(merch.getAreaCode());
		changeMerchInfo.setAddress(merch.getAddress());
		changeMerchInfo.setMobile(merch.getMobile());// 手机号始终不可以修改
		changeMerchInfo.setAddTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		changeMerchInfo.setMerchId(merch.getId());
		changeMerchInfo.setAuditStatus(ChangeMerchInfoAuditStatus.AUDITING.ordinal());
		changeMerchInfo.setAuditStatus(ChangeMerchInfoAuditStatus.AUDITING.ordinal());
		changeMerchInfo.setLegalName(reqData.getData().getIdName());
		changeMerchInfo.setIdCard(reqData.getData().getIdNo());
		changeMerchInfo.setValiTime(reqData.getData().getIdValiTime());
		//changeMerchInfo.setImgIdCard1(frontUrl);
		//changeMerchInfo.setImgIdCard2(backUrl);
		changeMerchInfo.setJsonStr(JSONUtils.strToJson("frontUrl", frontUrl, "backUrl", backUrl, ""));
		changeMerchInfo.setJsonStr(JSONUtils.strToJson("sessionId", reqData.getData().getSessionId(), "", "", changeMerchInfo.getJsonStr()));
		
		merch4Mod.setFullName("易购个体户"+reqData.getData().getIdName());
		merch4Mod.setMerchName("易购个体户"+reqData.getData().getIdName());
		merch4Mod.setIdentityCard(reqData.getData().getIdNo());
		merch4Mod.setLegalName(reqData.getData().getIdName());
		merch4Mod.setLinkMan(reqData.getData().getIdName());
		merch4Mod.setAuditStatus(1);
		merch4Mod.setJudgStatus(1);
		//merch4Mod.setIdentity1Img(frontUrl);
		//merch4Mod.setIdentity2Img(backUrl);
		merch4Mod.setRealNameAuthStatus(1);
		merch4Mod.setJsonStr(changeMerchInfo.getJsonStr());
		
		//更新user表记录
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put("merchId", merch.getId().toString());
		MerchUser user = merchUserMapper.queryMerchUser(userParams);
		
		user.setTrueName(reqData.getData().getIdName());
		
		if(changeMerchInfoService.add(changeMerchInfo)>0&&merchInfoMapper.updateMerchInfo(merch4Mod)>0
				&&merchUserMapper.updateMerchUserInfo(user)>0){
			singleResult.setErrorHint("身份认证成功");
			singleResult.setSuccess(true);
			return singleResult;
		};
		singleResult.setErrorCode(ResCode.API_ERROE_CODE_0020.getErrorCode());
		singleResult.setErrorHint(ResCode.API_ERROE_CODE_0020.getErrorMes());
		singleResult.setSuccess(false);
		return singleResult;
	}
	
	@Override
	public MerchInfo getByMerchno(String merchno) {
		return merchInfoMapper.getByMerchno(merchno);
	}

	@ServiceLogAnnotation(moduleName="根据Id获取商户信息")
	@Override
	public MerchInfo getById(Integer id) {
		return merchInfoMapper.getById(id);
	}

	@ServiceLogAnnotation(moduleName="商户升级支付")
	@Override
	public String upgradePay(String merchno, UpgradePayDataVO upVo) throws Exception {
		RequestBean<String> merchData = new RequestBean<String>();
		merchData.setData(merchno);
		MerchInfo merch = merchInfoFeign.getByMerchno(merchData).getData();
		
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merch.getId());
		MerchUser merchUser = merchUserService.listByPage(user4s).get(0);

		if (merchUser.getUserRank() >= upVo.getGradeType()) {
			throw new Exception("升级等级必须高于用户当前等级");
		}
		
		Grade grade = gradeService.getByGradeType(upVo.getGradeType());
		
		RequestBean<String> pmData = new RequestBean<String>();
		pmData.setData(SystemParamCode.SYSTEM_PAYEE_MERCHNO.name().toLowerCase());
		String payeeMerchno = systemParamFeign.getByCode(pmData).getData().getValue();
		
		QrcodePayDataVO qrVo = new QrcodePayDataVO();
		qrVo.setAmount(grade.getAmount().toPlainString());
		qrVo.setIsOffical(false);
		qrVo.setMerchno(payeeMerchno);
		//qrVo.setNotifyUrl(baseUrl + "/gradeOrder/notify?payerMerchId=" + merch.getId());
		qrVo.setPayType(upVo.getPayType());
		qrVo.setChannelType(upVo.getChannelType());
		
		RequestBean<QrcodePayDataVO> qrData = new RequestBean<QrcodePayDataVO>();
		qrData.setData(qrVo);
		ResultBean<QrcodePayResultVO> qrResult = qrcodeOrderFeign.qrcodePay(qrData);
		if (!qrResult.getRespCode().contentEquals("1")) {
			throw new Exception(qrResult.getMessage());
		}
		
		QrcodePayResultVO resultVo = qrResult.getData();
		final String orderno = resultVo.getOrderno();
		final String qrcodeUrl = resultVo.getQrcodeUrl();
		
		GradeOrder order = new GradeOrder();
		order.setAddTime(DateFormat.DATE_TIME.format());
		order.setAmount(grade.getAmount());
		order.setGradeType(grade.getGradeType());
		order.setRefno(orderno);
		order.setStatus(GradeOrderStatus.NEW.ordinal());
		order.setMerchId(merch.getId());
		
		RequestBean<GradeOrder> orderFeignData = new RequestBean<GradeOrder>();
		orderFeignData.setData(order);
		gradeOrderFeign.add(orderFeignData);
		
		return qrcodeUrl;
	}
	
	@Override
	public void upgradeByGradeOrderId(Integer gradeOrderId) throws Exception {
		RequestBean<Integer> orderData = new RequestBean<Integer>();
		orderData.setData(gradeOrderId);
		GradeOrder order = gradeOrderFeign.getById(orderData).getData();
		
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(order.getMerchId());
		MerchUser merchUser = merchUserService.listByPage(user4s).get(0);

		if (order.getGradeType() <= merchUser.getUserRank()) {
			log.debug("用户无需要升级，当前等级["+ merchUser.getUserRank() +"]已比目标等级[grade.getGradeType()]高");
			return;
		}
		
		merchUser.setUserRank(order.getGradeType());
		UpgradeFactory.getContext(merchUser).upgrade();
		
		MerchUser user4Mod = new MerchUser();
		user4Mod.setId(merchUser.getId());
		user4Mod.setUserRank(order.getGradeType());
		merchUserService.mod(user4Mod);
	}
	
	@ServiceLogAnnotation(moduleName="商户升级")
	@Override
	public void upgradeInviter(Integer merchId)throws ApiException{
		
		MerchUser4Search user4s = new MerchUser4Search();
		user4s.setMerchId(merchId);
		MerchUser merchUser = merchUserService.listByPage(user4s).get(0);
		
		user4s = new MerchUser4Search();
		user4s.setReferName("" + merchUser.getId());
		Integer totalQuantity = merchUserService.count(user4s);
		Grade grade = gradeService.getMaxByLessThan(totalQuantity);
		
		if (grade.getGradeType() <= merchUser.getUserRank()) {
			log.debug("用户无需要升级，当前等级["+ merchUser.getUserRank() +"]已比目标等级[grade.getGradeType()]高");
			return;
		}
		
		merchUser.setUserRank(grade.getGradeType());
		try {
			UpgradeFactory.getContext(merchUser).upgrade();
			
			if(2==merchUser.getUserRank()){
				MerchInfo merchInfo = new MerchInfo();
				merchInfo.setId(merchId);
				AgentInfo agentInfo = agentInfoMapper.getByMerchInfo(merchInfo);
				
				Map<String,String> map = new HashMap<String,String>();
				map.put("agentno", agentInfo.getAgentno());
				map.put("id", merchUser.getId().toString());
				merchInfoMapper.batUpdMerchInfo(map);
			}
			
			MerchUser user4Mod = new MerchUser();
			user4Mod.setId(merchUser.getId());
			user4Mod.setUserRank(grade.getGradeType());
			merchUserService.mod(user4Mod);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApiException(ApiCode.C9999);
		}
	}

	@ServiceLogAnnotation(moduleName="查询默认商户信息，用于登录")
	@Override
	public MerchInfo getByLoginName(String loginName) {
		Map<String,String> userParams = new HashMap<String,String>();
		userParams.put("loginName", loginName);
		return merchInfoMapper.getByLoginName(userParams);
	}

	@ServiceLogAnnotation(moduleName="更新商户信息")
	@Override
	public void mod(MerchInfo merch) {
		merchInfoMapper.mod(merch);
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改HXTC商户费率")
	public int modifyHXTCMerchFee(MerchInfo merchInfo) {
		return merchInfoMapper.modifyHXTCMerchFee(merchInfo);
	}

	@Override
	public String getMerchMaxId(String merchno) {
		return merchInfoMapper.getMerchMaxId(merchno);
	}
	
	@ServiceLogAnnotation(moduleName="查询商户列表")
	@Override
	public List<MerchInfo> listByPage(MerchInfo4Search merchInfo4s) {
		return merchInfoMapper.listByPage(merchInfo4s);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询单笔提现订单详情")
	public WithdrawalDetailInfo queryWithdrawalOrderDetail(String orderNo) {
		return merchInfoMapper.queryWithdrawalOrderDetail(orderNo);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询单笔收款订单详情")
	public ReceiveDetailInfo queryReceiveOrderDetail(String orderNo,String payType) {
		Map<String,String> queryParam=new HashMap<String,String>();
		queryParam.put("orderNo", orderNo);
		queryParam.put("payType", payType);
		return merchInfoMapper.queryReceiveOrderDetail(queryParam);
	}

	@Override
	@ServiceLogAnnotation(moduleName="修改御付商户费率")
	public int modifyYUFUMerchFee(MerchInfo merchInfo) {
		return merchInfoMapper.modifyYUFUMerchFee(merchInfo);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询各状态下收款明细")
	public List<ReceiveDetailInfo> queryReceiveDetailByStatus(String merchno, String startDate, String endDate,
			String tranStatus) {
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("merchno", merchno);
		queryParams.put("startDate", startDate);
		queryParams.put("endDate", endDate);
		queryParams.put("tranStatus", tranStatus);
		return merchInfoMapper.queryReceiveDetailByStatus(queryParams);
	}

	@Override
	@ServiceLogAnnotation(moduleName="查询各状态下提现明细")
	public List<WithdrawalDetailInfo> queryWithdrawalDetailByStatus(String merchno, String startDate, String endDate,
			String tranStatus) {
		Map<String,String> queryParams = new HashMap<String,String>();
		queryParams.put("merchno", merchno);
		queryParams.put("startDate", startDate);
		queryParams.put("endDate", endDate);
		queryParams.put("tranStatus", tranStatus);
		return merchInfoMapper.queryWithdrawalDetailByStatus(queryParams);
	}

	@SuppressWarnings("unused")
	@Override
	@ServiceLogAnnotation(moduleName="更新待审核商户信息")
	public SingleResult<String> updateChangeMerchInfo(RequestBean<LivingIdentifyVO> reqData) throws ApiException {
//		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
//		TransactionStatus status = platformTransactionManager.getTransaction(def);
		
		SingleResult<String> singleResult = new SingleResult<String>();
		String livingPhotoUrl = reqData.getData().getLivingPhotoUrl();
		String videoUrl = reqData.getData().getVideoUrl();
		
		MerchInfo merch = getByMerchno(reqData.getMerchno());
		MerchInfo merch4Mod = merch;

		ChangeMerchInfo changeMerchInfo = new ChangeMerchInfo();
		changeMerchInfo = changeMerchInfoService.getChangeMerchInfo(reqData.getMerchno());
		
		if(StrUtil.isEmpty(changeMerchInfo)){
			singleResult.setErrorCode(ResCode.API_ERROE_CODE_0026.getErrorCode());
			singleResult.setErrorHint(ResCode.API_ERROE_CODE_0026.getErrorMes());
			singleResult.setSuccess(false);
			return singleResult;
		}
		changeMerchInfo.setJsonStr(JSONUtils.strToJson("livingPhotoUrl", livingPhotoUrl, "videoUrl", videoUrl, 
				changeMerchInfo.getJsonStr()));
		
		merch4Mod.setJsonStr(changeMerchInfo.getJsonStr());
		merch4Mod.setRealNameAuthStatus(3);
		merch4Mod.setAuditStatus(3);
		merch4Mod.setJudgStatus(3);
		
		SingleResult<String> yufuResult = new SingleResult<String>();
		SingleResult<String> hxtcResult = new SingleResult<String>();
		SingleResult<String> hxtcJFResult = new SingleResult<String>();
		
		if(changeMerchInfoMapper.updateToAuditInfo(changeMerchInfo)>0&&merchInfoMapper.updateMerchInfo(merch4Mod)>0){
			//身份实名认证成功之后   上报商户所有快捷通道费率   Horanluo add
			try {
				yufuResult = channelMerchInfoService.reportMerchFee(reqData.getMerchno(),merch4Mod, "YUFU");
				hxtcResult = channelMerchInfoService.reportMerchFee(reqData.getMerchno(),merch4Mod, "HXTC");
				hxtcJFResult = channelMerchInfoService.reportMerchFee(reqData.getMerchno(),merch4Mod, "HXTC_JF");
				
				//完善资料成功，自动升级推荐人
				MerchUser merchUser = merchUserMapper.getReferInfo(reqData.getMerchno());
				upgradeInviter(merchUser.getMerchId());
				
				singleResult.setSuccess(true);
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				//platformTransactionManager.rollback(status);
				throw new ApiException(ApiCode.C9999);
			}//finally{
				//platformTransactionManager.commit(status);
			//}
		};
		singleResult.setErrorCode(ResCode.API_ERROE_CODE_0022.getErrorCode());
		singleResult.setErrorHint(ResCode.API_ERROE_CODE_0022.getErrorMes());
		singleResult.setSuccess(false);
		return singleResult;
	}

	@Override
	public List<MerchInfo> getByMerchInfo(MerchInfo merchInfo) {
		return merchInfoMapper.getByMerchInfo(merchInfo);
	}
}
