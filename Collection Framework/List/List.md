# List
  - **linear traverse** with **iterators**
  - **random access** with **index**
  - ArrayList, LinkedList가 List interaface implement 한다.


## List\<E> method
  - void add(int index, E element)
  - void remove(int index)
  - E get(int index)
  - E set(int index, E element)


## ListIterator\<E> extends Iterator\<E>
  - **양방향 traverse** 가능
  - **void add(E element)**
  - **E previous()**
  - **boolean hasPrevious()**
  - **void set(E element)**


## Concurrent Modification
  - 하나의 list에서 list 자체의 method 사용 혹은, 적용된 여러 iterator의 method 사용으로 인해서 list data를 수정하는데 서로 충돌이 발생할 수 있다.
  - 해결
    - **iterator가 모두 read()만 하고 있거나, (add, remove작용이 없음)**
    - **하나의 iterator만 list에 존재하는 경우**














