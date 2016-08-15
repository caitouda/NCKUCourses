package com.hcy.nns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

import com.hcy.nns.Rectangle;
import com.hcy.nns.Point;

public class NNS {
	private static List<Point> dataSet;
	private double left = 50000000;
	private double right = 0;
	private double down = 50000000;
	private double up = 0;
	private TreeMap<String, Rectangle> map;
	private Queue<String> q;
	private List<Point> candidate;

	/**
	 * 使用檔中的資料集
	 */
	private List<String> buildData(String... fileName) {
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
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("fail to find target file !");
		}
		dataSet = new ArrayList<Point>();
		map = new TreeMap<String, Rectangle>();
		q = new ArrayDeque<String>();
		candidate = new ArrayList<Point>();
		Point dSet;
		double x;
		double y;
		for (String d : data) {
			dSet = new Point();
			String[] dArr = d.split(" ");
			x = Double.parseDouble(dArr[0]);
			y = Double.parseDouble(dArr[1]);
			dSet.setX(x);
			dSet.setY(y);
			dataSet.add(dSet);
			if (left > x) {
				left = x;
			}
			if (right < x) {
				right = x;
			}
			if (down > y) {
				down = y;
			}
			if (up < y) {
				up = y;
			}
			// System.out.println("1");
		}
		return data;
	}

	private void split(Rectangle r, List<Point> rPointSet) {
		Rectangle r0 = new Rectangle();
		Rectangle r1 = new Rectangle();
		r0.setParentRID(r.getRectangleID());
		r1.setParentRID(r.getRectangleID());
		String rTemp;
		rTemp = r.getRectangleID();
		rTemp += "0";
		r0.setRectangleID(rTemp);
		r.setChildR0ID(rTemp);
		rTemp = r.getRectangleID();
		rTemp += "1";
		r1.setRectangleID(rTemp);
		r.setChildR1ID(rTemp);
		if ((r.getX2() - r.getX1()) >= (r.getY2() - r.getY1())) {
			r.setDirection(0);
			double xMiddle = (r.getX1() + r.getX2()) / 2;
			r.setMiddle(xMiddle);
			r0.setX1(r.getX1());
			r0.setX2(xMiddle);
			r0.setY1(r.getY1());
			r0.setY2(r.getY2());
			r1.setX1(xMiddle);
			r1.setX2(r.getX2());
			r1.setY1(r.getY1());
			r1.setY2(r.getY2());
			String pTemp;
			for (int i = rPointSet.size() - 1; i >= 0; i--) {
				Point p = rPointSet.get(i);
				pTemp = p.getRectangleID();
				if (p.getX() <= xMiddle) {
					pTemp += "0";
					p.setRectangleID(pTemp);
					r0.addRPoint(p);
				} else {
					pTemp += "1";
					p.setRectangleID(pTemp);
					r1.addRPoint(p);
				}
			}
		} else {
			r.setDirection(1);
			double yMiddle = (r.getY1() + r.getY2()) / 2;
			r.setMiddle(yMiddle);
			r0.setX1(r.getX1());
			r0.setX2(r.getX2());
			r0.setY1(r.getY1());
			r0.setY2(yMiddle);
			r1.setX1(r.getX1());
			r1.setX2(r.getX2());
			r1.setY1(yMiddle);
			r1.setY2(r.getY2());
			String pTemp;
			for (int i = rPointSet.size() - 1; i >= 0; i--) {
				Point p = rPointSet.get(i);
				pTemp = p.getRectangleID();
				if (p.getY() <= yMiddle) {
					pTemp += "0";
					p.setRectangleID(pTemp);
					r0.addRPoint(p);
				} else {
					pTemp += "1";
					p.setRectangleID(pTemp);
					r1.addRPoint(p);
				}
			}
		}
		map.put(String.valueOf(r0.getRectangleID()), r0);
		map.put(String.valueOf(r1.getRectangleID()), r1);
		q.add(r0.getRectangleID());
		q.add(r1.getRectangleID());
	}

	private String firstSearch(Rectangle r, double x, double y, double d) {
		if (r.getDirection() == -1) {
			return r.getRectangleID();
		} else if (r.getDirection() == 0) {
			if (x < r.getMiddle()) {
				return firstSearch(map.get(r.getChildR0ID()), x, y, d);
			} else {
				return firstSearch(map.get(r.getChildR1ID()), x, y, d);
			}
		} else {
			if (y < r.getMiddle()) {
				return firstSearch(map.get(r.getChildR0ID()), x, y, d);
			} else {
				return firstSearch(map.get(r.getChildR1ID()), x, y, d);
			}
		}
	}

	private void secondSearch(Rectangle r, double x, double y, double d) {
		if (distance(r, x, y, d) <= d) {
			if (r.getDirection() == -1) {
				for (Point p : r.getRPointSet()) {
					candidate.add(p);
				}
			} else {
				if (distance(map.get(r.getChildR0ID()), x, y, d) <= d) {
					secondSearch(map.get(r.getChildR0ID()), x, y, d);
				}
				if (distance(map.get(r.getChildR1ID()), x, y, d) <= d) {
					secondSearch(map.get(r.getChildR1ID()), x, y, d);
				}
			}
		}
	}

	private double distance(Rectangle r, double x, double y, double d) {
		double dis;
		if (x <= r.getX1()) {
			if (y <= r.getY1()) {
				dis = Math.sqrt((r.getX1() - x) * (r.getX1() - x) + (r.getY1() - y) * (r.getY1() - y));
			} else if (y <= r.getX2()) {
				dis = r.getX1() - x;
			} else {
				dis = Math.sqrt((r.getX1() - x) * (r.getX1() - x) + (r.getY2() - y) * (r.getY2() - y));
			}
		} else if (x <= r.getX2()) {
			if (y <= r.getY1()) {
				dis = r.getY1() - y;
			} else if (y <= r.getX2()) {
				dis = 0;
			} else {
				dis = y - r.getY2();
			}
		} else {
			if (y <= r.getY1()) {
				dis = Math.sqrt((r.getX2() - x) * (r.getX2() - x) + (r.getY1() - y) * (r.getY1() - y));
			} else if (y <= r.getX2()) {
				dis = x - r.getX2();
			} else {
				dis = Math.sqrt((r.getX2() - x) * (r.getX2() - x) + (r.getY2() - y) * (r.getY2() - y));
			}
		}
		return dis;
	}

	private Point betterResult(List<Point> rPointSet, double x, double y, double d) {
		Point result = new Point();
		result.setX(-50000000);
		result.setY(-50000000);
		double mDis = Math.sqrt((result.getX() - x) * (result.getX() - x) + (result.getY() - y) * (result.getY() - y));
		for (int i = rPointSet.size() - 1; i >= 0; i--) {
			Point p = rPointSet.get(i);
			double dis = Math.sqrt((p.getX() - x) * (p.getX() - x) + (p.getY() - y) * (p.getY() - y));
			if (dis < mDis) {
				mDis = dis;
				result = p;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		NNS nns = new NNS();
		nns.buildData("kd-test-big-input.txt");
		Rectangle r0 = new Rectangle();
		r0.setRectangleID("0");
		r0.setX1(nns.left);
		r0.setX2(nns.right);
		r0.setY1(nns.down);
		r0.setY2(nns.up);
		r0.setRPointSet(dataSet);
		nns.map.put(String.valueOf(r0.getRectangleID()), r0);
		nns.q.add(r0.getRectangleID());
		while (!nns.q.isEmpty()) {
			String rTemp = nns.q.poll();
			if (nns.map.get(String.valueOf(rTemp)).getRPointSet().size() > 1) {
				nns.split(nns.map.get(String.valueOf(rTemp)), nns.map.get(String.valueOf(rTemp)).getRPointSet());
			}
			// System.out.println(nns.deleteNumber());
		}
		// System.out.println(nns.map.size());
		/*
		 * Iterator<Entry<String, Rectangle>> iterator =
		 * nns.map.entrySet().iterator(); while (iterator.hasNext()) {
		 * Map.Entry<String, Rectangle> mapEntry = (Map.Entry<String,
		 * Rectangle>) iterator.next(); System.out.println("The key is: " +
		 * mapEntry.getValue().getRectangleID() + ", value is :" +
		 * mapEntry.getValue().getRPointSet().size()); }
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("請輸入d值：");
		String dS = sc.nextLine();
		double d = Double.parseDouble(dS);
		System.out.println("請輸入目標點t的橫坐標：");
		String xS = sc.nextLine();
		double x = Double.parseDouble(xS);
		System.out.println("請輸入目標點t的縱坐標：");
		String yS = sc.nextLine();
		double y = Double.parseDouble(yS);
		sc.close();
		String resultS = nns.firstSearch(r0, x, y, d);
		Point result;
		/*
		 * for (Point p : nns.map.get(resultS).getRPointSet()) {
		 * System.out.println(p.getX() + "-" + p.getY()); }
		 */
		if (nns.map.get(resultS).getRPointSet().isEmpty()) {
			// System.out.println("No");
			nns.secondSearch(r0, x, y, d);
		} else {
			// System.out.println("Yes");
			result = nns.betterResult(nns.map.get(resultS).getRPointSet(), x, y, d);
			double mDis = Math
					.sqrt((result.getX() - x) * (result.getX() - x) + (result.getY() - y) * (result.getY() - y));
			if (mDis <= d) {
				nns.secondSearch(r0, x, y, mDis);
				// System.out.println("YesYes");
			} else {
				nns.secondSearch(r0, x, y, d);
				System.out.println("YesNo");
			}
		}
		// System.out.println(nns.candidate.size());
		/*
		 * for (Point p : nns.candidate) { System.out.println(p.getX() + "-" +
		 * p.getY()); }
		 */
		result = nns.betterResult(nns.candidate, x, y, d);
		double mDis = Math.sqrt((result.getX() - x) * (result.getX() - x) + (result.getY() - y) * (result.getY() - y));
		if (mDis > d) {
			System.out.println("沒有答案");
		} else {
			System.out.println((long) (result.getX()) + "-" + (long) (result.getY()));
		}
		long endTime = System.currentTimeMillis();
		long totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime);
	}
}
