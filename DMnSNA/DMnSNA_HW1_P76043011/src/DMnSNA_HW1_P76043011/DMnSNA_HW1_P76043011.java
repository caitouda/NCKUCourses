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
	private static int SUPPORT; // 支持度閾值
	private static double CONFIDENCE; // 置信度閾值
	private final static String ITEM_SPLIT = " "; // 項之間的分隔符號
	private final static String CON = "->"; // 項之間的分隔符號
	private static List<String> transList = new ArrayList<String>(); // 所有交易
	private static List<Set<String>> dataSet;

	/**
	 * 使用檔中的資料集
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
	 * 獲得頻繁集
	 */
	public Map<String, Integer> getFC() {
		Map<String, Integer> frequentCollectionMap = new HashMap<String, Integer>();// 所有的頻繁集
		frequentCollectionMap.putAll(getItem1FC());
		Map<String, Integer> itemkFcMap = new HashMap<String, Integer>();
		itemkFcMap.putAll(getItem1FC());
		while (itemkFcMap != null && itemkFcMap.size() != 0) {
			Map<String, Integer> candidateCollection = getCandidateCollection(itemkFcMap);
			Set<String> ccKeySet = candidateCollection.keySet();
			// 對候選集項進行累加計數
			for (String trans : transList) {
				for (String candidate : ccKeySet) {
					boolean flag = true;// 用來判斷交易中是否出現該候選項，如果出現，計數加1
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
			// 從候選集中找到符合支持度的頻繁集項
			itemkFcMap.clear();
			for (String candidate : ccKeySet) {
				Integer count = candidateCollection.get(candidate);
				if (count >= SUPPORT) {
					itemkFcMap.put(candidate, count);
				}
			}
			// 合併所有頻繁集
			frequentCollectionMap.putAll(itemkFcMap);
		}
		return frequentCollectionMap;
	}

	/**
	 * 獲得候選集
	 */
	private Map<String, Integer> getCandidateCollection(Map<String, Integer> itemkFcMap) {
		Map<String, Integer> candidateCollection = new HashMap<String, Integer>();
		Set<String> itemkSet1 = itemkFcMap.keySet();
		Set<String> itemkSet2 = itemkFcMap.keySet();
		for (String itemk1 : itemkSet1) {
			for (String itemk2 : itemkSet2) {
				// 進行連接
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
				// 進行剪枝
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
	 * 獲得1頻繁集
	 */
	private Map<String, Integer> getItem1FC() {
		Map<String, Integer> sItem1FcMap = new HashMap<String, Integer>();
		Map<String, Integer> rItem1FcMap = new HashMap<String, Integer>();// 頻繁1項集
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
	 * 獲得關聯規則
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
				buildSubSet(source, result);// 獲得source的所有非空子集
				for (List<String> itemList : result) {
					if (itemList.size() < source.size()) {// 只處理真子集
						List<String> otherList = new ArrayList<String>();
						for (String sourceItem : source) {
							if (!itemList.contains(sourceItem)) {
								otherList.add(sourceItem);
							}
						}
						String reasonStr = "";// 前置
						String resultStr = "";// 結果
						for (String item : itemList) {
							reasonStr = reasonStr + item + ITEM_SPLIT;
						}
						for (String item : otherList) {
							resultStr = resultStr + item + ITEM_SPLIT;
						}
						double countReason = frequentCollectionMap.get(reasonStr);
						double itemConfidence = countAll / countReason;// 計算置信度
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
	 * 獲得source的所有非空子集
	 */
	private void buildSubSet(List<String> sourceSet, List<List<String>> result) {
		// 僅有一個元素時，遞迴終止。此時非空子集僅為其自身，所以直接添加到result中
		if (sourceSet.size() == 1) {
			List<String> set = new ArrayList<String>();
			set.add(sourceSet.get(0));
			result.add(set);
		} else if (sourceSet.size() > 1) {
			// 當有n個元素時，遞迴求出前n-1個子集，在於result中
			buildSubSet(sourceSet.subList(0, sourceSet.size() - 1), result);
			int size = result.size();// 求出此時result的長度，用於後面的追加第n個元素時計數
			// 把第n個元素加入到集合中
			List<String> single = new ArrayList<String>();
			single.add(sourceSet.get(sourceSet.size() - 1));
			result.add(single);
			// 在保留前面的n-1子集的情況下，把第n個元素分別加到前n個子集中，並把新的集加入到result中;
			// 為保留原有n-1的子集，所以需要先對其進行複製
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
		System.out.println("請輸入最小支持度（比如1000）：");
		SUPPORT = input.nextInt();
		System.out.println("請輸入最小置信度（比如0.8）：");
		CONFIDENCE = input.nextDouble();
		System.out.println("正在處理資料中，請稍等幾分鐘...");
		// 初始化交易記錄
		transList = apriori.buildData("input.txt");
		Map<String, Integer> frequentCollectionMap = apriori.getFC();
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter("result.txt");
			bw = new BufferedWriter(fw);
			bw.write("最小支持度SUPPORT = " + SUPPORT);
			bw.newLine();
			bw.write("最小置信度CONFIDENCE = " + CONFIDENCE);
			bw.newLine();
			// System.out.println("----------------頻繁集" + "----------------");
			bw.write("----------------頻繁集" + "----------------");
			bw.newLine();
			Set<String> fcKeySet = frequentCollectionMap.keySet();
			for (String fcKey : fcKeySet) {
				// System.out.println(fcKey + " : " +
				// frequentCollectionMap.get(fcKey));
				bw.write(fcKey + "  :  " + frequentCollectionMap.get(fcKey));
				bw.newLine();
			}
			Map<String, Double> relationRulesMap = apriori.getRelationRules(frequentCollectionMap);
			// System.out.println("----------------關聯規則" + "----------------");
			bw.write("----------------關聯規則" + "----------------");
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
			System.out.println("資料處理完成，結果已寫入result.txt中，請查閱");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
