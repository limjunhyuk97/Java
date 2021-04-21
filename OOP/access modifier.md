# 접근 제한자(access modifier, modifier)

## 접근 제한자의 역할
  - 선언부에서 **다른 대상으로부터의 특정 대상의 이용에 제한을 거는 부가적인 역할**을 한다.
  - 대상에는 **인터페이스, 클래스, 메소드, 생성자, 필드** 들이 들어간다.
  - **다른 대상의 package상 위치와, 접근제한의 수준**이 중요하다.
  
## 접근 제한자의 종류
 
   - private, protected, public, default
   - private < defualt < protected < public (----> 접근 제한이 약해짐 / <---- 접근제한이 강해짐) 


## 1. class와 access modifier     
   
   - **default**
     - 따로 선언하지 않으면, default 접근제한자 생성 (default라고 선언하는 것 아니다.)
     - 같은 package O, 다른 패키지 X

  - **public**
    - 같은 패키지 O, 다른 패키지 O (import 한 경우)
    - class 파일 하나에는 하나의 public class가 존재한다.

## 2. constructor와 access modifier

  - 따로 생성자를 선언하지 않은 상태라면(기본 생성자), class의 access modifier 선언을 따라간다.

  - **public**
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 O

  - **protected**
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 X (다른 패키지에 존재하는, 해당 클래스의 자식 클래스에서 호출은 O)
  
  - **private**
    - 같은 패키지에서 호출 X (해당 클래스 내부에서의 호출은 O), 다른 패키지에서 호출 X
    - private member는 subclass에서 직접 호출할 수 없다.
    
  - **default**
    - access modifier 선언을 생략하면 default modifier 접근 제한을 갖는다.
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 X

## 3. method, field와 access modifier

  - **public**
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 O

  - **protected**
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 X (다른 패키지에 존재하는, 해당 클래스의 자식 클래스에서 호출은 O)
  
  - **private**
    - 같은 패키지에서 호출 X (해당 클래스 내부에서의 호출은 O), 다른 패키지에서 호출 X
    - private member는 subclass에서 직접 호출할 수 없다.
    
  - **default**
    - access modifier 선언을 생략하면 default modifier 접근 제한을 갖는다.
    - 같은 패키지에서 호출 O, 다른 패키지에서 호출 X





