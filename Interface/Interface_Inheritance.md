# Interface와 Inheritance

## Interface 멤버별 Inheritance의 양상

### 1. 상수 필드
  - 상수 필드의 항목이 sub Interface들에게 그대로 상속된다.

### 2. 추상 메소드
  - 추상 메소드의 항목이 sub Interface들에게 그대로 상속된다.
  - '항목'이 상속되는 것이기에, sub Interface들의 구현 class에서 모두 다시 재정의 해주어야 한다.

### 3. 디폴트 메소드
  - 디폴트 메소드는 기본적으로 **구현 클래스에서** 재정의가 가능하다.
  - 디폴트 메소드가 **sub-interface에서 재정의되지 않았다면** 그대로 상속된다.
  - 디폴트 메소드가 **sub-interface에서 재정의되었다면**, 재정의된 내용이 디폴트 메소드가 된다.
  - 디폴트 메소드가 **서로 다른 sub-interface에서 재정의되었다가 다신 하나의 sub-interface의 super-interface로 작용한다면**, 컴파일 오류가 발생한다.
    - 즉, 디폴트 메소드의 여러번 재정의된 사항이 충돌한다면, 컴파일 오류가 발생한다.
    - [interfaces clash rule / superclasses win rule 로 해결](https://github.com/limjunhyuk97/java_study/blob/master/Interface/DefaultMethodConflict.md)

### 4. 정적 메소드
  - 정적 메소드는 상속되지 않는다!

## 전체적인 Interface inheritance 경우에 대한 예시
```java
///// 예시 /////

// A
public interface A {
	public static int num = 10;
	public void fooA();
	public default void dFoo() {
		System.out.println("dFoo()");
	}
}

// A <- B1
public interface B1 extends A{
	public void fooA();
	public void fooB1();
	public void fooB3();
	public default void dFoo() {
		System.out.println("Overrided dFoo() in interface B1");
	}
	public static void stFoo() {
		System.out.println("stFoo() in B1");
	}
}

// A <- B2
public interface B2 extends A{
	public void fooB2();
	public void fooB3();
	public default void dFoo() {
		System.out.println("Overrided dFoo() in interface B1");
	}
	public static void stFoo() {
		System.out.println("stFoo() in B2");
	}
}

// C <- B1, B2
public interface C extends B1, B2 {
	public void fooB3();
	public void fooC();
	// dFoo()에 대한 별다른 정의를 해주지 않을 경우, 문제 발생
	
	//  새로 사용할거면 새로 정의해주면 됨
	public default dFoo(){}
	
	// 이전 내용을 사용할거면 B1, B2의 dFoo() 중 하나를 선택해야 함
	public default dFoo(){
	  d1.super.dFoo();
	  // super class들 중, d1의 dFoo를 사용하겠습니다.
	}
}

// A - ObjA
public class ObjA implements A{
	public void fooA() {
		System.out.println("Implement A, ObjA");
	}
}

// B1 - ObjB1_1
public class ObjB1_1 implements B1{
	public void fooA() {
		System.out.println("Implement B1, ObjB1_1");
	}
	public void fooB1() {
		System.out.println("Implement B1, ObjB1_1");
	}
	public void fooB3() {
		System.out.println("Implement B1, ObjB1_1");
	}
}

// B2 - ObjB2_1
public class ObjB2_1 implements B2{
	public void fooA() {
		System.out.println("Implement B2, ObjB2_1");
	}	
	public void fooB2() {
		System.out.println("Implement B2, ObjB2_1");
	}
	public void fooB3() {
		System.out.println("Implement B2, ObjB2_1");
	}
}

// B2 - ObjB2_2
public class ObjB2_2 implements B2{
	public void fooA() {
		System.out.println("Implement B2, ObjB2_1");
	}	
	public void fooB2() {
		System.out.println("Implement B2, ObjB2_1");
	}
	public void fooB3() {
		System.out.println("Implement B2, ObjB2_1");
	}
	public void dFoo() {
		System.out.println("Overrided dFoo() in implemented class");
	}
}

// C - ObjC
public class ObjC implements C {
	public void fooA() {
		System.out.println("Implement C, ObjC");
	}
	public void fooB1() {
		System.out.println("Implement C, ObjC");
	}
	public void fooB2() {
		System.out.println("Implement C, ObjC");
	}
	public void fooB3() {
		System.out.println("Implement C, ObjC");
	}
	public void fooC() {
		System.out.println("Implement C, ObjC");
	}
}

public class MainTest {
	public static void main(String[] args) {
		A a;
		B1 b1;
		B2 b2;
		C c;
		
		ObjA oA = new ObjA();
		ObjB1_1 oB1 = new ObjB1_1();
		ObjB2_1 oB2_1 = new ObjB2_1();
		ObjB2_2 oB2_2 = new ObjB2_2();
		ObjC oC = new ObjC();
		
		System.out.println("oA : ");
		a = oA; a.fooA(); a.dFoo();
		System.out.println(oA.num);
		System.out.println("------------------");
		
		System.out.println("oB1 : ");
		a = oB1; a.fooA(); a.dFoo();
		b1 = oB1; b1.fooA(); b1.fooB1(); b1.fooB3(); b1.dFoo();
		System.out.println(oB1.num);
		B1.stFoo();
		System.out.println("------------------");
		
		System.out.println("oB2_1 : ");
		a = oB2_1; a.fooA(); a.dFoo();
		b2 = oB2_1; b2.fooA(); b2.fooB2(); b2.fooB3(); b2.dFoo();
		System.out.println(oB2_1.num);
		B2.stFoo();
		System.out.println("------------------");
		
		System.out.println("oB2_2 : ");
		a = oB2_2; a.fooA(); a.dFoo();
		b2 = oB2_2; b2.fooA(); b2.fooB2(); b2.fooB3(); b2.dFoo();
		System.out.println(oB2_2.num);
		System.out.println("------------------");
		
		System.out.println("oBC : ");
		a = oC; a.fooA(); a.dFoo();
		b1 = oC; b1.fooA(); b1.fooB1(); b1.fooB3(); b1.dFoo();
		b2 = oC; b2.fooA(); b2.fooB2(); b2.fooB3(); b2.dFoo();
		c = oC; c.fooA(); c.fooB1(); c.fooB2(); c.fooB3(); c.fooC(); c.dFoo();
		System.out.println(oC.num);
		System.out.println("------------------");
		
	}

// 실행 결과 //
/*
oA : 
Implement A, ObjA
dFoo()
10
------------------
oB1 : 
Implement B1, ObjB1_1
Overrided dFoo() in interface B1
Implement B1, ObjB1_1
Implement B1, ObjB1_1
Implement B1, ObjB1_1
Overrided dFoo() in interface B1
10
stFoo() in B1
------------------
oB2_1 : 
Implement B2, ObjB2_1
dFoo()
Implement B2, ObjB2_1
Implement B2, ObjB2_1
Implement B2, ObjB2_1
dFoo()
10
stFoo() in B2
------------------
oB2_2 : 
Implement B2, ObjB2_1
Overrided dFoo() in implemented class
Implement B2, ObjB2_1
Implement B2, ObjB2_1
Implement B2, ObjB2_1
Overrided dFoo() in implemented class
10
------------------
oBC : 
Implement C, ObjC
Overrided dFoo() in interface B1
Implement C, ObjC
Implement C, ObjC
Implement C, ObjC
Overrided dFoo() in interface B1
Implement C, ObjC
Implement C, ObjC
Implement C, ObjC
Overrided dFoo() in interface B1
Implement C, ObjC
Implement C, ObjC
Implement C, ObjC
Implement C, ObjC
Implement C, ObjC
Overrided dFoo() in interface B1
10
------------------
*/

```
