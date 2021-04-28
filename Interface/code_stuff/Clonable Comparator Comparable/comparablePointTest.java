package comparable_vs_comparator;

import java.util.Arrays;

public class comparablePointTest {
	public static void main(String[] args) {
		try {
			
			// Object method 실험
			comparablePoint[] pArr = new comparablePoint[5];
			pArr[0] = new comparablePoint(10, 2, new StringBuilder("P1"), "A");
			pArr[1] = new comparablePoint(4, 5, new StringBuilder("P2"), "B");
			pArr[2] = new comparablePoint(8, 2, new StringBuilder("P3"), "C");
			pArr[3] = pArr[1].clone();
			pArr[4] = new comparablePoint(8, 1, new StringBuilder("P5"), "D");
			
			System.out.println(pArr[0] + " / hashCode : " + pArr[0].hashCode());
			System.out.println(pArr[1] + " / hashCode : " + pArr[1].hashCode());
			System.out.println(pArr[3] + " / hashCode : " + pArr[3].hashCode());
			System.out.println(pArr[2] + " / hashCode : " + pArr[2].hashCode());
			System.out.println(pArr[1].equals(pArr[3]));
			
			pArr[1].setAlias("PPP");
			
			System.out.println(pArr[1]);
			System.out.println(pArr[3]);
			System.out.println(pArr[3].equals(pArr[1]));
			
			// sorting + Comparable interface
			System.out.println("Sorting using java.lang.Comparable in Ascending order");
			Arrays.sort(pArr);
			for(var I : pArr) {
				System.out.println(I);
			}
			System.out.println();
			
			// sorting + Comparator interface
			System.out.println("Sorting using java.util.Comparator in Descending order");
			Arrays.sort(pArr, new comparatorPoint());
			for(var I : pArr) {
				System.out.println(I);
			}
			System.out.println();

		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
