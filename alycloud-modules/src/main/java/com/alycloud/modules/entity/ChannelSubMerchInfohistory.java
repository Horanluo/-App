package com.alycloud.modules.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(value=Include.NON_NULL)
public class ChannelSubMerchInfohistory {
    private Integer id;

    private String branchno;

    private String agentno;

    private String merchno;

    private Date createdate;

    private String channelCode;

    private String channelName;

    private String channelSubmerchno;

    private String payType;

    private String aduitStatus;

    private BigDecimal payfeed0;

    private BigDecimal d0payRate;

    private BigDecimal t1payRate;

    private BigDecimal payfeet1;

    private String payMethod;

    private String remark;
}