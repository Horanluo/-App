package com.alycloud.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchJudg {
    private Integer id;

    private String merchId;

    private Integer auditStatus;

    private String reason;

    private String loginName;

    private String trueName;

    private String auditTime;
}