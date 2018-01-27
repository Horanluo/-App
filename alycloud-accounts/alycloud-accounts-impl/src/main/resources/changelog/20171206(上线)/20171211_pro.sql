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