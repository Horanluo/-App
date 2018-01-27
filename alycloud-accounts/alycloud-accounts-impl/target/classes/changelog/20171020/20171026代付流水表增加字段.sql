
USE `posp_2`;

/* Alter table in target */
ALTER TABLE `payment_trans` 
	ADD COLUMN `settle_type` int(8)   NULL COMMENT '结算方式：0－T0,1-T1' after `bank_no`, 
	CHANGE `liquidate_type` `liquidate_type` int(8)   NULL COMMENT '清算方式：0线下人工，1线上渠道' after `settle_type`, 
	CHANGE `pay_type` `pay_type` int(8)   NULL COMMENT '出款方式：0人工操作，1系统自动' after `liquidate_type`, 
	CHANGE `pay_status` `pay_status` int(8)   NULL COMMENT '出款状态：0未出款，1出款中，2出款成功，3出款失败，4出款异常' after `pay_type`, 
	CHANGE `pay_descr` `pay_descr` varchar(100)  COLLATE gbk_chinese_ci NULL COMMENT '出款结果描述' after `pay_status`, 
	CHANGE `pay_orderno` `pay_orderno` varchar(30)  COLLATE gbk_chinese_ci NULL COMMENT '出款流水号（平台单号）' after `pay_descr`, 
	CHANGE `pay_time` `pay_time` varchar(20)  COLLATE gbk_chinese_ci NULL COMMENT '出款日期时间' after `pay_orderno`, 
	CHANGE `pay_amount` `pay_amount` decimal(10,2)   NULL COMMENT '出款金额（元）' after `pay_time`, 
	CHANGE `channel_type` `channel_type` int(8)   NULL COMMENT '渠道类型：0江苏电子，1易联支付，2江苏电子2.0，3御付快捷，4汇享天成，5平安，6御付扫码' after `pay_amount`, 
	CHANGE `channel_merchno` `channel_merchno` varchar(30)  COLLATE gbk_chinese_ci NULL COMMENT '渠道商户号' after `channel_type`, 
	CHANGE `channel_orderno` `channel_orderno` varchar(30)  COLLATE gbk_chinese_ci NULL COMMENT '渠道单号' after `channel_merchno`, 
	CHANGE `is_pay_again` `is_pay_again` int(8)   NULL DEFAULT '0' COMMENT '是否已重出款' after `channel_orderno`, 
	CHANGE `add_time` `add_time` varchar(20)  COLLATE gbk_chinese_ci NULL COMMENT '添加时间' after `is_pay_again`, COMMENT='';