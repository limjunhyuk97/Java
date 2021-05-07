# Exception
  - 문제 발생 가능성이 있는 부분에 대해서! -> 문제 발생을 커버할 수 있게 대비해주는 것!!



## Catch or Declare Rule
  - checked exception이 발생할 가능성이 있으면, method가 직접 catch하거나, declare하여 exception handle 책임을 반드시 전가해야 한다.
  - library 들은 exception을 보통 직접 handle하지 않음 (이유와 처리방식이 application마다 다르기 때문이다.)

### + method overriding
  - method가 override 되었다면, exception **throws**를 더 추가할 수 없다.
  - 그러므로, **exception 처리를 추가**해야 한다면, **해당 method 내에서 직접 catch**해야 한다.



## Throwing Exception

 |throw|throws|
 |:---:|:---:|
 |명령어|declaration|
 |예외를 발생시킴, 예외를 던짐|예외를 던짐|
 |(메소드 내) 상위 블록으로|현재 메소드 -> 호출 메소드로|
 |억지로 에러를 발생시킬 때 사용|-|
 |프로그래머의 판단에 따른 처리|프로그램의 처리|
 |예외에 추가 정보 넣어서 전달 가능|-|

```java
String readData(Scanner in) throws EOFException{
  ...
  while(...){
    if(!in.hasNext()){
      if(n < len){
        String gripe = "Content-length: " + len + ", Received: " + n;
        throw new EOFException(gripe);
      }
    }
  }
}
```



## Defining Exception Classes
  - library에 적당한 exception이 없다면?
  - 직접 Throwable의 subclass들에 대해서 extend 해서 **새로운 Exception class를 정의할 수 있다.**
```java
class FileFormatException extends IOException{
  public FileFormatException() {}
  public FileFormatException(String gripe){
    super(gripe);
  }
}
```



## Rethrowing and Chaining Exceptions
  - 나누어서 exception을 처리하고 싶을 경우 rethrow를 이용하면 된다.
  - layered system에서 연쇄적으로 exception을 전달할 수 있다.
  


## The finally Clause
  - try block에서 중간에 exception이 발생할 경우, **catch block으로 갔다가 finally block까지 실행되게 함**

```java
// 예시 1
class ExtendedNumberFormatException extends NumberFormatException{
	public ExtendedNumberFormatException() {}
	public ExtendedNumberFormatException(String str) {
		super(str);
	}
}

public class FinallyExample {
	public static void main(String[] args) {
		
		int num1, num2, ans;
		while(true) {
			
			// try 이중 구조
			try {
			Scanner sc = new Scanner(System.in);
			num1 = Integer.parseInt(sc.nextLine());
			num2 = Integer.parseInt(sc.nextLine());
				// 이 밑의 try-finally가 서로 쌍이다.
        try {
					if(num2 == 0)
						throw new ExtendedNumberFormatException("WTF");
					ans = num1 / num2;
				}
			  finally {
			  	System.out.println("HAHAHA!");
			  	System.out.println();
		  	}
        
			break;
			}
		
			catch(NumberFormatException ex) {
				if(ex instanceof ExtendedNumberFormatException) {
					System.out.println("Also throwed " + ex.getClass());
				}
				else {
					System.err.println("Exception : " + ex.getClass());
					System.out.printf("You must enter integers. Please try again %n%n");
				}
			}
			catch(ArithmeticException ex) {
				System.err.println("Exception : " + ex);
				System.out.printf("You must enter not 0 in denominator. Please try again %n%n");
			}
		}
	
		System.out.println(ans);
	
	}
}

// 예시 2
m(){
  OutputStream out = new FileOutputStream(...);
  try{
    // 1
    code that might throw exceptions
    // 2
  }
  catch{
    // 3
    show error message
    // 4
  }
  finally{
    out.close(); // 5
  }
  // 6
  }
}
// no exception throw : 1 2 5 6
// exception throw - no rethrow : 1 3 4 5 6
// exception throw - rethrow : 1 3 5 (rethrow의 catch 없으므로 6 생략)
// exception throw - not caught : 1 5 (rethrow의 catch 없으므로 6 생략)
```



## The Try-with-resources Statement
  - try(Resources res) { ... }를 통해서 catch로 넘어가서 실행 못된 객체.close()의 기능을 finally 없이 구현 가능.
  - 이때 Resource로 만들어줄 수 있는 객체는 AutoClosable functional interface를 implement하는 객체이어야 한다.
    - AutoClosable class의 void close() throws Exception 메소드가 정의되어야 한다.  
```java
try(Scanner in = new Scanner(Paths.get("in.txt"), "UTF-8");
    PrintWriter out = new PrintWriter("out.txt")){
 
 while(in.hasNext())
   out.println(in.next().toUpperCase());
 
}
```



## Tips for Proper Use of Exceptions
  - 일반적인 method를 통해서 test할 수 있는 상황에 대해서도 try-catch 구조를 사용하지 말자
  - try를 크게 묶자
  - throw - class를 할 때, 구체적인 exception관련 class를 throw - catch 하자








