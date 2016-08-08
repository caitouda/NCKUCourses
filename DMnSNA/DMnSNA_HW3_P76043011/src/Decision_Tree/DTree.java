package Decision_Tree;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * �M����ID3�t��k
 * 
 */
public class DTree {
	/**
	 * �ڸ`�I
	 */
	TreeNode root;

	/**
	 * �i���ʰ}�C
	 */
	private boolean[] visable;

	/**
	 * �����`�I
	 */
	private static final int NO_FOUND = -1;

	/**
	 * �V�m��
	 */
	private Object[] trainingArray;

	/**
	 * �`�I����
	 */
	private int nodeIndex;

	public DTree() {
	}

	/**
	 * �ھڶǤJ��ƶi��w��
	 * 
	 * @param printData
	 * @param node
	 */
	public void compare(String[] printData, TreeNode node, BufferedWriter file_out) {
		String result = null;
		System.out.println("compare");
		int index = getNodeIndex(node.nodeName);
		if (index == NO_FOUND) {
			for (int i = 0; i < printData.length; i++) {
				System.out.print(printData[i] + "  ");
			}
			System.out.print(node.nodeName + "  " + (node.percent * 100) + "%");
			result = node.nodeName;
			try {
				if (result.startsWith("B"))
					result = "Basic";
				else if (result.startsWith("N"))
					result = "Normal";
				else if (result.startsWith("S"))
					result = "Silver";
				else
					result = "Gold";
				file_out.write(result);
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		TreeNode[] childs = node.childNodes;
		for (int i = 0; i < childs.length; i++) {
			if (childs[i] != null) {
				if (childs[i].parentArrtibute.equals(printData[index])) {
					compare(printData, childs[i], file_out);
				}
			}
		}
		// return result;
	}

	/**
	 * �Ы�
	 * 
	 * @param array
	 * @param index
	 */
	public void create(Object[] array, int index) {
		System.out.println("create");
		this.trainingArray = array;
		init(array, index);
		System.out.println("hello");
		createDTree(array);
		printDTree(root);

	}

	/**
	 * �o��̤j��T�W�q
	 * 
	 * @param array
	 * @return Object[]
	 */
	@SuppressWarnings("boxing")
	public Object[] getMaxGain(Object[] array) {
		System.out.println("getMaxGain");
		Object[] result = new Object[2];
		double gain = 0;
		int index = -1;

		for (int i = 0; i < visable.length; i++) {
			if (!visable[i]) {
				double value = gain(array, i);
				if (gain < value) {
					gain = value;
					index = i;
				}
			}
		}
		result[0] = gain;
		result[1] = index;
		if (index != -1) {
			visable[index] = true;
		}
		return result;
	}

	/**
	 * �ЫبM����
	 * 
	 * @param array
	 */
	public void createDTree(Object[] array) {
		System.out.println("createDTree");
		Object[] maxgain = getMaxGain(array);
		System.out.println("hello2");
		if (root == null) {
			root = new TreeNode();
			root.parent = null;
			root.parentArrtibute = null;
			root.attributes = getAttributes(((Integer) maxgain[1]).intValue());
			root.nodeName = getNodeName(((Integer) maxgain[1]).intValue());
			root.childNodes = new TreeNode[root.attributes.length];
			insertTree(array, root);
		}
	}

	/**
	 * ���J��M����
	 * 
	 * @param array
	 * @param parentNode
	 */
	public void insertTree(Object[] array, TreeNode parentNode) {
		System.out.println("insertTree");
		String[] Attributes = parentNode.attributes;
		System.out.println("���׬O" + Attributes.length);
		for (int i = 0; i < Attributes.length; i++) {
			Object[] pickArray = pickUpAndCreateArray(array, Attributes[i], getNodeIndex(parentNode.nodeName));
			Object[] info = getMaxGain(pickArray);
			double gain = ((Double) info[0]).doubleValue();
			if (gain != 0) {
				int index = ((Integer) info[1]).intValue();
				TreeNode currentNode = new TreeNode();
				currentNode.parent = parentNode;
				currentNode.parentArrtibute = Attributes[i];
				currentNode.attributes = getAttributes(index);
				currentNode.nodeName = getNodeName(index);
				currentNode.childNodes = new TreeNode[currentNode.attributes.length];
				parentNode.childNodes[i] = currentNode;
				insertTree(pickArray, currentNode);
			} else {
				TreeNode leafNode = new TreeNode();
				leafNode.parent = parentNode;
				leafNode.parentArrtibute = Attributes[i];
				leafNode.attributes = new String[0];
				leafNode.nodeName = getLeafNodeName(pickArray);
				System.out.println("nodeName:" + leafNode.nodeName);
				leafNode.childNodes = new TreeNode[0];
				parentNode.childNodes[i] = leafNode;

				double percent = 0;
				String[] arrs = getAttributes(this.nodeIndex);
				for (int j = 0; j < arrs.length; j++) {
					if (leafNode.nodeName == null)
						leafNode.nodeName = "B";
					if (leafNode.nodeName.equals(arrs[j])) {
						System.out.println("whyyy");
						Object[] subo = pickUpAndCreateArray(pickArray, arrs[j], this.nodeIndex);
						Object[] o = pickUpAndCreateArray(this.trainingArray, arrs[j], this.nodeIndex);
						double subCount = subo.length;
						percent = subCount / o.length;
					}
				}
				leafNode.percent = percent;
				System.out.println("�X�v�O" + leafNode.percent);
			}
		}
	}

	/**
	 * �C�L�M����
	 * 
	 * @param node
	 */
	public void printDTree(TreeNode node) {
		System.out.println("printDTree");
		TreeNode[] childs = node.childNodes;
		for (int i = 0; i < childs.length; i++) {
			if (childs[i] != null) {
				System.out.println(childs[i].parentArrtibute);
				printDTree(childs[i]);
			}
		}
	}

	/**
	 * ��l��
	 * 
	 * @param dataArray
	 * @param index
	 */
	public void init(Object[] dataArray, int index) {
		System.out.println("init");
		this.nodeIndex = index;
		// �ƾڪ�l��
		int test = ((String[]) dataArray[0]).length;
		System.out.println(test);
		// Scanner input = new Scanner(System.in);
		// test = input.nextInt();
		visable = new boolean[((String[]) dataArray[0]).length];
		// visable = new boolean[5];
		for (int i = 0; i < visable.length; i++) {
			if (i == index) {
				visable[i] = true;
			} else {
				visable[i] = false;
			}
		}
	}

	/**
	 * �Ũ��}�C
	 * 
	 * @param array
	 * @param arrtibute
	 * @param index
	 * @return Object[]
	 */
	public Object[] pickUpAndCreateArray(Object[] array, String arrtibute, int index) {
		System.out.println("pickUpAndCreateArray");
		List<String[]> list = new ArrayList<String[]>();
		for (int i = 0; i < array.length; i++) {
			String[] strs = (String[]) array[i];
			if (strs[index].equals(arrtibute)) {
				list.add(strs);
			}
		}
		return list.toArray();
	}

	/**
	 * Entropy(S)
	 * 
	 * @param array
	 * @param index
	 * @return double
	 */
	public double gain(Object[] array, int index) {
		System.out.println("gain");
		String[] playBalls = getAttributes(this.nodeIndex);
		int[] counts = new int[playBalls.length];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}
		for (int i = 0; i < array.length; i++) {
			String[] strs = (String[]) array[i];
			for (int j = 0; j < playBalls.length; j++) {
				if (strs[this.nodeIndex].equals(playBalls[j])) {
					counts[j]++;
				}
			}
		}
		/**
		 * Entropy(S) = S -p(I) log2 p(I)
		 */
		double entropyS = 0;
		for (int i = 0; i < counts.length; i++) {
			entropyS += DTreeUtil.sigma(counts[i], array.length);
		}
		String[] Attributes = getAttributes(index);
		/**
		 * total ((|Sv| / |S|) * Entropy(Sv))
		 */
		double sv_total = 0;
		for (int i = 0; i < Attributes.length; i++) {
			sv_total += entropySv(array, index, Attributes[i], array.length);
		}
		return entropyS - sv_total;
	}

	/**
	 * ((|Sv| / |S|) * Entropy(Sv))
	 * 
	 * @param array
	 * @param index
	 * @param arrtibute
	 * @param allTotal
	 * @return double
	 */
	public double entropySv(Object[] array, int index, String arrtibute, int allTotal) {
		String[] playBalls = getAttributes(this.nodeIndex);
		int[] counts = new int[playBalls.length];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}

		for (int i = 0; i < array.length; i++) {
			String[] strs = (String[]) array[i];
			if (strs[index].equals(arrtibute)) {
				for (int k = 0; k < playBalls.length; k++) {
					if (strs[this.nodeIndex].equals(playBalls[k])) {
						counts[k]++;
					}
				}
			}
		}

		int total = 0;
		double entropySv = 0;
		for (int i = 0; i < counts.length; i++) {
			total += counts[i];
		}
		for (int i = 0; i < counts.length; i++) {
			entropySv += DTreeUtil.sigma(counts[i], total);
		}
		return DTreeUtil.getPi(total, allTotal) * entropySv;
	}

