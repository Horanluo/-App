DROP TABLE IF EXISTS `channel_qrcode`;
CREATE TABLE `channel_qrcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_code` varchar(20) DEFAULT NULL COMMENT '渠道编码',
  `channel_name` varchar(20) DEFAULT NULL COMMENT '渠道名称',
  `channel_alias` varchar(50) DEFAULT NULL COMMENT '渠道别名（可向用户展示的名称）',
  `channel_address` varchar(200) DEFAULT NULL,
  `return_address` varchar(200) DEFAULT NULL, 
  `status` int(11) DEFAULT '2' COMMENT '状态(1-启用 2-禁用)', 
  `cal_type` int(11) DEFAULT '1' COMMENT '计费方式(1-四舍五入 1-进一法 2-去尾法)', 
  `key_rsa` varchar(2000) DEFAULT NULL COMMENT 'RSA公钥', 
  `key_md5` varchar(2000) DEFAULT NULL COMMENT 'MD5密钥', 
  `agentno` varchar(30) DEFAULT NULL COMMENT '代理号',
  `notify_address` varchar(300) DEFAULT NULL,
  `pay_address` varchar(200) DEFAULT NULL COMMENT '代付地址',
  `remark` varchar(2000) DEFAULT NULL COMMENT '备注信息',
  `pay_notify_url` varchar(500) DEFAULT NULL COMMENT '代付通知地址',
  `merch_rsa_key` varchar(3000) DEFAULT NULL COMMENT '商户RSA私钥',
  `t0_status` int(8) DEFAULT '0' COMMENT 'T0状态:0不支持,1需要提现,2不需要提现',
  `t1_status` int(8) DEFAULT '0' COMMENT 'T1状态:0不支付,1需要提现,2不需要提现',
  `amount_min` decimal(10,2) DEFAULT '5.00' COMMENT '单笔最小金额(元)',
  `amount_max` decimal(10,2) DEFAULT '100000.00' COMMENT '单笔最大金额(元)',
  `amount_day` decimal(10,2) DEFAULT '100000.00' COMMENT '当日最高交易额(元)',
  `t0_start_time` varchar(10) DEFAULT '00:00:00' COMMENT 'T0起始时间点',
  `t0_end_time` varchar(10) DEFAULT '23:59:59' COMMENT 'T0结束时间点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=gbk ROW_FORMAT=DYNAMIC;

INSERT INTO `channel_qrcode` VALUES (8,'GUOFU','国付',NULL,'http://api.gf-info.com/','http://ftp.esyto.com:6001/guofuQrcodeReturn.do',2,0,'','','','http://ftp.esyto.com:6001/guofuQrcodeNotify.do','http://api.gf-info.com/','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(9,'WFT','威富通',NULL,'https://pay.swiftpass.cn/pay/gateway','http://ftp.esyto.com:6001/wftQrcodeRonity.do',2,0,'','','','http://ftp.esyto.com:6001/wftQrodeReturn.do','','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(10,'JSDZ','江苏电子',NULL,'http://pay.ling-pay.com/PhonePOSPInterfaceNew/CooperativebusinessInterface','',2,1,'','','','http://120.25.246.81:6001/jsdzQrcodeNotify.do','','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(11,'LINKPAY','江苏电子V2',NULL,'http://180.96.28.8:8044/TransInterface/TransRequest','',2,1,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCic2TlO82kPVvi85BTx+06vZobf009QRM8Ctw5\r\nAOdykz/ByJQTk9lLUQgLN//A+qssN8wR3O+a2ma/Q0audZbM9JViojaOhLStrFBgQuud5Shkjd+D\r\niXPN2Z8XJYBhwtpMh46IKD34auAW2s5N936Dijn41aFx3HhuiWPi7A+f+wIDAQAB','','','http://120.25.246.81:6001/linkpayQrcodeNotify.do','','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(12,'PINGAN','平安支付',NULL,'https://api.orangebank.com.cn/mct1/','http://ftp.esyto.com/qrcode/return.do?m=pingan',1,1,'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvi25zPmX7AldQeCfm8vbkroVC8KWATKdmDvYI6TwVbZix/gxV70V2x1LBTUspJ9AIIHUy4ZgHwx8oxUVFrAHweXPmkv03m0c4hwWTdMevIgSsoMwjYXKYpL9R2xzB9u4DLNz/GsxTiVxI6vqEtUT2jMsop9Mv82NCzMnpn/lOUDa5gVNUmzrzUe5oWrmTHNoyL6B+ory/YqdiasnJkvOg27SfS20NRJMf4tItKYz9YlJ5oIIKmGguxthOqD27z5EZwnQqNFpexkfA4GaEt2EV6OldO4p80IGY7CXFET71ShaafGtuw+K4I7oQ+s1uyIwAUlT9KT+iWlhrizpsmrvUwIDAQAB','','','http://ftp.esyto.com/channel/notify.do?m=pingan','','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(13,'YUFU_QRCODE','御付二维码',NULL,'http://scp.yufu99.com/scanpay-api/api/','',1,1,'','f37d4321f5fabffd0c4c04b4e65c9877','','http://ftp.esyto.com/channel/notify/yufu4QrcodePay.do','','',NULL,'',0,0,5.00,100000.00,100000.00,'00:00:00','23:59:59'),(14,'YUFU','御付快捷',NULL,'http://api.mypays.cn/api/service.json','',1,1,'','','','','','（T+0提现时间） 周一到周日08：30-21：30，银行系统自动T+0提现，新客户使用，银行审核1个工作日内。',NULL,'',2,0,1000.00,20000.00,50000.00,'08:30:00','21:30:00'),(15,'HXTC','汇享天成',NULL,'http://api.mypays.cn/api/service.json','',1,1,'','','','','','（T+0提现时间） 周一到周日08：30-21：30，银行系统自动T+0提现',NULL,'',2,0,100.00,8000.00,50000.00,'08:30:00','21:30:00'),(16,'HXTC_JF','汇享天成_积分',NULL,'http://api.mypays.cn/api/service.json','',1,1,'','','','','','（T+0提现时间） 周一到周日08：30-21：30，银行系统自动T+0提现',NULL,'',2,0,100.00,20000.00,50000.00,'08:30:00','21:30:00');

