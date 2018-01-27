package com.alycloud.pay.mapper;

import com.alycloud.modules.entity.SystemCardBin;

/**
 * 卡BIN信息
 * @author Moyq5
 * @date 2017年10月21日
 */
public interface SystemCardBinMapper {

	SystemCardBin getByCardno(String cardno);

}
