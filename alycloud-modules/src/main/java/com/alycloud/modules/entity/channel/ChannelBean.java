package com.alycloud.modules.entity.channel;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelBean {

	private String batchNo;
	private BigDecimal payAmount;
	private String bankCardNumber;
	private String subject;
	private String remark;
	private String businessNumber;
	private String stampDate;
	private String bankCardName;
	private String inputDate;
	private String status;
	private String payBusinessNumber;
}
