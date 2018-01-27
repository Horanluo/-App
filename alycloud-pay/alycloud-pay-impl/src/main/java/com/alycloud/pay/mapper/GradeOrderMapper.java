package com.alycloud.pay.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.alycloud.modules.entity.GradeOrder;
import com.alycloud.modules.search.GradeOrder4Search;

/**
 * 商户升级付款订单
 * @author Moyq5
 * @date 2017年10月30日
 */
public interface GradeOrderMapper {

	/**
	 * 添加
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order
	 */
	public void add(GradeOrder order);
	
	/**
	 * 更新
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order
	 */
	public void mod(GradeOrder order);

	/**
	 * 根据ID查询
	 * @author Moyq5
	 * @date 2017年11月5日
	 * @param id
	 * @return
	 */
	public GradeOrder getById(Integer id);
	
	/**
	 * 查询
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order4s
	 * @return
	 */
	public List<GradeOrder> listByPage(GradeOrder4Search order4s);
	
	/**
	 * 统计金额
	 * @author Moyq5
	 * @date 2017年10月30日
	 * @param order4s
	 * @return
	 */
	public BigDecimal sumAmount(GradeOrder4Search order4s);

}
