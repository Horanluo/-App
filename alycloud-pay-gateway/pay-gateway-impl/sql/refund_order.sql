﻿DROP TABLE IF EXISTS refund_order;
CREATE TABLE refund_order (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `branchno` varchar(10) DEFAULT NULL COMMENT '机构号',
  `branch_name` varchar(30) DEFAULT NULL COMMENT '机构名称',
  `agentno` varchar(10) DEFAULT NULL COMMENT '代理商号',
  `agent_name` varchar(50) DEFAULT NULL COMMENT '代理商名称',
  `merchno` varchar(15) DEFAULT NULL COMMENT '商户号',
  `merch_name` varchar(50) DEFAULT NULL COMMENT '商户名称',
  `trans_date` varchar(10) DEFAULT NULL COMMENT '交易日期',
  `trans_time` varchar(8) DEFAULT NULL COMMENT '交易时间',
  `pay_type` int(11) DEFAULT NULL COMMENT '支付类型',
  `scan_type` int(11) DEFAULT NULL COMMENT '扫码方式',
  `traceno` varchar(50) DEFAULT NULL COMMENT '商户流水号',
  `orderno` varchar(32) DEFAULT NULL,
  `amount` decimal(12,2) DEFAULT NULL COMMENT '交易金额',
  `notify_url` varchar(500) DEFAULT NULL,
  `return_url` varchar(200) DEFAULT NULL,
  `settle_type` int(11) DEFAULT NULL COMMENT '结算方式(1-T+0结算 2-T+1结算)',
  `status` int(11) DEFAULT NULL COMMENT '订单状态(0-下单成功 1-支付成功 2-支付失败)',
  `status_desc` varchar(3000) DEFAULT NULL COMMENT '状态说明',
  `channel_code` varchar(30) DEFAULT NULL COMMENT '渠道编码',
  `channel_merchno` varchar(50) DEFAULT NULL COMMENT '渠道商户号',
  `channel_url` varchar(3000) DEFAULT NULL COMMENT '通道方交易地址',
  `merch_fee` decimal(12,2) DEFAULT NULL COMMENT '商户手续费',
  `channel_fee` decimal(12,2) DEFAULT NULL COMMENT '渠道手续费',
  `branch_fee` decimal(12,2) DEFAULT NULL COMMENT '机构手续费',
  `certno` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `accountno` varchar(20) DEFAULT NULL COMMENT '结算卡号',
  `account_name` varchar(30) DEFAULT NULL COMMENT '结算户名',
  `bankno` varchar(20) DEFAULT NULL COMMENT '联行号',
  `bank_type` varchar(100) DEFAULT NULL,
  `open_id` varchar(20) DEFAULT NULL COMMENT '用户的OpenId',
  `bank_name` varchar(100) DEFAULT NULL,
  `query_count` int(11) DEFAULT '0' COMMENT '查询次数',
  `last_query_time` varchar(19) DEFAULT NULL COMMENT '上次查询时间',
  `bankCardType` varchar(10) DEFAULT NULL,
  `channelRefundNo` varchar(50) DEFAULT NULL COMMENT '通道退款号',
  `payer_remark` varchar(500) DEFAULT NULL COMMENT '支付备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `qrcode_order_refno` (`orderno`),
  UNIQUE KEY `qrcode_order_traceno` (`merchno`,`traceno`),
  KEY `qrcode_order_orderno` (`orderno`),
  KEY `qrcode_order_date_time` (`trans_date`,`trans_time`),
  KEY `qrcode_order_id` (`id`),
  KEY `qrcode_order_status` (`status`),
  KEY `qrcode_order_merchno` (`merchno`)
) ENGINE=InnoDB AUTO_INCREMENT=560355 DEFAULT CHARSET=gbk;