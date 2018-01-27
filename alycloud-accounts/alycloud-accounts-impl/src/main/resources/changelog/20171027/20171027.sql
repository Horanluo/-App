alter table change_merch_info drop COLUMN user_id;
alter table merch_user add COLUMN (user_rank int(11) DEFAULT null);