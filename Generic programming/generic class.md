## Generic
  - Polymorphism의 일종이다. (다양한 타입을 지원한다.)
  - 같은 구성을 갖으면서, 여러 다른 type을 지원하기 위해서 만들어진 개념이다.

## Drawback of Object class
  - Generic class 개념 도입 이전에 Object class를 만들어서 다양한 type을 받고자 했다.
  - Drawback
    - **compiler의 type checking이 이루어지지 않는다.**
      - 개발자가 직접 type checking 해주어야 한다.
      - return이 Object로 되니까, type casting 따로 해주어야 한다.
      - 본래 Object의 어떤 subclass의 가리키는지 기억하거나, 나중에 찾아줘야 한다.
    - 나중에 엉뚱한 type을 받는 일이 생길 수도 있다.

```java
public class Pair<T>{

  private Object first;
  private Object second;
  
  public Pair() { first = null; second = null; }
  public Pair(Object first, Object second) { this.first = first; this.second = second; }
  
  public Object getFirst() { return first; }
  public Object getSecond() { return second;}

  public void setFirst(Object newValue) { first = newValue; }
  public void setSecond(Object newValue) { second = newValue; }

}

// 사용 시에
Pair p1 ...
String name1 = p1.getFirst(); // 컴파일 오류
String name1 = (String)p1.getFirst();

p1.setFirst(/*type1*/);
p1.setFirst(/*type2*/);
// 이런 식의 서로 다른 타입을 받는 상황은 곤란하다.
```

# Generic class
  - **하나 이상의 type variable (type parameter)**를 갖는 class이다.
  - type에 따라서 여러 개의 class를 생성해야 하는 번거로움을 줄인다.
  - Object class에서 생길 수 있는 단점을 해결할 수 있다.

```java
public class Pair<T>{

  private T first;
  private T second;
  
  public Pair() { first = null; second = null; }
  public Pair(T first, T second) { this.first = first; this.second = second; }
  
  public T getFirst() { return first; }
  public T getSecond() { return second;}

  public void setFirst(T newValue) { first = newValue; }
  public void setSecond(T newValue) { second = newValue; }

}
```

## 어디서 사용되는가?
  - library collection framework
  - generic interface, generic class

