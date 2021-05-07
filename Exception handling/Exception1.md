# Exception



## Error 발생의 종류

#### 1. User input error - 사용자의 잘못된 input
#### 2. Device error - 환경
#### 3. Physical limitation - 메모리 공간
#### 4. Code error - 코드상 에러



## Error 대응 원칙

#### 1. Error에 대한 description 제공
#### 2. 이전 작업들 저장가능하게 함
#### 3. program이 투박하게 종료되지 않게 함



## Error 대응

#### 1. method에서는 -1, null 등을 반환
#### 2. calling method에서는 반환값 확인, 에러 출력, 프로그램 종료 등 ..
#### 3. EXCEPTION HANDLING
  - Exception handling을 통해 프로그램을 종료하지 않고 정상 실행 상태를 유지할 수 있다.
  - Java는 컴파일 시에 예외 처리 유무를 확인한다 : 문제 발생 가능성 O, 예외 처리 코드 X = 컴파일 X



## Exception의 종류

![exception](https://user-images.githubusercontent.com/59442344/117400308-49259e00-af3d-11eb-92f6-723c7ee87200.png)

### 시점에 따른 Exception 구분

### 1. Checked-exception
  - exceptions checked at compile time
  - 실행 시에 예외 발생 가능성이 높기 때문에, 컴파일 과정에서 관련 예외 처리 코드가 있는지 검사.
  - 예외 처리 코드가 없으면 컴파일 에러
  - **Error, Runtime Exception 와 그 sub class들을 제외한 모든Exception**

### 2. Unchecked-exception
  - 실행 시에 예외 발생 가능성을 예측할 수 없기 때문에, 컴파일 과정에서 관련 예외 처리 코드가 있는지 검사 안함.
  - **Error, Runtime Exception**의 sub class들 해당

### 3가지 Exception

<img src="https://user-images.githubusercontent.com/59442344/117425873-32dc0a00-af5e-11eb-8d40-a768c742350b.jpg" height=70% width=70%>

### 1. Error Exception (unchecked Exception)
  - Java runtime system의 내부적인 error와 resource exhaustion 을 잡아낸다.
  - **개발자가 보통 만지는 부분이 아니고**, Java에서 자체적으로 던지는 error이다.

### 2. Runtime Exception (unchecked Exception)
  - 코드 상의 **치명적이지 않은 예외 상황을 발생시키는 경우**에 대한 exception
  - 코드 상의 오류가 이를 통해 발견이 되면, 개발자는 웬만하면 logic을 수정해야하고, 일부 경우는 exception-handling 해준다.
  - (예)
    - 잘못된 type casting
    - null pointer access
    - out-of-bound array access 

### 3. IO Exception (checked Exception)
  - **치명적인 예외상황을 발생시키는 경우이므로** 예외처리/수정 강제
  - (예)
    - 존재하지 않는 파일을 열려고 하는 경우
    - 파일 끝을 지나서 읽으려고 시도하는 경우

### Predefined / User-defined

### 1. Predefined Exception class 
  - 미리 정의되어 있는 exception class
### 2. User-defined Exception class 
  - Predefined Exception class를 상속하여 사용자 새롭게 정의한 exception class



## Exception Handling

### Throw(try{}) - Handle(catch{})
  - Exception class의 객체를 던지고 받는다.

#### try{ } block
  - Exception을 throw할 가능성이 있는 부분을 try{} 블록으로 묶는다.

#### catch{ } block
  - Exception에 대해서 처리할 코드를 기술한다.

![exception handling](https://user-images.githubusercontent.com/59442344/117405265-53986580-af46-11eb-80e5-3884ed0fb807.png)

#### no-matching clause
  - main을 종료하고, System으로 exception을 던지고, System은 stack tracer를 출력하고, 무조건 프로그램 종료.

```java
import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean continueLoop = true;
		
		do {
			try {
				System.out.print("Please enter numberator : ");
				int numerator = Integer.parseInt(sc.nextLine());
				System.out.print("Please enter denominator : ");
				int denominator = Integer.parseInt(sc.nextLine());
				
				int result = quotient(numerator, denominator);
				System.out.printf("%nResult: %d/ %d = %d%n",numerator,denominator, result);
				continueLoop = false;
			}
			catch(NumberFormatException ex) {
				System.err.println("Exception : " + ex);
				System.out.printf("You must enter integers. Please try again %n%n");
			}
			catch(ArithmeticException ex) {
				System.err.println("Exception : " + ex);
				System.out.printf("You must enter not 0 in denominator. Please try again %n%n");
			}
		}while(continueLoop);
	}
	
	public static int quotient(int num, int denom) {
		return num/denom;
	}
}

```



## Catching Multiple Exceptions
  - Exception hierarchy를 고려했을 떄,
    - **더 specific한 catch가, general한 경우보다 exception을 먼저 잡는다.**
    - 그러므로 더 specific한 catch를, general한 경우보다 먼저 선언해주어야 한다. (아니면 compile error)



## Declaring Exceptions
  - method안에서 exception이 발생하는 경우를 'throws' 시키기 위해서 시그니처에 'throws' clause를 정의할 수 있다.
  - **runtime exception(unchecked exception)은 굳이 'throws'를 declare 해줄 필요가 없다!** (알아서 내보낸다.)

```java
// 굳이 ArithmeticException을 선언하지 않아도 catch에서 잡을 수 있음
public static int quotient(int num, int denom) throws ArithmeticException { ... }
public static int quotient(int num, int denom) throws ArithmeticException, NumberFormatException { ... }
```


