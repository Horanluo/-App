/*
 * 类文件名:  AreaBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月7日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 区域编码
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月7日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaBean
{
    private Integer id;
    private String areaCode;
    private String areaName;
    private Integer areaLevel;
    private String city;
    private String province;
}
