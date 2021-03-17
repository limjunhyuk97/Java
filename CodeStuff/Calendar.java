import java.time.*;

public class LocalDateExample {

	public static void main(String[] args) {
		
		LocalDate date = LocalDate.now();
		date = date.of(2021, 4, 8);
		
		int month = date.getMonthValue();
		int today = date.getDayOfMonth();
		// 오늘 월, 일 정보 가져옴
		
		date = date.minusDays(today - 1);
		// 이번 달 1일로 date 정보를 옮김
		
		DayOfWeek weekday = date.getDayOfWeek();
		// DayOfWeek이라는 enum 사용자 정의 열거 자료형에 대한, weekday라는 사용자 정의 열거 자료형 변수(객체)를 설정 = date의 enum weekday 값 대입
		// DayOfWeek은 enum이므로, reference type이다.
		
		int value = weekday.getValue();
		// weekday 객체의 weekday 값의 numerical value를 반환받기 위해서 getValue() method를 사용한다.
		// 1 = Monday, 2 = Tuesday, 3 = Wednesday, ... , 6 = Saturday, 7 = Sunday 를 반환한다.
		
		System.out.println("Mon Tue Wed Thu Fri Sat Sun");
		for(int i=1; i<value; ++i) System.out.print("    "); // 적당한 요일에서 시작하기 위하여 앞을 비우는 작업
		
		for(int i=1; i <= date.lengthOfMonth(); ++i) {
			System.out.printf("%3d",i);
			if(i==today) System.out.print("*");
			else System.out.print(" ");
			if(value == 7 && i%7==0) System.out.println();
			else if((i%7) == (8-value)) System.out.println();
		}
		
	}
