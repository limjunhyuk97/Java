// Swap 함수 구현 //
// C 에서처럼 주소(참조)를 이용한 swap을 구현해보자
// swap의 가능여부는, 'heap 영역에 있는 정수객체를 참조하여 swap 하는가', '아니면 JVM Stack 영역에 잠깐 생성되는 지역 변수만 sawp 하는가' 에 달려있다.

package referenceType_primitiveType;

public class SwapInt {
	
  // heap 영역에 있는 정수 객체를 참조하여, 그 내부 값을 swap.
	public static void Swap(Myclass obj1, Myclass obj2) {
		int tmp = obj1.num;
		obj1.num = obj2.num;
		obj2.num = tmp;
	}
	
  // main 스레드에서 생성되는 main 프레임 다음으로 호출되는, 이 Swap 프레임 내에서 임시로 생긴 지역 변수들끼리만 swap.
	public static void Swap(int num1, int num2) {
		int tmp = num1;
		num1 = num2;
		num2 = tmp;
	}
	
	public static void main(String[] args) {
    
    // heap 영역에 객체가 생성되었다.
		Myclass obj1 = new Myclass(20);
		Myclass obj2 = new Myclass(30);
		
		System.out.println("obj1 : " + obj1.num + ", obj2 : " + obj2.num);
		
    Swap(obj1.num, obj2.num);
		System.out.println("obj1 : " + obj1.num + ", obj2 : " + obj2.num);
    
		Swap(obj1, obj2);
		System.out.println("obj1 : " + obj1.num + ", obj2 : " + obj2.num);
		
	}
	
}

class Myclass{
	public Integer num;
	
	public Myclass(int num) {
		this.num = num;
	}
}
