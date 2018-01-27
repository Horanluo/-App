alter table merch_info modify COLUMN merchno varchar(20);
alter table merch_info modify COLUMN merchno varchar(15);
alter table change_merch_info add COLUMN vali_time varchar(50) DEFAULT NULL COMMENT '身份证有效时间';