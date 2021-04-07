# Promotion(타입 변환)

## 타입변환의 방법
  - 기본 타입에서 뿐만 아니라, class 사이에서도 상속관계에 의거해서 타입변환이 일어날 수 있다.
  - **같은 객체를 가리키지만, 다른 타입을 갖게** 할 수 있다.

```java
상위타입 class  상위타입 변수 = 하위타입 객체

class A{}
class B extends A{}
class C extends B{}
class D extends A{}
class E extends D{}
// A<-B<-C , A<-D<-E

public class Promotion {
	public static void main(String[] args) {
		B b = new B();
		C c = new C();
		D d = new D();
		E e = new E();
		
		A a1 = b;
		A a2 = c;
		A a3 = d;
		A a4 = e;
		
		B b1 = c;
		D d1 = e;
		
	}
}
```

## 타입변환의 결과
  - **상위 class에 선언된 member들(field와 method들)에만 접근이 가능**하다.
  - 단, **메소드가 하위 class에서 재정의 되었다면, 재정의된 메소드가 호출**된다 : **간접적 호출이든, 직접적 호출이든**
    - cpp에서는 자료형 기준 member 호출 + (virtual) 가상함수를 도입해서 객체 기준 함수 호출을 유도했다.
    - java에서는 자료형 기준 member 호출 + (기본적으로) 객체 기준 재정의된 메소드 호출

```java
public class Parent {
	public void method1() {
		System.out.println("Parent's method1() called");
	}
	public void method2() {
		System.out.println("Parent's method2() called");
	}
}

public class Child extends Parent{
	public void method2() {
		System.out.println("Child's method2() called");
	}
	public void method3() {
		System.out.println("Child's method3() called");
	}
}

public class Test {
	public static void main(String[] args) {
		Child child1 = new Child();
		Parent parent1 = child1;
		
		parent1.method1();
		parent1.method2();
		//parent1.method3(); 호출 불가능
		
		child1.method1();
		child1.method2();
		child1.method3();
	}
}
```

## 왜 타입변환을 하는가?
  - 프로그램 성능의 업그레이드는 **마치 자동차 부품의 교환처럼 객체단위의 교환**을 통해서 이루어질 수 있다.
  - 이러한 **프로그램의 부품으로서의 객체들을 계속 교환**해주기 위해서 **상속, 재정의, 타입변환을 이용**하는 것이다.
    - **(객체)필드의 다형성 : 부품 갈아끼우기**
    - **매개변수의 다형성 : 파생 객체들에게 요청하기**
    - **강제 타입변환 전 객체 타입 조사 : 강제 타입 변환 가능성 확인하기**

### 1. 필드의 다형성 (부품 갈아끼우기)
  - **필드 객체**의 타입을 **부모타입으로 선언**하면 다양한 **자식객체들이 저장**될 수 있다.
  - 하나의 필드 객체 타입 <- 그 타입에서 파생된 여러 자식들을 동일한 타입에 갈아끼운다. 
    - 자식 class가 부모 class의 **field와 method를 그대로 갖고 있으니, 이용방식이 같다.**
    - 자식 class에서 **재정의된 부모 class의 method를 통해 코드 개선**이 가능하다.
    - **자식 타입을 부모 타입으로 변환이 가능**한 성질.
  -  그러므로, **필드 객체**가 자식 객체로 갈아끼워진다면, **객체의 메소드 실행의 전반을 고치지 않아도, 성능향상이 가능**하다! 

