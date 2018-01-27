package com.alycloud.modules.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class MerchInfo {
    private Integer id;

    private String merchno;

    private String merchName;

    private String fullName;

    private Integer merchType;

    private String organNo;

    private String bizLicense;

    private String legalName;

    private String identityCard;

    private String linkMan;

    private String email;

    private String telephone;

    private String mobile;

    private Integer saler;

    private String salerName;

    private String mcc;

    private Integer industryType;

    private String areaCode;

    private String province;

    private String city;

    private String address;

    private String superAgent;

    private Integer status;

    private String openTime;

    private Integer auditStatus;

    private Integer judgStatus;

    private String auditAgentno;

    private Integer rateMode;

    private BigDecimal debitRate;

    private BigDecimal debitFixed;

    private BigDecimal creditRate;

    private BigDecimal creditFixed;

    private BigDecimal stripeCardFixed;

    private BigDecimal chipCardFixed;

    private Integer agentFeeId;

    private Integer settleType;

    private Integer defSettleType;

    private String accountno;

    private Integer accountType;

    private String accountName;

    private String bankno;

    private String bankName;

    private BigDecimal amtLimit;

    private Integer dayLimit;

    private Integer monthLimit;

    private Integer transCtrl;

    private String bizLicenseImg;

    private String accountImg;

    private String identity1Img;

    private String identity2Img;

    private String identity3Img;

    private String card1Img;

    private String card2Img;

    private String credit1Img;

    private String credit2Img;

    private String cashierImg;

    private String placeImg;

    private String headImg;

    private String contractImg;

    private String otherFile;

    private String addTime;

    private String firstAgentno;

    private String branchno;

    private Integer salerId;

    private Integer bizType;

    private BigDecimal tradRate;

    private BigDecimal t0Rate;

    private Integer merchLevel;

    private String customerno;

    private String silenceMerch;

    private BigDecimal t0Fixed;

    private BigDecimal appRate;

    private BigDecimal appFixed;

    private BigDecimal manuRate;

    private BigDecimal manuFixed;

    private BigDecimal wxRate;

    private BigDecimal zfbRate;

    private BigDecimal bdRate;

    private BigDecimal qqRate;

    private BigDecimal jdRate;

    private String merchKey;

    private String onlineUrlNotify;

    private String onlineUrlReturn;

    private BigDecimal appCredit;

    private BigDecimal appT0Rate;

    private BigDecimal gatewayRateT0;

    private Integer headIndex;

    private BigDecimal fastpayRateT0;

    private BigDecimal fastpayRateT1;

    private String hpmercode;

    private Integer syncTypeStatus;

    private Integer type;

    private Integer forceSettleType;

    private BigDecimal hxtcfastpayratet0;

    private String hfbankd0merchno;

    private String hfbankt1merchno;

    private String userId;
    
    private String jsonStr;
    
    private Integer realNameAuthStatus;//实名认证标识字段
    
    private Integer userRank;
    
    private Integer sumTieCard;//绑卡数量
    
    private String referName;
    
    private String userRankName;
    
    private String isDisable;
}