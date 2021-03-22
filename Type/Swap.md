# Swap

## 함수에서의 값의 교환이 실제 값의 교환과 일치하도록 하는 방법은 무엇인가?
  - Java 는 call-by-value를 사용한다.
  - **primitive value**는 call-by-value를 통해서 함수로 전달된다. : 함수 내에서 값이 복사되고, 함수 종료와 함께 휘발된다.
  - **reference value** 또한 call-by-value를 통해서 함수로 전달된다. : 함수 내에서 주소값이 복사되고, 함수 종료와 함께 휘발된다.

### 1. 그러므로, 객체 주소 값(reference value)의 call-by-value를 통한 전달 후 (함수 parameter들이 같은 객체를 가리키도록 하게 한 후)  
### 2. 함수 parameter들이 가리키고 있는 객체 내부의 값을 실제로 바꿔주어야 한다.

![swap](https://user-images.githubusercontent.com/59442344/111946350-a8ab2400-8b1e-11eb-921f-d252df7cb661.png)

### Card shuffle

```java
// 카드 섞기

// Card 객체 
class Card{
	
	public String suit;
	public int rank;
	
	Card(String suit, int rank){this.suit = suit; this.rank =rank;}
	Card() {}
	
}

// deck 생성
	public static void deckGenerator(Card[] deck) {
		for(int i=0 ; i<4; ++i) {
			for(int j=0; j<13; ++j) {
				switch(i) {
				case 0: deck[i*13 + j] = new Card("Spade", j+1); break;
				case 1: deck[i*13 + j] = new Card("Diamond", j+1); break;
				case 2: deck[i*13 + j] = new Card("Heart", j+1); break;
				case 3: deck[i*13 + j] = new Card("Club", j+1); break;
				}
			}
		}
	}
	
// deck shuffle
public static void deckShuffler(Card[] deck) {
	Card cTmp; int nTmp;
	for(int i=0 ;i<52; ++i) {
		nTmp = (int)(Math.random() * 52);
		cTmp = deck[nTmp];
		deck[nTmp] = deck[i];
		eck[i] = cTmp;
	}
}
```

### Employee 정보 교환 (swap 안되는 경우)

```java

// main function에서 객체 생성후 전달
Employee a = new Employee("Alice", 70000);
Employee b = new Employee("Bob", 50000);
swap(a, b);

// call-by-value이기 때문에, x와 y 사이의 reference 값의 교환만 일어나고 있다.
public static void swap(Employee x, Employee y) {
  Employee tmp = x;
  x = y;
	y = tmp;
		
	System.out.println("End of method x = " + x.getName() + " " +  x.hashCode());
	System.out.println("End of method y = " + y.getName() + " " + y.hashCode());
}
```
