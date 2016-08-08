package Decision_Tree;

import Decision_Tree.TreeNode;

public class TreeNode {

	/**
	 * 父節點
	 */
	TreeNode parent;

	/**
	 * 指向父的哪個屬性
	 */
	String parentArrtibute;

	/**
	 * 節點名
	 */
	String nodeName;

	/**
	 * 屬性陣列
	 */
	String[] attributes;

	/**
	 * 節點陣列
	 */
	TreeNode[] childNodes;

	/**
	 * 可信度
	 */
	double percent;

}
