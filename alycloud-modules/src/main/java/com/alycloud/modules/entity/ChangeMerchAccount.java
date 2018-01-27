package com.alycloud.modules.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ChangeMerchAccount {
    private Integer id;

    private Long merchId;

    private String merchName;

    private String agentno;

    private Integer oriAccountType;

    private String oriAccountno;

    private String oriAccountName;

    private String oriBankno;

    private String oriBankName;

    private String oriImgCard1;

    private String oriImgCard2;

    private Integer accountType;

    private String accountno;

    private String accountName;

    private String bankno;

    private String bankName;

    private String imgCard1;

    private String imgCard2;

    private String filePath;

    private String auditAgent;

    private Integer auditStatus;

    private String auditDescr;

    private String auditTime;

    private String userName;

    private String addTime;
}