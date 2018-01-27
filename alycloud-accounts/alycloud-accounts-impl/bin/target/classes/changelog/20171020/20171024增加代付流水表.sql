
USE `posp_bak_201710101717`;

/* Create table in target */
CREATE TABLE `payment_trans`(
	`id` bigint(11) NOT NULL  auto_increment , 
	`biz_type` int(8) NULL  COMMENT '业务类型:0快捷，1POS，2扫码，3网关，4重出款，5商户提现，6代理商提现' , 
	`trans_amount` decimal(10,2) NULL  COMMENT '交易金额（元）' , 
	`trans_fee` decimal(10,2) NULL  COMMENT '手续费（元）' , 
	`refno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '业务类型对应的原交易单号 ' , 
	`traceno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '客户单号（第四方流水号）' , 
	`id_card_no` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '收款人身份证号' , 
	`mobile` varchar(11) COLLATE gbk_chinese_ci NULL  COMMENT '收款人手机号' , 
	`account_name` varchar(50) COLLATE gbk_chinese_ci NULL  COMMENT '收款人银行卡账户名' , 
	`account_no` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '收款人银行卡号' , 
	`bank_type` int(8) NULL  COMMENT '收款人银行类型' , 
	`bank_name` varchar(50) COLLATE gbk_chinese_ci NULL  COMMENT '收款人银行名称（支行）' , 
	`bank_no` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '收款人银行联行号' , 
	`pay_status` int(8) NULL  COMMENT '出款状态：0未出款，1出款中，2出款成功，3出款失败，4出款异常' , 
	`pay_descr` varchar(100) COLLATE gbk_chinese_ci NULL  COMMENT '出款结果描述' , 
	`pay_orderno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '出款流水号（平台单号）' , 
	`pay_time` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '出款日期时间' , 
	`pay_amount` decimal(10,2) NULL  COMMENT '出款金额（元）' , 
	`channel_type` int(8) NULL  COMMENT '渠道类型：0江苏电子，1易联支付，2江苏电子2.0，3御付快捷，4汇享天成，5平安，6御付扫码' , 
	`channel_merchno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '渠道商户号' , 
	`channel_orderno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '渠道单号' , 
	`is_pay_again` int(8) NULL  DEFAULT '0' COMMENT '是否已重出款' , 
	`add_time` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '添加时间' , 
	PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET='gbk';
