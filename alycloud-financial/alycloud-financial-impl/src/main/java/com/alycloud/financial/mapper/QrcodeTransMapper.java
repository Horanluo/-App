package com.alycloud.financial.mapper;

import java.util.List;

import com.alycloud.modules.entity.QrcodeTrans;
import com.alycloud.modules.search.QrcodeTrans4Search;

/**
 * 二维码的交易处理
 * @author Moyq5
 * @date 2017年3月15日
 */
public interface QrcodeTransMapper {

	public int add(QrcodeTrans trans);

	public QrcodeTrans getByOrdernoOnToday(String orderno);
	
	public List<QrcodeTrans> listByPageOnToday(QrcodeTrans4Search trans4s);
	
	public QrcodeTrans getByOrdernoOnHistory(String orderno);
	
	public List<QrcodeTrans> listByPageOnHistory(QrcodeTrans4Search trans4s);

	public int modOnToday(QrcodeTrans trans);

	public int modOnHistory(QrcodeTrans trans);
	
}
