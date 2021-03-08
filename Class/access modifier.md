# 접근 제한자(access modifier, modifier)

## 접근 제한자의 역할
  
  - 선언부에서 **다른 대상으로부터의 특정 대상의 이용에 제한을 거는 부가적인 역할**을 한다.
  - 대상에는 **인터페이스, 클래스, 메소드, 생성자, 필드** 들이 들어간다.
  - **다른 대상의 package상 위치와, 접근제한의 수준**이 중요하다.
  
 ## 접근 제한자의 종류
 
   - private, protected, public, default   
   
   - **default 접근 제한자**
     - 접근제한자로 따로 아무것도 적지 않으면 default 접근제한자 생성 (default라고 선언하는 것 아니다.)
     - 같은 package O, 다른 패키지 X
     
```java

// package A : class A
// package B : public class B, public class C 존재

public class C{
  
  A newA = new A(); // O
  B newB = new B(); // X

}
```

  - **public 접근 제한자**
    - public class ...
    - 같은 패키지 O, 다른 패키지 X
    
```java



```
