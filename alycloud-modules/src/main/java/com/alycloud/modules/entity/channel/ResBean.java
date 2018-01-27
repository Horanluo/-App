package com.alycloud.modules.entity.channel;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value =Include.NON_NULL)
public class ResBean {

	private String code;
	private String count;
	private List<ChannelBean> data;
	private String msg;
	private String result;
}
