package com.hcy.nns;

public class Point {
	private double x;
	private double y;
	private int pointID;
	private String rectangleID;
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

	public int getPointID() {
		return this.pointID;
	}

	public void setPointID(int pointID) {
		this.pointID = pointID;
	}

	public String getRectangleID() {
		return this.rectangleID;
	}

	public void setRectangleID(String rectangleID) {
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
}
