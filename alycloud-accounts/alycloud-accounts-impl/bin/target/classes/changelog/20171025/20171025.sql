alter table change_merch_info add COLUMN (user_id int(11) DEFAULT NULL COMMENT '用户id');
alter table change_merch_info modify user_id varchar(11);