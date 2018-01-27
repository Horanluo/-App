package com.alycloud.modules.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于判断上报渠道费率  标识位
 * @author Horanluo
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class ReportMark {

	private String reportMark;
}
