/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.alycloud.modules.constrant;

/**
 * 类ErrorCodeConstant.java的实现描述：错误代码常量
 * 
 * @author Sunney 2017年11月30日
 */
public class ErrorCodeConstant {

    public static final String ERROR_UNKNOWN                      = "19999"; // "known error";
    public static final String ERROR_EXCEPTION                    = "19998"; // exception.

    public static final String ERROR_BAD_PARA                     = "10001"; // "PAMA IS NOT NULL";
    public static final String ERROR_NEW_SERIAL_FAIL              = "10002"; // get next serial failed.
    public static final String ERROR_NEW_ACCOUNT_FAIL             = "10003"; // new fund account failed.
    public static final String ERROR_BAD_AFFECTED_ROW_COUNT       = "10004"; // bad affected rows number.
    public static final String ERROR_SQL_EXCEPTION                = "10005"; // SQL exception.
    public static final String ERROR_NOT_FOUND                    = "10006"; // user is not found.
    public static final String ERROR_CONNECT_FAILED               = "10007"; // open connection failed.
    public static final String ERROR_BAD_STATE                    = "10008"; // bad state.
    public static final String ERROR_INSUFFICIENT_AMOUNT          = "10009"; // can not afford, insufficient amount.
    public static final String ERROR_FUND_FAILED                  = "10010"; // fund failed, may be insufficient amount
                                                                             // or bad account state.
    public static final String ERROR_DB_FAILED                    = "10011"; // DB operation failed
    public static final String ERROR_CHANNEL_NOT_FOUND            = "10012"; // Channel not found.
    public static final String ERROR_CHANNEL_CONSTRAINT_NOT_FOUND = "10013"; // ChannelConstraint not found.
    public static final String NEW_ORDER_FAIL = "10014"; // 生成订单失败.
    public static final String NEW_EVIDENCE_FAIL = "10015"; // 生成订单失败.
    public static final String NEW_SEREIAL_FAIL = "10016"; // 生成序列号失败.
    public static final String NEW_PAY_INFO_FAIL = "10017"; // 生成支付单失败.
    public static final String UPDATE_PAY_INFO_FAIL = "10017"; // 更新支付单失败.
    public static final String ERROR_BANK_CONFIG = "10018"; // 银行配置参数错误.
    public static final String ERROR_NOT_SUPORT_CARD = "10019"; //不支持卡类型
    public static final String ERROR_PARAM_FORMAT_ERROR = "10020"; //参数校验异常
    public static final String ERROR_AMOUNT_EXCEEDED  = "10021"; //超出限额
    public static final String ERROR_SEND_SMS  = "10022"; //发送短信异常
}
