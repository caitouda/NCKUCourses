package com.hcy.bbs;

public class Point {
	private double x;
	private double y;
	private long rectangleID;
	private int candidate;
	private int count;

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public long getRectangleID() {
		return this.rectangleID;
	}

	public void setRectangleID(long rectangleID) {
		this.rectangleID = rectangleID;
	}

	public int getCandidate() {
		return this.candidate;
	}

	public void setCandidate(int candidate) {
		this.candidate = candidate;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Point() {
		this.rectangleID = 0;
		this.candidate = 0;
	}
}
