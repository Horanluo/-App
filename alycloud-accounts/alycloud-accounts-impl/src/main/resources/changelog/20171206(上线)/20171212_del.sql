/** 登录名  多条的数据*/
select *,count(login_name) ct from merch_user GROUP BY login_name HAVING ct>1;

delete from merch_user where id in (1740,2560,2670,2670,1999,1719,2535,2015,1780,3171,1663,2298,1663,1722,1657,2902,3033,3185,1852,1710,2373,1773,1671,1672,1668,2632,3182,3181,1892,2009,2920,3239,2378,1823,2524,1735,1717,1684);

delete from agent_info where mobile='13823695289';

delete from qrcode_order where merch_fee is null;