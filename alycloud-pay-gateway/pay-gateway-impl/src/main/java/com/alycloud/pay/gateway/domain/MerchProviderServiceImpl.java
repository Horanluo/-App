/*
 * 类文件名:  MerchProviderServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月21日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alycloud.channel.api.IQuickpayService;
import com.alycloud.channel.factory.QuickpayFactory;
import com.alycloud.channel.utils.hxtc.HxtcUtils;
import com.alycloud.core.annotation.ServiceLogAnnotation;
import com.alycloud.core.enums.SysChannelType;
import com.alycloud.core.mapper.system.AreaMapper;
import com.alycloud.core.utils.ReflectUtils;
import com.alycloud.modules.channel.ChannelSubmerchInfoBean;
import com.alycloud.modules.channel.yufu.AbstractYufuEntity;
import com.alycloud.modules.channel.yufu.AbstractYufuResultEntity;
import com.alycloud.modules.channel.yufu.YufuChannelMerchBean;
import com.alycloud.modules.channel.yufu.YufuChannelMerchBiz;
import com.alycloud.modules.channel.yufu.YufuMerchBean;
import com.alycloud.modules.channel.yufu.YufuMerchPayServiceBean;
import com.alycloud.modules.channel.yufu.enums.MerchState;
import com.alycloud.modules.channel.yufu.enums.YufuPayServiceType;
import com.alycloud.modules.entity.MerchInfo;
import com.alycloud.modules.system.AreaBean;
import com.alycloud.pay.gateway.api.IMerchProviderService;
import com.alycloud.pay.gateway.api.channel.IChannelSubmerchService;
import com.alycloud.pay.gateway.channel.util.HttpsClientUtil;
import com.alycloud.pay.gateway.channel.util.RequestBuilder;
import com.alycloud.pay.gateway.channel.util.SignUtil;
import com.alycloud.pay.gateway.dto.MerchDTO;
import com.alycloud.pay.gateway.mapper.AgentMapper;
import com.alycloud.pay.gateway.mapper.BranchMapper;
import com.alycloud.pay.gateway.mapper.MerchMapper;
import com.alycloud.pay.gateway.mapper.MerchRegisterMapper;
import com.alycloud.pay.gateway.mapper.MerchUserMapper;
import com.alycloud.pay.gateway.mapper.channel.YufuChannelMerchBizMapper;
import com.alycloud.pay.gateway.mapper.channel.YufuChannelMerchMapper;
import com.alycloud.pay.gateway.models.BranchBean;
import com.alycloud.pay.gateway.models.MerchRelateBean;
import com.alycloud.pay.gateway.models.MerchUserBean;
import com.alycloud.pay.gateway.models.RegisterResponseBean;
import com.alycloud.pay.gateway.models.ResponseBean;
import com.alycloud.pay.gateway.models.agent.AgentBean;
import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchFeeBean;
import com.alycloud.pay.gateway.utils.MD5;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class MerchProviderServiceImpl implements IMerchProviderService
{
    Logger logger = LoggerFactory.getLogger(MerchProviderServiceImpl.class);
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Autowired
    private BranchMapper branchMapper;
    
    @Autowired
    private AgentMapper agentMapper;
    
    @Autowired
    private MerchMapper merchMapper;
    
    @Autowired
    private AreaMapper areaMapper;
    
    @Autowired
    private MerchUserMapper merchUserMapper;
    
    @Autowired
    private MerchRegisterMapper merchRegisterMapper;
    
    @Autowired
    private IChannelSubmerchService submerchService;
    
    @Autowired
    private AbstractYufuEntity config;
    
    private String d0account;
    private String d0Key;
    private String requestUrl;
    
    private String t1account;
    private String t1Key;
    
    @Autowired
    private YufuChannelMerchMapper yufuChannelMerchMapper;
    
    @Autowired
    private YufuChannelMerchBizMapper yufuChannelMerchBizMapper;
//    private String requestUrl = "https://apihf.onepaypass.com/aps/cloudplatform/api/trade.html";
    
    /**
     * 重载方法
     * 
     * @param merchBean
     * @return
     */
    @ServiceLogAnnotation(moduleName="用户注册")
    public ResponseBean merchRegister(MerchDTO merchDTO,String key)
        throws Exception
    {
        AgentBean agentBean = agentMapper.getByAgentno(merchDTO.getAgentno());
        
        if (agentBean == null || StringUtils.isEmpty(agentBean.getAgentName()))
        {
            throw new Exception("找不到代理商信息");
        }
        logger.info("查询到代理商[" + agentBean.getAgentName() + "]的名称 >>>> [" + agentBean.getAgentName() + "]");
//        merchDTO.setBranchno(agentBean.getBranchno());
        String branchno = agentBean.getBranchno();
        MerchInfo merch = buildMerchInfo(merchDTO, branchno);
//        String branchno = merch.getBranchno();
        String agentno = merch.getSuperAgent();
        String mobile = merch.getMobile();
        String merchName = merch.getMerchName();
        logger.info("判断分公司[" + branchno + "]是否存在");
        BranchBean branch = branchMapper.getByBranchno(branchno);
        if (branch == null)
        {
            throw new Exception("分公司不存在");
        }
        
        String merchno = generateMerchno(branch.getBizCode(), merch.getAreaCode(), merch.getMcc());
        logger.info("产生商户编号 >>>> " + merchno);
        merch.setMerchno(merchno);
        merchDTO.setMerchno(merchno);
        
        // MerchBean merchBean = buildMerchInfo(merchDTO);
        List<QrcodeMerchFeeBean> qrcodeRate = buildQrcodeFeeBean(merchDTO,branchno);
        
        logger.info("判断代理商[" + agentno + "]是否属于分公司[" + branchno + "]的一级代理商");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("branchno", branchno);
        param.put("agentno", agentno);
        //String agentName = agentMapper.getAgentName(param);
//        logger.info("查询到代理商[" + agentno + "]的名称 >>>> [" + agentName + "]");
//        if (StringUtils.isEmpty(agentName))
//        {
//            throw new Exception("找不到代理商信息");
//        }
        logger.info("判断商户的手机号码[" + mobile + "]是否已经存在");
        Map<String, String> userParams = new HashMap<String, String>();
        userParams.put("loginName", mobile);
        userParams.put("branchno", branchno);
        MerchUserBean userBean = merchMapper.getByLoginName(userParams);
        if (userBean != null)
        {
            throw new Exception("该用户[" + mobile + "]已存在");
        }
        logger.info("判断商户的简称[" + merchName + "]是否存在");
        // if (merchDao.validMerchName(merchName))
        // {
        // throw new Exception("该用户简称[" + merchName + "]已存在");
        // }
        logger.info("判断商户的全称[" + merchName + "]是否存在");
        if (merchMapper.validFullName(merchName) > 0)
        {
            throw new Exception("该用户全称[" + merchName + "]已存在");
        }
        logger.info("判断该代理商是否存在未绑定的终端");
        // List<TermBean> list = termDao.listByAgentno(agentno);
        // if (list.size() < 0)
        // {
        // throw new Exception("该分公司未找到可使用的终端,自动注册失败");
        // }
        // logger.info("查询到[" + list.size() + "]个有效终端");
        
        try
        {
            /** 新增商户信息 **/
            logger.info("创建商户基本信息:\r\n" + merch);
            if(merchDTO.getPayTypes() != null && merchDTO.getPayTypes().size() > 0)
            {
                merch.setHfbankt1merchno(hfbanckT1MerchNo(merchDTO));
                merch.setHfbankd0merchno(hfbanckD0MerchNo(merchDTO));  
            }
            
            //注册汇享天成用户
            String channelSubmerchno =  registerHxtcSubMerch(merch,merchDTO);
            
            //注册御付账户
            registerYufuSubMerch(merch,merchDTO);
            
            merch.setHpmercode(channelSubmerchno);
            int result = merchRegisterMapper.insertMerchInfo(merch);
            
            
            if (result <= 0)
            {
                throw new Exception("新增商户信息失败");
            }
            logger.info("商户ID:" + merch.getId());
            
            /** 插入商户关系 **/
            List<MerchRelateBean> relate = buildMerchRelate(merch, branch, agentBean.getAgentName());
            logger.info("创建商户与分公司、一级代理商的层级关系:\r\n" + relate);
            result = merchRegisterMapper.insertMerchRelate(relate);
            if (result <= 0)
            {
                throw new Exception("新增商户层级关系失败");
            }
            
            /** 插入商户操作员 **/
            MerchUserBean user = buildMerchUser(merch);
            logger.info("创建商户的操作员:\r\n" + user);
            result = merchRegisterMapper.insertMerchUser(user);
            if (result <= 0)
            {
                throw new Exception("新增商户操作员失败");
            }
            if(qrcodeRate != null && qrcodeRate.size() > 0)
            {
                result = merchMapper.batchAddQrcodeChannelFee(qrcodeRate);
                if (result <= 0)
                {
                    throw new Exception("添加二维码费率失败");
                }
            }
            
            RegisterResponseBean responseBean = new RegisterResponseBean("1", "注册成功", merch.getMerchno(), null, null, merch.getMerchKey());
            String sign = SignUtil.genSign(key, SignUtil.createLinkString(SignUtil.paraFilter(ReflectUtils.convertToMaps(responseBean))));
            responseBean.setSign(sign);
            return responseBean;
            // } else {
            // throw new Exception("T+0申请失败," + parser.getResMsg());
            // }
        }
        catch (Exception e)
        {
            logger.error("商户注册出现异常", e);
            return new ResponseBean("0", e.getMessage());
        }
        // return merchRegisterMapper.register(branch, merch, agentName);
        
        // return merchRegisterMapper.register(branch,
        // merch,
        // agentName,
        // list.get(0),
        // Config.getString("tl-ip"),
        // Config.getInt("tl-port"),
        // Config.getString("tl-url"),
        // Config.getString("tl-key"));
    }
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月14日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private String registerYufuSubMerch(MerchInfo merch, MerchDTO merchDTO)
    {
//        IQuickpayService quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.YUFU.getCode());
//        YufuMerchQueryBean queryData = new YufuMerchQueryBean();
//        queryData.setPhone(merch.getMobile());
//        queryData.setSerialNo("E" + new Date().getTime());
//        YufuMerchQueryResultBean queryResultBean = quickpayService.queryChannelSubmerch(queryData);
        
        final BigDecimal t0Rate = merchDTO.getQuickPayD0Rate().multiply(new BigDecimal("1000"));
        final BigDecimal t1Rate = merchDTO.getQuickPayT1Rate().multiply(new BigDecimal("1000"));
//        final String paymentFee = new BigDecimal(merchDTO.getPayFee()).multiply(new BigDecimal("100")).toPlainString();
        final String paymentFee = new BigDecimal(merchDTO.getPayFee()).multiply(new BigDecimal("100")).setScale(0).toPlainString();
        
        List<YufuMerchPayServiceBean> payServices = new ArrayList<YufuMerchPayServiceBean>();
        YufuMerchPayServiceBean payService = new YufuMerchPayServiceBean();
        payService.setBottomFee(new BigDecimal("0.3").multiply(new BigDecimal("100")).setScale(0,BigDecimal.ROUND_DOWN).toPlainString());
        payService.setCreditCardRate(t0Rate.setScale(2).toPlainString());
        payService.setD0FeeBi(paymentFee);
        payService.setD0MinAmount("500");
        payService.setD0Rate(t0Rate.subtract(t1Rate).setScale(2).toPlainString());
        payService.setDebitCardRate(t0Rate.setScale(2).toPlainString());
        payService.setLimitAmount("0");
        payService.setScale(t1Rate.setScale(2).toPlainString());
        payService.setPayService(YufuPayServiceType.KJ);
        payService.setTradeType("5210");// 快捷可默认填写5210
        payServices.add(payService);
        
        YufuMerchBean data = new YufuMerchBean();
        data.setAccountName(merch.getAccountName());
        data.setAccountNo(merch.getAccountno());
        data.setAccountType(1);// 0-民生对公、1-民生对私、2-非民生对公、3-非民生对私(快捷可默认填写：1)
        data.setBankBranchName(merch.getBankno());// 联行号，精确到支行网点非民生对公必填
        data.setBankCode("310290000013");// 开户行号，快捷可默认填写：310290000013
        data.setBankName("310290000013");// 开户行名    结算户名联行号，精确到总行和bankCode传递一样的值（快捷可默认填写：310290000013）
        data.setCity(2904);// 快捷可默认填写：2904
        data.setIdCard(merch.getIdentityCard());
        data.setMerchAddress(merch.getAddress());
        data.setMerchContacts(merch.getLinkMan());
        data.setMerchName(merch.getFullName());// 商户名称 全局唯一性判重
        data.setMerchShortName(merch.getMerchName());// 商户简称
        //data.setNotifyUrl(URLEncoder.encode("http://api.esyto.com/notify/yufu.do?m=apply", "UTF-8"));// 商户开通结果通知的url
        data.setNotifyUrl("http://api.esyto.com/channel/notify.do?m=yufu4FastApply");// 商户开通结果通知的url
        data.setOpenType(4);// 快捷可默认选择：4
        data.setPayServices(payServices);
        data.setPhone(merch.getMobile());// 联系人电话 全局唯一性判重
        data.setProvince(29);// 快捷可默认填写29
        String timestamp = "" + new Date().getTime();
        String merchno = merch.getMerchno();
        data.setSerialNo(timestamp.substring(timestamp.length()-8, timestamp.length()-1) + merchno.substring(merchno.length()-10, merchno.length()-1));// 流水号
        
        IQuickpayService quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.YUFU.getCode());
