package com.alycloud.channel.yufu.support.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alycloud.channel.yufu.bean.MerchOrderQueryData;
import com.alycloud.channel.yufu.bean.MerchOrderQueryResult;
import com.alycloud.channel.yufu.support.AbstractClient;
import com.alycloud.channel.yufu.support.Tool;
import com.alycloud.channel.yufu.support.utils.JSON;

/**
 * 商户订单查询接口
 * @author Moyq5
 * @date 2017年8月8日
 */
public class MerchOrderQuery extends AbstractClient<MerchOrderQueryData, MerchOrderQueryResult> {

	public MerchOrderQuery() {
		super("merOrderQuery", MerchOrderQueryResult.class);
	}

	@Override
	protected String getReqBody(MerchOrderQueryData data) throws Exception {
		String jsonString = JSON.toString(data);
		List<String> ignoreFields = new ArrayList<String>();
		ignoreFields.add("page_num");// 页码不参与签名
		Map<String, String> dataMap = Tool.getSortedFields(jsonString, ignoreFields, false);
		String sign = Tool.sign(dataMap, false, super.getContext().getConfig().getKey());
		dataMap = Tool.getSortedFields(jsonString, null, false);
		dataMap.put("sign", sign);
		return Tool.toQueryString(dataMap, null, false);
	}

}
