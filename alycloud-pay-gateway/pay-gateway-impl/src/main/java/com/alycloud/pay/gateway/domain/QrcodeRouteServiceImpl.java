/*
 * 类文件名:  QrcodeRouteServiceImpl.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月27日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alycloud.pay.gateway.api.IQrcodeRouteService;
import com.alycloud.pay.gateway.mapper.QrcodeRouteMapper;
import com.alycloud.pay.gateway.models.qrcode.QrcodeRouteBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月27日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service
public class QrcodeRouteServiceImpl implements IQrcodeRouteService
{
    /**
     * 设置时间的格式
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    @Autowired
    private QrcodeRouteMapper qrcodeRouteMapper;

    /**
     * 获取某个商户特定的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @param merchno
     *            商户号
     * @return
     * @throws Exception
     */
    @Override
    public List<QrcodeRouteBean> routeByPMerch(Integer payType, Integer scanType, Integer settleType, String merchno)
    {
        Map<String, Object> param = buildParam(null, payType, scanType, settleType, null);
        param.put("merchno", merchno);
        return qrcodeRouteMapper.routeByPMerch(param);
    }
    
    /**
     * 构造路由的基本参数
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @return
     */
    private Map<String, Object> buildParam(Integer interType, Integer payType, Integer scanType, Integer settleType, BigDecimal amount) {
        Date date = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("transTime", sdf.format(date));
        param.put("payType", payType);
        param.put("scanType", scanType);
        param.put("settleType", settleType);
        if(interType != null){
            param.put("interType", interType);
        }
        if(amount != null){
            param.put("amount", amount);
        }
        return param;
    }

    /**
     * 获取某个商户特定的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @param merchno
     *            商户号
     * @return
     * @throws Exception
     */
    @Override
    public List<QrcodeRouteBean> routeByMerch(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String merchno)
    {
        Map<String, Object> param = buildParam(interType, payType, scanType, settleType, amount);
        param.put("merchno", merchno);
        return qrcodeRouteMapper.routeByMerch(param);
    }

    /**
     * 获取某个代理特定的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @param branchno
     *            机构号
     * @param agentno
     *            代理商号
     * @return
     * @throws Exception
     */
    @Override
    public List<QrcodeRouteBean> routeByAgent(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount, String branchno, String agentno)
    {
        Map<String, Object> param = buildParam(interType, payType, scanType, settleType, amount);
        param.put("branchno", branchno);
        param.put("agentno", agentno);
        return qrcodeRouteMapper.routeByAgent(param);
    }

    /**
     * 获取某个机构特定的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @param branchno
     *            机构号
     * @return
     * @throws Exception
     */
    @Override
    public List<QrcodeRouteBean> routeByBranch(Integer interType, Integer payType, Integer scanType,
        Integer settleType, BigDecimal amount, String branchno)
    {
        Map<String, Object> param = buildParam(interType, payType, scanType, settleType, amount);
        param.put("branchno", branchno);
        return qrcodeRouteMapper.routeByBranch(param);
    }

    /**
     * 获取其他的可用的渠道信息
     * 
     * @param interType
     *            接口类型(1-APP支付 2-传统POS支付 4-接口接入)
     * @param scanType
     *            扫码类型(1-主扫 2-被扫 4-公众号 8-WAP)
     * @param payType
     *            支付方式(1-支付宝 2-微信)
     * @param settleType
     *            结算类型(1-T+0结算 2-T+1结算)
     * @param amount
     *            交易金额
     * @return
     * @throws Exception
     */
    @Override
    public List<QrcodeRouteBean> routeByOther(Integer interType, Integer payType, Integer scanType, Integer settleType,
        BigDecimal amount)
    {
        Map<String, Object> param = buildParam(interType, payType, scanType, settleType, amount);
        return qrcodeRouteMapper.routeByOther(param);
    }
    
}
