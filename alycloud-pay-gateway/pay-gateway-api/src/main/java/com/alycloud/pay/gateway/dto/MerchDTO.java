/*
 * 类文件名:  MerchDTO.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月23日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.alycloud.pay.gateway.models.qrcode.QrcodeMerchFeeBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户注册信息
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月23日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchDTO implements Serializable
{

    @JsonIgnore
    private String merchno;
    
//    @JsonIgnore
//    private String branchno = "0010440304";
    
    @NotBlank(message = "agentno代理商编号不能为空")
    private String agentno;
    
    @NotBlank(message = "merchName商户名不能为空")
    private String merchName;
    
    @NotBlank(message = "fullName商户名全称不能为空")
    private String fullName;
    
    /**
     * 法人
     */
    @NotBlank(message = "legalName法人不能为空")
    private String legalName;
    
    @NotBlank(message = "identityCard身份证号不能为空")
    private String identityCard;
    
    @NotBlank(message = "mobile手机号不能为空")
    private String mobile;
    
//    @JsonIgnore
//    private String mcc = "5411";
    
    @NotBlank(message = "areaCode区域码不能为空")
    private String areaCode;
    
    @NotBlank(message = "address地址不能为空")
    private String address;
    
    //费率模式
//    @JsonIgnore
//    private Integer rateMode = 2;
    
//    private BigDecimal t1Rate;
//    
//    private BigDecimal t1Fixed;
    
    @NotBlank(message = "accountno结算账户不能为空")
    private String accountno;
    
    @NotBlank(message = "accountName收款人不能为空")
    private String accountName;
    
    /**
     * 帐户类型 1-对私 2-对公
     */
    private int accountType;
    
    @NotBlank(message = "bankno银行联号不能为空")
    private String bankno;
    
    @NotBlank(message = "bankName银行名称不能为空")
    private String bankName;
    
//    @JsonIgnore
//    private String bizType = "119";
    
//    private BigDecimal t0Rate;
    
    /**
     * 提现手续费，单位 元，保留两位小数点
     */
    private double payFee;
    
    /**
     * 电子邮件
     */
    private String email;
    
    /**
     * 省份
     */
    @NotBlank(message = "province省份不能为空")
    private String province;
    
    /**
     * 城市
     */
    @NotBlank(message = "city城市不能为空")
    private String city;
    
    /**
     * 区县
     */
    private String areaName;
    
    /**
     * 银行类型
     */
    @NotBlank(message = "银行类型不能为空")
    private String bankType;
    
    /**
     * 营业执照类型
     * 营业执照类型：NATIONAL_LEGAL：营业执照；NATIONAL_LEGAL_MERGE:多证合一；INST_RGST_CTF：事业单位法人证书
     */
    private String businessLicenseType;
    
    /**
     * 营业执照号
     */
    private String businessLicense;
    
    /**
     * 受理商户负责人信息
     * 负责人信息：LEGAL_PERSON：法人；CONTROLLER：实际控制人；AGENT：代理人；OTHER：其他
     */
    private String legalPersonType;
    
    @Size(min=1, message = "payTypes支付类型不能为空")
    private List<QrcodeMerchFeeBean> payTypes;
    
    /**
     * 微信经营类目
     */
    private String categoryWx;
    
    /**
     * 支付宝经营类目
     */
    private String categoryZfb;
    
    /**
     * 快捷支付 D0费率
     */
    @DecimalMin(value="0.0038", message="快捷支付 D0费率不能少于0.0038")
    private BigDecimal quickPayD0Rate;
    
    /**
     * 快捷支付 T1费率
     */
    @DecimalMin(value="0.0035", message="快捷支付T1费率不能少于0.0035")
    private BigDecimal quickPayT1Rate;
    
    /**
     * 接口版本
     */
    private String version;
    
    /**
     * 签名
     */
//    @NotBlank(message = "sign签名不能为空")
    private String sign;
}
