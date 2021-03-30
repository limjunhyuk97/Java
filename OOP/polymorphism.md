# Polymorphism(다형성)

## Polymorphism이란?
  - **하나의 요소(객체)** 가 **하나 이상의 타입** 을 갖는 것이다.
  - 다형성의 결과는, **하나의 메시지**에 대한 **(하나의 요소/객체 -> 하나 이상의 타입)다양한 대답의 가능성**이다.
    - **하나의 객체 - 하나이상의 타입**
    - **하나의 메시지 - 하나이상의 메소드**
    - **같은 역할, 같은 책임 - 다른 타입의 객체**

## Polymorphism 구현 방법

### "메소드 재정의 + 타입변환"

### 1. [메소드 재정의](https://github.com/limjunhyuk97/java_study/blob/master/OOP/Inheritance.md)

### 2. ]타입변환(Promotion)](https://github.com/limjunhyuk97/java_study/blob/master/OOP/Promotion.md)
  - 기본 타입에서 뿐만 아니라, class 사이에서도 상속관계에 의거해서 타입변환이 일어날 수 있다.
  - **같은 객체를 가리키지만, 다른 타입을 갖게** 할 수 있다.

```java
상위타입 class  상위타입 변수 = 하위타입 객체

class A{}
class B extends A{}
class C extends B{}
class D extends A{}
class E extends D{}
// A<-B<-C , A<-D<-E

public class Promotion {
	public static void main(String[] args) {
		B b = new B();
		C c = new C();
		D d = new D();
		E e = new E();
		
		A a1 = b;
		A a2 = c;
		A a3 = d;
		A a4 = e;
		
		B b1 = c;
		D d1 = e;
		
	}
}
```
