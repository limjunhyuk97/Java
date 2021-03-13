# Array 메소드

## 1. **배열의 복사**
  - **for문을 이용한 직접 복사 방식**
  - **System.arraycopy(Object src, int srcPos, Object dest, int destPos, int len)**
    - src : source object , 원본 객체
    - srcPos : sourcePosition , 원본에서 복사 시작할 위치
    - dest : destination, 복사본 객체
    - destPos : destinationPosition, 복사본에서 복붙 시작할 위치
    - len : length, srcPos부터 복사할 갯수

![arraycopy](https://user-images.githubusercontent.com/59442344/111024356-ae6b8000-8421-11eb-9838-9bc5f335d2c0.jpg)

  - **Arrays.copyOf(Object src, int len)**
    - src : source object, 원본 객체
    - len : length, 0부터 len길이 만큼의 배열을 복사해서 넣음

![Arrays copyOf](https://user-images.githubusercontent.com/59442344/111024438-25a11400-8422-11eb-9c5a-0a76a91b6650.jpg)

  - **System.arraycopy VS Arrays.copyOf**
    - Arrays.copyOf 는 System.arraycopy 함수를 래핑한 함수일 뿐이다. (System.arraycopy가 훨씬 빠름)
    - System.arraycopy 에서 len이 src나, dest내의 범위를 벗어난 부분까지 지정하면 error 발생. dest를 src의 특정 부분을 갈아끼는 느낌
    - Arrays.copyOf 에서 len이 src 범위 밖의 길이를 지정해도 자동으로 null로 저장된다. dest를 src의 특정 부분으로 바꾸는 느낌
    - **error 발생의 경우**
      - reference type <- -> primitive type으로 복사를 시도한 경우
      - src나 dest가 null인 경우
      - src > dest 공간 차이로 인해서 복사가 불가능한 경우

  - **배열의 얕은 복사**
    - **primitive data type array(일반 자료형 배열)**
      - = 연산자를 통해서 얕은 복사가 일어난다. : = 연산자는 참조의 위치를 대입하는 것이므로, 같은 값을 지니는 새로운 배열의 생성과정이 없다.
    - **reference data type array(참조 자료형 배열)**
      - = 연산자를 통해서 얕은 복사가 일어난다.
      - .arraycopy , .clone , .copyOf 모두 얕은 복사가 일어난다. 

```java
// == primitive type copy == //

int arr5[][] = {{1, 2, 5}, {2, 3, 4}};
int arr6[][] = {{2, 3}, {4, 5, 6}};		
arr5 = arr6; // arr5 변수가 arr6가 참조하는 주소를 받게 된다.

// == reference type copy == //

public class ObjectShallowCopy {
	
public static void main(String[] args) {
		
		Pos p1[] = {new Pos(2,3) , new Pos(3, 4), new Pos(5, 6), new Pos(10, 23)};
		Pos p2[] = new Pos[] {new Pos(3,5) , new Pos(2, 0), new Pos(3, 7), new Pos(10, 23), new Pos(9, 6)};
		
    		// 얕은 복사
		// p2 array의 0 ~ 3번째 idx == p1 array의 0 ~ 3번째 idx
		System.arraycopy(p1, 0, p2, 0, 4);
		
		// p1 이 바뀌면 p2 array 내의 객체도 바뀐다.
		p1[0].setXPos(10);
		
		for(Pos p : p1) p.print();
		System.out.println();
		for(Pos p : p2) p.print();
		System.out.println();
		
	}
	
}

class Pos{
	private int xpos, ypos;
	
	public Pos(int xpos, int ypos) {
		this.xpos = xpos;
		this.ypos = ypos;
	}
	
	public void print() {
		System.out.println("xpos : " + this.xpos + ", ypos : " + this.ypos);
	}
	
	public void setXPos(int xpos) { this.xpos = xpos; }
}

		
```

  - **배열의 깊은 복사**
    - **primitive data type array(일반 자료형 배열)**
      - .arraycopy , .clone , .copyOf 를 이용하여 깊은 복사를 할 수 있다. : 배열 내부의 값 자체를 바꿔 버린다.
    - **reference data type array(참조 자료형 배열)**
      - for문을 통하여 new 연산자로 새롭게 생성된 객체를 배열마다 넣어주는 작업이 필요하다.
```java

```

## 2. **배열의 정렬**
  - **Arrays.sort(Object src)**
    - src : source object, 원본 객체

## 3. **java.util.Arrays**
  - **util package 안의 Arrays class에서 지원하는 static method들..** 
  - type : int, long, short, char, byte, float, double (method overloading이다.)
  - static vois sort(type[] a)
  - static int binarySearch(type[] a, type v)
  - static int binarySearch(type[] a, int start, int end)
  - static void fill(type[]a , type b) 
  - static Boolean equals(type []a, type []b)
  - static type[] copyOf(type []a, int length)
  - static type[] copyOfRange(type []a, int start, int end)
  - static String toString(type []a)
