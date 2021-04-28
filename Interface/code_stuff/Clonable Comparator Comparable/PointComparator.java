package comparable_vs_comparator;

import java.util.Comparator;

// generic interface에서 class 만들때는 <T>의 T의 자료형을 지정해주어야 한다.
// CPP의 template과 비슷한 개념이다!
public class comparatorPoint implements Comparator<comparablePoint> {
	public int compare(comparablePoint n1, comparablePoint n2) {
		if(n1.getX() != n2.getX())
			return n2.getX() - n1.getX();
		else
			return n2.getY() - n1.getY();
	}
}
