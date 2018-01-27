package com.alycloud.modules.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchInfoVO {

    /**
     * 商户状态
     */
    private String status;
    
    /**
     * 商户等级
     */
    private String gradeType;
    
    /**
     * 查询参数  姓名或手机号
     */
    private String queryParam;
}
