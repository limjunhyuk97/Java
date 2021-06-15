public class GenericTest {

	public static void main(String[] args) {
		
		Pair<? extends Pair2> pairE = new Pair<>(new Pair2(2, 3), new Pair2(4, 5));
		Pair<? super Pair2> pairS = new Pair<Pair2>(new Pair3(1, 6, 7), new Pair2(1, 3));
		Pair<Pair2> pairT = new Pair<Pair2>(new Pair2(1, 3), new Pair3(1, 2, 3));
		
		// 정리
		// 
		// (1) super, extends
		//
		// super, extends : getter, setter에 대한 제약, type parameter의 상속관계를 generic class instance에서도 유지.
		//
		//
		// (2) Generic field 제약
		//
		// - <? ... !> 에서
		//		: extends ! , ! 는 type parameter가 될 수 있는 가장 상한선(super type)이다.
		//		: super !   , ! 는 type parameter가 될 수 있는 가장 하한선(sub type)이다.
		//
		// - <T>
		//		: T 는 type parameter가 될 수 있는 가장 상한선(super type)이다.
		//
		// - 위의 두 조건을 바탕으로 generic class에서 생성된 객체는, 생성 당시 type parameter에 대한 상한선, 하한선을 유지한다.
		// 
		//
		// (3) Generic field type 설정
		// 
		// - new ClassName<>( ... ) : 초기화 시 넣어지는 객체들에 의해서 field type이 추론된다. 
		//							: 만약 상속 관계를 갖는 타입들의 객체가 다양하게 넣어졌다면, 가장 상한선(super type)을 type parameter에 할당한다.
		//
		// - new ClassName< ... >( ... ) : type parameter가 명시되어 있다면, 그것을 할당한다.
		
		changeMember(pairT, new Pair3(3, 4, 5));
		pairT.setFirstPair(new Pair3(4, 5, 6));
		
		// PairS 내부의 Field들에 대해서는 Pair2의 subType이면 된다.
		pairS.setFirstPair(new Pair3(3, 4, 5));
		System.out.println(pairS);
		
		// <? super Pair> wildCard를 사용하는 것은, generic class instance들간의 관계 떄문이지, field 때문이 아니다!
		pairS = new Pair<Pair1>(new Pair2(2, 3), new Pair2(4,5));
		
		// 불가능 : pairS = new Pair<Pair3>(new Pair3(1,2,3), new Pair3(4,5,6));
		// 불가능 : changeMember(pairS, new Pair1(3));
		
	}
	
	public static <T extends Pair1> void changeMember(Pair<T> pair, T p) {
		pair.setFirstPair(p);
	}
	
}

class Pair <T>{

	private T p1, p2;
	
	public Pair(T t1, T t2) {
		this.p1 = t1;
		this.p2 = t2;
	}
	
	public T getFirstPair() {return p1;}
	public T getSecondPair() {return p2;}
	
	public void setFirstPair(T t) {this.p1 = t;}
	public void setSecondPair(T t) {this.p2 = t;}
	
	public String toString() {
		return p1 +"\n" + p2;
	}
}

class Pair1 {
	
	private int n;
	
	public Pair1(int n) {this.n = n;}
	
	public int getOne() {return n;}
	public void setOne(int n) {this.n = n;}
	
	public String toString() {
		return "Pair1 " + n;
	}
}

class Pair2 extends Pair1{

	private int m;
	
	public Pair2(int n, int m) {super(n); this.m = m ;}
	
	public int getTwo() {return m;}
	public void setTwo(int n) {this.m = m;}
	
	public String toString() {
		return "Pair2 " + super.getOne() + " " + m;
	}
	
}

class Pair3 extends Pair2{

	private int k;
	
	public Pair3(int n, int m, int k) {
		super(n, m);
		this.k = k;
	}
	
	public int getThree() {return k;}
	public void setThree(int n) {this.k = k;}
	
	public String toString() {
		return "Pair3 " + super.getOne() + " " + super.getTwo() + " " + k;
	}
}









