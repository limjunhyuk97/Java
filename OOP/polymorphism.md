# Polymorphism(다형성)

## Polymorphism이란?
  - **하나의 요소(객체)** 가 **하나 이상의 타입** 을 갖는 것이다. 
    - type : subtype 관계와, superclass : subclass 관계는 같다.
    - sub class의 모든 object는 super class의 object이기도 하다.
  - 다형성의 결과는, **하나의 메시지**에 대한 **(하나의 요소/객체 -> 하나 이상의 타입)다양한 대답의 가능성**이다.
    - **하나의 객체 - 하나이상의 타입**
    - **하나의 메시지 - 하나이상의 메소드**
    - **같은 역할, 같은 책임 - 다른 타입의 객체**

## Polymorphism 구현 방법

### "상속(IS-A relationship) + 메소드 재정의(method overriding) + 타입변환(dynamic binding : promotion)"

### 1. [상속](https://github.com/limjunhyuk97/java_study/blob/master/OOP/Inheritance.md)

### 2. [메소드 재정의](https://github.com/limjunhyuk97/java_study/blob/master/OOP/Inheritance.md)

### 3. [타입변환(Promotion)](https://github.com/limjunhyuk97/java_study/blob/master/OOP/Promotion.md)

### 4. [Generic Programming](https://github.com/limjunhyuk97/java_study/tree/master/Generic%20programming)

## Polymorphism의 장점
  - **easily extensible한 프로그램**을 만들 수 있다 : 프로그램의 확장 시에 유용하다!
  - **simple하고 compact한 프로그램**을 만들 수 있다.

## Polymorphism and Method call
  - polymorphism이 적용된 경우, method call에 대해 run time과 compile time에 어떤 일이 일어나는가?
  - obj라는 객체의 Foo라는 함수가 실행될 때..
 
### 1. Compile time
  - obj 변수의 타입과, 그 타입의 super class 타입들에 존재하는 접근가능한 모든 Foo함수를 찾는다.
  - 찾은 Foo함수들 중에서 parameter들이 실제 호출된 Foo의 parameter들과 match되는 함수를 찾는다.
  - 만약에 match되는 것이 없으면 compile error를 발생시킨다.
  - 만약에 match된 method가 private이나, static이나, final이라면, 바로 Foo와 binding한다. (static binding)

### 2. Run time
  - 실행 시점에서 obj의 실제 타입을 바탕으로 dynamic binding을 수행한다.

### 어떻게 쉽게 생각할 수 있을까?
  - 타입변환이 일어난 상태에서 (즉, 다형성의 측면에서) 어떤 메소드가 호출되는지 어떻게 판단할 것인가?
  - 변수의 class 타입에 존재하는 method들만 실행시킬 수 있다.
  - 만약에 method들이 sub class에서 재정의 되었다면, 재정의된 method가 호출된다 : 직접 호출이든, 간접 호출이든.

![method and polymorphism](https://user-images.githubusercontent.com/59442344/113875648-f51a8300-97f1-11eb-91c0-f0a303fba01c.png)









