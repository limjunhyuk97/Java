## Functional Interface 이란?
  - **하나의 function(기능)을 구현하기 위한 목적으로 만들어진 interface**다. 
    - interface -> class 구현 -> 객체 생성 -> 객체의 특징적 state 변화를 이용함 (X)
    - interface -> class 구현 -> 객체 생성 -> 객체를 function으로써 사용함(O)
  - functional interface는 하나의 추상메소드만을 멤버로 갖는 interface 개념이다.

```java
// 1. ActionListener
interface ActionListner extends EventListner{
  void actionPerformed(ActionEvent e);
}

class TimePrinter implements ActionListener{
  public void actionPerformed(ActionEvent event){
    // ...
  }
}

// main..
Timert = new Timer(4000, new TimePrinter());
t.start();
// actionPerformed의 기능을 위한 객체의 생성이다.


// 2. Comparator
interface Comparator<T>{
  int Compare(T o1, T o2);
}

class LengthComparator implements Comparator<String>{
  public int compare(String first, String second){
    // ...
  }
}

// main..
Arrays.sort(arr, new LengthComparator());
// compare의 기능을 위한 객체의 생성이다.
```


## Function과 Object의 차이
  - function : state가 변해가는 것과 무관하게, data input에 따라 output을 내놓을 뿐이다.
  - object : 어떤 state가 저장되어 있고, data input에 따라 state가 계속 변해간다.


## Functional Interface의 사용방식(3)

### 1. Functional interface에 대한 class 구현
  - 함수를 parameter로 바로 전달할 수 없으니 함수를 포함한 객체를 만들어 전달

### 2. Functional interface에 대한 annonymous inner class 구현
  - 필요한 곳에서 바로 interface 구체화한 객체를 전달 

### 2. Lambda Expression을 사용
  - interface의 abstract instance method를 lambda로 바로 구현하여 즉시 사용
  - 코드의 간결성 제공
  - functional interface에 더 많은 유연성 제공


## First class Citizen
  - **변수에 저장**할 수 있고,
  - 함수에 **매개변수로 전달**할 수 있으며,
  - **함수의 반환값**이 될 수 있으며,
  - **나중에 다시 여러번 실행**시킬 수 있고,
  - **동일 비교의 대상이 될 수 있다.**
  - 즉, **변수 다루듯이 다룰 수 있으며, 일반적으로 적용가능한 연산을 지원하는 객체를 1급 객체**라고 한다.



# Lambda Expressions
  - 일반적으로,
    - **익명의 function**이다.
    - 일급 객체이다.
  - Java에서,
    - Functional interface object가 사용될 것으로 예상되는 자리에서 사용된다.  
    - Functional interface의 instance를 대표한다.
    - **Functional interface의 기능을 구하기 위한 쉬운 방식! (코드의 간결성 제공)**

## Syntax of Lambda Expression

### General form : parameters -> code

#### parameters
##### 1. (type - parameter list) : (String first, String second) ->
##### 2. () / (no parameter) : () ->
##### 3. (parameter list) / (omit type) : (first, second) ->
##### 4. one parameter with inferres type / (omit parentheses) : event ->

#### code
##### 1. {} / (String first, String second) -> ['return' 생략가능] first.length() - second.length();
##### 2. {} / (String first, String second) -> {return 0;}

### Code Example

```java
// Example 1
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class LambdaTest {
	public static void main(String[] args) {
		String[] planets = new String[] {"Mercury",
				"Venus", "Earth", "Mars", "Jupiter",
				"Saturn", "Uranus", "Neptune"};
		
		System.out.println(Arrays.toString(planets));
		System.out.println("Sorted in dictionary order : ");
		Arrays.sort(planets);
		System.out.println(Arrays.toString(planets));
		
    // omitted type(String), omitted return
		System.out.println("Sorted by length : ");
		Arrays.sort(planets, (first, second)->first.length() - second.length());
		System.out.println(Arrays.toString(planets));
    // Compiler실행과 lambda expression
    // 1. Compiler가 sort(String[] a, Comparator<String> c)를 호출한다.
    // 2. c에게 lambda expression을 넘겨준다.
    // 3. c.compare(a[i], a[j])가 lambda expression을 통해서 이루어 진다.
		
    // omitted type(ActionEvent)
		Timer t = new Timer(1000, event ->System.out.println("The time is "+ new Date()));
		t.start();
		
		// keep program running until user selects "OK"
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}


// Example 2
public interface MyFunction {
	public double apply(double v);
}

public interface MySupplier {
	public double get();
}

public class MyFunctionMySupplierTest {
	public static void main(String[] args) {
		
		MyFunction func;
		func = (n)-> 10.0 / n;
		// interface 변수안에 익명의 lambda expression을 넣는다.
    // omitted type, omitted return
		
		MySupplier supply = ()->98.0;
		
		System.out.println(func.apply(4.0));
		System.out.println(func.apply(8.0));
		System.out.println(supply.get());
		
	}
}
```

