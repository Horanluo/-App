alter table merch_user add COLUMN (refer_name varchar(30) DEFAULT NULL COMMENT '推荐人');
alter table merch_info MODIFY COLUMN user_id varchar(11);
alter table merch_user drop COLUMN user_id;
alter table merch_info drop COLUMN merch_id;