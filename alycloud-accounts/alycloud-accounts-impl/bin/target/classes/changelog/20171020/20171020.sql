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
)ENGINE=InnoDB AUTO_INCREMENT=4061 DEFAULT CHARSET=gbk

alter table merch_user_bank modify COLUMN head_bank_No int(12);
alter table merch_user_bank modify COLUMN user_id varchar(11);