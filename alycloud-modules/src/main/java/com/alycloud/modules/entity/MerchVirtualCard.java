package com.alycloud.modules.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchVirtualCard {
    private Integer id;

    private String cardno;

    private String branchno;

    private String merchno;

    private String validDate;

    private Integer bizType;

    private Integer status;

    private String rateCode;

    private String passwd;

    private BigDecimal availAmount;

    private BigDecimal transitAmount;

    private String accountno;

    private String accountName;

    private String bankno;

    private String bankName;

    private String addTime;

    private String frozenTime;

    private Integer payType;

    private String payKey;

    private Integer channelType;
}