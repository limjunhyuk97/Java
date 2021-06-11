# Collection Frameworks


## 00. Java Collection Frameworks
  - **collective objects를 표현하고 처리하는 unified된 architecture(구조/동작원리) 제공.**
    - 사용자는 내부의 detail을 정확히 모르더라도 사용할 수 있다.
    - 다양한 interface와 implementation들을 제공하여, programming effort를 줄여준다.
    - generic programming 기법이 많이 사용된다.
  - **Collection**은 object들에 대한 reference를 갖고 있다.
    - primitive type은 wrapper calss를 이용한다
  - **java.util**에 존재


## 01. Seperating Collection interfaces from implementation

### interface와 implementation을 분리한다는 것은 어떤 의미인가?
  - interface와 implementation(구현)을 분리함으로써, interface를 implement한 어떤 객체를 사용하는지와 무관하게, 동일한 사용법을 갖게 된다.
  - 구체적으로는 **핵심적인 기능에서는 동일한 method명(동일한 사용법)을 사용하기 때문에, 어떤 구체화된 imlementation을 사용하는지와 무관하게 코드를 유지할 수 있다.**
  - **polymorphic한 code를 만들 수 있다.**


## 02. Collection Interface
  - Collection library의 가장 상위에 있는 root interface이다.
  - fundamental method를 여러개 갖는다.
    - size()
    - add()
    - **iterator\<E> iterator()**
      - **collection 내부의 원소들을 traverse할 수 있게 해준다.**
      - **Index를 사용는 array에서든, reference를 사용하는 linked list에서든 'traverse'할 수 있도록 지원해 준다.**


## 03. Collection Interface - Iterators

### 1. Iterator 란
  - generic interface이다.
  - **collection의 종류, implementation에 관계없이 내부를 traverse할 수 있도록 해준다.**
  - 단방향, 양방향 traverse
    - java.util.Iterator<T> : one-way traverse
    - java.util.ListIterator<T> : two-way traverse

```java
// Iterator interface
public interface Iterator <E> {
  E next();
  boolean hasNext();
  void remove();
  default void forEachRemaining(Consumer <? super E> action);
}

// Iterator의 사용 예시 - 1
Collection<String> c;
Iterator<String> iter = c.iterator(); // c라는 Collection의 iterator를 iter에 할당
while(iter.hasNext()){
  String element = iter.next();
}

// Iterator의 사용 예시 - 2
// Collection이 Iterable을 implement 했다면 적용가능
// 그리고, Collection interface는 iterable을 extend 하고 있다.
for(String element : c){
  ...
}
```

### 2. Iterator의 사용
  - collection type에 따라서(어떤 collection인지에 따라) element가 불려오는 순서가 다르다.
  - 내부적 구현이 어떤지 간에, **iterator가 현재 존재하는 위치에 대해 'element의 사이에 있다' 라고 생각하는 것이 좋다.**
    - next()로 반환하는 원소 : iterator 다음으로 존재하는 element
    - remove()로 삭제하는 원소 : iterator 이전에 존재하는 원소

![image](https://user-images.githubusercontent.com/59442344/121659546-5fdf8780-cadd-11eb-85ce-43af8374e16e.png)


### 3. ListIterator
  - **ListIterator\<E> extends Iterator\<E>**
  - **양방향 traverse** 가능
  - Iterator에서 추가된 method
    - **void add(E element)**
    - **E previous()**
    - **boolean hasPrevious()**
    - **void set(E element)**


## 04. Generic Utiliy Methods
  - Collection, Iterator interface를 통하여 다양한 generic한 utility method를 만들 수 있다.
  - **Collection interface내 method**와 **Collections class의 static method**로 useful method를 제공한다.
    - int size()
    - boolean isEmpty()
    - boolean contains(Object obj)
    - boolean conatainAll(Collection<?> c) : \<?\>는 c 내부의 원소를 이용하는 수준일 때 사용
    - boolean add(E element)
    - boolean addAll(Collection<? extends E> from)
      - (ex) c.addAll(d)는 c에, c Collection type variable의 sub type을 type variable로 갖는 d Collection의 element를 추가할 수 있다는 의미이다.
    
    
```java
// 특정 element가 Collection에 존재하는지 확인하는 메소드
public static <E> boolean contains(Collection<E> C, Object obj){
  for(E element : c){
    if (element.equals(obj))
      return true;
  }
  return false;
}
```


## 05. Interfaces (in Framework)

![image](https://user-images.githubusercontent.com/59442344/121661562-493a3000-cadf-11eb-845d-f3af01fc2ca5.png)

  - Map : key/value pair를 다룬다.
  - List : Ordered collection, Duplication allowed
  - Set : Unordered collection, without Duplication 
  - SortedSet/ SortedMap : element/ key 값이 sort된 순서로 traverse 가능하도록
  - NavigatableSet/ NavigatableMap : set/ map보다 더 많은 method들 제공


## 06. Classes (in Framework)

![image](https://user-images.githubusercontent.com/59442344/121662097-e5643700-cadf-11eb-947f-08e3d9bca519.png)


## 07. Concrete collections

![image](https://user-images.githubusercontent.com/59442344/121662478-5b689e00-cae0-11eb-934e-77fe1454fc1f.png)





