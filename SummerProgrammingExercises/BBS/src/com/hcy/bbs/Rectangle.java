package com.hcy.bbs;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private long rectangleID;
	private long parentRID;
	private long childR0ID;
	private long childR1ID;
	private long childR2ID;
	private long childR3ID;
	private int candidate;
	private List<Point> rPointSet;

	public double getX1() {
		return this.x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return this.y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return this.x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return this.y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public long getRectangleID() {
		return this.rectangleID;
	}

	public void setRectangleID(long rectangleID) {
		this.rectangleID = rectangleID;
	}

	public long getParentRID() {
		return this.parentRID;
	}

	public void setParentRID(long parentRID) {
		this.parentRID = parentRID;
	}

	public long getChildR0ID() {
		return this.childR0ID;
	}

	public void setChildR0ID(long childR0ID) {
		this.childR0ID = childR0ID;
	}

	public long getChildR1ID() {
		return this.childR1ID;
	}

	public void setChildR1ID(long childR1ID) {
		this.childR1ID = childR1ID;
	}

	public long getChildR2ID() {
		return this.childR2ID;
	}

	public void setChildR2ID(long childR2ID) {
		this.childR2ID = childR2ID;
	}

	public long getChildR3ID() {
		return this.childR3ID;
	}

	public void setChildR3ID(long childR3ID) {
		this.childR3ID = childR3ID;
	}

	public int getCandidate() {
		return this.candidate;
	}

	public void setCandidate(int candidate) {
		this.candidate = candidate;
	}

	public List<Point> getRPointSet() {
		return this.rPointSet;
	}

	public void setRPointSet(List<Point> rPointSet) {
		this.rPointSet = new ArrayList<Point>(rPointSet);
	}

	public void addRPoint(Point p) {
		this.rPointSet.add(p);
	}

	public Rectangle() {
		this.candidate = 0;
		this.rPointSet = new ArrayList<Point>();
	}
}
