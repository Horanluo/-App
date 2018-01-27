/*
 * 类文件名:  SystemErrorLogBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月1日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.log;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 异常日志对象
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月1日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemErrorLogBean implements Serializable
{
    private static final long serialVersionUID = -6584572339686763567L;

    /**
     * 关键字，自增长
     */
    private int id;
    
    /**
     * 默认值:  0.0.0.0 操作IP地址
     */
    private String ipAddress;
    
    /**
     * 创建时间
     */
    private Date createDate;
    
    /**
     * 创建人，手机号
     */
    private String loginName;
    
    /**
     * 商户号
     */
    private String merchno;
    
    /**
     * 代理商号
     */
    private String agentno;
    
    /**
     * 机构号
     */
    private String branchno;
    
    /**
     * 设备唯一识别号
     */
    private String deviceId;
    
    /**
     * 设备类型
     */
    private String deviceType;
    
    /**
     * 输入参数
     */
    private String inputParams;
    
    /**
     * 输出参数
     */
    private String outputParams;
    
    /**
     * 错误内容
     */
    private String errorMessage;
    
    /**
     * 所属模块
     */
    private String moduleName;
    
    /**
     * 服务地址
     */
    private String servicerl;
}
