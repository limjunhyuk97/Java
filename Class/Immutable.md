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

## [Immutable object와 encapsulation](https://github.com/limjunhyuk97/java_study/blob/master/OOP/encapsulation.md)
  - class 안에 참조타입의 private (final) field가 있는 경우
    - **해당 field와 메소드(생성자) 매개변수가 직접 상호작용하여 encapsulation이 깨지는 경우가 발생가능**하다.
    - constructor에서
    - getter method에서
    - setter method에서
  - 이 문제는 **방어적 복사를 통한 immutable한 상태 구현**으로 해결이 가능하다.

```java
public class Date {
	private int year;
	private int month;
	private int day;
	
	Date(int yy, int mm, int dd){
		year = yy;
		month = mm;
		day = dd;
	}
	
	int getYear() { return year; }
	int getMonth() { return month; }
	int getDay() { return day; }
	
	void setYear(int year) {this.year = year;}
	void setMonth(int month) {this.month = month;}
	void setDay(int Day) {this.day = day;}
	
	public String toString() {
		return year+"/"+month+"/"+day;
	}
	
}

public class Lover {
	private String boyName;
	private String girlName;
	private Date Anniversary;
	// Date class를 field로 이용함
	
	public Lover(String bn, String gn, Date date){
		this.boyName = bn;
		this.girlName = gn;
		this.Anniversary = new Date(date.getYear(), date.getMonth(), date.getDay());
		// 방어적 복사
	}
	
	public Date getAnniversary() {
		return new Date(Anniversary.getYear(), Anniversary.getMonth(), Anniversary.getDay());
		// 방어적 복사
	}
	
	void setAnniversary(Date date) {
		this.Anniversary = new Date(date.getYear(), date.getMonth(), date.getDay());
		// 방어적 복사
	}
	
	public String toString() {
		return boyName +" and " + girlName + " : " + Anniversary;
	}
}

public class LoverTest {
	public static void main(String[] args) {
		
		Date date = new Date(2021, 2, 24);
		Lover couple1 = new Lover("Tom", "Lucy", date);
		System.out.println(date);
		System.out.println(couple1);
		System.out.println();
		
		date.setYear(2022);
		System.out.println(date);
		System.out.println(couple1);
		System.out.println();
		
		date = couple1.getAnniversary();
		date.setYear(2022);
		System.out.println(date);
		System.out.println(couple1);
		
		
	}
}
```
