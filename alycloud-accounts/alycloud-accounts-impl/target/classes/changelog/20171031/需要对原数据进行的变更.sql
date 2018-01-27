update merch_user set user_rank=2 where  user_rank=0 and exists(select 1 from merch_info mi where mi.id=merch_id and exists(select 1 from agent_info ai where ai.mobile=mi.mobile));
update merch_user set user_rank=1 where user_rank=0;

-- 御付快捷渠道商户
insert into qrcode_merch 
(merchno,partner_id,partner_type,partner_status,partner_descr,termno,merch_key,status,channel_code,merch_name,settle_type,pay_type,scan_type,liquidate_type,t0_rate_type)
select plat_merchno,merch_id,1,1,'审核通过',term_id,one_code_url,1,'YUFU',merch_name,1<<0,1<<6,1<<5,1,1 from channel_merch_yufu where state=1 and not exists(select 1 from qrcode_merch where channel_code='YUFU' and merchno=plat_merchno);

-- 御付快捷渠道商户费率
-- T0
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,1<<6,1<<5,1<<0,mi.fastpay_rate_t0,2,2 from qrcode_merch qm left join merch_info mi on mi.merchno=qm.merchno 
where qm.channel_code='YUFU' and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<6)=1<<6 and qcmf.scan_type&(1<<5)=1<<5 and qcmf.settle_type&(1<<0)=1<<0);
-- T1
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,1<<6,1<<5,1<<1,mi.fastpay_rate_t1,2,2 from qrcode_merch qm left join merch_info mi on mi.merchno=qm.merchno 
where qm.channel_code='YUFU' and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<6)=1<<6 and qcmf.scan_type&(1<<5)=1<<5 and qcmf.settle_type&(1<<1)=1<<1);

-- T0
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<0,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='YUFU_QRCODE' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<0)=1<<0
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<0)=1<<0);

-- T1
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<1,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='YUFU_QRCODE' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<1)=1<<1
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<1)=1<<1);

-- T0
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<0,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='PINGAN' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<0)=1<<0
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<0)=1<<0);

-- T1
insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<1,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='PINGAN' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<1)=1<<1
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<1)=1<<1);


DELIMITER $$

DROP PROCEDURE IF EXISTS init_user_grade_type$$

CREATE PROCEDURE init_user_grade_type()
BEGIN
	/* 存量用户推荐人计算 */
	DECLARE Done INT DEFAULT 0;    
	DECLARE v_user_id int(11);
        DECLARE v_merch_id int(11);
        DECLARE users CURSOR FOR select id,merch_id from merch_user;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET Done = 1;
		
	OPEN users; 
	FETCH NEXT FROM users INTO v_user_id,v_merch_id;
	REPEAT
	    update merch_user set refer_name=(select id from ((select u2.id from merch_user u2 where exists(select 1 from merch_info m1 where u2.merch_id=m1.id and exists(select 1 from agent_info a1 where a1.mobile=m1.mobile and exists(select 1 from merch_info m2 where m2.super_agent=a1.agentno and m2.id=v_merch_id)))) ) as tmp limit 0,1)
            where id=v_user_id;
            FETCH NEXT FROM users INTO v_user_id,v_merch_id; 
	UNTIL Done END REPEAT;     
	CLOSE users;
    END$$

DELIMITER ;

call init_user_grade_type();