//        YufuMerchQueryBean queryData = new YufuMerchQueryBean();
//        queryData.setPhone(merch.getMobile());
//        queryData.setSerialNo("E" + new Date().getTime());
//        YufuMerchQueryResultBean queryResultBean = quickpayService.queryChannelSubmerch(queryData);
        
        AbstractYufuResultEntity result =   quickpayService.registerChannelSubmerch(data);
        YufuChannelMerchBean yufuMerchBean = buildChannelMerchYufu(merch.getMerchno(), data);
        if (!result.getResultCode().equals("0000")) {
            log.warn("商户[{}]入驻御付申请失败：[{}]{}", new Object[]{merch.getMerchno(), result.getResultCode(), result.getResultMessage()});
            yufuMerchBean.setState(3);// 异常
            yufuMerchBean.setRemark(result.getResultMessage());
        }
        long yufuMerchKey =  yufuChannelMerchMapper.insertYufuMerch(yufuMerchBean);
        List<YufuChannelMerchBiz> bizs = buildChannelMerchYufuBizs(data.getPayServices(), yufuMerchBean.getId());
        yufuChannelMerchBizMapper.insertBatch(bizs);
//        String channelSubmerchno = "";
        if(result.getResultCode().equals("0000"))
            {
//               channelSubmerchno = data.get("third_merchant_code");
               ChannelSubmerchInfoBean submerchInfoBean = new ChannelSubmerchInfoBean();
               submerchInfoBean.setBranchno(merch.getBranchno());
               submerchInfoBean.setAgentno(merch.getSuperAgent());
               submerchInfoBean.setMerchno(merch.getMerchno());
               submerchInfoBean.setChannelCode(SysChannelType.YUFU.getCode());
               submerchInfoBean.setChannelName("御付快捷通道");
               submerchInfoBean.setD0payRate(merchDTO.getQuickPayD0Rate());
               submerchInfoBean.setT1payRate(merchDTO.getQuickPayT1Rate());
               submerchInfoBean.setPayFeeD0(new BigDecimal(merchDTO.getPayFee()));
               submerchInfoBean.setPayFeeT1(new BigDecimal(merchDTO.getPayFee()));
               submerchInfoBean.setPayType("QUICKPAY");
               submerchInfoBean.setPayMethod("QUICKPAY");
               submerchInfoBean.setAduitStatus("0");
//               submerchInfoBean.setRemark("接口插入");
//               submerchInfoBean.setChannelSubmerchno(channelSubmerchno);
               submerchService.insert(submerchInfoBean);
            }
        
        return "";
    }
    
    /**
     * 根据御付商户支付接口参数创建相应数据库登记信息
     * @author Moyq5
     * @date 2017年8月6日
     * @param payServices
     * @return
     */
    private List<YufuChannelMerchBiz> buildChannelMerchYufuBizs(List<YufuMerchPayServiceBean> payServices, Long yufuMerchKey) {
        List<YufuChannelMerchBiz> bizList = new ArrayList<YufuChannelMerchBiz>();
        for (YufuMerchPayServiceBean payBiz: payServices) {
            YufuChannelMerchBiz biz = new YufuChannelMerchBiz();
            biz.setBottomFee(payBiz.getBottomFee());
            biz.setCreditCardRate(payBiz.getCreditCardRate());
            biz.setD0FeeBi(payBiz.getD0FeeBi());
            biz.setD0MinAmount(payBiz.getD0MinAmount());
            biz.setD0Rate(payBiz.getD0Rate());
            biz.setDebitCardRate(payBiz.getDebitCardRate());
            biz.setLimitAmount(payBiz.getLimitAmount());
            biz.setPayService(payBiz.getPayService().name());
            biz.setScale(payBiz.getScale());
            biz.setTradeType(payBiz.getTradeType());
            biz.setYufuId(yufuMerchKey);
            bizList.add(biz);
        }
        return bizList;
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * 
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月14日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private YufuChannelMerchBean buildChannelMerchYufu(String merchno, YufuMerchBean data)
    {
        YufuChannelMerchBean yufu = new YufuChannelMerchBean();
        yufu.setAccountName(data.getAccountName());
        yufu.setAccountNo(data.getAccountNo());
        yufu.setAccountType(data.getAccountType());
        yufu.setBankCode(data.getBankCode());
        yufu.setBankName(data.getBankName());
        yufu.setBranchId(config.getBranchNo());
        yufu.setCity(data.getCity());
        //yufu.setCountRole(countRole);
        //yufu.setGszcName(gszcName);
        yufu.setIdCard(data.getIdCard());
        //yufu.setKhxkCode(khxkCode);
        //yufu.setKjKey(kjKey);
        yufu.setMerchAddr(data.getMerchAddress());
        yufu.setMerchContacts(data.getMerchContacts());
        //yufu.setMerchId(merchId);
        //yufu.setMerchLevel(merchLevel);
        yufu.setMerchName(data.getMerchName());
        yufu.setMerchShortName(data.getMerchShortName());
        //yufu.setOneCodeUrl(oneCodeUrl);
        yufu.setOpenBranch(data.getBankBranchName());
        yufu.setOpenType(data.getOpenType());
        //yufu.setParentMerchId(parentMerchId);
        yufu.setPhone(data.getPhone());
        yufu.setPlatMerchno(merchno);
        yufu.setProvince(data.getProvince());
        yufu.setRemark(MerchState.NEW.getText());
        yufu.setState(MerchState.NEW.ordinal());
        //yufu.setSwdjCode(swdjCode);
        //yufu.setTermId(termId);
        //yufu.setYyzzCode(yyzzCode);
        //yufu.setZzjgCode(zzjgCode);
        return yufu;
    }

    /**
     * 汇享天成商户注册
     * @author   曾云龙
     * @version  V001Z0001
     * @date     2017年9月7日
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    private String registerHxtcSubMerch(MerchInfo bean,MerchDTO merchDTO)
    {
        String areaCode = "440305";
        if (bean.getAreaCode() == null || bean.getAreaCode().equals("") || bean.getAreaCode().length() <=5)
        {
          bean.setAreaCode(areaCode);
        }
        AreaBean areaBean = areaMapper.getByCode(areaCode);
        String city = null;
        String province = null;
        if (StringUtils.isEmpty(areaBean.getCity()))
        {
            AreaBean childerBean = areaMapper.getByParentId(String.valueOf(areaBean.getId()));
            city = childerBean.getCity();
            if (StringUtils.isEmpty(childerBean.getProvince()))
            {
                province = areaMapper.getByParentId(String.valueOf(childerBean.getId())).getProvince();
            }
            else
            {
                province = childerBean.getProvince();
            }
        }
        bean.setProvince(province);
        bean.setCity(city);
        String address = "泰邦大厦12I";
        if(StringUtils.isEmpty(bean.getAddress()))
        {
           bean.setAddress(address);
        }
        
        IQuickpayService quickpayService = QuickpayFactory.getQuickpayService(SysChannelType.HXTC.getCode());
        String bankType = merchMapper.getBankByCardno(bean.getAccountno());
        String bankId = merchMapper.getBankIDByBankName(bankType);
        Map<String, String> businessReq = HxtcUtils.builderRegisterRequestParams(bean, bankId,merchDTO.getPayFee());
        Map<String, Object> result =   quickpayService.registerChannelSubmerch(businessReq);
        String channelSubmerchno = "";
        if(result.get("respCode").equals("000000"))
            {
               Map<String, String>  data = (Map<String, String>)result.get("data");
               channelSubmerchno = data.get("third_merchant_code");
               ChannelSubmerchInfoBean submerchInfoBean = new ChannelSubmerchInfoBean();
               submerchInfoBean.setBranchno(bean.getBranchno());
               submerchInfoBean.setAgentno(bean.getSuperAgent());
               submerchInfoBean.setMerchno(bean.getMerchno());
               submerchInfoBean.setChannelCode(SysChannelType.HXTC.getCode());
               submerchInfoBean.setChannelName("汇享天成快捷通道");
               submerchInfoBean.setD0payRate(merchDTO.getQuickPayD0Rate());
               submerchInfoBean.setT1payRate(merchDTO.getQuickPayT1Rate());
               submerchInfoBean.setPayFeeD0(new BigDecimal(merchDTO.getPayFee()));
               submerchInfoBean.setPayFeeT1(new BigDecimal(merchDTO.getPayFee()));
               submerchInfoBean.setPayType("QUICKPAY");
               submerchInfoBean.setPayMethod("QUICKPAY");
               submerchInfoBean.setAduitStatus("1");
               submerchInfoBean.setRemark("接口插入");
               submerchInfoBean.setChannelSubmerchno(channelSubmerchno);
               submerchService.insert(submerchInfoBean);
            }
        
        return channelSubmerchno;
    }
    
    /**
     * 生成商户编号
     * 
     * @param bizCode
     * @param areaCode
     * @param mcc
     * @return
     * @throws Exception
     */
    private String generateMerchno(String bizCode, String areaCode, String mcc)
        throws Exception
    {
        if (areaCode.length() < 4)
        {
            throw new Exception("地区编码[" + areaCode + "]有误");
        }
        
        String prefix = bizCode + areaCode.substring(0, 4) + mcc;
        String maxId = merchMapper.getMerchMaxId(prefix);
//        if (maxId == null || maxId.equals(""))
//        {
//            maxId = "0001";
//        }
        maxId = getMerchMaxId(maxId);
        String merchno = prefix + maxId;
        if (merchno.length() != 15)
        {
            throw new Exception("商户号生成失败");
        }
        return merchno;
    }
    
    public String getMerchMaxId(String obj) throws Exception {
        if (com.alycloud.pay.gateway.utils.StrUtil.isEmpty(obj)) {
            obj = "0";
        }
        int max = Integer.parseInt(obj) + 1;
        String id = "0000" + max;
        return id.substring(id.length() - 4);
    }
    
    /**
     * 重载方法
     */
    @Override
    public void merchChange()
    {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 重载方法
     */
    @Override
    public void qrcodeActivePay()
    {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * 创建商户与分公司和代理商的层级关系
     * 
     * @param merch
     * @param branch
     * @param agentName
     * @return
     */
    private List<MerchRelateBean> buildMerchRelate(MerchInfo merch, BranchBean branch, String agentName)
    {
        List<MerchRelateBean> relate = new ArrayList<MerchRelateBean>();
        
        MerchRelateBean r1 = new MerchRelateBean();
        /** 创建商户与分公司的层级关系 **/
        r1.setAgentLevel("1");
        r1.setAgentName(branch.getBranchName());
        r1.setAgentno(branch.getBranchno());
        r1.setMerchId(String.valueOf(merch.getId()));
        relate.add(r1);
        
        /** 创建商户与代理商的层级关系 **/
        MerchRelateBean r2 = new MerchRelateBean();
        r2.setAgentLevel("2");
        r2.setAgentName(agentName);
        r2.setAgentno(merch.getSuperAgent());
        r2.setMerchId(String.valueOf(merch.getId()));
        relate.add(r2);
        return relate;
    }
    
    /**
     * 创建商户操作员信息
     * 
     * @param merch
     * @throws Exception
     */
    private MerchUserBean buildMerchUser(MerchInfo merch)
        throws Exception
    {
        String mobile = merch.getMobile();
        String passwd = mobile.substring(mobile.length() - 6);
        // 插入商户操作员
        MerchUserBean user = new MerchUserBean();
        user.setBranchno(merch.getBranchno());
        user.setMerchId(merch.getId());
        user.setUserRoler(1);// 用户角色(1-商户管理员)
        user.setLoginName(mobile);
        user.setTrueName(merch.getLegalName());
        user.setPassword(MD5.getMD5ofStr(passwd, "GBK"));
        user.setMobile(mobile);
        user.setStatus(1);// 1-正常
        user.setErrorCount(0);// 登陆错误次数
        user.setAddress(merch.getAddress());
        return user;
    }
    
    /**
     * 创建商户信息
     * 
     * @param param
     * @return
     */
    private MerchInfo buildMerchInfo(MerchDTO merchDTO, String branchno)
    {
        
    	MerchInfo bean = new MerchInfo();
        /** 接口信息 **/
        bean.setBranchno(branchno);
        bean.setSuperAgent(merchDTO.getAgentno());
        bean.setFirstAgentno(merchDTO.getAgentno());
        // bean.setCustomerno(customerno);
        bean.setSilenceMerch("N");
        
        /** 商户基本信息 **/
        bean.setMerchType(15); // 1-MPOS用户
        bean.setType(2);
        bean.setMerchLevel(1);// 商户级别
        bean.setMerchName(merchDTO.getMerchName());
        bean.setFullName(merchDTO.getFullName());
        bean.setLegalName(merchDTO.getLegalName());
        bean.setLinkMan(merchDTO.getLegalName());
        bean.setMobile(merchDTO.getMobile());
        bean.setIdentityCard(merchDTO.getIdentityCard());
        bean.setMcc("5411");
        bean.setAreaCode(merchDTO.getAreaCode());
        bean.setAddress(merchDTO.getAddress());
        bean.setMerchKey(mkQrcodeKey());
        
        /** 费率信息 **/
        bean.setBizType(119);
        bean.setRateMode(2);// 费率模式
//        bean.setDebitRate(merchDTO.getT1Rate());
//        bean.setDebitFixed(merchDTO.getT1Fixed());
//        bean.setCreditRate(merchDTO.getT1Rate());
//        bean.setCreditFixed(merchDTO.getT1Fixed());
//        bean.setT0Rate(merchDTO.getT0Rate());
        
        /** 结算卡信息 **/
        bean.setAccountType(merchDTO.getAccountType());
        bean.setAccountno(merchDTO.getAccountno());
        bean.setAccountName(merchDTO.getAccountName());
        bean.setBankno(merchDTO.getBankno());
        bean.setBankName(merchDTO.getBankName());
        
        /** 接口常量信息 **/
        bean.setIndustryType(1);
        bean.setSettleType(3);// 1-T+1 2-T+0
        bean.setTransCtrl(3);// 1-消费 2-余额查询
        bean.setAuditStatus(3);// 3-审核通过
        bean.setAuditAgentno(branchno);// 审核对象
        bean.setJudgStatus(3);// 1-待审核
        bean.setStatus(2);// 0-已注册 1-未开通 2-已开通
        bean.setOpenTime(sdf.format(new Date()));
        bean.setAddTime(sdf.format(new Date()));
        
        bean.setFastpayRateT0(merchDTO.getQuickPayD0Rate());
        bean.setFastpayRateT1(merchDTO.getQuickPayT1Rate());
        return bean;
    }
    
    private static String mkQrcodeKey()
    {
        return mkRandomStr(32);
    }
    
    private static String mkRandomStr(int length)
    {
        final char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            sb.append(chars[new Long(Math.round(Math.random() * (chars.length - 1))).intValue()]);
        }
        return sb.toString();
    }
    
    private List<QrcodeMerchFeeBean> buildQrcodeFeeBean(MerchDTO merchDTO,String branchno)
    {
        List<QrcodeMerchFeeBean> qrcodeRate = new ArrayList<QrcodeMerchFeeBean>();
        if (merchDTO.getPayTypes() != null && merchDTO.getPayTypes().size() > 0)
        {
            for (QrcodeMerchFeeBean bean : merchDTO.getPayTypes())
            {
                QrcodeMerchFeeBean res = new QrcodeMerchFeeBean();
                res.setBranchno(branchno);
                res.setAgentno(merchDTO.getAgentno());
                res.setMerchno(merchDTO.getMerchno());
                res.setPayType(bean.getPayType());
                res.setScanType(bean.getScanType());
                res.setSettleType(bean.getSettleType());
                res.setRate(bean.getRate());
                qrcodeRate.add(res);
            }
        }
        // /**获取值**/
        // int [] payType = {1,1,2,2};
        // int [] scanType = {7,7,7,7};
        // int [] settleType = {2,1,2,1};
        // String [] rate = {"0.0038","0.0049","0.0038","0.0049"};
        // for (int i = 0; i < payType.length; i++) {
        //
        // }
        return qrcodeRate;
    }
    
    private String hfbanckD0MerchNo(MerchDTO merchDTO) throws Exception
    {
        Map<String, String> returnMap = null;
        try
        {
            
            Map<String, String> map = RequestBuilder.registerBuilder(merchDTO, d0account, 1);
            
            // 过滤空值或null
            Map<String, String> filterMap = SignUtil.paraFilter(map);
            
            // 拼接
            String toSign = SignUtil.createLinkString(filterMap);
            
            // 生成签名sign
            String sign = SignUtil.genSign(d0Key, toSign);
            filterMap.put("sign", sign);
            ObjectMapper mapper = new ObjectMapper();
            // 转为json串
            String postStr = mapper.writeValueAsString(filterMap);
            
            // 发送请求
            String returnStr = HttpsClientUtil.sendRequest(requestUrl, postStr, "application/json");
            // 解析返回串
            returnMap = mapper.readValue(returnStr, Map.class);
            
            // 验签
            if (SignUtil.validSign(returnMap, d0Key))
            {
                String returnCode = returnMap.get("returnCode");
                if (returnCode.equals("0"))
                {
                    // 请在微信客户端打开该url
                    return returnMap.get("subMchId");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw  new Exception("用户注册失败, " + returnMap.get("errCodeDes")) ;
        }
        return null;
    }
    
    private String hfbanckT1MerchNo(MerchDTO merchDTO) throws Exception
    {
        try
        {
            Map<String, String> map = RequestBuilder.registerBuilder(merchDTO, t1account, 2);
            
            // 过滤空值或null
            Map<String, String> filterMap = SignUtil.paraFilter(map);
            
            // 拼接
            String toSign = SignUtil.createLinkString(filterMap);
            
            // 生成签名sign
            String sign = SignUtil.genSign(t1Key, toSign);
            filterMap.put("sign", sign);
            ObjectMapper mapper = new ObjectMapper();
            // 转为json串
            String postStr = mapper.writeValueAsString(filterMap);
            
            // 发送请求
            String returnStr = HttpsClientUtil.sendRequest(requestUrl, postStr, "application/json");
            // 解析返回串
            Map<String, String> returnMap = mapper.readValue(returnStr, Map.class);
            
            // 验签
            if (SignUtil.validSign(returnMap, t1Key))
            {
                String returnCode = returnMap.get("returnCode");
                if (returnCode.equals("0"))
                {
                    // 请在微信客户端打开该url
                    return returnMap.get("subMchId");
                }else
                {
                    throw  new Exception("用户注册失败, " + returnMap.get("errCodeDes")) ;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw  new Exception("用户注册失败") ;
        }
        return null;
    }

    /**
     * 获取 d0account
     * @return 返回 d0account
     */
    public String getD0account()
    {
        return d0account;
    }

    /**
     * 设置 d0account
     * @param 对d0account进行赋值
     */
    public void setD0account(String d0account)
    {
        this.d0account = d0account;
    }

    /**
     * 获取 d0Key
     * @return 返回 d0Key
     */
    public String getD0Key()
    {
        return d0Key;
    }

    /**
     * 设置 d0Key
     * @param 对d0Key进行赋值
     */
    public void setD0Key(String d0Key)
    {
        this.d0Key = d0Key;
    }

    /**
     * 获取 requestUrl
     * @return 返回 requestUrl
     */
    public String getRequestUrl()
    {
        return requestUrl;
    }

    /**
     * 设置 requestUrl
     * @param 对requestUrl进行赋值
     */
    public void setRequestUrl(String requestUrl)
    {
        this.requestUrl = requestUrl;
    }

    /**
     * 获取 t1account
     * @return 返回 t1account
     */
    public String getT1account()
    {
        return t1account;
    }

    /**
     * 设置 t1account
     * @param 对t1account进行赋值
     */
    public void setT1account(String t1account)
    {
        this.t1account = t1account;
    }

    /**
     * 获取 t1Key
     * @return 返回 t1Key
     */
    public String getT1Key()
    {
        return t1Key;
    }

    /**
     * 设置 t1Key
     * @param 对t1Key进行赋值
     */
    public void setT1Key(String t1Key)
    {
        this.t1Key = t1Key;
    }
    
    // private void search()
    // {
    // /** 查找交易路由 **/
    // QrcodeRouteBean route = passiveService.qrcodeRoute(order);
    // logger.info("根据渠道编码[" + route.getChannelCode() + "]获取渠道信息");
    // QrcodeChannelBean channel = channelService.getByChannelCode(route.getChannelCode());
    // }
    
}
