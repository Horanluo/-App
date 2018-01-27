package io.bestpay.framework.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.avro.specific.SpecificRecord;

import io.bestpay.framework.base.Tree;


/**
 * 树形对像封装
 * @author <a href="mailto:hadoop@139.com">wangqt</a>
 * @createAt 2016年9月29日
 */
public class TreeMapper extends Tree {
	
	/**
	 * 子节点
	 */
	private List<TreeMapper> children = new ArrayList<TreeMapper>();

	public TreeMapper addChild(TreeMapper tree) {
		children.add(tree);
		return this;
	}

	public List<TreeMapper> getChildren() {
		Collections.sort(this.children);
		return children;
	}

	public void setChildren(List<TreeMapper> children) {
		this.children = children;
	}

	@Override
	public int compareTo(SpecificRecord that) {
		if (that instanceof Tree) {
			Tree t = (Tree)that;
			Long value = this.getSequence() - t.getSequence();
			return value.intValue();
		}
		return super.compareTo(that);
	}
}
