# Class   

  - **(public / (default)) + (abstract / X) + (final / X) + (strictfp/ X)**
    - access modifier : public / (default)
    - abstract / X : abstract class or not
    - final / X : forbidding inheritance or not 
    - strictfp / X : restrict floating-point calculations to ensure portability or not

  - **Role of Classes**
    - Code template to **create objects**(instances)
    - A **type for objects** : user-defined-types
      - Purpose of setting type : memory allocation, type checking(operability, **checking callable method**, ,,)
  
  - **Kind of Classes**
    - **User-defined Classes**
    - **Library Classes**
  
  - **Presence of main method**
    - **Workhorse class** : without main method
    - **domain class** / application class : with main method 
    
## 1. 메소드, 필드, 생성자
  - **데이터 : 상태정보 : "클래스 내의 인스턴스 변수" : "멤버변수( 마치 cpp 멤버변수 )" : "필드"**
  - **메소드 : 행동정보 : "클래스 내의 인스턴수 메소드( 마치 cpp 멤버함수 )"**
  - **생성자 : class에서 인스턴스인, 객체를 만들어낼때 필요한 메소드. new 연산자를 통해 호출된다.**
  - class 는 연관있는 데이터와 메소드를 하나로 묶어준다.
    - 인스턴스 변수는 같은 클래스 내의 인스턴스 메소드 내에서 접근 가능하다.
    - class의 필드와 메소드 구성을 이용하려면, **class를 통해서 찍어낸 인스턴스(객체)를 이용해야 한다.**
      - **참조변수 = 인스턴스 생성** 의 구조를 띈다.
      - 참조변수는..
        - 여러개의 인스턴스들을 참조할 수도 있고.
        - 하나의 인스턴스를 여러개의 참조변수가 동시에 참조할 수도 있다.
        - 메소드의 인자로 참조변수를 전달할 수도 있다.
        - 참조변수 = NULL 은 참조변수가 아무것도 가리키고 있지 않은 상태를 말한다.
  - package 내의 class의 수만큼 .class 확장자의 파일이 생긴다.

```java

// Bank package 안의 클래스.
package Bank;

public class BankAccount{
  
  // 데이터 - 객체의 상태이다.
  static int balance = 0;
  
  // main 메소드가 프로그램의 시작점 : 다른 메소드들을 호출할 수 있다.
  public static void main(String[] args){
    
    deposit(10000);
    checkMyBalance();
    withdraw(3000);
    checkMyBalance();
    
  }
  
  // deposit 메소드 - 객체의 행동이다.
  public static int deposit(int money){
    balance += money;
    return balance;
  } 
  
  // withdraw 메소드 - 객체의 행동이다.
  public static int withdraw(int money){
    balance -= money;
    return balance;
  }
  
  // checkMyBalance 메소드 - 마찬가지로 객체가 수행가능한 행동이다.
  public static int checkMyBalance(){
    System.out.println("balance : " + balance);
    return balance;
  } 
}


// BankAccount 인스턴스(객체)의 생성방법
BankAccount myAcnt1 = new BankAccount();

// BacnkAccount 자료형을 위한 myAcnt1 참조변수를 생성하고,
// myAcnt1 참조변수가 새로 생성된 BankAccount 인스턴스의 주소를 참조하게끔, = 으로 할당한다.
// 즉, '참조변수' myAcnt1이 인스턴스(객체)를 참조하는 것이다.


// 메소드 인자로 참조변수 전달방법
public static void check(BankAccount acc){
  acc.checkMyBalance();
}
```

## 2. 필드   

  - **(private / public / protected) + (static / X) + (final / X) + (transient / X) + (volatile /X)**
    - access modifier : private / public / protected
    - static / X : static or not
    - final / X : final or not
    - trandient : 
    - volatile :    
  
  - 객체의 고유한 정보, 부품, **상태 정보**를 갖고 있는 속성.
    - **set of fields value is the current state of the object**
  - **final 필드(final field)**
    - 상수(final)는 한번 초기화 되면 수정할 수 없다.
    - 상수 필드(final field)에 초기값을 주는 방법 : 필드 생성과 동시에 초기화 / **생성자를 통한 초기화(값이 객체마다 주어져야 하는 경우)**
  - **상수 필드(static final field)**
    - 불변의 값(상수)은 객체마다 존재할 필요가 없는 공용성을 갖고 있고, 객체마다 값이 다르면 안된다.
    - static final 선언은 이러한 상수의 조건을 만족시켜준다.
    - **아예 변하지 않는 값들은 정적 상수(static final)로 지정해 주자.**

```java

public class Person {
	static final String country = "Korea";
	final int age;
	final String name;
}

```

