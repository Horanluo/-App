/*
 *  @Copyright 2016 www.bestpay.io Inc. All rights reserved.
 */
package io.bestpay.framework.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.bestpay.framework.base.SpecificRecordUtils;
import io.bestpay.framework.base.Tree;
import io.bestpay.framework.resource.Resource;
import io.bestpay.framework.resource.ResourceType;

/**
 * 资源工具类
 * <pre>
 * </pre>
 * @author <a href="hadoop@139.com">Qituan Wang</a>
 * @createAt 2016年12月24日 
 *
 */
public class ResourceTreeMapper implements TreeBuildable {
	private List<Resource> resources;

	public ResourceTreeMapper(List<Resource> resources) {
		super();
		this.resources = resources;
	}

	@Override
	public TreeMapper create() {
		return this.toTree(resources);
	}
	
	public TreeMapper toTree(List<Resource> resources) {
		
		Tree root = Tree.newBuilder().setId("root")
				 .setExpanded(true)
				 .setLeaf(false)
				 .setText("root")
				 .setViewClass("")
				 .setSequence(0L)
				 .build();
		TreeMapper treeMapper = new TreeMapper();
		SpecificRecordUtils.copy(root, treeMapper);
		if (null == resources) {
			return treeMapper;
		}
		Map<String, List<Resource>> childrenMap = new HashMap<String, List<Resource>>();
		Map<String, Resource> resourceMap = new HashMap<String, Resource>();
		List<Resource> rootResources = new ArrayList<Resource>();
		for (Resource r : resources) {
			String parentCode = r.getParentId();
			String code = r.getId();
			if (null == parentCode || "".equals(parentCode)) {
				rootResources.add(r);
			}
			resourceMap.put(code, r);
			if (!childrenMap.containsKey(code)) {
				childrenMap.put(code, new ArrayList<Resource>());
			}
			
			if (!childrenMap.containsKey(parentCode)) {
				childrenMap.put(parentCode, new ArrayList<Resource>());
			}
			childrenMap.get(parentCode).add(r);
		}
		
		if (rootResources.size() == 1) {
			for (Resource r : rootResources) {
				treeMapper = this.toTreeMapper(r);
				this.createTree(treeMapper, childrenMap.get(r.getId()), childrenMap);
			}
		} else {
			
			createTree(treeMapper, rootResources, childrenMap);
			return treeMapper;
		}
		return treeMapper;
	} 
	
	/**
	 * 转换成树对像
	 * @param resource
	 * @return
	 */
	protected TreeMapper toTreeMapper(Resource resource) {
		TreeMapper treeMapper = new TreeMapper();
		Tree.Builder builder = Tree.newBuilder();
		
		if (resource.getResourceType().equals(ResourceType.VIEW)) {
			builder.setLeaf(true).setExpanded(false);
		} else {
			builder.setLeaf(false).setExpanded(true);
		}
		
		if (null != resource.getDisplay()) {
			builder.setShowInMenu(resource.getDisplay());
		} else {
			builder.setShowInMenu(true);
		}
		
		builder.setId(resource.getCode().replaceAll("\\.", "-"));
		builder.setText(resource.getName());
		builder.setViewClass(resource.getViewClass());
		builder.setSequence(resource.getSortNo());
		
		if (null == builder.getViewClass()) {
			builder.setViewClass("");
		}
		if (null == builder.getSequence()) {
			builder.setSequence(100L);
		}
		
		SpecificRecordUtils.copy(builder.build(), treeMapper);
		return treeMapper;
	}
	
	/**
	 * 递归生成树形结构
	 * @param parent
	 * @param resources
	 * @param childrenMap
	 */
	protected void createTree(TreeMapper parent, List<Resource> resources, Map<String, List<Resource>> childrenMap) {
		if (null == resources || resources.isEmpty()) {
			return;
		}
		Collections.sort(resources, new Comparator<Resource>() {

			@Override
			public int compare(Resource o1, Resource o2) {
				if (null != o1.getSortNo() && null != o2.getSortNo()) {
					return o1.getSortNo().compareTo(o2.getSortNo());
				}
				return 0;
			}
			
		});
		for (Resource r : resources) {
			TreeMapper child = this.toTreeMapper(r);
			if (null != child) {
				parent.addChild(child);
				List<Resource> children = childrenMap.get(r.getId());
				createTree(child, children, childrenMap);
			}
		}
	}
}
