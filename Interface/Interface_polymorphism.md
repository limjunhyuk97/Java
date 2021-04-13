# Interface와 Polymorphism

## Interface 사용이유
  - 구현해야하는 행동들에 대한 공통의 사용지침을 만들어서, 코드 수정 시 비용을 줄일 수 있다.
  - 다형성을 극대화 시켜서 코드의 재사용성을 높이는 것이다!

## Interface 다형성 
  - 인터페이스 변수에 할당된 구현 객체를 달리하면, 복잡한 코드 수정없이 다양한 실행결과를 낼 수 있다.
  - **다형성의 구현**
    - **Interface Implementation + Inheritance** : 구현 클래스 사용, 상속
    - **promotion** : 타입 변환
    - **method overriding** : 메소드 재정의
    - **Interface field, parameter** : 필드와 매개변수의 Interface 이용
      - 필드의 다형성 (부품 갈아끼우기)
      - 메소드의 다형성 (파생 구현 객체에게 요청하기)
      - 강제 타입 변환 (원래 타입으로 되돌리기, 구현 class의 더 많은 기능 이용하기)

```java
Interface I { ... }

// Interface 변수에 할당한 구현 객체를 A에서 B로 달리한다면..
I i = new A();
i = new B();

// 같은 i에 대한 method이지만, 다른 구현 객체의 할당에 따라서 결과가 달라진다.
i.method1();
i.method2();
```

``` java

// 필드 다형성
public interface Tire {
	public void roll();
}

public class KumhooTire implements Tire {
	@Override
	public void roll() {
		System.out.println("Kumhoo Tire rolls");
	}
}

public class HankookTire implements Tire{
	@Override
	public void roll() {
		System.out.println("Hankook tire rolls");
	}
}

public class Car {
	Tire fr = new KumhooTire();
	Tire fl = new KumhooTire();
	Tire rr = new KumhooTire();
	Tire rl = new KumhooTire();
		
	void Runs() {
		System.out.print("Front right :"); fr.roll();
		System.out.print("Front left :"); fl.roll();
		System.out.print("Rear right :"); rr.roll();
		System.out.print("Rear left :"); rl.roll();
	}
}

public class CarExample {
	public static void main(String[] args) {
		Car car= new Car();
		
		car.Runs();
		
		car.fr = new HankookTire();
		car.rl = new HankookTire();
		System.out.println();
		
		car.Runs();
	}
}

// 매개변수 다형성 + 강제 타입 변환 + instanceof이용
public interface Vehicle {
	public void run();
}

public class Car implements Vehicle{
	public void run() {
		System.out.println("Car runs!");
	}
}

public class Bus implements Vehicle{
	public void run() {
		System.out.println("Bus runs!");
	}
	public void checkFare() {
		System.out.println("Checking bus fare");
	}
}

public class Driver {
	public void drive(Vehicle vehicle) {
		vehicle.run();
	}
	
	// 강제 타입 변환 + instanceof 이용
	public void getFare(Vehicle vehicle) {
		if(vehicle instanceof Bus) {
			Bus bus = (Bus)vehicle;
			bus.checkFare();
		}
		else {
			System.out.println("This is not a bus!");
		}
	}
}

public class Simulator {
	public static void main(String[] args) {
		// 매개변수의 다형성
		Driver driver = new Driver();
		driver.drive(new Car());
		driver.drive(new Bus());
		
		// 강제 타입 변환 + instanceof 이용
		Bus bus = new Bus();
		Car car = new Car();
		
		driver.getFare(bus);
		driver.getFare(car);		
	}
}

```
