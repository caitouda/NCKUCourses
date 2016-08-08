package DBSCAN;

import java.math.BigDecimal;

public class Point {
	private double x;
	private double y;
	private int ID;
	private boolean isKey;
	private boolean isClassed;

	public boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(boolean isKey) {
		this.isKey = isKey;
	}

	public boolean getIsClassed() {
		return isClassed;
	}

	public void setIsClassed(boolean isClassed) {
		this.isClassed = isClassed;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Point(String str) {
		String[] p = str.split(" ");
		this.x = Double.parseDouble(p[0]);
		this.y = Double.parseDouble(p[1]);
		this.ID = 0;
		this.isKey = false;
		this.isClassed = false;
	}

	public static String getPrettyNumber(double number) {
		return BigDecimal.valueOf(number).stripTrailingZeros().toPlainString();
	}

	public String print() {
		return getPrettyNumber(this.x) + " " + getPrettyNumber(this.y) + " " + this.ID;
	}

}
