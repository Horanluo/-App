/*
 * 类文件名:  RequestBean.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月30日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.modules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求实体类
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月30日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class RequestBean<T>
{
    /**
     * 请求的IP地址
     */
    private String addresIp;
    
    /**
     * 客户端请求的版本
     */
    private String version;
    
    /**
     * 请求的IMEI设备号
     */
    private String deviceId;
    
    /**
     * 设备类型
     */
    private String deviceType;
    
    /**
     * 获取手机经度
     */
    private String longitude;
    
    /**
     * 获取手机纬度
     */
    private String latitude;
    
    /**
     * 平台商户号
     */
    //@NotBlank(message ="商户号不能为空")
    private String merchno;
    
    /**
     * App登录账号
     */
    private String loginName;
    
    /**
     * 请求的对象
     */
    //@NotNull(message ="请求参数不能为空")@JsonManagedReference and @JsonBackReference
    @JsonManagedReference 
    private T data;
}
