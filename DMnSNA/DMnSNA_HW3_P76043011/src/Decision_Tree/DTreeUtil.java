package Decision_Tree;

public class DTreeUtil {

	/**
	 * 屬性值熵的計算 Info(T)=(i=1...k)pi*log（2）pi
	 * 
	 * @param x
	 * @param total
	 * @return double
	 */
	public static double sigma(int x, int total) {
		if (x == 0) {
			return 0;
		}
		double x_pi = getPi(x, total);
		return -(x_pi * logYBase2(x_pi));
	}

	/**
	 * log2y
	 * 
	 * @param y
	 * @return double
	 */
	public static double logYBase2(double y) {
		return Math.log(y) / Math.log(2);
	}

	/**
	 * pi是當前這個屬性出現的概率（=出現次數/總數）
	 * 
	 * @param x
	 * @param total
	 * @return double
	 */
	public static double getPi(int x, int total) {
		return x * Double.parseDouble("1.0") / total;
	}

}
