package DBSCAN;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class DBSCAN {
	private static String inputFile = "src/DBSCAN/data/0_random.txt";
	private static String inputFile2 = "src/DBSCAN/data/1_random.txt";
	private static String outputFile = "src/DBSCAN/0_result.txt";
	private static String outputFile2 = "src/DBSCAN/1_result.txt";
	private static List<Point> pointsList;
	private static List<List<Point>> resultList;
	private static double e;
	private static int minPts;

	// 從輸入資料中獲得點列表
	private static List<Point> getPointsList(String inputFile) throws IOException {
		List<Point> list = new ArrayList<Point>();
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String str = "";
		while ((str = br.readLine()) != null && str != "") {
			list.add(new Point(str));
		}
		br.close();
		return list;
	}

	// 獲得點和點的歐式距離
	private static double getDistance(Point p, Point q) {
		double dx = p.getX() - q.getX();
		double dy = p.getY() - q.getY();
		double distance = Math.sqrt(dx * dx + dy * dy);
		return distance;
	}

	// 判斷是否是核心點
	private static List<Point> isKeyPoint(List<Point> list, Point p, double e, int minPts) {
		int count = 0;
		List<Point> tempList = new ArrayList<Point>();
		tempList.add(p);
		for (Iterator<Point> it = list.iterator(); it.hasNext();) {
			Point q = it.next();
			if (getDistance(p, q) <= e) {
				count++;
				// System.out.print(q.print() + "\n");
				if (!tempList.contains(q)) {
					tempList.add(q);
				}
			}
		}
		if (count >= minPts) {
			p.setIsKey(true);
			// System.out.print(p.print() + " " + count + "\n");
			return tempList;
		}
		return null;
	}

	// 將核心點的鄰域設為已分類
	private static void setListClassed(List<Point> list) {
		for (Iterator<Point> it = list.iterator(); it.hasNext();) {
			Point p = it.next();
			if (!p.getIsClassed()) {
				p.setIsClassed(true);
			}
		}
	}

	// 擴充一個cluster類別
	private static boolean expandCluster(List<Point> a, List<Point> b) {
		boolean expand = false;
		// System.out.println(a.size() + " " + b.size() + "\n");
		if (a.contains(b.get(0))) {
			expand = true;
		}
		if (expand) {
			for (int index = 0; index < b.size(); index++) {
				if ((!a.contains(b.get(index))) && (!b.get(index).getIsClassed())) {
					a.add(b.get(index));
				}
			}
		}
		return expand;
	}

	// 如果是核心點則構造它的鄰域
	private static void applyDbscan(String inputFile) {
		try {
			pointsList = getPointsList(inputFile);
			for (Iterator<Point> it = pointsList.iterator(); it.hasNext();) {
				Point p = it.next();
				// if (!p.getIsClassed()) {
				List<Point> tempList = new ArrayList<Point>();
				if ((tempList = isKeyPoint(pointsList, p, e, minPts)) != null) {
					// setListClassed(tempList);
					resultList.add(tempList);
				}
				// }
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 為核心點構造新的cluster類別
	private static List<List<Point>> getResult() {
		int length = resultList.size();
		// System.out.println(length);
		for (int i = 0; i < length; i++) {
			for (Iterator<Point> it = resultList.get(i).iterator(); it.hasNext();) {
				Point p = it.next();
				if (p.getIsClassed()) {
					it.remove();
				}
			}
			if (!resultList.get(i).isEmpty()) {
				setListClassed(resultList.get(i));
				for (int j = i + 1; j < length; j++)
					if (!resultList.get(j).isEmpty()) {
						if (expandCluster(resultList.get(i), resultList.get(j))) {
							setListClassed(resultList.get(j));
							resultList.get(j).clear();
						}
					}
			}
		}
		return resultList;
	}

	// 將結果輸出在屏幕上
	private static void showResult() {
		int index = 1;
		int num = 0;
		// System.out.println("hello");
		Iterator<List<Point>> it = resultList.iterator();
		while (it.hasNext()) {
			List<Point> list = it.next();
			if (list.isEmpty()) {
				continue;
			}
			System.out.println("第" + index + "個聚類");
			// System.out.println(list.size());
			for (Iterator<Point> it1 = list.iterator(); it1.hasNext();) {
				Point p = it1.next();
				// System.out.println("why");
				p.setID(index);
				System.out.println(p.print());
				num++;
			}
			index++;
		}
		// System.out.println(num);
	}

	// 把結果寫入檔案中
	private static void writeintotxt(String outputFile) {
		FileWriter fw;
		BufferedWriter file_out;
		try {
			fw = new FileWriter(outputFile);
			file_out = new BufferedWriter(fw);
			Iterator<Point> it = pointsList.iterator();
			while (it.hasNext()) {
				Point p = it.next();
				file_out.write(p.print());
				file_out.newLine();
			}
			file_out.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		pointsList = new ArrayList<Point>();
		resultList = new ArrayList<List<Point>>();
		Scanner input = new Scanner(System.in);
		System.out.println("現在處理第一份資料：");
		System.out.println("請輸入半徑大小（如5）：");
		e = input.nextDouble();
		System.out.println("請輸入密度閾值（如8）：");
		minPts = input.nextInt();
		applyDbscan(inputFile);
		getResult();
		showResult();
		writeintotxt(outputFile);
		System.out.println("第一份資料處理完成，結果已寫入0_result.txt中，請查閱");
		pointsList = new ArrayList<Point>();
		resultList = new ArrayList<List<Point>>();
		System.out.println("現在處理第二份資料：");
		System.out.println("請輸入半徑大小（如4）：");
		e = input.nextDouble();
		System.out.println("請輸入密度閾值（如6）：");
		minPts = input.nextInt();
		applyDbscan(inputFile2);
		getResult();
		showResult();
		writeintotxt(outputFile2);
		System.out.println("第二份資料處理完成，結果已寫入1_result.txt中，請查閱");
	}
}