	/**
	 * ���o�ݩʰ}�C
	 * 
	 * @param index
	 * @return String[]
	 */
	@SuppressWarnings("unchecked")
	public String[] getAttributes(int index) {
		System.out.println("getAttributes");
		TreeSet<String> set = new TreeSet<String>(new SequenceComparator());
		// System.out.println(trainingArray.length);
		for (int i = 0; i < trainingArray.length; i++) {
			String[] strs = (String[]) trainingArray[i];
			// System.out.println("why");
			// System.out.println(strs);
			set.add(strs[index]);
		}
		String[] result = new String[set.size()];
		return set.toArray(result);
	}

	/**
	 * ���o�`�I�W
	 * 
	 * @param index
	 * @return String
	 */
	public String getNodeName(int index) {
		String[] strs = new String[] { "marital_status", "num_children_at_home numeric", "year_income", "age",
				"member_card" };
		for (int i = 0; i < strs.length; i++) {
			if (i == index) {
				return strs[i];
			}
		}
		return null;
		// return "B";
	}

	/**
	 * ���o���`�I�W
	 * 
	 * @param array
	 * @return String
	 */
	public String getLeafNodeName(Object[] array) {
		if (array != null && array.length > 0) {
			String[] strs = (String[]) array[0];
			return strs[nodeIndex];
		}
		return null;
	}

	/**
	 * ���o�`�I����
	 * 
	 * @param name
	 * @return int
	 */
	public int getNodeIndex(String name) {
		String[] strs = new String[] { "marital_status", "num_children_at_home numeric", "year_income", "age",
				"member_card" };
		for (int i = 0; i < strs.length; i++) {
			if (name.equals(strs[i])) {
				return i;
			}
		}
		return NO_FOUND;
	}

}
