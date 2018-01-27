package com.alycloud.modules.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchVirtualTrans {
    private Integer id;

    private String transDate;

    private String transTime;

    private String branchno;

    private String branchName;

    private String agentno;

    private String agentName;

    private String merchno;

    private String merchName;

    private String cardno;

    private Integer transType;

    private BigDecimal amount;

    private String traceno;

    private Integer status;

    private String loginName;

    private BigDecimal amountBefore;

    private BigDecimal amountAfter;

    private BigDecimal transAmount;

    private BigDecimal transFee;

    private String typeCode;

    private String transRefno;

    private String addTime;

    private String remark;

    private Integer payStatus;

    private String payMsg;

    private Integer payType;

    private String batchno;

    private String address;

    private String accountno;

    private String accountName;

    private String bankno;

    private String bankName;

    private String processTime;

    private Integer dfType;

    private String mobile;

    private String bankType;

    private String channelMerchno;

    private Integer paymentType;

    private BigDecimal fee;

    private BigDecimal withdrawFee;

    private BigDecimal rate;
    
    private BigDecimal totalProxyAmount;
    
    private String totalRecord;
    
}