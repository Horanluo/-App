
USE `posp_2`;

/* Create table in target */
CREATE TABLE `grade`(
	`id` bigint(15) NOT NULL  auto_increment , 
	`grade_type` int(8) NULL  COMMENT '等级，从0开始，值越大等级超高' , 
	`grade_name` varchar(50) COLLATE gbk_chinese_ci NULL  COMMENT '等级名称' , 
	`quantity` int(8) NULL  COMMENT '升级需要推荐的人数' , 
	`amount` decimal(10,2) NULL  COMMENT '升级需要支付的费用，单位：元' , 
	PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET='gbk';


/* Create table in target */
CREATE TABLE `grade_fee`(
	`id` bigint(15) NOT NULL  auto_increment , 
	`grade_type` int(8) NULL  COMMENT '参考grade表grade_type字段' , 
	`pay_type` int(8) NULL  COMMENT '支付类型（多选）：0银联，1支付宝，2微信，3百度，4QQ，5京东，6快捷' , 
	`scan_type` int(8) NULL  COMMENT '支付方式（多选）：0主扫，1被扫，2公众号，3WAP，4APP，5快捷' , 
	`settle_type` int(8) NULL  COMMENT '结算类型（多选）：0-T0，1-T1' , 
	`rate` decimal(10,4) NULL  COMMENT '费率' , 
	`fee` decimal(10,2) NULL  COMMENT '手续费，单位：元' , 
	PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET='gbk';


/* Create table in target */
CREATE TABLE `grade_order`(
	`id` bigint(15) NOT NULL  auto_increment , 
	`merch_id` bigint(15) NULL  COMMENT '升级商户id' , 
	`amount` decimal(10,2) NULL  COMMENT '支付费用，单位：元' , 
	`refno` varchar(30) COLLATE gbk_chinese_ci NULL  COMMENT '支付单号（同扫码支付订单号）' , 
	`status` int(8) NULL  COMMENT '支付状态：0未支付，1支付成功，2支付失败' , 
	`add_time` varchar(20) COLLATE gbk_chinese_ci NULL  COMMENT '添加时间：yyyy-MM-dd HH:mm:ss' , 
	PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET='gbk';


/* Alter table in target */
ALTER TABLE `qrcode_channel_merch_fee` 
	CHANGE `id` `id` bigint(15)   NOT NULL auto_increment first, 
	ADD COLUMN `channel_merch_id` bigint(15)   NOT NULL COMMENT '渠道商户id' after `id`, 
	CHANGE `pay_type` `pay_type` int(8)   NOT NULL COMMENT '支付类型(多选)：' after `channel_merch_id`, 
	CHANGE `scan_type` `scan_type` int(8)   NOT NULL COMMENT '支付方式(多选)：' after `pay_type`, 
	CHANGE `settle_type` `settle_type` int(8)   NOT NULL COMMENT '结算类型(多选)：' after `scan_type`, 
	CHANGE `rate` `rate` decimal(10,4)   NOT NULL COMMENT '费率' after `settle_type`, 
	ADD COLUMN `fee` decimal(10,2)   NOT NULL COMMENT '手续费（元）' after `rate`, 
	ADD COLUMN `status` int(8)   NOT NULL COMMENT '状态：0待审核，1审核中，2审核通过，3审核失败' after `fee`, 
	DROP COLUMN `merch_id`, COMMENT='';