## 3. 생성자   

  - **(private / public / protected)**
    - access modifier : private / public / modifier   

  - **생성자의 역할**
    - new 연산자에 의해서 객체 생성 시에 호출되어, 객체의 초기화를 담당함.
    - 생성자가 실행되지 않으면, 객체가 생성되지 않는다.   
    
  - **기본 생성자**
    - 생성자를 따로 정의해 주지 않는다면, 자동으로 바이트 코드에 기본 생성자가 생성된다.
    - 만약, 명시적으로 생성자를 하나라도 만든다면, 기본 생성자는 생성되지 않는다.
    - 기본 생성자는 { }로 이루어진, 모든 상태를 기본 상태로 초기화 하는 생성자 이다.   
  
  - **생성자 선언**
    - 클래스 명과 같고, 리턴 값이 없는 함수와 같다.
    - 생성자를 따로 만든다면 그 생성자를 반드시 실행 시켜주어야 한다.
    - this.에서 this는 생성된 객체 자신을 가리키는 참조변수이다.
  
  - **생성자 오버로딩**
    - 매개변수를 달리하는 생성자를 여러 개 생성하여, 다양한 객체 초기화의 방법을 제공하는 것이다.
    - 생성자 오버로딩이 가능한 경우(생성자가 서로 구분되는 경우)는 : 매개변수의 타입과 갯수, 순서가 다른 경우일때만 이다. (CPP의 함수 오버로딩과 비슷함.)
  
  - **생성자 코드 중복 간소화 (this(), 다른 생성자 호출)**
    - this() 를 통한 다른 생성자의 호출은, 생성자블록의 첫 줄에서만 허용된다.
    - 전체적인 초기화 내용을 다루는 생성자 하나를 중심으로, 구체화된 초기화 내용들을 인자로 전달한다.
    
```java

package Object;

public class Car {
	String company = "Tesla";
	String model = "Model S";
	String color = "Black";
	int maxSpeed = 300;
	int speed;
	
	Car() {}
	Car(String model) {
    this(model, "black", 300);
  }
	Car(String model, String color) {
    this(model, color, 250);
  }
	Car(String model, String color, int maxSpeed) {
   this.model = model; this.color = color; this.maxSpeed = maxSpeed;
  }
	
}

```

## 4. 메소드   

  - **(private / public / protected) + (static / X) + (final / X) + (synchronized / X) + (native / X) + (strictfp / X)**
    - access moifier : private / public / protected
    - static / X : static or not
    - final / X : restrict overloading or not
    - synchronized / X :
    - native / X : 
    - strictfp / X : restrict floating-point calculation to ensure portability or not   

  - **메소드 선언의 구성**
    - **메소드 선언부(메소드 시그니처)**
      - 반환형
      - 메소드 명
      - 메소드 매개변수 선언
        - **매개변수 갯수 모르는 경우**
          - **배열로 전달**
            - 배열 매개인자 <- 배열 전달
          - **... 으로 전달**
            - ... 매개인자 <- 배열 전달 or 값 목록으로 바로 전달 가능
          - 두가지 경우 모두, 넘겨받은 항목에 의거하여 메소드 내에서 배열을 생성한다.
  - **메소드 실행블럭**

```java

package Object;

public class Computer{
  
  // 매개인자를 배열로 받는 경우
  int sum1(int[] values){
    int sum = 0;
    for(int i ; values)
      sum += i;
    return sum;
  }
  
  // 매개인자를 ...로 받는 경우
  int sum2(int ... values){
    int sum = 0;
    for(int i=0 ; i<values.length ; ++i)
      sum += values[i];
    return sum;
  }
  
  // main 메소드에서 확인
  public static void main(String[] args){
    
    Computer com = new Computer();
    
    // sum1에는 배열로 인자 넘기기
    int result1 = com.sum1(new int[] {1 ,2, 3, 4, 5});
    
    // sum2에는 값 목록으로 인자 넘기기, 배열로도 인자 넘기기 가능
    int result2 = com.sum2(1, 2, 3, 4, 5);
    int result3 = com.sum2(new int[] {1, 2, 3, 4, 5});
    
  }
   
}

```

  - **메소드 호출**
    - **객체 내부에서 다른 메소드 호출**
      - 객체 생성의 필요 없이 메소드 이름만으로 호출 가능하다. (메소드명)
    - **객체 외부에서 다른 메소드 호출**
      - class로 객체를 생성 후에, 객체 내부의 메소드를 실행시켜야 한다. (객체.메소드명)
      
```java
public class Class1{
  
  int sum(int n, int m){
    return n+m;
  }
  
  // 객체 내부 - 이름만
  int sumprint(int n, int m){
    System.out.println(sum1(n, m));
  }

}

class Class2{
  
  int sum(int n, int m, int l){
    
    // 객체 외부 - 객체 생성 요구
    Class1 obj = new Class1();
    
    return obj.sum(n ,m) + l;
  }

}
```

  - **메소드 오버로딩**
    - **매개값을 다양하게 처리하기 위해서** 오버로딩이 존재한다.
    - **매개변수의 타입, 개수, 순서 중 하나가 달라야** 메소드 오버로딩의 조건이 충족된다.
    - 첫번째로 타입 일치 여부 확인 -> 두번째로 타입 변환 가능 여부 확인
    
  - **final 메소드**
    - 상속관계에서, 상속은 시키되, 유도 클래스에서의 **메소드 재정의를 금지 시킨다.**
    - 상속관계에서, 유도 클래스에서의 **메소드 오버로딩은 허가한다.**
    
