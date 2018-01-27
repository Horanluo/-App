/*
 * 类文件名:  HxtcBillsResult.java
 * 著作版权:  深圳市易商云电子商务有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年9月29日
 * 功能版本:  V001Z0001
 */
package com.alycloud.modules.channel.hfbank;

import java.io.Serializable;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年9月29日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class HxtcBillsResult implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2207214512007737939L;
    
    private List<String> bills;

    /**
     * 获取 bills
     * @return 返回 bills
     */
    public List<String> getBills()
    {
        return bills;
    }

    /**
     * 设置 bills
     * @param 对bills进行赋值
     */
    public void setBills(List<String> bills)
    {
        this.bills = bills;
    }
}
