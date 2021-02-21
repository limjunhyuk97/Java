# Java 변수와 타입


### 1. Java 변수 설정

  - 변수에 값을 할당해주지 않으면 변수는 생성되지 않는다.
  
### 2. Java 기본 타입

  - 기본 타입 8개
    - 정수 타입 5개 : byte , short , char(유니코드의 정수값 저장) , int , long
    - 실수 타입 2개 : float , double
      - float의 정밀도 : 소수점 이하 6자리까지
      - double의 정밀도 : 소수점 이하 15자리까지
    - 논리 타입 1개 : boolean
    - 정수 타입으로 선언된 변수에 정수 리터럴을 대입해서 정수값 저장 가능하다.
    
  - 정수 리터럴이란?
    - 리터럴 : 소스코드에 입력된 값
    - 리터럴 중에 정수로 인식되는 것이 정수 리터럴
    - **기본적으로 컴파일러는 정수 리터럴을 int형으로 알아먹으니, int 범위를 넘는 것을 정수형으로 받아들이게끔 하려면 L 붙여야 함.**
    
```java
long balance = 30000000000L
```
    - 정수 리터럴 표현 예시
      - 2진수 : 0b1011
      - 8진수 : 0132
      - 10진수 : 12 , 28
      - 16진수 : 0xB12
      
  - 문자 리터럴이란?
    - '단일문자'형식의 값
    - 문자리터럴은 유니코드로 변환되어 저장된다. : 기본타입 - 정수타입 - char형 - 문자 리터럴 저장
    
  - 문자열 리터럴이란?
    - "여러개의 문자"형식의 값
    - **문자열리터럴은 유니코드로 변환되지 않는다. : class타입 - String형 - 문자열 리터럴 저장**
    
  - 실수 리터럴이란?
    - 소수점이 있는 숫자 리터럴
    - **기본적으로 컴파일러는 실수 리터럴을 double형으로 알아먹으니, float형에 저장하면 컴파일 에러가 발생한다.**
    - float형에 저장하고자 하면 0.12f 처럼 f나, F를 붙여야 한다.
    - **e나, E를 통해서 표현된 숫자리터럴은 컴파일러가 지수, 가수로 표현된 소수점이 있는 double형 실수로 받아들인다.**
    
### 3. 타입 변환

  - 자동 타입 변환
    - '허용 범위가 큰 타입 = 허용 범위가 작은 타입' 식으로 대입한다면 자동으로 타입변환이 일어나 대입된다.
    - 허용 범위 순서 : byte < short(char) < int < long < float < double.
      - char형 = byte형 X : byte형은 음수를 다루지만, char형은 그렇지 않기 때문.
      - 실수형 = 정수형 무조건 가능
     
  - 강제 타입 변환
    - '허용 범위가 작은 타입 = 허용 범위가 큰 타입'식의 연산을 캐스팅 연산자 괄호()를 통해서 수행한다.
  
  - **산술연산식에서의 변수 강제 타입 변환**
    - 산술 연산식의 피연산자가 byte형이나, short형 변수라면, 변수들은 자동으로 int형으로 형변환되고, 연산결과도 int형으로 저장해야 한다.
    - 산술 연산식의 피연산자에 int형보다 범위가 큰 long타입이 존재한다면, 연산결과를 long형으로 저장해야 한다.
      - 단, 리터럴간의 연산에서는 int형 변환이 필요없다.
    - 실수 변수가 있는 산술 연산식에서도,
      - 피연산자들끼리 같은 타입이라면, 그 타입으로 연산한다. "float = float + float"
      - 피연산자들끼리 다른 타입이라면, 가장 범위가 큰 타입으로 변환되어 연산결과가 저장된다.
    - 특별한 경우들에 한해서, 위의 경우를 염두해두고 형변환을 강제해주면 된다.
    
```java
public class FOO{
  public static void main(String args[]){
    
    // 피연산자 - int 형 타입 변환
    byte type1 = 3;
    short type2 = 2;
    int type3 = type1 + type2;
    System.out.println(type3);
    
    // 피연산자 - double 형 타입변환
    float typeA = 2;
    double typeB = 3.4;
    dobule typeC = typeA + typeB;
    
  }
}
```
  
  - **'+'연산자의 두가지 기능**
    - 피연산자가 숫자(정수형, 실수형 - 여기에 char형의 문자 포함이다.)일 경우 덧셈연산을 한다
    - 피연산자에 String형의 문자열이 등장하면 문자열 결합연산을 한다.
    - **왼쪽에서 오른쪽으로 연산을 진행하면서, String형이 등장하는 순간부터 변수들이 문자열로 취급되어, 문자열 결합연산을 하게된다.**

```java
public class FOO{
  public static void main(String args[]){
    
    System.out.println(2 + 3 + "a" + 2 + 3);
    // 5a23 출력
    System.out.println(2 + (3 + "a") + 2 + 3);
    // 23a23 출력
    
  }
}
```
  
  - **문자열 -> 숫자 , 숫자 -> 문자열**
    - 문자열을 숫자로 바꾸고 싶다!
      - byte로 -> Byte.parseByte(String_type);
      - short -> Short.parseShort(String_type);
      - int -> Integer.parseInt(String_type);
      - long -> Long.parseLong(String_type);
      - float -> Float.parseFloat(String_type);
      - double -> Double.parseDouble(String_type);
      - boolean ->Boolean.parseBoolean(true or false);
    - 숫자를 문자열로 바꾸고 싶다!
      - String.valueOf(기본타입 값)

