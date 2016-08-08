package DMnSNA_HW2_P76043011;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class DMnSNA_HW2_P76043011 {
	public static void main(String[] args) {
		AprioriAllCalculation ap = new AprioriAllCalculation();
		ap.aprioriAllProcess();
	}
}

class AprioriAllCalculation {
	Vector<Vector<Vector<String>>> data = null;
	Vector<String> itemList = null;
	Vector<Vector<String>> litemset = null;
	HashMap<Vector<String>, Integer> litemMaps = null;
	Vector<Vector<Vector<Vector<String>>>> transformedSequence = null;
	Vector<Vector<Vector<Integer>>> transformedMappedSequence = null;
	Vector<Vector<Integer>> seqItemset = null;
	Vector<Vector<Integer>> seqCandidates = new Vector<Vector<Integer>>();
	Vector<Integer> mappedItemList = null;
	Vector<Vector<Integer>> seqData = null;
	Vector<Vector<Integer>> maximalLargeSequence = null;
	Vector<Vector<Vector<String>>> resultSet = null;
	Vector<Vector<String>> candidates = new Vector<Vector<String>>(); // the
																		// current
																		// candidates
	String transaFile = "src/DMnSNA_HW2_P76043011/input.txt"; // transaction file
	String outputFile = "src/DMnSNA_HW2_P76043011/result.txt";// output file
	int numItems; // number of items per transaction
	int numTransactions = 0; // number of transactions
	double minSupRatio; // minimum support for a frequent itemset
	double minSupNumber;
	String itemSep = " "; // the separator value for items in the database
	private List<Set<String>> dataSet;
	FileWriter fw;
	BufferedWriter file_out;

