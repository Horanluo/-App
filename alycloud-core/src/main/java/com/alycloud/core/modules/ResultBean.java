/*
 * 类文件名:  ResultBean.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月30日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.modules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果bean
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
public class ResultBean<T>
{
    /**
     * 返回状态,1: 成功,0: 失败,99:无数据
     */
    private String respCode;
    
    /**
     * 返回的数据
     */
    private String message;
    
    /**
     * 错误编码
     */
    private String errorcode;  
    
    /**
     * 返回数据签名
     */
    private String sign;
    
    /**
     * 返回的结果
     */
    private T data;
    
    public static <T> ResultBean<T> newInstance() {
        return new ResultBean<>();
    }
}
