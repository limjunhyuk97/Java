# [Encapsulation](https://github.com/limjunhyuk97/OOP_study/blob/main/Ajou/week03/Lecture_Scrum.md)

## More on Encapsulation in Java 

### 1. Returning Mutable Object 
  - 객체가 field로 갖는 **Mutable Object를 method의 return 값으로 반환**한다면 **문제**가 생길 수 있다.
  - **encapsulation의 원칙**상, 외부에서 private field로 접근할 수 있으면 안되는데, **reference를 통해 외부에서 참조가 가능해지기 때문**이다.
    - 그렇기 때문에 mutable object는 return하지 않는 것이 좋다.
    - 해결책 : **방어적 복사를 통한 immutable한 상태 구현**
  
### 2. Class-Based Access Privileges
  - private data에 접근하는 기준은 객체 기준이 아니라, class 기준이다!
  - method can access private data of all objects of its class
  
```java
Class Employee{
  ...
  public boolean equals(Employee other){
    return name.equals(ohter.name);
    // class 종류가 일치하는 다른 객체의 private member에 접근할 수 있다.
  }
  ...
}
```
