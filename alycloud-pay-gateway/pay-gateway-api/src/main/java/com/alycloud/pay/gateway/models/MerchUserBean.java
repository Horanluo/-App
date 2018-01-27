/*
 * 类文件名:  MerchUserBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月22日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月22日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchUserBean implements Serializable
{

    private static final long serialVersionUID = -5822942692587138472L;
    
    private Integer id;
    /**
     * 用户角色编号
     */
    private Integer userRoler;
    /**
     * 分公司编码
     */
    private String branchno;
    /**
     * 所属商户
     */
    private Integer merchId;
    /**
     * 登陆的用户名
     */
    private String loginName;
    /**
     * 真实姓名
     */
    private String trueName;
    /**
     * 密码
     */
    private String password;
    /**
     * 固定电话
     */
    private String telephone;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 地址
     */
    private String address;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 上次登陆时间
     */
    private String lastLogin;
    /**
     * 上次错误登陆日期
     */
    private String loginErrorDate;
    /**
     * 错误登陆次数
     */
    private Integer errorCount;
    
    /**
     * 用户状态  1-正常 2-冻结 3-锁定
     */
    public static final int STATUS_NORMAL = 1;
    public static final int STATUS_LOCK = 2;
    public static final int STATUS_CANCEL = 3;
    /**
     * 商户编号
     */
    private String merchno;
    /**
     * 商户名称
     */
    private String merchName;
    private String openId;
    
}
