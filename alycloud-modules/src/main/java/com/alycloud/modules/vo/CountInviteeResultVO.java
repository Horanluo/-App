package com.alycloud.modules.vo;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Moyq5
 * @date 2017年11月8日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountInviteeResultVO {

	private Integer totalCount;
	private List<Map<String, Object>> grades;
	
}
