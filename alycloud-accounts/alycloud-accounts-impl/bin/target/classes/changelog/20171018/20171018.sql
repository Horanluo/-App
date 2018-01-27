alter table merch_info add COLUMN (user_id int(11) DEFAULT NULL COMMENT '用户id');
alter table merch_user add COLUMN (user_id int(11) DEFAULT NULL COMMENT '用户id');
alter table merch_info add COLUMN (merch_id int(11) DEFAULT NULL COMMENT '商户内部id');