# Comparator

## Comparator VS comparable
 |Comparator|Comparable|
 |:---:|:---:|
 |implements : (java.util.)Comparator|implements : (java.lang.)Comparable|
 |compare(Class o1, Class o2)|.compareTo(Class otherO)|
 |class 외부에서 sort 규칙 지정|class 내부에 자체 sort 규칙 지정|
 |CPP의 내부 연산자 오버로딩과 유사|CPP의 friend 정의를 통한 외부 연산자 오버로딩과 유사|
 
## Comparator 사용

### 1. Comparator<T/>를 implement하는 새로운 class 생성

```java
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

// main method에서의 사용
Arrays.sort(pArr, new comparatorPoint());
```

### 2. 익명객체 생성
  
  
