/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.resource.protocol;

import java.util.Collection;
import java.util.List;

import io.bestpay.framework.base.CodeException;
import io.bestpay.framework.resource.Resource;

/**
 * 资源协议
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年12月24日 
 *
 */
public interface ResourceProtocol {

	/**
	 * 获取所有菜单
	 * @return
	 */
	List<Resource> getAll();
	/**
	 * 创建
	 * @param resource
	 * @return
	 * @throws CodeException
	 */
	public boolean createIfNotExists(Resource resource) throws CodeException;
	
	/**
	 * 创建所有
	 * @param resources
	 * @return
	 * @throws CodeException
	 */
	public boolean createIfNotExists(Collection<Resource> resources) throws CodeException;
}
