package com.alycloud.modules.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ChangeMerchInfo {
    private Integer id;

    private Integer merchId;

    private String agentno;

    private String fullName;

    private String shortName;

    private String legalName;

    private String idCard;

    private String mobile;

    private String email;

    private String linkMan;

    private String telephone;

    private String areaCode;

    private String address;

    private String imgIdCard1;

    private String imgIdCard2;

    private String imgIdCard3;

    private String oriFullName;

    private String oriShortName;

    private String oriLegalName;

    private String oriIdCard;

    private String oriMobile;

    private String oriEmail;

    private String oriLinkMan;

    private String oriTelephone;

    private String oriAreaCode;

    private String oriAddress;

    private String oriImgIdCard1;

    private String oriImgIdCard2;

    private String oriImgIdCard3;

    private Integer auditStatus;

    private String auditDescr;

    private String auditTime;

    private String auditAgent;

    private String userName;

    private String addTime;
    
    private String valiTime;
    
    private String jsonStr;
}