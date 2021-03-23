# Data Classes

## 데이터 저장을 위한 구조로서 객체를 정의할 때 Data Class 라는 개념을 사용한다.
  - Card (suit, value) : Card1.suit , Card2.value ..
  - User authentication information (name, password)
  - Point (x, y) : p1.x , p2. y ...

## 객체 사이의 데이터 전달을 위해 사용한다.
  - **매개변수로 전달 시**, 다중 데이터를 Data Class 객체를 묶어서 보낸다.
  - **return value로 전달 시**, Data Class 객체를 묶어 내보낸다.
  - **객체 저장, 및 읽기** 시에

## 객체 사이의 '다중' 데이터 전달을 위해 사용한다.
  - **여러 개의 데이터 정보를 하나의 class object로 묶어서 보내**면, 그 안의 여러 데이터를 쓸 수 있게 된다.

```java
public class MultipleDataClass {
	
	public int min;
	public int max;
	
	public MultipleDataClass(int min, int max){
		this.min = min;
		this.max = max;
	}
	
}
```

## 구현 방법
  - public data로 선언해서 simple 하게 data access 허용
    - read-only 제공 불가
    - encapsulation 위배
    - data validation check 불가
    - 간단한 코드 
  - private data로 선언해서 get/set method 제공
    - read-only 제공 가능
    - data validation check 가능
    - 번거로운 코드

```java
// public data 
public class Point{
  
  public int x;
  public int y;

  public Point(int x, int y){
  this.x = x;
  this.y = y;
  }
}

// private data - read only , immutable Data class
public class Point{
  
  private int x;
  private int y;

  public Point(int x, int y){
  this.x = x;
  this.y = y;
  }
  
  public int getX(){
    return x;
  }
  
  public int getY(){
    return y;
  }
}

```
