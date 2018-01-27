CREATE TABLE `merch_user_bank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`user_id` int(11) NOT NULL COMMENT '登录账户',
  `card_name` varchar(50) DEFAULT NULL COMMENT '持卡人姓名',
  `head_bank_No` varchar(12) DEFAULT NULL COMMENT '总行联行号',
  `head_bank_name` varchar(50) DEFAULT NULL COMMENT '总行名称',
  `branch_bank_No` int(12) DEFAULT NULL COMMENT '分行联行号',
  `card_no` varchar(20) DEFAULT NULL COMMENT '银行卡号',
	`card_type` varchar(2) DEFAULT NULL COMMENT '卡类型',
  `exp_date` varchar(10) DEFAULT NULL COMMENT '信用卡有效期',
  `phone` varchar(11) DEFAULT NULL COMMENT '预留手机号',
	PRIMARY KEY (`id`),
	KEY `user_bank_index_1` (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=4061 DEFAULT CHARSET=gbk;

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

CREATE TABLE `channel_submerch_info_history` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `branchno` varchar(50) NOT NULL DEFAULT '' COMMENT '机构号',
  `agentno` varchar(50) DEFAULT NULL COMMENT '代理商号',
  `merchno` varchar(50) DEFAULT NULL COMMENT '商户号',
  `createdate` datetime DEFAULT NULL COMMENT '渠道商户号申请时间',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `channel_name` varchar(50) DEFAULT NULL COMMENT '渠道名称',
  `channel_submerchno` varchar(50) DEFAULT NULL COMMENT '渠道分配的上游子商户号',
  `pay_type` varchar(50) DEFAULT 'QUICKPAY' COMMENT '支付类型,QUICKPAY： 快捷支付,WEIXIN,ALIPAY,京东支付: JD,QQ 钱包: QQWALLET，UNIONPAY',
  `aduit_status` varchar(1) DEFAULT '0' COMMENT '0: 待审核,1：审核通过,2: 审核拒绝',
  `payFeeD0` decimal(3,2) DEFAULT '0.00' COMMENT 'D0提现手续费',
  `d0pay_rate` decimal(6,4) DEFAULT NULL COMMENT 'D0支付费率',
  `t1pay_rate` decimal(6,4) DEFAULT NULL COMMENT 'T1支付费率',
  `remark` text COMMENT '备注',
  `payFeeT1` decimal(3,2) DEFAULT NULL COMMENT 'T1提现费',
  `pay_method` varchar(50) DEFAULT NULL COMMENT '支付方式，wxPub、wxPubQR、wxApp、wxMicro、alipayQR、alipayApp、alipayMicro、jdMicro、jdQR、unionpayQR、qqwalletQR,QUICKPAY',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/* Create table in target */
CREATE TABLE `grade`(
	`id` bigint(15) NOT NULL  auto_increment , 
	`grade_type` int(8) NULL  COMMENT '等级，从0开始，值越大等级超高' , 
	`grade_name` varchar(50) COLLATE gbk_chinese_ci NULL  COMMENT '等级名称' , 
	`quantity` int(8) NULL  COMMENT '升级需要推荐的人数' , 
	`amount` decimal(10,2) NULL  COMMENT '升级需要支付的费用，单位：元' , 
	PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET='gbk';

alter table merch_info add COLUMN (user_id int(11) DEFAULT NULL COMMENT '用户id');
alter table merch_user add COLUMN (refer_name varchar(30) DEFAULT NULL COMMENT '推荐人');
alter table merch_info MODIFY COLUMN user_id varchar(11);
alter table merch_user_bank modify COLUMN head_bank_No int(12);
alter table merch_user_bank modify COLUMN user_id varchar(11);
ALTER TABLE `channel_qrcode` 
	ADD COLUMN `t0_status` int(8)   NULL DEFAULT '0' COMMENT 'T0状态:0不支持,1需要提现,2不需要提现' after `merch_rsa_key`, 
	ADD COLUMN `t1_status` int(8)   NULL DEFAULT '0' COMMENT 'T1状态:0不支付,1需要提现,2不需要提现' after `t0_status`, 
	ADD COLUMN `amount_min` decimal(10,2)   NULL DEFAULT '5.00' COMMENT '单笔最小金额(元)' after `t1_status`, 
	ADD COLUMN `amount_max` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '单笔最大金额(元)' after `amount_min`, 
	ADD COLUMN `amount_day` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '当日最高交易额(元)' after `amount_max`, COMMENT='';

/* Alter table in target */
ALTER TABLE `channel_submerch_info` 
	CHANGE `pay_method` `pay_method` varchar(50)  COLLATE utf8_general_ci NULL COMMENT '支付方式，wxPub、wxPubQR、wxApp、wxMicro、alipayQR、alipayApp、alipayMicro、jdMicro、jdQR、unionpayQR、qqwalletQR,QUICKPAY' after `payFeeT1`, COMMENT='';

/* Alter table in target */
ALTER TABLE `agent_virtual_trans` 
	CHANGE `pay_type` `pay_type` int(11)   NULL COMMENT '提现方式(0－公众号，1-手机APP 2-接口 3-后台)' after `pay_msg`, COMMENT='';

/* Alter table in target */
ALTER TABLE `payment_trans` 
	ADD COLUMN `liquidate_type` int(8)   NULL COMMENT '清算方式：0线下人工，1线上渠道' after `bank_no`, 
	ADD COLUMN `pay_type` int(8)   NULL COMMENT '出款方式：0人工操作，1系统自动' after `liquidate_type`;

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

alter table merch_user add COLUMN (user_rank int(11) DEFAULT null);

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



alter table merch_user_bank MODIFY COLUMN branch_bank_No varchar(12);
alter table merch_user_bank MODIFY COLUMN head_bank_No varchar(15);
alter table merch_info modify COLUMN merchno varchar(15);
alter table change_merch_info add COLUMN vali_time varchar(50) DEFAULT NULL COMMENT '身份证有效时间';
alter table merch_user_bank add COLUMN cvv2 varchar(3) DEFAULT NULL COMMENT '信用卡安全码';
alter table merch_user_bank modify COLUMN head_bank_No varchar(12);
alter table merch_user_bank modify COLUMN branch_bank_No varchar(12);
alter table merch_user_bank add COLUMN bank_name_code varchar(20) DEFAULT null COMMENT '银行名称代码';

/* Alter table in target */
ALTER TABLE `agent_trans` 
	CHANGE `type_memo` `type_memo` varchar(100)  COLLATE gbk_chinese_ci NULL COMMENT '分润类型描述' after `agent_name`, 
	ADD COLUMN `type` int(8)   NULL DEFAULT '0' COMMENT '分润类型：0普通分润，1升级分润' after `type_memo`, 
	CHANGE `refno` `refno` varchar(50)  COLLATE gbk_chinese_ci NULL after `type`, 
	CHANGE `amount` `amount` decimal(12,2)   NULL COMMENT '交易金额' after `refno`, 
	CHANGE `divide` `divide` int(11)   NULL DEFAULT '0' COMMENT '与平台的分成比例(范围:0-100)' after `amount`, 
	CHANGE `total_fee` `total_fee` decimal(12,2)   NULL COMMENT '总手续费' after `divide`, 
	CHANGE `channel_fee` `channel_fee` decimal(12,2)   NULL COMMENT '渠道手续费' after `total_fee`, 
	CHANGE `cost_fee` `cost_fee` decimal(12,2)   NULL COMMENT '渠道手续费' after `channel_fee`, 
	CHANGE `total_agent_fee` `total_agent_fee` decimal(12,6)   NULL COMMENT '代理商总利润=(总手续费-代理成本)*当前的分成比例' after `cost_fee`, 
	CHANGE `agent_fee` `agent_fee` decimal(12,6)   NULL COMMENT '代理商纯利润=当前代理商总利润-下级代理商总利润' after `total_agent_fee`, 
	CHANGE `trans_status` `trans_status` int(11)   NULL DEFAULT '1' COMMENT '状态(1-成功 2-强制成功 3-强制失败)' after `agent_fee`, 
	CHANGE `liquid_status` `liquid_status` int(11)   NULL DEFAULT '1' COMMENT '商户清算状态(1-未清算 2-中清算 3-已清算)' after `trans_status`, 
	CHANGE `liquid_date` `liquid_date` varchar(10)  COLLATE gbk_chinese_ci NULL COMMENT '商户清算日期' after `liquid_status`, 
	CHANGE `pay_fee` `pay_fee` decimal(12,2)   NULL COMMENT 'ÌáÏÖÊÖÐø·Ñ' after `liquid_date`, 
	CHANGE `branchno` `branchno` varchar(10)  COLLATE gbk_chinese_ci NULL COMMENT '机构号' after `pay_fee`, 
	CHANGE `branch_name` `branch_name` varchar(50)  COLLATE gbk_chinese_ci NULL COMMENT '机构名称' after `branchno`, 
	CHANGE `settle_date` `settle_date` varchar(10)  COLLATE gbk_chinese_ci NULL COMMENT '清算日期' after `branch_name`, 
	CHANGE `agent_level` `agent_level` int(11)   NULL COMMENT '代理商级别' after `settle_date`, 
	CHANGE `t0_agent_fee` `t0_agent_fee` decimal(12,2)   NULL DEFAULT '0.00' COMMENT '代理商T+0分润' after `agent_level`, 
	ADD COLUMN `trans_type` int(11)   NULL DEFAULT '1' COMMENT '交易类型：(0-快捷 1-银行卡 2-二维码)' after `t0_agent_fee`, 
	CHANGE `divide_type` `divide_type` int(1)   NULL DEFAULT '0' COMMENT '分成类型：0手续费，1交易金额，2结算金额' after `trans_type`, COMMENT='';
/* Alter table in target */
ALTER TABLE `channel_qrcode` ADD COLUMN  `t0_start_time` varchar(10)  COLLATE gbk_chinese_ci NULL DEFAULT '00:00:00' COMMENT 'T0起始时间点';
ALTER TABLE `channel_qrcode` ADD COLUMN  `t0_end_time` varchar(10)  COLLATE gbk_chinese_ci NULL DEFAULT '23:59:59' COMMENT 'T0结束时间点';

ALTER TABLE `channel_qrcode` 
	ADD COLUMN `channel_alias` varchar(50)  COLLATE gbk_chinese_ci NULL COMMENT '渠道别名（可向用户展示的名称）' after `channel_name`, 
	CHANGE `channel_address` `channel_address` varchar(200)  COLLATE gbk_chinese_ci NULL after `channel_alias`, 
	CHANGE `return_address` `return_address` varchar(200)  COLLATE gbk_chinese_ci NULL after `channel_address`, 
	CHANGE `status` `status` int(11)   NULL DEFAULT '2' COMMENT '状态(1-启用 2-禁用)' after `return_address`, 
	CHANGE `cal_type` `cal_type` int(11)   NULL DEFAULT '1' COMMENT '计费方式(1-四舍五入 1-进一法 2-去尾法)' after `status`, 
	CHANGE `key_rsa` `key_rsa` varchar(2000)  COLLATE gbk_chinese_ci NULL COMMENT 'RSA公钥' after `cal_type`, 
	CHANGE `key_md5` `key_md5` varchar(2000)  COLLATE gbk_chinese_ci NULL COMMENT 'MD5密钥' after `key_rsa`, 
	CHANGE `agentno` `agentno` varchar(30)  COLLATE gbk_chinese_ci NULL COMMENT '代理号' after `key_md5`, 
	CHANGE `notify_address` `notify_address` varchar(300)  COLLATE gbk_chinese_ci NULL after `agentno`, 
	CHANGE `pay_address` `pay_address` varchar(200)  COLLATE gbk_chinese_ci NULL COMMENT '代付地址' after `notify_address`, 
	CHANGE `remark` `remark` varchar(2000)  COLLATE gbk_chinese_ci NULL COMMENT '备注信息' after `pay_address`, 
	CHANGE `pay_notify_url` `pay_notify_url` varchar(500)  COLLATE gbk_chinese_ci NULL COMMENT '代付通知地址' after `remark`, 
	CHANGE `merch_rsa_key` `merch_rsa_key` varchar(3000)  COLLATE gbk_chinese_ci NULL COMMENT '商户RSA私钥' after `pay_notify_url`, 
	CHANGE `t0_status` `t0_status` int(8)   NULL DEFAULT '0' COMMENT 'T0状态:0不支持,1需要提现,2不需要提现' after `merch_rsa_key`, 
	CHANGE `t1_status` `t1_status` int(8)   NULL DEFAULT '0' COMMENT 'T1状态:0不支付,1需要提现,2不需要提现' after `t0_status`, 
	CHANGE `amount_min` `amount_min` decimal(10,2)   NULL DEFAULT '5.00' COMMENT '单笔最小金额(元)' after `t1_status`, 
	CHANGE `amount_max` `amount_max` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '单笔最大金额(元)' after `amount_min`, 
	CHANGE `amount_day` `amount_day` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '当日最高交易额(元)' after `amount_max`, 
	CHANGE `t0_start_time` `t0_start_time` varchar(10)  COLLATE gbk_chinese_ci NULL DEFAULT '00:00:00' COMMENT 'T0起始时间点' after `amount_day`, 
	CHANGE `t0_end_time` `t0_end_time` varchar(10)  COLLATE gbk_chinese_ci NULL DEFAULT '23:59:59' COMMENT 'T0结束时间点' after `t0_start_time`, COMMENT='';
/* Alter table in target */
ALTER TABLE `grade` 
	CHANGE `grade_type` `grade_type` int(8)   NULL COMMENT '等级，从1开始，值越大等级超高' after `id`, 
	ADD COLUMN `percent` int(8)   NULL DEFAULT '0' COMMENT '升级费分成百分比' after `amount`, 
	ADD COLUMN `remark` varchar(300)  COLLATE gbk_chinese_ci NULL COMMENT '等级说明' after `percent`, COMMENT='';
/* Alter table in target */
ALTER TABLE `grade_fee` 
	ADD COLUMN `channel_type` int(8)   NULL DEFAULT '0' COMMENT '渠道类型（多选）' after `grade_type`, 
	CHANGE `pay_type` `pay_type` int(8)   NULL COMMENT '支付类型（多选）：0银联，1支付宝，2微信，3百度，4QQ，5京东，6快捷' after `channel_type`, 
	CHANGE `scan_type` `scan_type` int(8)   NULL COMMENT '支付方式（多选）：0主扫，1被扫，2公众号，3WAP，4APP，5快捷' after `pay_type`, 
	CHANGE `settle_type` `settle_type` int(8)   NULL COMMENT '结算类型（多选）：0-T0，1-T1' after `scan_type`, 
	CHANGE `rate` `rate` decimal(10,4)   NULL COMMENT '费率' after `settle_type`, 
	CHANGE `fee` `fee` decimal(10,2)   NULL COMMENT '提现手续费，单位：元' after `rate`, 
	ADD COLUMN `cap` decimal(10,2)   NULL DEFAULT '0.00' COMMENT '封顶手续费，单位：元，0表示不封顶' after `fee`, COMMENT='';
/* Alter table in target */
ALTER TABLE `grade_order` 
	CHANGE `amount` `amount` decimal(10,2)   NULL COMMENT '升级费用，单位：元' after `merch_id`, 
	ADD COLUMN `grade_type` int(8)   NULL DEFAULT '1' COMMENT '升级等级' after `amount`, 
	CHANGE `refno` `refno` varchar(30)  COLLATE gbk_chinese_ci NULL COMMENT '支付单号（同扫码支付订单号）' after `grade_type`, 
	CHANGE `status` `status` int(8)   NULL COMMENT '支付状态：0未支付，1支付成功，2支付失败' after `refno`, 
	CHANGE `add_time` `add_time` varchar(20)  COLLATE gbk_chinese_ci NULL COMMENT '添加时间：yyyy-MM-dd HH:mm:ss' after `status`, COMMENT='';
/* Alter table in target */
ALTER TABLE `qrcode_agent_fee` 
	CHANGE `rate` `rate` decimal(12,4)   NULL COMMENT '费率' after `settle_type`, COMMENT='';
/* Alter table in target */
ALTER TABLE `qrcode_merch_fee` 
	ADD COLUMN `fee` decimal(10,2)   NULL DEFAULT '0.00' COMMENT '单笔提现手续费（元）' after `rate`, COMMENT='';

alter table merch_info add COLUMN json_str varchar(1000) DEFAULT null COMMENT '额外存储信息';
alter table change_merch_info add COLUMN json_str varchar(1000) DEFAULT null COMMENT '额外存储信息';
alter table merch_info add COLUMN real_name_auth_status int(1) DEFAULT null COMMENT '0表示没有进行任何认证，1表示只进行了身份证认证，
2表示进行了身份证和银行卡四要素认证，3表示身份证认证、银行卡四要素认证、活体识别都实名认证';

ALTER TABLE channel_submerch_info ADD COLUMN yufu_one_code_url varchar(150) DEFAULT null;
ALTER TABLE channel_submerch_info ADD COLUMN yufu_kj_key varchar(32) DEFAULT null;
ALTER TABLE channel_submerch_info ADD COLUMN yufu_term_id varchar(30) DEFAULT null;

alter table merch_virtual_trans add COLUMN (fee decimal(12,2) DEFAULT 0.00 COMMENT '提现费');
alter table merch_virtual_trans add COLUMN (withdrawFee decimal(12,2) DEFAULT 0.00 COMMENT '提现手续费');
alter table merch_virtual_trans add COLUMN (rate decimal(6,4) DEFAULT 0.0000 COMMENT '提现费率');

alter table payment_trans add COLUMN (fee decimal(12,2) DEFAULT NULL COMMENT '提现费');
alter table payment_trans add COLUMN (withdrawFee decimal(12,2) DEFAULT NULL COMMENT '提现手续费');
alter table payment_trans add COLUMN (rate decimal(6,4) DEFAULT NULL COMMENT '提现费率');

ALTER TABLE `system_service_log`     CHANGE `input_params` `input_params` TEXT NULL  COMMENT '输入参数';
ALTER TABLE `system_service_log`     CHANGE `output_params` `output_params` TEXT NULL  COMMENT '输出参数';
ALTER TABLE `system_error_log`     CHANGE `input_params` `input_params` TEXT NULL  COMMENT '输入参数';
ALTER TABLE `system_error_log`     CHANGE `output_params` `output_params` TEXT NULL  COMMENT '输出参数';
ALTER TABLE `system_error_log`     CHANGE `error_message` `error_message` TEXT NULL  COMMENT '异常信息';
                           
alter table qrcode_merch add COLUMN (`t0_start_time` varchar(10) DEFAULT '00:00:00' COMMENT 'T0起始时间点');
alter table qrcode_merch add COLUMN (`t0_end_time` varchar(10) DEFAULT '23:59:59' COMMENT 'T0截止时间点');

alter table fast_order add COLUMN receiveFee decimal(12,2);
alter table fast_order add COLUMN fee decimal(12,2);
alter table fast_order add COLUMN rate decimal(6,4);
alter table fast_order modify COLUMN payer_remark varchar(1000);

alter table fast_trans add COLUMN receiveFee decimal(12,2);
alter table fast_trans add COLUMN fee decimal(12,2);
alter table fast_trans add COLUMN rate decimal(6,4);
alter table fast_trans modify COLUMN payer_remark varchar(1000);

alter table qrcode_order add COLUMN receiveFee decimal(12,2);
alter table qrcode_order add COLUMN fee decimal(12,2);
alter table qrcode_order add COLUMN rate decimal(6,4);
alter table qrcode_order modify COLUMN payer_remark varchar(1000);

alter table qrcode_trans_history add COLUMN receiveFee decimal(12,2);
alter table qrcode_trans_history add COLUMN fee decimal(12,2);
alter table qrcode_trans_history add COLUMN rate decimal(6,4);
alter table qrcode_trans_history modify COLUMN payer_remark varchar(1000);

alter table qrcode_trans_today add COLUMN receiveFee decimal(12,2);
alter table qrcode_trans_today add COLUMN fee decimal(12,2);
alter table qrcode_trans_today add COLUMN rate decimal(6,4);
alter table qrcode_trans_today modify COLUMN payer_remark varchar(1000);

alter table payment_trans add COLUMN (batchno varchar(50) COMMENT '代付批次号');