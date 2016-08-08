package Decision_Tree;

import java.io.*;
import java.util.*;

public class DataTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("boxing")
	public static void main(String[] args) {
		/**
		 * 導入訓練集和測試集
		 */
		Object[] trainingSet = new Object[491];
		Object[] testSet = new Object[211];
		String trainingFile = "data/training.txt";
		String testFile = "data/test.txt";
		test(trainingSet, testSet, trainingFile, testFile);
	}

	public static void test(Object[] array, Object[] array2, String filename1, String filename2) {
		System.out.println("訓練集樣本檔： " + filename1);
		System.out.println("測試集樣本檔： " + filename2);
		/**
		 * 記錄正確的結果
		 */
		String[] right = new String[array2.length];
		try {
			/**
			 * 讀取訓練集
			 */
			File file = new File(filename1);
			Scanner scan = new Scanner(file);
			int cntNum = 0;
			while (scan.hasNextLine()) {
				// String[] cntLine = new String[] { scan.next().trim(),
				// scan.next().trim(), scan.next().trim(),
				// scan.next().trim(), scan.next().trim(), scan.next().trim(),
				// scan.next().trim() };
				String cntLine2 = scan.nextLine();
				String[] cntLine = new String[5];
				if (cntLine2.contains("0 ")) {
					// System.out.println("最後出現0");
					cntLine[0] = cntLine2.substring(cntLine2.indexOf("0 ") + 2, cntLine2.indexOf("0 ") + 3);
					// System.out.println(cntLine[0]);
				} else
					cntLine[0] = "M";
				// cntLine[0] = "S";
				if (cntLine2.contains("1 ")) {
					// System.out.println("最後出現1");
					cntLine[1] = cntLine2.substring(cntLine2.indexOf("1 ") + 2, cntLine2.indexOf("1 ") + 3);
					// System.out.println(cntLine[1]);
				} else
					cntLine[1] = "1";
				// cntLine[1] = "0";
				if (cntLine2.contains("2 ")) {
					// System.out.println("最後出現2");
					cntLine[4] = cntLine2.substring(cntLine2.indexOf("2 ") + 2, cntLine2.indexOf("2 ") + 3);
					// System.out.println(cntLine[4]);
				} else
					cntLine[4] = "B";
				if (cntLine2.contains("3 ")) {
					// System.out.println("最後出現3");
					cntLine[3] = cntLine2.substring(cntLine2.indexOf("3 ") + 2, cntLine2.indexOf("3 ") + 4);
					// System.out.println(cntLine[3]);
					cntLine[3] = cntLine[3].substring(0, 1) + "0";
				} else
					cntLine[3] = "50";
				if (cntLine2.contains("4 ")) {
					// System.out.println("最後出現4");
					cntLine[2] = cntLine2.substring(cntLine2.indexOf("4 ") + 2, cntLine2.indexOf("}"));
					// System.out.println(cntLine[2]);
				} else
					cntLine[2] = "40000";
				System.out.println(cntLine[0] + cntLine[1] + cntLine[2] + cntLine[3] + cntLine[4]);
				array[cntNum] = cntLine;
				cntNum++;
			}
			System.out.println(cntNum);
			scan.close();
			// Scanner input = new Scanner(System.in);
			// int test = input.nextInt();

			/**
			 * 讀取測試集
			 */
			File file2 = new File(filename2);
			Scanner scan2 = new Scanner(file2);
			cntNum = 0;
			while (scan2.hasNextLine()) {
				// String[] cntLine = new String[] { scan2.next().trim(),
				// scan2.next().trim(), scan2.next().trim(),
				// scan2.next().trim(), scan2.next().trim(), scan2.next().trim()
				// };
				String cntLine2 = scan2.nextLine();
				String[] cntLine = new String[5];
				if (cntLine2.contains("0 ")) {
					// System.out.println("最後出現0");
					cntLine[0] = cntLine2.substring(cntLine2.indexOf("0 ") + 2, cntLine2.indexOf("0 ") + 3);
					// System.out.println(cntLine[0]);
				} else
					cntLine[0] = "M";
				if (cntLine2.contains("1 ")) {
					// System.out.println("最後出現1");
					cntLine[1] = cntLine2.substring(cntLine2.indexOf("1 ") + 2, cntLine2.indexOf("1 ") + 3);
					// System.out.println(cntLine[1]);
				} else
					cntLine[1] = "1";
				if (cntLine2.contains("2 ")) {
					// System.out.println("最後出現2");
					// cntLine[4] = cntLine2.substring(cntLine2.indexOf("2 ") +
					// 2, cntLine2.indexOf("2 ") + 3);
					right[cntNum] = cntLine2.substring(cntLine2.indexOf("2 ") + 2, cntLine2.indexOf("2 ") + 3);
					;
					// System.out.println(right[cntNum]);
				} else
					// cntLine[4] = "B";
					right[cntNum] = "B";
				if (cntLine2.contains("3 ")) {
					// System.out.println("最後出現3");
					cntLine[3] = cntLine2.substring(cntLine2.indexOf("3 ") + 2, cntLine2.indexOf("3 ") + 4);
					// System.out.println(cntLine[3]);
					cntLine[3] = cntLine[3].substring(0, 1) + "0";
				} else
					cntLine[3] = "50";
				if (cntLine2.contains("4 ")) {
					// System.out.println("最後出現4");
					cntLine[2] = cntLine2.substring(cntLine2.indexOf("4 ") + 2, cntLine2.indexOf("}"));
					// System.out.println(cntLine[2]);
				} else
					cntLine[2] = "40000";
				System.out.println(cntLine[0] + cntLine[1] + cntLine[2] + cntLine[3]);
				array2[cntNum] = cntLine;
				cntNum++;
			}
			System.out.println(cntNum);
			scan2.close();
		} catch (IOException ex) {
			System.out.println("IOException occur in read in data!");
			System.exit(0);
		}

		System.out.println("=============PRINT DECISION TREE=============");
		DTree tree = new DTree();
		tree.create(array, 4);
		System.out.println("===============END PRINT TREE===============");
		System.out.println("");
		System.out.println("===============DECISION RESULT===============");
		try {
			File file = new File("data/test.txt");
			Scanner scan2 = new Scanner(file);
			FileWriter fw = new FileWriter("result.txt");
			BufferedWriter file_out = new BufferedWriter(fw);
			// int i=-1;
			// while (scan2.hasNextLine()) {
			for (int i = 0; i < array2.length; i++) {
				// i++;
				String cntLine2 = scan2.nextLine();
				file_out.write(cntLine2 + " member_card = ");
				tree.compare((String[]) array2[i], tree.root, file_out);
				System.out.println("  member_card = " + right[i]);
				/*
				 * if (result.startsWith("B")) result = "Basic"; else if
				 * (result.startsWith("N")) result = "Normal"; else if
				 * (result.startsWith("S")) result = "Silver"; else result =
				 * "Gold";
				 */
				file_out.newLine();
			}
			scan2.close();
			file_out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("===============END PRINT RESULT===============\n\n\n");
	}

}
