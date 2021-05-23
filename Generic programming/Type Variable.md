# Type Variable

## 01. Type Variable 특징
  - class에 유연하게 type을 부여할 수 있다는 것을 **<T\>**로 나타낸다 : 동일 code, multiple type.
  - **class definition 전반에 type variable이 사용**된다 : field, return type, local variable ...
    - type variable 명칭 : T(ype), E(lement), K(ey), N(umber), V(alue), S U V
  - T를 특정해주는 **substituting** -> substitute 된 class를 이용하여 **Instantiating**
    - class A\<T>   /   A \<String>   /   A \<String> a
    - **substituting -> instantiating**

```java

public class PairTest {
	public static void main(String[] args) {
		String[] words = {"Marry", "had", "a", "little", "lamb"};
		
		Pair<String> mm = ArrayAlg.minmax(words);
		
		System.out.println("min = " + mm.getFirst());
		System.out.println("max = " + mm.getSecond());
	}
}

class ArrayAlg{
	public static Pair<String> minmax(String[] a){
		if(a == null || a.length == 0) return null;
		
		String max = a[0];
		String min = a[0];
		
		for(int i=1; i<a.length; ++i) {
      // ASCII code 값 min - a[i] 으로 생각 -> 길이 오름차순, 사전순 정렬..
			if(min.compareTo(a[i])<0) min = a[i];
			if(max.compareTo(a[i])>0) max = a[i];
		}
		
		// type 역 추론 (type inference)
		// min, max가 String인 것을 바탕으로 추론해 냄.
		return new Pair<>(min, max);
		
	}
}

class Pair<T>{

  private T first;
  private T second;

  public Pair() { first = null; second = null; }
  public Pair(T first, T second) { this.first = first; this.second = second; }

  public T getFirst() { return first; }
  public T getSecond() { return second; }

  public void setFirst(T newValue) { first = newValue; }
  public void setSecond(T newValue) { second = newValue; }

}

```

## 02. Bound for Type Variable (Type Variable 제한사항)

### 문제 : type variable cannot be instantiated with arbitrary types
  - **즉, T라는 모호한 타입을 바탕으로 Instantiate하면 문제가 발생할 수 있다.**
    - (예) T 내에 특정 method가 존재하는지 안하는지 어떻게 알 수 있는가?  

```java
class ArrayAlg2{
	public static<T> Pair<T> minmax(T[] a){
		if(a == null || a.length == 0) return null;
		
		T max = a[0];
		T min = a[0];
		
		for(int i=1; i<a.length; ++i) {
			if(min.compareTo(a[i])<0) min = a[i];
			if(max.compareTo(a[i])>0) max = a[i];
      // 문제 발생 : T type이 compareTo() method를 갖고 있을 지 어떻게 아는가?
		}
		
		// type 역 추론 (type inference)
		// min, max가 String인 것을 바탕으로 추론해 냄.
		return new Pair<T>(min, max);
		
	}
}
```
  
### 해결 : extends를 이용해서(상속의 의미 아님), T를 어떤 class 군으로 한정 짓는다.
  - **Bounded Type parameter**

```java
class ArrayAlg2{
  
  // T로 Comparable interface를 상속하는 것만 가능..!
	public static<T extends Comparable> Pair<T> minmax(T[] a){
		if(a == null || a.length == 0) return null;
		
		T max = a[0];
		T min = a[0];
		
		for(int i=1; i<a.length; ++i) {
			if(min.compareTo(a[i])<0) min = a[i];
			if(max.compareTo(a[i])>0) max = a[i];
		}
		
		// type 역 추론 (type inference)
		// min, max가 String인 것을 바탕으로 추론해 냄.
		return new Pair<T>(min, max);
		
	}
}
```



