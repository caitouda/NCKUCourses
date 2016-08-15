package com.hcy.bbs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;

public class BBS {
	private static List<Point> dataSet;
	private double left = 50000000;
	private double right = 0;
	private double down = 50000000;
	private double up = 0;
	private TreeMap<String, Rectangle> map;
	private Queue<Long> q;

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
		q = new ArrayDeque<Long>();
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
		Rectangle r2 = new Rectangle();
		Rectangle r3 = new Rectangle();
		double xMiddle = (r.getX1() + r.getX2()) / 2;
		double yMiddle = (r.getY1() + r.getY2()) / 2;
		r0.setX1(r.getX1());
		r0.setX2(xMiddle);
		r0.setY1(r.getY1());
		r0.setY2(yMiddle);
		r1.setX1(r.getX1());
		r1.setX2(xMiddle);
		r1.setY1(yMiddle);
		r1.setY2(r.getY2());
		r2.setX1(xMiddle);
		r2.setX2(r.getX2());
		r2.setY1(r.getY1());
		r2.setY2(yMiddle);
		r3.setX1(xMiddle);
		r3.setX2(r.getX2());
		r3.setY1(yMiddle);
		r3.setY2(r.getY2());
		r0.setParentRID(r.getRectangleID());
		r1.setParentRID(r.getRectangleID());
		r2.setParentRID(r.getRectangleID());
		r3.setParentRID(r.getRectangleID());
		String rTemp;
		rTemp = String.valueOf(r.getRectangleID());
		rTemp += "0";
		r0.setRectangleID(Long.parseLong(rTemp));
		r.setChildR0ID(Long.parseLong(rTemp));
		rTemp = String.valueOf(r.getRectangleID());
		rTemp += "1";
		r1.setRectangleID(Long.parseLong(rTemp));
		r.setChildR1ID(Long.parseLong(rTemp));
		rTemp = String.valueOf(r.getRectangleID());
		rTemp += "2";
		r2.setRectangleID(Long.parseLong(rTemp));
		r.setChildR2ID(Long.parseLong(rTemp));
		rTemp = String.valueOf(r.getRectangleID());
		rTemp += "3";
		r3.setRectangleID(Long.parseLong(rTemp));
		r.setChildR3ID(Long.parseLong(rTemp));
		String pTemp;
		for (int i = rPointSet.size() - 1; i >= 0; i--) {
			Point p = rPointSet.get(i);
			if (p.getX() < xMiddle) {
				if (p.getY() < yMiddle) {
					// p.setCount(p.getCount() + 1);
					pTemp = String.valueOf(p.getRectangleID());
					pTemp += "0";
					p.setRectangleID(Long.parseLong(pTemp));
					r0.addRPoint(p);
				} else {
					pTemp = String.valueOf(p.getRectangleID());
					pTemp += "1";
					p.setRectangleID(Long.parseLong(pTemp));
					r1.addRPoint(p);
				}
			} else {
				if (p.getY() < yMiddle) {
					pTemp = String.valueOf(p.getRectangleID());
					pTemp += "2";
					p.setRectangleID(Long.parseLong(pTemp));
					r2.addRPoint(p);
				} else {
					pTemp = String.valueOf(p.getRectangleID());
					pTemp += "3";
					p.setRectangleID(Long.parseLong(pTemp));
					r3.addRPoint(p);
					// dataSet.remove(i);
				}
			}
		}
		map.put(String.valueOf(r0.getRectangleID()), r0);
		map.put(String.valueOf(r1.getRectangleID()), r1);
		map.put(String.valueOf(r2.getRectangleID()), r2);
		q.add(r0.getRectangleID());
		q.add(r1.getRectangleID());
		q.add(r2.getRectangleID());
		if (!r0.getRPointSet().isEmpty()) {
			r3.setCandidate(-1);
			for (int i = rPointSet.size() - 1; i >= 0; i--) {
				Point p = rPointSet.get(i);
				if (p.getX() >= xMiddle && p.getY() >= yMiddle) {
					p.setCandidate(-1);
					// r3.getRPointSet().remove(i);
					rPointSet.remove(i);
				}
			}
		} else {
			q.add(r3.getRectangleID());
			map.put(String.valueOf(r3.getRectangleID()), r3);
		}
		// System.out.println("Why");
	}

	/*
	 * private int deleteNumber() { int count = 0; for (int i = dataSet.size() -
	 * 1; i >= 0; i--) { Point p = dataSet.get(i); if (p.getCandidate() == -1) {
	 * count++; } } return count; }
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		BBS bbs = new BBS();
		bbs.buildData("bbs-test-big-input.txt");
		Rectangle r0 = new Rectangle();
		r0.setRectangleID(1);
		r0.setX1(bbs.left);
		r0.setX2(bbs.right);
		r0.setY1(bbs.down);
		r0.setY2(bbs.up);
		r0.setRPointSet(dataSet);
		bbs.map.put(String.valueOf(r0.getRectangleID()), r0);
		bbs.q.add(r0.getRectangleID());
		// System.out.println(r0.getRPointSet().size());

		while (!bbs.q.isEmpty()) {
			long rTemp = bbs.q.poll();
			if (bbs.map.get(String.valueOf(rTemp)).getCandidate() != -1
					&& bbs.map.get(String.valueOf(rTemp)).getRPointSet().size() >= 4) {
				bbs.split(bbs.map.get(String.valueOf(rTemp)), bbs.map.get(String.valueOf(rTemp)).getRPointSet());
			}
			// System.out.println(bbs.deleteNumber());
		}

		// System.out.println(bbs.deleteNumber());
		// System.out.println(bbs.map.size());

		long minR = 1333333333;
		String minRK = "";
		Iterator<Entry<String, Rectangle>> iterator = bbs.map.entrySet().iterator();
		while (iterator.hasNext()) {
			// System.out.println("Yes");
			Map.Entry<String, Rectangle> mapEntry = (Map.Entry<String, Rectangle>) iterator.next();
			if (String.valueOf(mapEntry.getValue().getRectangleID()).length() < 10) {
				int length = 10 - String.valueOf(mapEntry.getValue().getRectangleID()).length();
				String s = "";
				for (int i = 0; i < length; i++) {
					s = s + "4";
				}
				mapEntry.getValue()
						.setRectangleID(Long.parseLong(String.valueOf(mapEntry.getValue().getRectangleID()) + s));
			}
			if (mapEntry.getValue().getRectangleID() < minR) {
				minR = mapEntry.getValue().getRectangleID();
				minRK = mapEntry.getKey();
			}
			// System.out.println("The key is: " +
			// mapEntry.getValue().getRectangleID() + ", value is :" +
			// mapEntry.getValue());
		}

		// System.out.println(minR);
		// System.out.println(minRK);

		for (int i = bbs.map.get(minRK).getRPointSet().size() - 1; i >= 0; i--) {
			Point p1 = bbs.map.get(minRK).getRPointSet().get(i);
			// System.out.println(p1.getX() + "-" + p1.getY());
			for (int j = bbs.map.get(minRK).getRPointSet().size() - 1; j >= 0; j--) {
				Point p2 = bbs.map.get(minRK).getRPointSet().get(j);
				if (p1.getX() < p2.getX()) {
					if (p1.getY() <= p2.getY()) {
						p2.setCandidate(-1);
					}
				} else if (p1.getX() == p2.getX()) {
					if (p1.getY() < p2.getY()) {
						p2.setCandidate(-1);
						/*
						 * if (p2.getX() == 260795) {
						 * System.out.println(p1.getX() + "-" + p1.getY() +
						 * "dominates" + p2.getX() + "-" + p2.getY()); }
						 */
					}
				}
			}
		}
		List<Point> pp = new ArrayList<Point>();
		for (int k = dataSet.size() - 1; k >= 0; k--) {
			Point p = dataSet.get(k);
			if (p.getCandidate() == 0) {
				for (int i = bbs.map.get(minRK).getRPointSet().size() - 1; i >= 0; i--) {
					Point p1 = bbs.map.get(minRK).getRPointSet().get(i);
					if (p1.getX() < p.getX()) {
						if (p1.getY() <= p.getY()) {
							p.setCandidate(-1);
						}
					} else if (p1.getX() == p.getX()) {
						if (p1.getY() < p.getY()) {
							p.setCandidate(-1);
							/*
							 * if (p.getX() == 260795) {
							 * System.out.println(p1.getX() + "-" + p1.getY() +
							 * "dominates" + p.getX() + "-" + p.getY()); }
							 */
						}
					}
				}
				if (p.getCandidate() == 0) {
					pp.add(p);
				}
			}
		}

		// System.out.println(pp.size());

		for (int i = pp.size() - 1; i >= 0; i--) {
			Point p1 = pp.get(i);
			for (int j = pp.size() - 1; j >= 0; j--) {
				Point p2 = pp.get(j);
				if (p1.getX() < p2.getX()) {
					if (p1.getY() <= p2.getY()) {
						p2.setCandidate(-1);
					}
				} else if (p1.getX() == p2.getX()) {
					if (p1.getY() < p2.getY()) {
						p2.setCandidate(-1);
					}
				}
			}
		}

		for (int i = pp.size() - 1; i >= 0; i--) {
			Point p = pp.get(i);
			if (p.getCandidate() == 0) {
				System.out.println((long) p.getX() + "-" + (long) p.getY());
			}
		}
		/*
		 * Iterator<Entry<String, Rectangle>> iterator =
		 * bbs.map.entrySet().iterator(); while (iterator.hasNext()) {
		 * System.out.println("Yes"); Map.Entry<String, Rectangle> mapEntry =
		 * (Map.Entry<String, Rectangle>) iterator.next(); System.out.println(
		 * "The key is: " + mapEntry.getKey() + ", value is :" +
		 * mapEntry.getValue()); bbs.split(mapEntry.getValue(), dataSet); }
		 */

		/*
		 * System.out.println(bbs.map.size()); for (Object key :
		 * bbs.map.keySet()) { System.out.println("Yes");
		 * System.out.println(bbs.map.get(key)); bbs.split(bbs.map.get(key),
		 * bbs.map.get(key).getRPointSet()); bbs.map.remove(key); }
		 */

		/*
		 * System.out.println(bbs.map.size()); Iterator<Entry<String,
		 * Rectangle>> iterator = bbs.map.entrySet().iterator(); while
		 * (iterator.hasNext()) { System.out.println("No"); Map.Entry<String,
		 * Rectangle> mapEntry = (Map.Entry<String, Rectangle>) iterator.next();
		 * System.out.println("The key is: " + mapEntry.getKey() + ",value is :"
		 * + mapEntry.getValue()); // bbs.split(mapEntry.getValue(), //
		 * mapEntry.getValue().getRPointSet()); }
		 */

		/*
		 * for (Object key : bbs.map.keySet()) { System.out.println("No");
		 * System.out.println(bbs.map.get(key).getRectangleID()); if
		 * (bbs.map.get(key).getCandidate() != -1) { bbs.split(bbs.map.get(key),
		 * bbs.map.get(key).getRPointSet()); } // bbs.split(bbs.map.get(key),
		 * bbs.map.get(key).getRPointSet()); bbs.map.remove(key); }
		 */
		long endTime = System.currentTimeMillis();
		long totTime = endTime - startTime;
		System.out.println("Using Time:" + totTime);
	}
}
