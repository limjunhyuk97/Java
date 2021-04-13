# Interface


## Interface란 무엇인가?
  - **공통된 행돌들의 묶음**이다!
  - 개발 코드와 객체 사이를 이어주는 역할을 한다.
    - **개발 코드 -> 인터페이스의 추상 메소드 -> 실체 메소드, 재정의된 메소드 실행** 순 
  - 기능의 관점
    - **객체의 사용방식을 정의한 기본 사용설명서이다.**
    - **Java의 다형성을 극대화 시켜서, 개발코드의 수정을 줄이고, 유지보수의 효율성을 극대화 시킨다.**
  - 사용의 관점
    - **Interface는 reference type이기 때문에, 객체의 번지를 참조하며, 다형적으로 이용할 수 있다.**
    - **Interface 타입에 따라서 타입변환이 일어난다. (상속에서의 타입변환과 다형성의 성질과 유사)** 


## Interface를 왜 사용하는가?
  - 개발 코드를 변경해야하는 경우, 개발 코드에서 사용하는 메소드 일체를 통일된 규격으로 맞춰두었다면 유지보수가 수월해진다.
  - **다형성을 극대화시켜서 유지보수에 드는 비용을 줄이기 위해서!**
    - interface 하나와 여러 객체들을 묶어서 사용할 수 있다.
    - 개발 코드의 변경 없이 실행내용과 리턴값을 다양하게 만들 수 있다.


## Abstract class와 Interface의 차이점은?

|**interface**|**abstract class**|
|:---:|:---:|
|HAS-A|IS-A|
|행동의 공통성|속성의 공통성|
|기본 사용 설명서|미완의 설계도|
|class, 객체에 여러 interface 동시 적용(implement) 가능|class, 객체에 여러 abstract class 동시 적용(inherit)불가능|