```java
public class FOO{
  public static void main(String[] args){
  
    int value1 = Integer.parseInt("3000");
    // value1에 3000 들어감
    
    String str1 = String.valueOf(455);
    // String형 str1에 455가 저장된다.
    
  }

}
```

### 4. 값의 입출력

 - 출력
   - System.out.println()
   - System.out.print()
   - System.out.printf()
   
 - 입력
   - System.in.read()
   - **Scanner scanner = new Scanner(System.in);**
     - 스캐너 객체 생성...!
     - System.in은 입력값을 byte 단위로 읽어들이는 것을 의미함
     - scanner.next() : 다음 단어, 공백 기준
     - scanner.nextLine() : 다음 문장, 개행문자 기준
     - scanner.nextInt() : 다음 정수
     - scanner.nextDouble()...

```java

import java.util.Scanner;
// java.util 패키지에서 Scanner 외부 클래스를 Import하는 것이다.

public class FOO{
  public static void main(String[] args){
  
    Scanner scanner = new Scanner(System.in);
    // cpp에서 객체 생성하는 것과 상당히 유사한 느낌이다..!
    // Scanner 클래스를 통해서 scanner라는 객체를 생성하는 과정이다.
    String inputString;
    
    while(true){
      inputString = scanner.nextline();
      // scanner 객체 안의 nextline() 메소드를 사용하는 것이다.
      
      if(inputString.equals("q")){
        break;
      }
      // string 객체는 객체간의 동등비교를 할 때 .equals()하는 내부 메소드를 사용한다.
      // 기본 자료형들은 == 동등비교연산을 통해 동등비교를 수행한다.
      
    }
  
  }

}
```

### 5. Java 참조 타입

  - Java 참조 타입 4개
    - 배열 타입
    - 열거 타입
    - 클래스
    - 인터페이스
    
  - Java 참조 타입의 정보 저장 방식
    - Java **참조 타입은 번지 정보를 저장**하고, **번지를 참조하여, 번지 내에 있는 정보에 접근**하는 것이다! 
      - 마치 cpp / c의 *포인터 정보 저장, &참조 위치 저장 과 비슷한 느낌이다.
      
  - **JVM 메모리 영역 (Runtime Data Area)**
    - **메소드 영역 (Method Area)**
      - 모든 스레드가 공유하는 영역이다.
      - 코드에서 사용하는 클래스( .class )들을 클래스 로더로 읽는다.
      - 정적필드와 상수 / 메소드 코드 / 생성자 코드 등을 분류해서 저장한다.  
    - **힙 영역 (Heap Area)**
      - 생성된 객체와 배열을 저장해두는 곳이다.
      - 생성된 객체와 배열을, JVM 스택영역의 변수나, 다른 객체의 필드에서 참조한다.
      - 참조하는 변수나 필드가 없다면 알아서 쓰레기 수집기를 실행시켜서, 객체나 배열을 제거한다.
    - **JVM 스택 영역 (Stack)**
      - 메소드가 호출될 경우마다 프레임을 추가하고, 종료되면 프레임을 제거한다.
        - 프레임 내부에는 로컬 변수 스택이 있다.
        - 기본 타입 변수나, 참조 타입 변수가 추가되거나 제거된다.
        - 블록 내에서 처음으로 **값이 저장될 때** 변수가 생성, 추가 된다.
        - 실행 흐름이 블록을 벗어난다면 변수가 제거된다.
    - **참조 타입과 기본 타입 변수의 JVM 스택 영역에서의 값 처리 방식**
      - 기본 타입 변수는, 스택 영역에 직접 값을 가지고 있다.
      - 참조 타입 변수는, 스택 영역에 힙 영역의 객체, 또는 배열 주소를 가지고 있다. (Java 에서 배열은 객체 취급이 됨.)
        - **JVM 스택 영역 내의, 프레임 속의 참조 변수는, 힙영역에 존재하는 실제 생성된 객체의 주소, 그 내부값을 참조한다!!**
  
  - Java 참조 변수와 == != 연산
    - 참조 변수가 참조하고 있는 주소의 값이 동일한 가를 판단할 때 사용한다. (서로 같은 객체를 가리키고 있는가?)
  
  - Java 참조 변수와 null 연산
    - 참조 변수가 아무것도 가리키고 있지 않으면 null 값을 저장할 수 있다.
    - 값이 저장된 것으로 판단하기에, jvm 스택 영역에 생성된다.
    - null이 저장된 참조 변수 내의 값에 접근하려고 하면 nullpointerexception이 발생한다.
  
  - String class의 생성과 비교
    - **문자열 리터럴**이 동일하다면, String 객체를 공유하도록 되어 있다.
    - **new 객체 생성 연산자**를 통해서 객체를 만들경우, 다른 참조변수에 같은 문자열 리터럴을 집어넣더라도, 다른 객체 공간을 참조하게 된다.
      - **new는 힙 영역에 새로운 객체를 생성하는 객체 생성연산자이다!!**
    - 내부 문자열 정보의 동일여부를 비교하고 싶다면, .equals() 메소드를 사용해야 한다!
    
```java

String name1 = "Lim";
String name2 = "Lim";
// name1 == name2

String name3 = new String("Jun");
String name4 = new String("Jun");
// name3 != name4

boolean bool = name3.equals(name4);
// name3 문자열 == name4 문자열 이므로, true 값 반환함.

```
    
    
    
    
    
    
    
  
  
  
  
