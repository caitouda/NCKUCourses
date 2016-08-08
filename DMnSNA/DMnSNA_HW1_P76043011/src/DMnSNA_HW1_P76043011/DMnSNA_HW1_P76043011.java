package DMnSNA_HW1_P76043011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DMnSNA_HW1_P76043011 {
	private static int SUPPORT; // ������H��
	private static double CONFIDENCE; // �m�H���H��
	private final static String ITEM_SPLIT = " "; // �����������j�Ÿ�
	private final static String CON = "->"; // �����������j�Ÿ�
	private static List<String> transList = new ArrayList<String>(); // �Ҧ����
	private static List<Set<String>> dataSet;

	/**
	 * �ϥ��ɤ�����ƶ�
	 */
	List<String> buildData(String... fileName) {
		List<String> data = new ArrayList<String>();
		if (fileName.length != 0) {
			File file = new File(fileName[0]);
			// File file = new File("data.txt");
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					data.add(line);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("fail to find target file !");
		}
		dataSet = new ArrayList<Set<String>>();
		Set<String> dSet;
		for (String d : data) {
			dSet = new TreeSet<String>();
			String[] dArr = d.split(" ");
			for (String str : dArr) {
				// System.out.println(str);
				dSet.add(str);
			}
			dataSet.add(dSet);
		}
		return data;
	}

	/**
	 * ��o�W�c��
	 */
	public Map<String, Integer> getFC() {
		Map<String, Integer> frequentCollectionMap = new HashMap<String, Integer>();// �Ҧ����W�c��
		frequentCollectionMap.putAll(getItem1FC());
		Map<String, Integer> itemkFcMap = new HashMap<String, Integer>();
		itemkFcMap.putAll(getItem1FC());
		while (itemkFcMap != null && itemkFcMap.size() != 0) {
			Map<String, Integer> candidateCollection = getCandidateCollection(itemkFcMap);
			Set<String> ccKeySet = candidateCollection.keySet();
			// ��Կﶰ���i��֥[�p��
			for (String trans : transList) {
				for (String candidate : ccKeySet) {
					boolean flag = true;// �ΨӧP�_������O�_�X�{�ӭԿﶵ�A�p�G�X�{�A�p�ƥ[1
					String[] candidateItems = candidate.split(ITEM_SPLIT);
					for (String candidateItem : candidateItems) {
						if (trans.indexOf(candidateItem + ITEM_SPLIT) == -1) {
							flag = false;
							break;
						}
					}
					if (flag) {
						Integer count = candidateCollection.get(candidate);
						candidateCollection.put(candidate, count + 1);
					}
				}
			}
			// �q�Կﶰ�����ŦX����ת��W�c����
			itemkFcMap.clear();
			for (String candidate : ccKeySet) {
				Integer count = candidateCollection.get(candidate);
				if (count >= SUPPORT) {
					itemkFcMap.put(candidate, count);
				}
			}
			// �X�֩Ҧ��W�c��
			frequentCollectionMap.putAll(itemkFcMap);
		}
		return frequentCollectionMap;
	}

	/**
	 * ��o�Կﶰ
	 */
	private Map<String, Integer> getCandidateCollection(Map<String, Integer> itemkFcMap) {
		Map<String, Integer> candidateCollection = new HashMap<String, Integer>();
		Set<String> itemkSet1 = itemkFcMap.keySet();
		Set<String> itemkSet2 = itemkFcMap.keySet();
		for (String itemk1 : itemkSet1) {
			for (String itemk2 : itemkSet2) {
				// �i��s��
				String[] tmp1 = itemk1.split(ITEM_SPLIT);
				String[] tmp2 = itemk2.split(ITEM_SPLIT);

				String c = "";
				if (tmp1.length == 1) {
					if (tmp1[0].compareTo(tmp2[0]) < 0) {
						c = tmp1[0] + ITEM_SPLIT + tmp2[0] + ITEM_SPLIT;
					}
				} else {
					boolean flag = true;
					for (int i = 0; i < tmp1.length - 1; i++) {
						if (!tmp1[i].equals(tmp2[i])) {
							flag = false;
							break;
						}
					}
					if (flag && (tmp1[tmp1.length - 1].compareTo(tmp2[tmp2.length - 1]) < 0)) {
						c = itemk1 + tmp2[tmp2.length - 1] + ITEM_SPLIT;
					}
				}
				// �i��ŪK
				boolean hasInfrequentSubSet = false;
				if (!c.equals("")) {
					String[] tmpC = c.split(ITEM_SPLIT);
					for (int i = 0; i < tmpC.length; i++) {
						String subC = "";
						for (int j = 0; j < tmpC.length; j++) {
							if (i != j) {
								subC = subC + tmpC[j] + ITEM_SPLIT;
							}
						}
						if (itemkFcMap.get(subC) == null) {
							hasInfrequentSubSet = true;
							break;
						}
					}
				} else {
					hasInfrequentSubSet = true;
				}

				if (!hasInfrequentSubSet) {
					candidateCollection.put(c, 0);
				}
			}
		}
		return candidateCollection;
	}

	/**
	 * ��o1�W�c��
	 */
	private Map<String, Integer> getItem1FC() {
		Map<String, Integer> sItem1FcMap = new HashMap<String, Integer>();
		Map<String, Integer> rItem1FcMap = new HashMap<String, Integer>();// �W�c1����
		for (String trans : transList) {
			String[] items = trans.split(ITEM_SPLIT);
			for (String item : items) {
				Integer count = sItem1FcMap.get(item + ITEM_SPLIT);
				if (count == null) {
					sItem1FcMap.put(item + ITEM_SPLIT, 1);
				} else {
					sItem1FcMap.put(item + ITEM_SPLIT, count + 1);
				}
			}
		}
		Set<String> keySet = sItem1FcMap.keySet();
		for (String key : keySet) {
			Integer count = sItem1FcMap.get(key);
			if (count >= SUPPORT) {
				rItem1FcMap.put(key, count);
			}
		}
		return rItem1FcMap;
	}

	/**
	 * ��o���p�W�h
	 */
	public Map<String, Double> getRelationRules(Map<String, Integer> frequentCollectionMap) {
		Map<String, Double> relationRules = new HashMap<String, Double>();
		Set<String> keySet = frequentCollectionMap.keySet();
		for (String key : keySet) {
			double countAll = frequentCollectionMap.get(key);
			String[] keyItems = key.split(ITEM_SPLIT);
			if (keyItems.length > 1) {
				List<String> source = new ArrayList<String>();
				Collections.addAll(source, keyItems);
				List<List<String>> result = new ArrayList<List<String>>();
				buildSubSet(source, result);// ��osource���Ҧ��D�Ťl��
				for (List<String> itemList : result) {
					if (itemList.size() < source.size()) {// �u�B�z�u�l��
						List<String> otherList = new ArrayList<String>();
						for (String sourceItem : source) {
							if (!itemList.contains(sourceItem)) {
								otherList.add(sourceItem);
							}
						}
						String reasonStr = "";// �e�m
						String resultStr = "";// ���G
						for (String item : itemList) {
							reasonStr = reasonStr + item + ITEM_SPLIT;
						}
						for (String item : otherList) {
							resultStr = resultStr + item + ITEM_SPLIT;
						}
						double countReason = frequentCollectionMap.get(reasonStr);
						double itemConfidence = countAll / countReason;// �p��m�H��
						if (itemConfidence > 1)
							itemConfidence = 1;
						if (itemConfidence >= CONFIDENCE) {
							String rule = reasonStr + CON + resultStr;
							relationRules.put(rule, itemConfidence);
						}
					}
				}
			}
		}

		return relationRules;
	}

	/**
	 * ��osource���Ҧ��D�Ťl��
	 */
	private void buildSubSet(List<String> sourceSet, List<List<String>> result) {
		// �Ȧ��@�Ӥ����ɡA���j�פ�C���ɫD�Ťl���Ȭ���ۨ��A�ҥH�����K�[��result��
		if (sourceSet.size() == 1) {
			List<String> set = new ArrayList<String>();
			set.add(sourceSet.get(0));
			result.add(set);
		} else if (sourceSet.size() > 1) {
			// ��n�Ӥ����ɡA���j�D�X�en-1�Ӥl���A�b��result��
			buildSubSet(sourceSet.subList(0, sourceSet.size() - 1), result);
			int size = result.size();// �D�X����result�����סA�Ω�᭱���l�[��n�Ӥ����ɭp��
			// ���n�Ӥ����[�J�춰�X��
			List<String> single = new ArrayList<String>();
			single.add(sourceSet.get(sourceSet.size() - 1));
			result.add(single);
			// �b�O�d�e����n-1�l�������p�U�A���n�Ӥ������O�[��en�Ӥl�����A�ç�s�����[�J��result��;
			// ���O�d�즳n-1���l���A�ҥH�ݭn�����i��ƻs
			List<String> clone;
			for (int i = 0; i < size; i++) {
				clone = new ArrayList<String>();
				for (String str : result.get(i)) {
					clone.add(str);
				}
				clone.add(sourceSet.get(sourceSet.size() - 1));
				result.add(clone);
			}
		}
	}

	public static void main(String[] args) {
		DMnSNA_HW1_P76043011 apriori = new DMnSNA_HW1_P76043011();
		Scanner input = new Scanner(System.in);
		System.out.println("�п�J�̤p����ס]��p1000�^�G");
		SUPPORT = input.nextInt();
		System.out.println("�п�J�̤p�m�H�ס]��p0.8�^�G");
		CONFIDENCE = input.nextDouble();
		System.out.println("���b�B�z��Ƥ��A�еy���X����...");
		// ��l�ƥ���O��
		transList = apriori.buildData("input.txt");
		Map<String, Integer> frequentCollectionMap = apriori.getFC();
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter("result.txt");
			bw = new BufferedWriter(fw);
			bw.write("�̤p�����SUPPORT = " + SUPPORT);
			bw.newLine();
			bw.write("�̤p�m�H��CONFIDENCE = " + CONFIDENCE);
			bw.newLine();
			// System.out.println("----------------�W�c��" + "----------------");
			bw.write("----------------�W�c��" + "----------------");
			bw.newLine();
			Set<String> fcKeySet = frequentCollectionMap.keySet();
			for (String fcKey : fcKeySet) {
				// System.out.println(fcKey + " : " +
				// frequentCollectionMap.get(fcKey));
				bw.write(fcKey + "  :  " + frequentCollectionMap.get(fcKey));
				bw.newLine();
			}
			Map<String, Double> relationRulesMap = apriori.getRelationRules(frequentCollectionMap);
			// System.out.println("----------------���p�W�h" + "----------------");
			bw.write("----------------���p�W�h" + "----------------");
			bw.newLine();
			Set<String> rrKeySet = relationRulesMap.keySet();
			for (String rrKey : rrKeySet) {
				// System.out.println(rrKey + " : " +
				// relationRulesMap.get(rrKey));
				bw.write(rrKey + "  :  " + relationRulesMap.get(rrKey));
				bw.newLine();
			}
			if (bw != null)
				bw.close();
			System.out.println("��ƳB�z�����A���G�w�g�Jresult.txt���A�Ьd�\");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
