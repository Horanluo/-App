update merch_user set user_rank=2 where  user_rank=0 and exists(select 1 from merch_info mi where mi.id=merch_id and exists(select 1 from agent_info ai where ai.mobile=mi.mobile));
update merch_user set user_rank=1 where user_rank=0;
/*御付快捷渠道商户*/
insert into qrcode_merch 
(merchno,partner_id,partner_type,partner_status,partner_descr,termno,merch_key,status,channel_code,merch_name,settle_type,pay_type,scan_type,liquidate_type,t0_rate_type)
select plat_merchno,merch_id,1,1,'审核通过',term_id,one_code_url,1,'YUFU',merch_name,1<<0,1<<6,1<<5,1,1 from channel_merch_yufu where state=1 and not exists(select 1 from qrcode_merch where channel_code='YUFU' and merchno=plat_merchno);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,1<<6,1<<5,1<<0,mi.fastpay_rate_t0,2,2 from qrcode_merch qm left join merch_info mi on mi.merchno=qm.merchno 
where qm.channel_code='YUFU' and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<6)=1<<6 and qcmf.scan_type&(1<<5)=1<<5 and qcmf.settle_type&(1<<0)=1<<0);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,1<<6,1<<5,1<<1,mi.fastpay_rate_t1,2,2 from qrcode_merch qm left join merch_info mi on mi.merchno=qm.merchno 
where qm.channel_code='YUFU' and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<6)=1<<6 and qcmf.scan_type&(1<<5)=1<<5 and qcmf.settle_type&(1<<1)=1<<1);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<0,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='YUFU_QRCODE' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<0)=1<<0
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<0)=1<<0);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<1,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='YUFU_QRCODE' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<1)=1<<1
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<1)=1<<1);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<0,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='PINGAN' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<0)=1<<0
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<0)=1<<0);

insert into qrcode_channel_merch_fee
(channel_merch_id,pay_type,scan_type,settle_type,rate,fee,status)
select qm.id,(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5),(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4),1<<1,mf.rate,qm.df_fee,2 from qrcode_merch qm left join qrcode_merch_fee mf on mf.merchno=qm.merchno 
where qm.status=1 
and qm.channel_code='PINGAN' 
and mf.pay_type&(1<<1)=(1<<1) and  mf.scan_type&(1<<0)=(1<<0) and  mf.settle_type&(1<<1)=1<<1
and not exists(select 1 from qrcode_channel_merch_fee qcmf where qcmf.channel_merch_id=qm.id and qcmf.pay_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)+(1<<5) and qcmf.scan_type&(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4)=(1<<0)+(1<<1)+(1<<2)+(1<<3)+(1<<4) and qcmf.settle_type&(1<<1)=1<<1);

INSERT INTO `grade_fee` VALUES 
					(7,1,96,63,31,1,0.0049,2.00,0.00),
					(8,1,96,63,31,2,0.0039,1.00,0.00),
					(9,2,96,63,31,1,0.0045,2.00,0.00),
					(10,2,96,63,31,2,0.0036,1.00,0.00),
					(11,3,96,63,31,1,0.0041,2.00,0.00),
					(12,3,96,63,31,2,0.0033,1.00,0.00),
					(13,4,96,63,31,1,0.0038,2.00,0.00),
					(14,4,96,63,31,2,0.0031,1.00,0.00),
					(15,5,96,63,31,1,0.0035,2.00,0.00),
					(16,5,96,63,31,2,0.0029,1.00,0.00),
					(17,6,96,63,31,1,0.0032,2.00,0.00),
					(18,6,96,63,31,2,0.0027,1.00,0.00),
				   (20,1,24,64,32,2,0.0050,1.00,0.00),
			       (22,2,24,64,32,2,0.0047,1.00,0.00),
			       (24,3,24,64,32,2,0.0045,1.00,0.00),
			       (26,4,24,64,32,2,0.0041,1.00,0.00),
			       (28,5,24,64,32,2,0.0037,1.00,0.00),
			       (30,6,24,64,32,2,0.0035,1.00,0.00),
				   (31,1,24,64,32,1,0.0049,2.00,0.00),
			       (32,2,24,64,32,1,0.0045,2.00,0.00),
			       (33,3,24,64,32,1,0.0041,2.00,0.00),
			       (34,4,24,64,32,1,0.0038,2.00,0.00),
			       (35,5,24,64,32,1,0.0032,2.00,0.00),
			       (36,6,24,64,32,1,0.0030,2.00,0.00),
			       (37,1,128,64,32,1,0.0055,2.00,0.00),
			       (38,2,128,64,32,1,0.0052,2.00,0.00),
			       (39,3,128,64,32,1,0.0049,2.00,0.00),
			       (40,4,128,64,32,1,0.0045,2.00,0.00),
			       (41,5,128,64,32,1,0.0042,2.00,0.00),
			       (42,6,128,64,32,1,0.0038,2.00,0.00);

INSERT INTO `grade` VALUES (1,1,'员工',0,0.00,0,'银联大额隔天C 0.43%'),
                           (2,2,'店长',3,200.00,40,'银联大额隔天C 0.39%|邀请3人，或者支付200元立即升级|交易1000万，可赚4000.00分润'),
                           (3,3,'老板',10,488.00,50,'银联大额隔天C 0.37%|邀请10人，或者支付488元立即升级|交易1000万，可赚5000.00分润'),
                           (4,4,'普通代理商',200,2880.00,60,'银联大额隔天C 0.35%|邀请200人，或者支付2880元立即升级|交易1000万，可赚6000.00分润'),
                           (5,5,'高级代理商',1000,12880.00,65,'银联大额隔天C 0.30%|邀请1000人，或者支付12880元立即升级|交易1000万，可赚1000.00分润'),
                           (6,6,'合伙人',999999,50000.00,70,'请联系客服');
                           
update merch_user set user_rank=1;

/*更新存量用户，状态=3  默认完成实名认证*/
UPDATE MERCH_INFO SET real_name_auth_status=3;

update merch_user u,agent_info a set u.user_rank=4 where u.login_name=a.mobile and a.mobile!='';

update merch_info i,merch_user u set i.user_id=u.login_name where i.id=u.merch_id;

update merch_info set province='广东省',city='深圳市';

update merch_info set address='福田区' where address is null;

update merch_info set area_code='440300' where area_code is null;

update merch_info i,merch_user u set u.true_name=i.legal_name where u.merch_id=i.id and u.true_name='';

update merch_user set mobile=login_name where mobile is null;

update merch_user set mobile=login_name where mobile='';

update merch_info set open_time=add_time where open_time is null;

update merch_info set open_time=add_time where open_time='';

update
	channel_submerch_info csi,channel_merch_yufu cmy 
	set csi.yufu_one_code_url=cmy.one_code_url,csi.yufu_kj_key=cmy.kj_key,csi.yufu_term_id=cmy.term_id
	where csi.merchno=cmy.plat_merchno and csi.yufu_one_code_url is null and cmy.state=1 and csi.channel_code='YUFU';
	
insert into merch_info(merchno,merch_name,full_name) values('200541100000464','测试数据','测试数据');