## Lamda Expression의 사용

### 1. Functional interface가 사용되는 장소
  - 변수 선언과 할당
  - 메소드나 생성자의 매개변수로 사용
  - return으로 반환
  - 삼항 연산자
  - 타입변환과 같이 쓰일 수 있다.
  - 
### 2. High-order function
  - 합성함수의 개념으로, 함수에 함수를 전달할 때.

### 3. Anonymous inner class
  - interface를 바탕으로 객체를 생성하여 바로 전달하는 방식이 익명 객체 전달 방식
  - 익명 객체의 사용을 대신하여 Lambda Expression 사용

## Lambda Expression과 Generic Functional Interface
  - **java.util.function에서 generic functional interface를 정의**해준다.

### 1. Predicate
```java
public interface Predicate<T> {
  boolean test(T t);
}

// predicate example
import java.util.function.Predicate;
import static java.lang.Math.*;

public class PredicateExample {
	
	// generic interface를 사용하려면 generic type을 구체화 해주어야 한다.
	public static int filter(Predicate<Integer> p, int num) {
		if(p.test(num))
			return 5;
		else
			return 0;
	}
	
	public static void main(String[] args) {
		
		int[] score = new int[10];
		int filter1 = 0, filter2 = 0;
		
		for(int i=0; i<10; ++i) {
			score[i] = (int)(random() * 100) + 1;
		}
		
		for(int i = 0; i<10; ++i) {
			System.out.printf("%d 번째 학생 점수 = %d\n", i+1, score[i]);
			// predicate에 대한 lambda expression
			filter1 += filter((n)->{ return n>80;}, score[i]);
			filter2 += filter(n->{if(n>20) return true; else return false;}, score[i]);
		}
		System.out.println("filter1 : " + filter1);
		System.out.println("filter2 : " + filter2);
		
	}
}

```

### 2. Consumer
```java
public interface Consumer<T> {
  void accept(T t);
}

// Consumer Example
// interface Consumer를 들여올 수 있다.
import java.util.function.*;

public class ConsumerExmaple {
	
	// interface Consumer를 들여와서 매개수 타입으로 사용할 수 있다.
	public static void filter(Consumer<Student> a, Student s) {
		a.accept(s);
	}
	
	public static void main(String[] args) {
		Student[] sList = new Student[5];
		for(int i=0; i<5; ++i) {
			sList[i] = new Student();
		}
		sList[0].name= "tom";
		sList[1].name= "jack";
		sList[2].name= "megan";
		sList[3].name= "chris";
		sList[4].name= "brown";
		
		for(int i=0; i<5; ++i) {
			sList[i].score = (int)(Math.random()*100) + 1;
			System.out.print(sList[i].name + " , score : " + sList[i].score);
			filter(a->{
				if(a.score>80) System.out.printf(" : A Grade");
			else if(a.score>60)System.out.printf(" : B grade");
			else if(a.score>40)System.out.printf(" : C grade");
			else System.out.print(" : failed");
				System.out.println();}, sList[i]);
			
		}

	}
}
```

### 3. Supplier
```java
public interface Supplier<T> {
  T get();
}

// Supplier Example
import static java.lang.Math.random;

import java.util.function.*;

public class SupplierExample {
	
	public static void compliment(Supplier<String> g, Student s) {
		if(s.score >=80)
			System.out.print(g.get());
	}
	
	
	public static void main(String[] args) {
		
		
		Student[] sList = new Student[5];
		// list 각각에 객체 부여하는 것 잊으면 안됨..!
		for(int i=0; i<5; ++i) {
			sList[i] = new Student();
		}
		sList[0].name= "tom";
		sList[1].name= "jack";
		sList[2].name= "megan";
		sList[3].name= "chris";
		sList[4].name= "brown";
		
		for(int i=0; i<5; ++i) {
			sList[i].score = (int)(Math.random()*100) + 1;
			System.out.print(sList[i].name + " , score : " + sList[i].score);
			compliment(()->{return " Great job!";}, sList[i]);
			System.out.println();
		}
		
	}
}

```

### 4. Function
```java
public interface Function<T, R> {
 R apply(T t);
}

// Function Example
import java.util.function.*;
import java.util.Scanner;

public class FunctionExample {

	public static int newOp(Function<Integer, Integer> f, int n) {
		return f.apply(n);
	}
	
	public static void main(String[] args) {
		int num;
		Scanner sc= new Scanner(System.in);
		
		System.out.print("number 1 : ");
		num = Integer.parseInt(sc.nextLine());
		System.out.println(" ^^ " + num +" = " + newOp( (a)->{return a*a - a*(a/2);} ,num));
		
	}
}

```







