package com.alycloud.pay.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeBranchFee;
import com.alycloud.modules.search.QrcodeBranchFee4Search;


/**
 * 机构二维码费率
 * @author Moyq5
 * @date 2017年10月18日
 */
public interface QrcodeBranchFeeMapper {
	
	public int addList(List<QrcodeBranchFee> qrcodeFees);
	
	public int add(QrcodeBranchFee qrcodeFee);
	
	public int mod(QrcodeBranchFee qrcodeFee);
	
	public List<QrcodeBranchFee> list(QrcodeBranchFee4Search qrcodefee4s);
}
