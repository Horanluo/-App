/*
 * 类文件名:  PageResultBean.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年11月6日
 * 功能版本:  V001Z0001
 */
package com.alycloud.core.modules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年11月6日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class PageResultBean<T> extends ResultBean<T>
{
    /**
     * 页面大小
     */
    private int pageSize;
 
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    
    public static <T> PageResultBean<T> newInstance() {
        return new PageResultBean<>();
    }
}