```java

// declare A1
package A;

class A1{

  public final void foo() {}
  public final void foo(int x) {}
  // 같은 class 내에서는 overloading 가능
  
}

// declare A2
package A;

class A2 extends A1{ 

  public void foo() {}
  // 불가능
  // 상속 시에 함수의 메소드 재정의가 불가능 하다.

}

```

  - **getter, setter method**
    - 한번에 field 값에 접근하는 것을 막음으로써 객체의 무결성을 높인다. 
  
  - **Mutator method, Accessor method**
    - **Mutator method** : mutable (instance 값 변경 가능)
    - **Accessor method** : immutable (instance 값 변경 불가능)

```java
import java.time.*;

// mutator method
GregorianCalendar someDay = new GregorianCalendar(2021, 3, 17);
somDay.add(Calendar.DAY_OF_MONTH, 1000);
// 기존의 someDay 값이 바뀌어버림.

// accessor method
LocalDate date = LocalDate.now();
date.plusDays(1000); 
// 새로운 객체를 생성해버림.
```

  - **implicit parameter, explicit parameter**
    - **implicit parameter** : object of specific method, appears before the method name (메소드 호출 대상 객체)
     - **target**
     - **receiver**
    - **explicit parameter** : number inside the parentheses after the method name (메소드의 인자)

## 5. **멤버 (인스턴스 멤버, 정적 멤버)**
  
  - **인스턴스 멤버 (instance member)**
    - **객체 생성 후에 사용할 수 있는 필드와 메소드.**
    - 객체에 속하기 때문에 객체 없이는 사용할 수 없다.
    - **객체들 내에 개별적으로 존재한다.**
    - **인스턴스 필드**
      - 객체 별로 따로 생성되어 힙영역에서 존재한다.
    - **인스턴스 메소드**
      - 메소드 영역에 저장되고 공유된다.
      - 왜 인스턴스라고 하냐 그럼?
        - 특정 객체 안의 인스턴스 필드를 메소드로 처리할때, 어쨌든 특정 객체가 메소드를 부르기 때문에.
    - **this.** 는 **객체 내부의 인스턴스 멤버에 접근하기 위하여 사용**하는 것이다!
        
  - **정적 멤버 (static member) / 클래스 멤버**
    - class에 정적(static)으로 존재하는 필드와 메소드.
    - 객체에 속하지 않기 때문에, **객체 생성 없이 사용할 수 있는 필드와, 메소드를 의미한다.** 
      - (public static void main : 접근범위 public의, 정적으로 존재하는, 반환형 없는, main 함수)
    - 메소드 메모리 영역에 저장 시에, 클래스 별로 관리되므로, 클래스 로딩이 끝나면 바로 사용 가능.
      - 바이트 코드 파일 내 클래스 -> (클래스 로더 : 클래스 로딩) -> 메소드 영역 저장(static field, static method)
    - **객체들이 공유한다.**
    - **this.** 로 **객체 내부의 정적 멤버에 접근할 수 없다.** (this는 인스턴스 객체가 스스로를 지칭하는 것)
      
  - **인스턴스 멤버 vs 정적 멤버 / 클래스 멤버**

|처리|필드|메소드|객체생성유무|
|:---:|:---:|:---:|:---:|
|고유한 값 처리|인스턴스 필드의 처리|인스턴스 메소드 사용|객체 생성 후 이용 가능|
|공유/ 가변 값 처리|정적 필드의 처리|정적 메소드 사용|객체 생성 없이 이용 가능 (String 같은 느낌)|

  - **정적 멤버의 사용**
    - 정적 멤버의 가장 중요한 특징은, **객체를 생성하지 않고도, class 내부의 멤버를 사용할 수 있다는 데에 있다.**
      - 사용시에, 참조변수를 통해서 접근할 수도 있으나, **class명. 을 통해서 접근하는 것이 좋다.**
      - **정적 멤버 외의 멤버 (인스턴스 필드, 인스턴스 메소드)를 정적 메소드에서 사용할 수 없다. / 정적 멤버는 소속 객체가 없어도 되기 때문**

  - **싱글톤(Singleton)**
    - **오로지 단 하나의 객체만 생성되어야 하는 경우**, **이때 생성된 객체를 싱글톤**이라고 한다.
    - 싱글톤 생성 방법

```java

// class name : Singleton
public class Singleton{
  
  // Singleton 객체의 내부 생성.
  // private - 외부 접근 안됨 (은닉)
  // static - class에 하나 생성
  private static Singleton singleton = new Singleton();
  
  // Singleton 객체 생성자.
  // private - 외부 접근 안됨 (은닉)
  private Singleton() {}
  
  // 단 하나의 싱글톤 객체의 반환
  // static - class명으로도 (객체 생성 없이) 접근 가능해야 함
  static Singleton getInstance(){
    return singleton;
  }
  
}

```















