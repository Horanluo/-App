package com.alycloud.channel.yufu.merch.support.client;

import com.alycloud.channel.yufu.merch.bean.MerchApplyData;
import com.alycloud.channel.yufu.merch.bean.MerchApplyResult;
import com.alycloud.channel.yufu.merch.support.AbstractClient;

/**
 * 商户入驻接口
 * @author Moyq5
 * @date 2017年8月1日
 */
public class MerchApply extends AbstractClient<MerchApplyData, MerchApplyResult> {

	public MerchApply() {
		super(MerchApplyResult.class);
	}

}