![interface, abstract class](https://user-images.githubusercontent.com/59442344/114558944-2ab5e500-9ca6-11eb-80ab-49b07218daf4.png)

 
## Interface의 구성(4)과 구현

### 1.상수 필드
  - **절대적으로 수정이 불가능**
  - **Should be used in static way**
  - 생략되어도, 필드에 public static final 선언이 자동으로 이루어진다.
  - 선언과 동시에 초기화 요구

### 2. 추상 메소드
  - **강제적으로 재정의 되어야 한다.**
  - **인터페이스 변수, 클래스 변수를 통해서 재정의 된 메소드가 호출된다.**
  - 생략되어도, 메소드에 public abstract 선언이 자동으로 이루어진다.
  - interface를 사용하는 클래스에서 반드시 재정의 되어야 한다.
    - 재정의 시에 접근제한을 강화시킬 수 없다.

### 3. 디폴트 메소드
  - **선택적으로 재정의 하거나, 그냥 이용할 수 있다.**
  - **인터페이스 변수, 클래스 변수를 통해서 재정의 된 메소드가 호출된다.**
  - [public] default [반환형] [메소드명] ( [매개인자들] ... ) 형식을 갖는다.

### 4. 정적 메소드
  - **절대적으로 수정이 불가능**
  - **Should be used in static way**
  - [public] static [반환형] [메소드명] ( [매개인자들] ... ) 형식을 갖는다.

### 5. Interface 구현
  - Interface 변수 <- 구현(Implement) 객체를 할당 <- 구현 클래스로 생성.

```java
public interface RemoteControl {
  
  // 정적 상수 필드
	public static int MAX_VOLUME = 10;
	public static int MIN_VOLUME = 0;
	
  // 추상 메소드
	public void turnOn();
	public void turnOff();
	public void setVolume(int volume);
	
  // 디폴트 메소드
	public default void foo1() {
		System.out.println("This is foo1() in class RemoteControl");
		System.out.println("If you want to change this method, change it!");
	}
		
  // 정적 메소드
	public static void foo2() {
		System.out.println("This is foo2() in class RemoteControl");
		System.out.println("You can't change pre-defined foo2() method..!");
	}
}

// 구현 클래스
public class Television implements RemoteControl {
	
	private int volume;
	
	public void turnOn() {
		System.out.println("TV를 켭니다.");
	}
	
	public void turnOff() {
		System.out.println("TV를 끕니다");
	}
	
	public void setVolume(int volume) {
		if(RemoteControl.MAX_VOLUME < volume)
			this.volume = RemoteControl.MAX_VOLUME;
		else if(RemoteControl.MIN_VOLUME > volume)
			this.volume = RemoteControl.MIN_VOLUME;
		else
			this.volume = volume;
		
		System.out.println("current TV volume : " + this.volume);
	}
	
	// 구현 객체 내에서 default method를 재정의할 시에는 default 선언은 제외해준다.
	public void foo1() {
		System.out.println("foo1() overrided in Television class!");
	}
	
	/* 어짜피 foo2는 static method라서 구현 class 안에 존재할 수 없다!
	public void foo2() {
		System.out.println("foo2() overrided in Television class!");
	}
	*/
	
}

// 인터페이스 사용
public class RemoteControlExample {
    public static void main(String[] args) {
    
    // 인터페이스에 구현 객체 대입
    RemoteControl rc1 = new Television();
    RemoteControl rc2 = new Audio();
		
    rc1.turnOn();
    rc1.setVolume(5);
    rc1.foo1();  // 디폴트 메소드
    RemoteControl.foo2(); // 정적 메소드
    rc1.turnOff();
    System.out.println();
		
    rc2.turnOn();
    rc2.setVolume(5);
    rc2.foo1();  // 디폴트 메소드
    RemoteControl.foo2(); // 정적 메소드
    rc2.turnOff();
    
    }
}
```

## 다중 인터페이스 구현 클래스
  - 하나의 객체에 여러개의 interface가 구현되있을 수 있다.
  - 이때 객체는 **각 interface 내의 모든 추상 메소드에 대한 실체 메소드를 갖고 있어야** 한다.

```java
public class SmartTelevision implements Searchable, RemoteControl{
	...
}
```

## 인터페이스 사용
  - **class 내 다음과 같은 위치에 interface 타입을 사용**할 수 있다!
    - **필드** : 필드의 interface 변수에 구현 객체를 저장
    - **생성자 매개변수** : 생성자의 interface 매개변수에 구현 객체 전달
    - **생성자 로컬변수** : 생성자의 interface 로컬변수에 구현 객체 저장
    - **메소드 매개변수** : 메소드의 interface 매개변수에 구현 객체 전달
    - **메소드 로컬변수** : 메소드의 interface 로컬변수에 구현 객체 저장

```java

// Interface 와 구현 객체
public interface RemoteControl <- Television, Audio

// MyClass class
public class MyClass {
	
	RemoteControl rc = new Television();
	
	MyClass(){}
	
	MyClass(RemoteControl rc){
		this.rc = rc;
		rc.turnOn();
		rc.setVolume(3);
	}
	
	
	void methodA() {
		RemoteControl rc = new Audio();
		rc.turnOn();
		rc.setVolume(7);
	}
	
	void methodB(RemoteControl rc) {
		rc.turnOn();
		rc.setVolume(8);
	}
}

public class MyClassExample {
	public static void main(String[] args) {
		System.out.println("1--------------");
		
		// 필드 interface 구현 객체의 메소드 호출
		MyClass myClass1 = new MyClass();
		myClass1.rc.turnOn();
		myClass1.rc.setVolume(10);
		
		System.out.println("2--------------");
		
		// 생성자 매개변수로 interface 구현 객체 전달
		MyClass myClass2 = new MyClass(new Audio());
		
		System.out.println("3--------------");
		
		// 메소드 내부 로컬변수로 존재하는 interface 구현 객체 메소드 호출
		MyClass myClass3 = new MyClass();
		myClass3.methodA();
		
		System.out.println("4--------------");
		
		// 메소드의 매개변수로 전달된 interface 구현 객체의 메소드 호출
		MyClass myClass4 = new MyClass();
		myClass4.methodB(new Television());
		
	}
}
```
