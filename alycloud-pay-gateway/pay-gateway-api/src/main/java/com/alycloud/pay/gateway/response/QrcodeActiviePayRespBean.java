/*
 * 类文件名:  QrcodeActiviePayRespBean.java
 * 著作版权:  深圳市云智恒生科技有限公司 Copyright 2012-2022, E-mail: 283912449@qq.com, All rights reserved
 * 功能描述:  <描述>
 * 类创建人:  曾云龙
 * 创建时间:  2017年7月25日
 * 功能版本:  V001Z0001
 */
package com.alycloud.pay.gateway.response;

import com.alycloud.pay.gateway.models.ResponseBean;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author   曾云龙
 * @version  V001Z0001
 * @date     2017年7月25日
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class QrcodeActiviePayRespBean extends ResponseBean
{
    private String merchno;
    /**
     * 商户流水号
     */
    private String traceno;
    /**
     * 系统参考号
     */
    private String refno;
    /**
     * 支付方式
     */
    private String payType;
    /**
     * 渠道订单号
     */
    private String channelOrderno;
    /**
     * 备注信息
     */
    private String remark;

    public QrcodeActiviePayRespBean() {
        super();
    }

    public QrcodeActiviePayRespBean(String merchno, String traceno, String respCode, String message) {
        super(respCode, message);
        this.merchno = merchno;
        this.traceno = traceno;
    }

    public String getMerchno() {
        return merchno;
    }

    public void setMerchno(String merchno) {
        this.merchno = merchno;
    }

    public String getTraceno() {
        return traceno;
    }

    public void setTraceno(String traceno) {
        this.traceno = traceno;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getChannelOrderno() {
        return channelOrderno;
    }

    public void setChannelOrderno(String channelOrderno) {
        this.channelOrderno = channelOrderno;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "QrcodeActiviePayRespBean [merchno=" + merchno + ", traceno=" + traceno + ", refno=" + refno + ", payType=" + payType + ", channelOrderno=" + channelOrderno + ", remark=" + remark
                + "]";
    } 
}
