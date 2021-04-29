# Lambda Expression

## Functional Interface   
  - **하나의 function(기능)을 구현하기 위한 목적으로 만들어진 interface**다. 
  - interface -> 객체 생성 -> 객체의 특징적 state 변화를 이용함 (X) / interface -> 객체 생성 -> 객체를 function으로써 사용함(O)
  - functional interface는 하나의 추상메소드만을 멤버로 갖는 interface 개념이다.

```java
interface ActionListner extends EventListner{
  void actionPerformed(ActionEvent e);
}

interface Comparator<T>{
  int Compare(T o1, T o2);
}
```

  - **function과 object의 차이**가 무엇인가? 
    - function : state가 변해가는 것과 무관하게, data input에 따라 output을 내놓을 뿐이다.
    - object : 어떤 state가 저장되어 있고, data input에 따라 state가 계속 변해간다.
