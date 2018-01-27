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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8
