package com.alycloud.financial.support.agent;

import com.alycloud.modules.entity.FastTrans;
import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.entity.QrcodeTrans;

/**
 * 分润计算工厂类
 * @author Moyq5
 * @date 2017年11月6日
 */
public abstract class TransBuilderFactory {

	public static TransBuilder getBuilder(FastTrans trans) {
		return new TransBuilder4FastTrans(trans);
	}
	
	public static TransBuilder getBuilder(QrcodeTrans trans) {
		return new TransBuilder4QrcodeTrans(trans);
	}
	
	public static TransBuilder getBuilder(GradeOrder trans) {
		return new TransBuilder4GradeOrder(trans);
	}
	
}
