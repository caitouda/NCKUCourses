package com.hcy.nns;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private double middle;
	private String rectangleID;
	private String parentRID;
	private String childR0ID;
	private String childR1ID;
	private int direction;
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
	
	public double getMiddle() {
		return this.middle;
	}

	public void setMiddle(double middle) {
		this.middle = middle;
	}

	public String getRectangleID() {
		return this.rectangleID;
	}

	public void setRectangleID(String rectangleID) {
		this.rectangleID = rectangleID;
	}

	public String getParentRID() {
		return this.parentRID;
	}

	public void setParentRID(String parentRID) {
		this.parentRID = parentRID;
	}

	public String getChildR0ID() {
		return this.childR0ID;
	}

	public void setChildR0ID(String childR0ID) {
		this.childR0ID = childR0ID;
	}

	public String getChildR1ID() {
		return this.childR1ID;
	}

	public void setChildR1ID(String childR1ID) {
		this.childR1ID = childR1ID;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
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
		this.direction = -1;
		this.rPointSet = new ArrayList<Point>();
	}
}
