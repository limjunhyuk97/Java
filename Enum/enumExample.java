import java.util.*;

public class enumTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a size : (SMALL, MEDIUM, LARGE, EXTRA_LARGE) : ");
		String input = in.next().toUpperCase();
		
		// Enum.valueOf(enumclass명.class, instance constant 이름) -> enum에 존재하는 instance constant 반환
		Size size = Enum.valueOf(Size.class, input);
		System.out.println("size = " + size);
		System.out.println("abbreviation = " + size.getAbbreviation());
		
		// enumclass.valueOf(instance constant 이름) -> enum에 존재하는 instance constant 반환
		Type type = Type.valueOf("TYPE1");
		System.out.println("type = " + type);
		if(size == Size.EXTRA_LARGE) {
			System.out.println("GG");
		}
		
		// enumclass.values() -> enum 내부의 모든 instance constant 배열 반환
		Size[] list = size.values();
		for(Size i : list) {
			System.out.println(i);
		}
		
		// enu. ordinal() -> enum 내부의 instance constant들의 번호 반환
		Type[] list2 = Type.values();
		for(Type t : list2) {
			System.out.println(t.ordinal() + ". " +t);
		}
		
	}
}

enum Size{
	SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");
	private String abbreviation;
	private Size(String abbreviation) {this.abbreviation = abbreviation;}
	public String getAbbreviation() {return abbreviation;}
	public String toString() {
		return abbreviation;
	}
}

enum Type{
	TYPE1("A", 1), TYPE2("B", 2), TYPE3("C", 3); 
	private String str;
	private int num;
	private Type(String str, int num) {this.str = str; this.num = num;}
	public String toString() {
		return str + " : " + num;
	}
}
