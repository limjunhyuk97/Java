# Object class


## Object class란?
  - **Object class**는 **java 내에 존재하는 모든 class의 super class**이다.
    - **array class type 또한 Object class type의 sub class**이다.
    - Object class가 super class인 것은 다양한 방식으로 선언할 수 있다.
      - implicitly
      - explicitly
      - indirectly
  - **Object class와 Objects class는 서로다르다!!**
    - Object class : java.lang.Object
    - Objects class : java.util.Objects
      
```java
public class Employee{ ... }  // implicit
public class Employee extends Object { ... } // explicit
public class Manager extends Employee { ... } // indirect
```

  - polymorphism에 따르면, 모든 oject들은 Object class type 변수에 할당할 수 있다.
  - Object class가 제공하는 모든 class type을 위한 유용한 method가 있다.
    - toString()
    - hashCode()
    - equals()
    
    
## Useful Method

### 1. .equals() Method
  - 객체와 객체를 **사용자가 정한 기준을 바탕으로 비교할 수 있게 해주는 method**
  - 두 객체의 field 값들이 같은지를 비교하는 equals method를 만든 것이다.
  
```java
public class Foo {
	
	private int fooInt = 0;
	private double fooDouble = 0;
	
	public void hello() {
		System.out.println("My name is FOo!");
	}
	
	// 같은 class 객체간 field값 비교
	public boolean equals(Object object) {
		
		// 둘이 아예 같은 객체를 가리키고 있는 경우
		if(this == object)	return true;
		
		// 상대방이 null인경우 
		if(object == null) return false;
		
		// 내 class와 상대방 class가 다른 경우
		if(getClass() != object.getClass()) return false;
		
		// 상대방이 null이 아니고, 내 class와 상대방 class가 같은 경우
		// 강제 타입 변환을 통해 Foo class로 바꿔줌 + 각 field값 비교 결과를 return
		Foo foo = (Foo)object;
		return (foo.fooInt == fooInt) && (foo.fooDouble == fooDouble);
		 
	}
}
```

  - **field에 null을 가리키는 참조변수**가 있다면(예를 들면 null값인 String) **.equals() 메소드는 nullPointerException** 일으킬 수 있다.
  - java.util의 **Objects.equals(Object a, Object b)**를 이용하면 null pointer의 경우까지 다룰 수 있다.
    - 둘다 null 이면 true
    - 한쪽만 null 이면 false
    - 둘다 null이 아니면 .equals() method 호출
  
```java
// 같은 class 객체간 field값 비교
	public boolean equals(Object object) {
		
		// 둘이 아예 같은 객체를 가리키고 있는 경우
		if(this == object)	return true;
		
		// 상대방이 null인경우 
		if(object == null) return false;
		
		// 내 class와 상대방 class가 다른 경우
		if(getClass() != object.getClass()) return false;
		
		// 상대방이 null이 아니고, 내 class와 상대방 class가 같은 경우
		// 강제 타입 변환을 통해 Foo class로 바꿔줌 + 각 field값 비교 결과를 return + String 비교에 있어서 Objects.equals(Object a, Object b) 사용
		Foo foo = (Foo)object;
		return (foo.fooInt == fooInt) && (foo.fooDouble == fooDouble) && Objects.equals(fooName, foo.fooName);
		
	}
```

  - subclass에서 equals 사용하는 방법 : super.equals 통해 상위 class 먼저 비교 + 나머지 비교
  
```java
public class Foo2 extends Foo{
	private String str;
	
	Foo2(int intF, double doubleF, String stringF, String str){
		super(intF, doubleF, stringF);
		this.str = str;
	}
	
	public boolean equals(Object object) {
		
    // super type field에서의 비교 먼저 진행
		if(!super.equals(object))
			return false;		
		
    // sub type field의 값들을 비교해준다.
		Foo2 foo2 = (Foo2)object;
		return Objects.equals(str, foo2.str);
	}
}
```
  

### 2. .hashCode() method
  - object -> integer 값으로 바꿔준다. 
  - 같은 객체를 가리킨다면, 같은 hashCode() 값, 다른 객체를 가리킨다면, 다른 hashCode() 값을 가져야 한다.
  - 사용자가 **equals() method를 Override했다면, hashCode() method의 결과도 equals() method의 같은 객체 기준에 맞게 Override 해줘야 함**
    - **equals 결과가 바뀌면, hashCode 결과도 바뀌어야 한다..!**

```java
public int hashCode() {
		return Objects.hash(fooInt, fooDouble, fooName);
    // hash는 Objects 클래스의 static 메소드
    // Objects 클래스는 java.util.Objects
	}
```

### 3. .toString() method
  - 객체를 대표하는 String을 return 한다.
  - "String + 객체"의 연산(concatenation)에서 객체의 toString 메소드가 호출된다.
  - **사용자가 원하는 String 모습으로 toString 메소드를 Overriding 시킬 수 있다.**

### 4. getClass() method
  - **실제 변수가 참조하고 있는 객체의 class 정보를 제공**한다.
  
```java
public class Test {
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		A a2 = b;
		
		System.out.println(a.equals(b));
		System.out.println(a2.equals(b));
		
		System.out.println(System.out);
		System.out.println(a.getClass()); // class A
		System.out.println(b.getClass()); // class B
		System.out.println(a2.getClass()); // class B
	}
}
```