	public void aprioriAllProcess() {
		initial("src/DMnSNA_HW2_P76043011/seqdata.dat");
		getConfig();
		System.out.println("...Sort Phase....");
		SortPhase();
		System.out.println("Phase 1 is completed");
		System.out.println("data : \n" + data);
		System.out.println("...Litem Phase....");
		LitemPhase();
		System.out.println("Phase 2 is completed");
		System.out.println("litemset : \n" + litemset);
		MapCreation();
		System.out.println("...Transformation Phase....");
		TransformationPhase();
		System.out.println("Phase 3 is completed");
		System.out.println("mapped sequence : \n" + transformedMappedSequence);
		System.out.println("...Sequence Phase....");
		SequencePhase();
		System.out.println("Phase 4 is completed");
		System.out.println("Sequence Item Set : \n" + seqItemset);
		System.out.println("...Maximal Phase....");
		MaximalPhase();
		System.out.println("Phase 5 is completed");
		System.out.println("Result is : \n" + resultSet);
		try {

			// create the output file
			fw = new FileWriter(outputFile);
			file_out = new BufferedWriter(fw);
			file_out.write("Result is : \n" + resultSet);
			file_out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void initial(String... fileName) {
		List<String> ddata = new ArrayList<String>();
		if (fileName.length != 0) {
			File file = new File(fileName[0]);
			// File file = new File("ddata.txt");
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while ((line = reader.readLine()) != null) {
					ddata.add(line);
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
		dSet = new TreeSet<String>();
		int num = 0;
		int ttime = 0;
		boolean start = true;
		boolean same = true;
		String sstr = "";
		BufferedWriter bw = null;
		try {
			FileWriter fw = new FileWriter("src/DMnSNA_HW2_P76043011/input.txt");
			bw = new BufferedWriter(fw);
			for (String d : ddata) {
				// if (d.startsWith(" ") && (!dSet.isEmpty())) {
				if (d.startsWith(" ") && sstr != "") {
					bw.write(sstr);
					bw.newLine();
					bw.newLine();
					dSet.add(sstr);
					numTransactions++;
					// System.out.println(num);
					dataSet.add(dSet);
					dSet = new TreeSet<String>();
					num = 0;
					ttime = 0;
					start = true;
					same = true;
					sstr = "";
				}
				String[] dArr = d.split("\\s+");
				for (String str : dArr) {
					// str.replaceAll(" ", "");{
					// System.out.println(str + "hello");
					num++;
					if (num >= 3) {
						if (num % 2 == 1) {
							if (start == true) {
								ttime = Integer.parseInt(str);
								start = false;
							} else {
								if (ttime == Integer.parseInt(str)) {
									same = true;
								} else {
									ttime = Integer.parseInt(str);
									same = false;
								}
							}
						} else {
							if (same == true) {
								if (sstr == "")
									sstr = str;
								else
									sstr = sstr + " " + str;
							} else {
								bw.write(sstr);
								bw.newLine();
								dSet.add(sstr);
								sstr = str;
							}
						}
					}
					// dSet.add(str);
				}
				// System.out.println(d);
			}
			bw.write(sstr);
			dSet.add(sstr);
			dataSet.add(dSet);
			numTransactions++;
			// System.out.println(numTransactions);
			if (bw != null)
				bw.close();
			System.out.println("資料初步整理完成，結果已寫入input.txt中，請查閱");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getConfig() {
		Scanner input = new Scanner(System.in);
		minSupRatio = input.nextDouble();
		// output config info to the user
		System.out.print("\nInput configuration: " + numItems + " items, " + numTransactions + " transactions, ");
		System.out.println("minsup = " + minSupRatio);
		System.out.println();
		minSupNumber = minSupRatio * numTransactions;
	}

	private void SortPhase() {
		data = new Vector<Vector<Vector<String>>>();
		itemList = new Vector<String>();
		FileInputStream file_in; // file input stream
		BufferedReader data_in; // data input stream
		StringTokenizer stFile;
		try {
			// load the transaction file
			file_in = new FileInputStream(transaFile);
			data_in = new BufferedReader(new InputStreamReader(file_in));
			int i = 0;
			while (i < numTransactions) {
				Vector<Vector<String>> sequence = new Vector<Vector<String>>();
				stFile = new StringTokenizer(data_in.readLine(), itemSep);
				while (true) {
					// stFile = new StringTokenizer(data_in.readLine(),
					// itemSep);
					if (stFile.countTokens() == 0) {
						break;
					} else {
						Vector<String> basket = new Vector<String>();
						while (stFile.hasMoreTokens()) {
							String item = stFile.nextToken();
							basket.add(item);
							if (!itemList.contains(item)) {
								itemList.add(item);
							}
						}
						sequence.add(basket);
					}
				}
				data.add(sequence);
				i++;
			}
		}
		// if error at all in this process, catch it and print the error messate
		catch (IOException e) {
			System.out.println(e);
		}
	}

	private void LitemPhase() {
		int itemsetNumber = 0; // the current itemset being looked at
		numItems = itemList.size();
		litemset = new Vector<Vector<String>>();
		System.out.println("Apriori algorithm has started for litem phase...\n");
		// while not complete
		do {
			// increase the itemset that is being looked at
			itemsetNumber++;
			// generate the candidates
			generateCandidates(itemsetNumber);
			// determine and display frequent itemsets
			calculateFrequentItemsets(itemsetNumber);
			if (candidates.size() != 0) {
				System.out.println("Frequent " + itemsetNumber + "-itemsets");
				System.out.println(candidates);
			}
			litemset.addAll(candidates);
			// if there are <=1 frequent items, then its the end. This prevents
			// reading through the database again. When there is only one
			// frequent itemset.
		} while (candidates.size() > 1);
	}

	private void MapCreation() {
		litemMaps = new HashMap<Vector<String>, Integer>();
		for (int i = 1; i < litemset.size() + 1; i++) {
			litemMaps.put(litemset.get(i - 1), i);
		}
	}

	private void TransformationPhase() {
		transformedMappedSequence = new Vector<Vector<Vector<Integer>>>();
		transformedSequence = new Vector<Vector<Vector<Vector<String>>>>();
		seqData = new Vector<Vector<Integer>>();
		for (int i = 0; i < data.size(); i++) {
			int count = 0;
			transformedSequence.add(new Vector<Vector<Vector<String>>>());
			transformedMappedSequence.add(new Vector<Vector<Integer>>());
			seqData.add(new Vector<Integer>());
			for (int j = 0; j < data.get(i).size(); j++) {
				transformedSequence.get(i).add(new Vector<Vector<String>>());
				transformedMappedSequence.get(i).add(new Vector<Integer>());

				for (int k = 0; k < litemset.size(); k++) {
					if (data.get(i).get(j).containsAll(litemset.get(k))) {
						transformedSequence.get(i).get(j - count).add(litemset.get(k));
						transformedMappedSequence.get(i).get(j - count).add(litemMaps.get(litemset.get(k)));
						seqData.get(i).add(litemMaps.get(litemset.get(k)));
					}
				}
				if (transformedSequence.get(i).get(j - count).isEmpty()) {
					transformedSequence.get(i).remove(j - count);
					transformedMappedSequence.get(i).remove(j - count);
					count++;
				}
			}
		}
	}

	public void SequencePhase() {
		int itemsetNumber = 0;
		seqItemset = new Vector<Vector<Integer>>();
		System.out.println("Apriori algorithm has started for Sequence Phase");
		do {
			itemsetNumber++;
			generateSeqCandidates(itemsetNumber);
			calculateSeqFrequentItemsets(itemsetNumber);
			if (seqCandidates.size() != 0) {
				System.out.println("Frequent " + itemsetNumber + "-itemsets");
				System.out.println(seqCandidates);
			}
			seqItemset.addAll(seqCandidates);
			// if there are <=1 frequent items, then its the end. This prevents
			// reading through the database again. When there is only one
			// frequent itemset.
		} while (seqCandidates.size() > 1);

	}

	private void generateCandidates(int n) {

		Vector<Vector<String>> tempCandidates = new Vector<Vector<String>>(); // temporary
																				// candidate
																				// string
																				// vector
		Vector<String> tempElementVec;
		Vector<String> tempElementVec2;
		// if its the first set, candidates are just the numbers
		if (n == 1) {
			for (int i = 0; i < numItems; i++) {
				tempElementVec = new Vector<String>();
				tempElementVec.add(itemList.elementAt(i));
				tempCandidates.add(tempElementVec);
			}
		} else if (n == 2) // second itemset is just all combinations of itemset
							// 1
		{
			// add each itemset from the previous frequent itemsets together
			for (int i = 0; i < candidates.size(); i++)
				for (int j = i + 1; j < candidates.size(); j++) {
					tempElementVec = new Vector<String>();
					tempElementVec.add(candidates.get(i).get(0));
					tempElementVec.add(candidates.get(j).get(0));
					tempCandidates.add(tempElementVec);
				}

		} else {
			// for each itemset
			for (int i = 0; i < candidates.size(); i++) {
				// compare to the next itemset
				for (int j = i + 1; j < candidates.size(); j++) {
					tempElementVec = new Vector<String>();
					tempElementVec2 = new Vector<String>();
					for (int s = 0; s < n - 2; s++) {
						tempElementVec.add(candidates.get(i).get(s));
						tempElementVec2.add(candidates.get(j).get(s));
					}
					if (tempElementVec.equals(tempElementVec2)) {
						tempElementVec.add(candidates.get(i).get(n - 2));
						tempElementVec.add(candidates.get(j).get(n - 2));
						tempCandidates.add(tempElementVec);
						// ((str1 + " " + st1.nextToken() + " " +
						// st2.nextToken()).trim());
					}
				}
			}
		}
		// clear the old candidates
		candidates.clear();
		// set the new ones
		candidates = new Vector<Vector<String>>(tempCandidates);
		tempCandidates.clear();
	}

	private void calculateFrequentItemsets(int n) {
		Vector<Vector<String>> TempCandidates = new Vector<Vector<String>>();
		Boolean[] flags;
		int count;
		for (Vector<String> cand : candidates) {
			flags = new Boolean[data.size()];
			//System.out.println(data.size());
			count = 0;
			for (Vector<Vector<String>> customer : data) {
				int a = data.indexOf(customer);
				for (Vector<String> basket : customer) {
					if (basket.containsAll(cand)) {
						flags[a] = true;
						count++;
						break;
					}
				}
				if (flags[a] == null) {
					// System.out.println("false");
					flags[a] = false;
				}
			}
			/*
			 * int nnum = 0; for (Boolean flag : flags) { nnum++;
			 * System.out.println(nnum); if (flag) { System.out.println(count);
			 * count++; } else System.out.println("why"); }
			 */
			// Scanner input = new Scanner(System.in);
			// input.nextInt();
			//System.out.println("count:" + count);
			if (count >= minSupNumber)
				TempCandidates.add(cand);
		}
		// clear old candidates
		candidates.clear();
		// new candidates are the old frequent candidates
		candidates = new Vector<Vector<String>>(TempCandidates);
		TempCandidates.clear();
	}

	private void generateSeqCandidates(int n) {
		Vector<Vector<Integer>> tempCandidates = new Vector<Vector<Integer>>(); // temporary
																				// candidate
																				// string
																				// vector
		Vector<Integer> tempElementVec;
		Vector<Integer> tempElementVec2;
		// if its the first set, candidates are just the numbers
		if (n == 1) {
			for (int i = 1; i <= litemMaps.size(); i++) {
				tempElementVec = new Vector<Integer>();
				tempElementVec.add(i);
				tempCandidates.add(tempElementVec);
			}
		} else if (n == 2) // second itemset is just all combinations of itemset
							// 1
		{
			// add each itemset from the previous frequent itemsets together
			for (int i = 0; i < seqCandidates.size(); i++)
				for (int j = i + 1; j < seqCandidates.size(); j++) {
					tempElementVec = new Vector<Integer>();
					tempElementVec.add(seqCandidates.get(i).get(0));
					tempElementVec.add(seqCandidates.get(j).get(0));
					tempCandidates.add(tempElementVec);
				}
		} else {
			// for each itemset
			for (int i = 0; i < seqCandidates.size(); i++) {
				// compare to the next itemset
				for (int j = i + 1; j < seqCandidates.size(); j++) {
					tempElementVec = new Vector<Integer>();
					tempElementVec2 = new Vector<Integer>();
					for (int s = 0; s < n - 2; s++) {
						tempElementVec.add(seqCandidates.get(i).get(s));
						tempElementVec2.add(seqCandidates.get(j).get(s));
					}
					if (tempElementVec.equals(tempElementVec2)) {
						tempElementVec.add(seqCandidates.get(i).get(n - 2));
						tempElementVec.add(seqCandidates.get(j).get(n - 2));
						tempCandidates.add(tempElementVec);
					}
				}
			}
		}
		// clear the old candidates
		seqCandidates.clear();
		// set the new ones
		seqCandidates = new Vector<Vector<Integer>>(tempCandidates);
		tempCandidates.clear();
	}

	private void calculateSeqFrequentItemsets(int n) {
		Vector<Vector<Integer>> TempCandidates = new Vector<Vector<Integer>>();
		Boolean[] flags;
		int count;
		for (Vector<Integer> can : seqCandidates) {
			flags = new Boolean[seqData.size()];
			for (int i = 0; i < seqData.size(); i++) {
				Vector<Integer> cand = new Vector<Integer>(can);
				Vector<Integer> customer = new Vector<Integer>(seqData.get(i));
				while (cand.size() > 0 && customer.size() > 0) {
					if (cand.get(0).equals(customer.get(0))) {
						cand.remove(0);
					} else {
						customer.remove(0);
					}
				}
				if (cand.size() == 0)
					flags[i] = true;
				else
					flags[i] = false;
			}
			count = 0;
			for (Boolean flag : flags)
				if (flag)
					count++;
			if (count >= minSupNumber)
				TempCandidates.add(can);
		}
		// clear old candidates
		seqCandidates.clear();
		// new candidates are the old frequent candidates
		seqCandidates = new Vector<Vector<Integer>>(TempCandidates);
		TempCandidates.clear();
	}

	private void MaximalPhase() {
		maximalLargeSequence = new Vector<Vector<Integer>>();
		Vector<Vector<Integer>> tempSequenceSet = new Vector<Vector<Integer>>(seqItemset);
		for (Vector<Integer> seqs : seqItemset) {
			int check = 0;
			tempSequenceSet.remove(seqs);
			for (Vector<Integer> checkSeq : tempSequenceSet) {
				if (checkSeq.containsAll(seqs)) {
					check = 1;
				}
			}
			if (check == 0) {
				maximalLargeSequence.add(seqs);
			}
		}
		resultSet = new Vector<Vector<Vector<String>>>();
		for (Vector<Integer> seq : maximalLargeSequence) {
			resultSet.add(new Vector<Vector<String>>());
			for (int i = 0; i < seq.size(); i++) {
				for (Entry<Vector<String>, Integer> ent : litemMaps.entrySet()) {
					if (ent.getValue() == seq.get(i)) {
						resultSet.get(maximalLargeSequence.indexOf(seq)).add(ent.getKey());
					}
				}
			}
		}
	}

	public static String getInput() {
		String input = "";
		// read from System.in
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// try to get users input, if there is an error print the message
		try {
			input = reader.readLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		return input;
	}

}