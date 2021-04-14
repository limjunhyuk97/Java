# 1. 추상 클래스 (Abstract class)

## 추상 클래스란? (공통된 규격 정하기)
  - **추상 메소드가 하나라도 존재한다면 추상 클래스**이다. (객체를 만들 수 없기 때문에!)
  - 객체를 만들 수 있는 **실체 클래스에서 공통된 특성(메소드와, 필드)들을 추출하여 만든 클래스**
  - 반대로, 실체 클래스는 추상클래스를 상속하고, 추상클래스+a의 특성을 지닌다.
  - (예) 이런 특성의 타이어를 만드려고 합니다! <- 한국타이어의 ~ 특성 / 금호 타이어의 ~ 특성.
  
## 추상 클래스의 용도
  - **공통된 이름의 필드와 메소드를 통일**하여, 여럿이 작업할 때 생기는 오차를 줄인다.
  - **실체 클래스 작성 시에 시간 절약** : 공통된 작업을 여러번하지 않아도 됨.
  - **추상클래스를 상속하는 실체클래스를 만들고, 그 실제클래스를 통해서 객체를 생성해야만 한다!**

## 추상 클래스 선언
  - 필드, 메소드, 생성자(꼭 해주어야 한다, sub class에서의 super를 이용한 생성자 호출이 가능하기 때문!)의 선언과 정의 모두 가능하다.
  - **abstract class (class명)** 으로 선언한다 : 객체 못만든다는 선언과 같다. 상속을 통한 subclass 생성만 가능!

```java
// abstract class 'Phone'을 생성
public abstract class Phone{
  public String owner;
  public Phone(String owner){
    this.owner = owner;
  }
}

// 'Phone'에서 파생된 SmartPhone은 Phone의 멤버들을 그대로 갖는다.
public class SmartPhone extends Phone{
...
}

```

# 2. 추상 메소드 (Abstract Method)

## 추상 메소드란? ('행동의 종류' 정하기 / 내용은 정하지 않기)
  - 추상 클래스에서 **메소드의 내용을 모두 정해버리면**, **'같은 행동'에 대한 '다른 결과'를 만들 수 없다.**
  - **자식 클래스에서 추상메소드의 내용을 반드시 정의해줘야** 한다. (정의해주지 않으면 컴파일 에러 발생)
  - 메소드의 선언부만 있고, **중괄호 내의 실행 부분의 내용은 없다.**
    - [public/protected/private/(default)] abstract [return type] [method name] ( 타입 변수명 ,,,);
  - 추상 클래스를 상속했지만, **여전히 내부에 정의되지 않은 추상 메소드가 있다면, 여전히 추상클래스**이다!

```java
// Animal - abstract class
public abstract class Animal {
	public String kind;
	public Animal(String kind) {
		this.kind = kind;
	}
	public void breathe() {
		System.out.println("It breathes");
	}
	public abstract void sound();
}

public class Cat extends Animal {
	public Cat(String kind) {
		super(kind);
	}
	@Override
	public void sound() {
		System.out.println("meow");
	}
}

public class Dog extends Animal{
	public Dog(String kind) {
		super(kind);
	}
	public void sound() {
		System.out.println("Woof!");
	}
}

public class AnimalTest {
	public static void main(String[] args) {
		Cat cat = new Cat("cat");
		Dog dog = new Dog("dog");
		
		dog.sound();
		cat.sound();
		System.out.println("------");
		
		// 자동 타입 변환
		Animal animal = null;
		animal = dog;
		animal.sound();
		animal= cat;
		animal.sound();
		System.out.println("------");
		
		// 메소드의 다형성
		animalSound(cat);
		animalSound(dog);
		
	}
	
	public static void animalSound(Animal animal) {
		animal.sound();
	}
}
```





