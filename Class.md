# Class

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
  - 객체의 고유한 정보, 부품, 상태 정보를 갖고 있는 속성.

## 3. 생성자   

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
    - 생성자 오버로딩이 가능한 경우(생성자가 서로 구분되는 경우)는 : 매개변수의 타입과 갯수가 다른 경우일때만 이다. (CPP의 함수 오버로딩과 비슷함.)
  
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

# String

# Array

  - **배열 선언**   
    - 배열 변수는 참조 변수에 속하므로, **배열은 힙 영역에 생성**되고, **배열 변수는 힙영역의 배열을 참조하는 것**이다.
    - **1차원 배열**
      - 타입[] 배열명
      - 타입 배열명[]
    - **2차원 배열**
      - 타입[][] 배열명
      - 타입 배열명[][]

```java

// 형식1. 타입[] 변수;
int[] intArray;
double[] doubleArray;

// 형식2. 타입 변수[];
int intArray[];
String stringArray[];

```

  - **배열 생성**   
    - **1차원 배열**
    - 값 목록을 이용한 생성과, new 연산자를 이용한 생성의 두가지 방식이 있다.
    - **값 목록을 이용한 생성**
      - **{값0, 값1, 값2, 값3, ,,, }**
      - 배열 변수의 선언과, 값 목록을 통한 변수 저장은 같은 실행문 내에서 일어나야 한다. (생성과 초기화 동시에 일어나는 경우)
      - 마찬가지 이유로, 메소드로의 배열 목록 전달 또한 값 목록을 통해 일어날 수 없다.
    - **new 연산자를 이용한 생성**
      - **new 타입[] {값0, 값1, 값2, ,,, }**
      - 배열 변수의 선언과, 값 목록을 통한 변수 저장을 서로 다른 실행문에서 일어날 수 있게 해준다. (생성과 초기화 별도로 일어나는 경우)
      - 마찬가지 이유로, 메소드로의 배열 목록 전달을 new 연산자를 통해 시킬 수 있다.
      - **new 타입[길이]**
      - 값 목록을 통해 값들을 넣지는 않았지만, 길이만 정할 때 사용.
      - 생성 시에 자동으로 기본 값으로 초기화 된다. (boolean 값은 false로 초기화 됨.)   
    - **2차원 배열**
    - **값 목록을 이용한 생성**
      - {{값0, 값1, 값2}, {값0, 값1, 값2} ,,, }
    - **new 연사자를 이용한 생성**
      - 배열의 행마다 열의 길이가 다르게 2차원 배열을 생성할 수 있다.
      - new 타입[][] {{값0, 값1, 값2}, {값3, 값4, 값5, 값6}};
      - new 타입[길이][]; (행 별로 길이 다르게 설정 가능)
      - new 타입[길이][길이]; (n * m 길이 배열 설정)
  
  - **배열 길이**
    - **.length**
    - 배열 객체 내의 length 필드에 접근하는 것이다.

```java

// 값 목록을 이용한 생성
int[] arr = {10, 20 ,30};

// 값 목록을 이용하여 변수 선언 이후에 별도로 변수에 저장할 수는 없다.
int[] arr2;
arr2 = {10, 20 ,30};

// new 연산자를 이용하여 변수 선언 이후에 별도로 변수에 저장 가능하다.
int[] arr2;
arr2 = new int[] {10, 20, 30};

// 메소드로의 전달 - 값 목록 X
add(int[] scores) { ... }
add( {10, 20, 30}); // 컴파일 오류

// 메소드로의 전달 - new 연산자 O
add(int[] scores) { ... }
add( new int[] {10, 20, 30} );


// 2차원 배열을 만들어 줄때
int intArray[][] = new int[2][];
int intArray[][] = new int[3][4];
String[][] StringArray = new String[3][];

```
  - **main 메소드의 기본 인자, String[] args 의 의미.**
    - 기본적인 의미는 String 배열인 args라는 배열변수를 받아서 main 메소드에 전달하라는 것이다.
    - **명령라인(명령 프롬프트)에서 공백으로 구분된 문자열 배열을 따로 전달**한다면 main에서 받아들일 것이다.
    - 따로 문자열 배열을 전달하지 않으면 args로 전달된 것이 없는 것이다.
      - java -cp bin [package_name].[class_name] 문자열0 문자열1 문자열2 ... 문자열 n-1   
      
  - **배열을 함수 인자로 전달할 때**
    - 1차원 배열을 받는지, 2차원 배열을 받는지를 매개인자에 명시해줘야 한다.
    
```java

package StringEqualsExample;

public class ArrayReferenceObjectExample {
	
	public static void main(String[] args) {
		
		int intArray[][] = new int[2][];
		intArray[0] = new int[3];
		intArray[1] = new int[6];
		
		add2D(intArray);
	}
	
	static int add1D(int[] intArr) {
		int sum = 0;
		for(int i=0 ; i<intArr.length; ++i)
			sum += intArr[i];
		return sum;
	}
	
	static int add2D(int[][] intArr) {
		int sum = 0;
		
		for(int i=0; i<intArr.length; ++i) {
			for(int j=0; j<intArr[i].length; ++j) {
				intArr[i][j] = i + j;
				sum += intArr[i][j];
				System.out.print(intArr[i][j] + " ");
			}
			System.out.println();
		}
		return sum;
	}
}

```

  - **배열의 복사**
    - **for문을 이용**한 직접 복사 방식
    - **System.arraycopy(Object src, int srcPos, Object dest, int destPos, int len)**를 이용한 복사 방식
      - src : source object , 원본 객체
      - srcPos : sourcePosition , 원본에서 복사 시작할 위치
      - dest : destination, 복사본 객체
      - destPos : destinationPosition, 복사본에서 복붙 시작할 위치
      - len : length, srcPos부터 복사할 갯수

  - **향상된 for문**
    - for( 배열 원소 받을 인자 : 배열 명)
    - 배열 내를 순회하며 꺼낼 수 있는 인자를 모두 

```java

package StringEqualsExample;

public class exam04 {
	public static void main(String[] args) {
		
		int arr[] = new int[5];
		int max = 0;
		
		for(int i : arr) {
			i = (int)(Math.random() * 10);
			System.out.print(i +" ");
			if(max < i)
				max = i;
		}
		
		System.out.println("\nmax value : " + max);
	}
}

```






