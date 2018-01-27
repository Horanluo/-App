
USE `posp_bak_201710101717`;

/* Alter table in target */
ALTER TABLE `channel_qrcode` 
	ADD COLUMN `t0_status` int(8)   NULL DEFAULT '0' COMMENT 'T0状态:0不支持,1需要提现,2不需要提现' after `merch_rsa_key`, 
	ADD COLUMN `t1_status` int(8)   NULL DEFAULT '0' COMMENT 'T1状态:0不支付,1需要提现,2不需要提现' after `t0_status`, 
	ADD COLUMN `amount_min` decimal(10,2)   NULL DEFAULT '5.00' COMMENT '单笔最小金额(元)' after `t1_status`, 
	ADD COLUMN `amount_max` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '单笔最大金额(元)' after `amount_min`, 
	ADD COLUMN `amount_day` decimal(10,2)   NULL DEFAULT '100000.00' COMMENT '当日最高交易额(元)' after `amount_max`, COMMENT='';

/* Alter table in target */
ALTER TABLE `channel_submerch_info` 
	CHANGE `pay_method` `pay_method` varchar(50)  COLLATE utf8_general_ci NULL COMMENT '支付方式，wxPub、wxPubQR、wxApp、wxMicro、alipayQR、alipayApp、alipayMicro、jdMicro、jdQR、unionpayQR、qqwalletQR,QUICKPAY' after `payFeeT1`, COMMENT='';