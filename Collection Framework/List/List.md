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


```java
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkedListTest {
	
	public static void main(String[] args) {
		
		// b에 있는 단어들 a에 merge 하는 작업 수행
		List<String> a = new LinkedList<>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		
		List<String> b = new LinkedList<>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");
		
		// b에서 단순하게 읽어올 것이기 때문에 b.iterator 사용
		// a에서는 b에서 읽어온 것을 쓸 것이기 때문에 ListIterator 사용
		ListIterator<String> iterA =a.listIterator();
		Iterator<String> iterB = b.iterator();
		
		// a <- b merge
		while(iterB.hasNext()) {
			if(iterA.hasNext()) iterA.next();
			iterA.add(iterB.next());
		}
		
		System.out.println(a);
		
		// b의 2배수 index 삭제
		// iterator가 끝까지 가버렸기 때문에, iterator 다시 설정
		iterB = b.iterator();
		
		// next() 이전에 항상 hasNext()로 체크해 주어야 함
		while(iterB.hasNext()) {
			iterB.next();
			if(iterB.hasNext()) {
				iterB.next();
				iterB.remove();
			}
		}
		
		System.out.println(b);
		
		// bulk operation(collection 자체 operation 이용)
		// remove all words in b from a
		a.removeAll(b);
		
		System.out.println(a);
		
	}
	
}
```











