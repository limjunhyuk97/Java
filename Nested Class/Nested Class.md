# Nested class

![inner_classes](https://user-images.githubusercontent.com/59442344/116960616-e7b2c480-acdb-11eb-9970-2c24d3378042.jpg)


## Nested class의 종류
 
### 1. member class
  - static member class
  - instance member class
### 2. local class
  - local inner class
  - local anonymous inner class

```java
public class NestedExample {
	
	// outerclass instance field
	public int universalField;
  
  // outerclass static instance field
  public static int universalStaticField;
	
	
	// static member class
	public static class A{
		public int field1;
		public static String field2;
		
		public A() {}
		
		public void method1() {
			System.out.println("A");
		}
		public static void method2() {
			System.out.println("A");
		}
	}

	
	
	// instance inner class 
	public class B{
		public int field1;
		// public static int field2;
		
		public B() {}
		
		public void method1() {
			System.out.println("I'm instance member class!");
			System.out.println(universalField);
		}
		// public static void method2() {}
	}
	
	
	
	// local inner class In Instance method
	public void outermethod1() {
		
		// local inner class
		class C{
			int field1;
			// static int field2;
			
			C(){}
			
			void method1() {}
			// static void method2() {}
		}
		
	}
	
	// local anonymous
	public static void main(String[] args) {
		method(new NestedExample());
	}
	
}
```


## Nested class 사용의 이유
  - **encapsulation의 역할**을 수행할 수 있다. (같은 package의 다른 class로 부터 숨길 수 있기에)
  - **readable하고, maintainable한 code**를 구성할 수 있다. (관련 있는 요소들끼리 묶어 두었기에)
  - **nested class가 outer class에 정의된 data에 direct하게 access**할 수 있다.

-----

# Static member class
  - **static member라는 것은 class에게 속한 member라는 것이다.**
  - static member class를 사용하는 **2가지** 방법
    - **instance를 생성하여 static member class instance의 instance 멤버를 사용**
    - **instance를 생성하지 않고, static member class의 static 멤버를 사용** 

### 특징
  - **static member / instance member 모두 갖을 수 있다**
  - **outer의 멤버가 아니다. outer안에 있을 뿐!**
    - **outer class 객체 생성과 무관하게, inner class 객체 생성가능**하다.
    - **Access modifier가 붙을 수 있다.**
    - **outer instance member에 접근할 수 없다.**
    - **outer static memeber에 접근할 수 있다.**
  - ***내부 member가* static 이라는 것은 객체를 생성하지 않고 사용할 수 있다는 것이다.** 

```java
import java.util.*;

public class StaticNested {
	
	public static class Pair{
		public double first;
		public double second;
		public Pair(double first, double second) {
			this.first = first;
			this.second = second;
		}
		
		public String toString() {
			return "x : " + first + ", y : " + second;
		}
	}
	
	public static Pair minmax(double[] values) {
		
		double min, max;
		Arrays.sort(values);
		min = values[0];
		max = values[values.length-1];
		
		// outer class 객체와 별개이다.
		return new Pair(min, max);
	}
	
	public static void main(String[] args) {
		
		double[] arr = new double[10];
		for(int i=0; i<arr.length; ++i) {
			arr[i] = (double)(Math.random()*1000)/10;
		}
		
		System.out.println(minmax(arr));
	}

}
```

-----
  
# Instance inner (member) class
  - **instance member라는 것은 instance에게 속한 member라는 것이다.**
  - instance member class를 사용하는 **1가지** 방법
    - **instance를 생성하여 instance inner class의 instance 멤버를 사용**

### 특징
  - **instance member만 갖을 수 있다**
  - **outer의 멤버이므로!**
    - **outer class 객체가 생성되어야, inner class 객체가 생성**될 수 있다.
      - outer class [외부 method]에서 instantiate 할 때 해당
      - outer class [내부 static method]에서 instantiate 할 때 해당
      - 외부 class 객체 생성 -> 내부 class 객체 생성 [(outer_class).new (inner_class)( ,,, )] 
    - **Access modifier가 붙을 수 있다.**
    - **outer member에 접근할 수 있다.**
      - outer member과 이름이 겹친다면 어떻게 접근해야 할까?
      - outer member : outer_class.this.(멤버명)
      - inner member : (멤버명)  

### 객체 생성 - (1) 동일 outerclass 내 instance method 객체 생성
  - 원래 객체 생성방식과 동일하다. ( = new inner_class명(,,,) )
  - this.new inner_class명(,,,) 에서 this가 생략된 것이다.

```java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

// public class
public class InnerClassTest {
	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
	}
}


// class
class TalkingClock{
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start() {
		// ActionListener를 implement한 listener 이름의 TimePrinter 객체가 생성
		ActionListener listener = this.new TimePrinter();
		
		// TalkingClock의 interval 정보를 받아옴
		Timer t = new Timer(interval, listener);
		t.start();
	}
	
	// 내부 instance inner class TimePrinter class가 내부에 따로 정의 된 것임
	public class TimePrinter implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			System.out.println("At the tone, the time is " + new Date());
			// TalkingClock의 beep 정보에 접근할 수 있다!!
			if(beep) Toolkit.getDefaultToolkit().beep();
		}
	}
	
}
```

### (2) 사용방식 - 동일 outerclass 내 static method 객체 생성
  - outer class를 생성하고 inner class를 생성해야 한다.
  - outer_class명.new inner_class명(,,,) 
 
```java
public class example3 {
	
	// non-static inner class
	public class foo{
		public void foo() {
			System.out.println("FOO!");
		}
	}
	
	public static void main(String[] args) {
    // static method에서는 inner class 객체 생성을 위해서 outer class 객체 먼저 생성
		example3 ex3 = new example3();
		example3.foo ex3foo = ex3.new foo();
		
		ex3foo.foo();
		System.out.println(ex3foo.getClass());
    // 
	}
}
```

### (3) 사용방식 - 동일 outerclass 외부 method 객체 생성
  - outer class를 생성하고 inner class를 생성해야 한다.
  - outer_class명.new inner_class명(,,,) 

```java
public class example4 {
	public static void main(String[] args) {
		foo F = new foo();
		foo.inner in = F.new inner();
		
		in.method();
	}
}

class foo{
	public class inner{
		public void method() {
			System.out.println("FOOO~");
		}
	}
}

```

-----

# Local inner class
  - **non-static method 내에서 정의된 class** 이다.

### 특징
  - **Instance member만 갖을 수 있다.**
  - **method내부에서만 사용되므로**
    - **class 에 대해서, Access modifier가 필요 없다 (member들에 대해서는 필요하다)**
    - **외부에서 이 class에 대한 instance를 만들 수 없다.**
    - **instance 에 접근할 수 있다.**
  - **Captures variables**
    - lambda expression에서처럼, class instance의 외부 값이 capture되면, final처럼 변경되지 않는다. 

```java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class InnerClassTest {

	public static void main(String[] args) {
  
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
    
	}
  
}

class TalkingClock {
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start() {
		
		class TimePrinter implements ActionListener {
    
			public void actionPerformed(ActionEvent event) {
				System.out.println("At the tone, the time is " + new Date());
				// beep는 여기서 capture되어 effectively final 해진다.
				if(beep) Toolkit.getDefaultToolkit().beep();
			}
      
		} // local inner class end
		
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(interval, listener);
		t.start();
    
	} // start method end
	
}
```

-----

# Anonymous inner class
  - non-static method 내에 선언된 익명 class (declared without class name)
  - 한번만 사용되는 객체는 굳이 변수에 할당하는 과정이 필요 없다!
  - **class type이나, interface type에 대해서, sub class나, implementation class를 정의함과 동시에 객체 생성**

```java
// 익명 객체 생성방식

new SuperType( superclass constructor parameter ,,){
  // () 안에는 super class의 생성자에 대한 매개인자가 들어간다. {} 내에 sub-type 생성자 없음.
  // 또한 interface에는 생성자 없으므로, ()에 아무것도 넣지 않음.
  
  public class SuperType{
    // sub-class의 확장 : 새로 정의된 sub-type class의 method나 field에 대한 정의
  }
  
}

// 예시
Arrays.sort(arr, new comparator(){
  compare(Obect) 
})
```

```java
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class InnerClassTest {

	public static void main(String[] args) {
  
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
    
	}
  
}

class TalkingClock {
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval, boolean beep) {
		this.interval = interval;
		this.beep = beep;
	}
	
	public void start() {
  
    // 익명 객체를 통해 간결하게 구현할 수 있다.
		Timer t = new Timer(interval, new Actionlistener(){
      public void Actionlistener{
        System.out.println("At the tone, the time is " + new Date());
        if(beep) Toolkit.getDefaultToolkit().beep();
      }
    });
		t.start();
    
	} // start method end
	
}
```