```java
public class Tire {
	public int maxRotation;
	public int accumulatedRotation;
	public String location;
	
	public Tire(String location, int maxRotation) {
		this.location = location;
		this.maxRotation = maxRotation;
	}
	
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) {
			System.out.println(location + "수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else {
			System.out.println("*** "+location+" Tire 펑크! ***");
			return false;
		}
	}
}

public class HankookTire extends Tire {
	
	public HankookTire(String location, int maxRotation) {
		super(location, maxRotation);
	}
	
	@Override
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) {
			System.out.println(location + "HankookTire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else {
			System.out.println("*** "+location+" HankookTire 펑크! ***");
			return false;
		}
	}
}

public class KumhooTire extends Tire {
	
	public KumhooTire(String location, int maxRotation) {
		super(location, maxRotation);
	}
	
	@Override
	public boolean roll() {
		++accumulatedRotation;
		if(accumulatedRotation < maxRotation) {
			System.out.println(location + "KumhooTire 수명 : " + (maxRotation - accumulatedRotation) + "회");
			return true;
		}
		else {
			System.out.println("*** "+location+" KumhooTire 펑크! ***");
			return false;
		}
	}
}

public class Car {
	
	Tire frontLeftTire = new Tire("앞 왼쪽", 6);
	Tire frontRightTire = new Tire("앞 오른쪽", 2);
	Tire backLeftTire = new Tire("뒤 왼쪽", 3);
	Tire backRightTire = new Tire("뒤 오른쪽", 4);
	
	int run() {
		System.out.println("Car rolling~");
		if(frontLeftTire.roll()==false) {stop(); return 1;}
		if(frontRightTire.roll()==false) {stop(); return 2;}
		if(backLeftTire.roll()==false) {stop(); return 3;}
		if(backRightTire.roll()==false) {stop(); return 4;}
		return 0;
	}
	
	void stop() {
		System.out.println("[Car stops..]");
	}
	
}

public class Main {
	public static void main(String[] args) {
		Car car = new Car();
		
    // Tire 타입의 객체 필드들에 대해서 HankookTire, KumhooTire로의 자동타입변환이 일어난다.
		for(int i=0; i<10; ++i) {
			int tireFail = car.run();
			switch(tireFail) {
			case 0:
				break;
			case 1:
				System.out.println("change left front tire to HankookTire");
				car.frontLeftTire = new HankookTire("앞 왼쪽", 15);
				break;
			case 2:
				System.out.println("change right front tire to KumhooTire");
				car.frontRightTire = new KumhooTire("앞 오른쪽", 13);
				break;
			case 3:
				System.out.println("change left back tire to HankookTire");
				car.backLeftTire = new HankookTire("뒤 왼쪽", 14);
				break;
			case 4:
				System.out.println("change right back tire to KumhooTire");
				car.backRightTire = new KumhooTire("뒤 오른쪽", 17);
				break;
			}
			System.out.println("---------------------------------");
		}
	}
}

```

### 2. 매개변수의 다형성 (파생 객체들에게 요청하기)
  - 메소드가 매개변수로 받으려 선언한 class의 유도 class 객체를 매개인자로 전달하면, 자동 타입변환이 일어난다.

### 3. 강제 타입 변환 (다시 원래 타입으로 돌리기)
  - 자식 타입이 부모 타입으로 자동 타입 변환된 경우에, 다시 자식 타입으로 변환 시킬 수 있다.
  - 자식 타입에만 존재하는 필드나, 메소드를 부득이하게 사용해야할 경우에 이용할 수 있다.

```java
public class Vehicle {
	public void run() {
		System.out.println("Running Vehicle");
	}
}

public class Car extends Vehicle{
	public void run() {
		System.out.println("running Car");
	}
}

public class Bus extends Vehicle{
	public void run() {
		System.out.println("Running bus");
	}
}

public class Driver {
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
}

public class TestDrive {
	public static void main(String[] args) {
		Driver driver = new Driver();
    
		Vehicle vehicle = new Vehicle();
		driver.drive(vehicle);
		
		vehicle = new Bus();
		driver.drive(vehicle);
		
		// 강제 타입 변환 + 매개변수의 다형성 (파생 객체들에게 )
		// 자식타입이 부모타입으로 자동변환된 경우에, 다시 자식타입으로 변환할 때 강제 형변환 이용 가능하다!
		Bus bus = (Bus)vehicle;
		driver.drive(bus);
		
		vehicle = new Car();
		driver.drive(vehicle);	
		
		Car car = new Car();
		driver.drive(car);
		
	}
}
```

## 객체 타입의 조사
  - **강제 타입 변환**에 앞서, **실제 변수가 가리키는 객체가 어떤 타입의 instance인지 확인**하는 절차가 요구된다.
  - 만약, 부모 타입의 instance를 자식 타입으로 강제 타입 변환하려 한다면, **ClassCastException 오류**가 발생할 수 있다
    - **부모타입 -> 자식타입 으로의 강제 타입 변환 불가능** 

```java

boolaen result = (객체가 할당되어 있는 타입 변수) instanceof (타입)
// 객체 타입 또는, 객체 타입의 상위 타입이면 : true
// 객체 타입의 하위 타입이면 : false

class A{}

class B extends A {}

public class InstanceOf {
	public static void method1(A a) {
    
    // a가 B의 instance인가???
    // a <- B객체
    // a가 A의 instance인지 물어보면 true 반환(조심!)
    // a가 B의 instance인지 물어보면 true 반환
    // a <- A객체
    // a가 A의 instance인지 물어보면 true 반환
    // a가 B의 instance인지 물어보면 false 반환
		boolean flag = a instanceof B;
		if(!flag) {
			System.out.println("method1 - B로의 변환일어나지 않음");
		}
		else {
			B b = (B)a;
			System.out.println("method1 - B로의 변환 가능");
		}
	}
	
	public static void method2(A a) {
		B b = (B)a;
		System.out.println("method2 - B로의 변환 가능");
	}
	
	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		
		method1(a2);
		method2(a2);
		
		method1(a1);
		method2(a1); // 오류 : class instanceOf.A cannot be cast to class instanceOf.B
		
	}
}
```









