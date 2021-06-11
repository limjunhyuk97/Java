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
