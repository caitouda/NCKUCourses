package Decision_Tree;

import Decision_Tree.TreeNode;

public class TreeNode {

	/**
	 * ���`�I
	 */
	TreeNode parent;

	/**
	 * ���V���������ݩ�
	 */
	String parentArrtibute;

	/**
	 * �`�I�W
	 */
	String nodeName;

	/**
	 * �ݩʰ}�C
	 */
	String[] attributes;

	/**
	 * �`�I�}�C
	 */
	TreeNode[] childNodes;

	/**
	 * �i�H��
	 */
	double percent;

}
