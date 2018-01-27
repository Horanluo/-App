package com.alycloud.modules.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum MerchUserRank {

	STAFF_MEMBER(1,"员工"),
	STORE_MANAGER(2,"店长"),
	THE_BOSS(3,"老板"),
	GENERAL_AGENT(4,"普通代理商"),
	SENIOR_AGENT(5,"高级代理商"),
	THE_PARTNER(6,"合伙人");
	
	private Integer value;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
