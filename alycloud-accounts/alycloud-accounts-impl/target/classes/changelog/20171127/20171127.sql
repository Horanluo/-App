alter table merch_info add COLUMN json_str varchar(1000) DEFAULT null COMMENT '额外存储信息';
alter table change_merch_info add COLUMN json_str varchar(1000) DEFAULT null COMMENT '额外存储信息';
alter table merch_info add COLUMN real_name_auth_status int(1) DEFAULT null COMMENT '0表示没有进行任何认证，1表示只进行了身份证认证，
2表示进行了身份证和银行卡四要素认证，3表示身份证认证、银行卡四要素认证、活体识别都实名认证';