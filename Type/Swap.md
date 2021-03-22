# Swap

## 함수에서의 값의 교환이 실제 값의 교환과 일치하도록 하는 방법은 무엇인가?
  - Java 는 call-by-value를 사용한다.
  - **primitive value**는 call-by-value를 통해서 함수로 전달된다. : 함수 내에서 값이 복사되고, 함수 종료와 함께 휘발된다.
  - **reference value** 또한 call-by-value를 통해서 함수로 전달된다. : 함수 내에서 주소값이 복사되고, 함수 종료와 함께 휘발된다.

### 1. 그러므로, 객체 주소 값(reference value)의 call-by-value를 통한 전달 후 (함수 parameter들이 같은 객체를 가리키도록 하게 한 후)  
### 2. 함수 parameter들이 가리키고 있는 객체 내부의 값을 실제로 바꿔주어야 한다.

![swap](https://user-images.githubusercontent.com/59442344/111946350-a8ab2400-8b1e-11eb-921f-d252df7cb661.png)
