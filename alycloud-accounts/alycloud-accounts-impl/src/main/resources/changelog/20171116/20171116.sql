alter table merch_user_bank add COLUMN cvv2 varchar(3) DEFAULT NULL COMMENT '信用卡安全码';
alter table merch_user_bank modify COLUMN head_bank_No varchar(12);
alter table merch_user_bank modify COLUMN branch_bank_No varchar(12);
alter table merch_user_bank add COLUMN bank_name_code varchar(20) DEFAULT null COMMENT '银行名称代码';