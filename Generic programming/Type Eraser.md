# Type Eraser
  - Generic programming이 어떤 방식으로 구현되는가?
  - C++의 template과 jvm의 generic을 구현하는 방식은 완전히 다르다.
  
## Type Eraser + Cast Insertion
  - Java에서 generic programming이 동작되는 방식
  - 이전 버전과의 호환성 문제 때문에 이렇게 구현된 것이다.

### 1. Type Eraser
  - JVM 내에는 generic type과 method라는 개념이 없다.
  - Compiler가 T라는 **Type variable로 선언된 부분을 다 지워**버리고, **"raw type" 생성**
  - Compiler가 **Type variable을 모두 bounding type이나, Object type으로 갈아치워**버린다
  
### 2. Cast Insertion
  - Compiler가 Type T로 선언된 모든 곳은 bounding type이나, Object type으로 갈아치웠다.
  - **Compiler는 이제 <사용자 요구 타입>을 casting** 한다.
    - **method에 대한 return**의 경우
    - **public field에 대한 접근**의 경우 
  - parameter에서는 casting 불필요하다.
    - 모든 type은 Object type의 subtype이기 때문이다. 

```java

// erasing 벌어짐
// 즉, Pair buddies = ...;
Pair<Employee> buddies = ...;

// getFirst() return 값이 나올 때 cast insertion이 벌어진다!
// 즉, Employee buddy = (Employee)buddies.getFirst();
Employee buddy = buddies.getFirst();

// public field 값에 대한 접근 시에도 마찬가지이다.
// 즉, Employee buddy = (Employee)buddies.first;
Employee buddy = buddies.first;

```

### 3. Legacy Code
  - 이전 버전에서 사용하던 Object 기반 class나, method의 code
  - 이들과의 연계성을 위해서 erasing과 cast insertion을 사용하는 것이다.
