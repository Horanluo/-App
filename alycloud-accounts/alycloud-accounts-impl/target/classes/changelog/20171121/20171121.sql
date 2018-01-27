
USE `posp_2`;

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