package com.alycloud.modules.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商户操作员
 * @author Moyq5
 * @date 2017年5月23日
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class MerchUser implements Serializable {
	
	private Integer id;
	/**
	 * 角色Id
	 */
	private Integer userRoler;
	/**
	 * 机构编码
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
	 * 固定号码
	 */
	private String telephone;
	/**
	 * 绑定的微信公众号OPENID
	 */
	private String openId;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 当天登陆错误次数
	 */
	private Integer errorCount;
	
	private Integer headIndex;
	/**
	 * 上次登陆时间
	 */
	private String lastLogin;
	/**
	 * 登陆错误日期
	 */
	private String loginErrorDate;
	/**
	 * 用户状态 1-正常 2-冻结 3-锁定
	 */
	private Integer status;
	
	private String email;
	
	private String referName;
	
	/**
	 * 账户等级
	 */
	private Integer userRank;
	
	private String userRankName;
	
	private String merchMobile;
	
	private String agentno;
}
