# Method

## 1. java에서 method는 cpp의 멤버함수
  - 자바 프로그램은 main method에서 시작한다.
  - main method에서 다른 method들을 호출할 수 있다.
  - method의 상대적 위치에 상관없이 method들을 호출 가능하다.

# Class

## 1. class는 메소드와 데이터로 이루어져 있다.
  - **데이터 : 상태정보 : "클래스 내의 인스턴스 변수" : "멤버변수( 마치 cpp 멤버변수 )" : "필드"**
  - **메소드 : 행동정보 : "클래스 내의 인스턴수 메소드( 마치 cpp 멤버함수 )"**
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

## 2. String은 문자열 처리를 위해서 java에서 제공하는 클래스이다.

## 3. 생성자(Constructor)
  - 인스턴스 생성 시에 인스턴스의 초기화를 위한 메소드
  - 인스턴스 생성 시 한번 호출
  - 반환값 없음
  - 클래스 이름과 동일한 메소드임
  - 인스턴스 생성의 마지막 단계에서 생성자가 호출된다
  - 생성자 호출이 생략된 인스턴스는 존재할 수 없다.
  
```java

class BankAccount{
  
  String accNum;
  String ssNum;
  int balance;
  
  // 생성자
  public BankAccount(String acc, String ss, String bal){
    accNum = acc;
    ssNum = ss;
    balance = bal;
    
  }
  
}

public class BankAcconuntUniID{

  public static void main(String[] args){
    
    // 인스턴스 생성 시에 호출해야 하는 생성자.
    BankAccount Lim = new BankAccount("my bankAccount", "my SocialSecurityNumber", mybalance);
    
  }

}

```











