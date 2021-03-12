# Immutable

## Immutable object
  - **불변 객체**로써, **재할당은 가능하지만, 한번 할당 시에 내부 데이터를 바꿀 수 없는 객체**이다.
  - 대표적으로 불변 객체를 생성하는 class로 String, Integer, Boolean 등이 있다.

```java

/*
immutable 객체 Integer를 이용한 예시
: 함수 매개변수 num이 30을 값으로 갖는 새로운 Integer 객체를 생성시키고 사라질 뿐, num1에는 변화가 없다.
*/
package referenceType_primitiveType;

public class Immutable2 {
	
	public static void changeInteger(Integer num) { num = 30; }
	
	public static void main(String[] args) {
		
		Integer num1 = 10;
		changeInteger(num1);
		System.out.println(num1);
		
	}
}


/*
객체로 감싼 immutable 객체 Integer를 이용한 예시
: 함수 매개변수 Immutable 객체 obj가 인자로 받은 obj1을 가리키게 되고, 그 obj1이 가리키는 Integer 객체를 바꾸기에 변화가 있다.
*/
package referenceType_primitiveType;

public class Immutable {
	
	public Integer var;
	
	public Immutable(Integer var) {
		this.var = var;
	}
	
	public static void changeInteger(Immutable obj) {
		obj.var = 20;
	}
	
	
	public static void main(String[] args) {
		Immutable obj1 = new Immutable(10);
		
		System.out.println(obj1.var);
		
		changeInteger(obj1);
		System.out.println(obj1.var);
		
	}
}

```
