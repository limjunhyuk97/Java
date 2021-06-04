# Text IO

## Text Input/Output
  - **text를 읽고 쓸 때**에는, 각 character를 어떤 방식으로 Byte로 바꾸는 지에 대한 **Encoding 방식이 중요**하다.
  - Java는 UTF-16의 encoding 체계를 이용하지만 / File에 저장될 때에는 다양한 encoding 체계가 이용된다.
    - 실제로 Web Page들은 보통 UTF-8을 이용한다.
  - **Reader/Writer에 정의된 여러 Stream class**들이 **Encoding 방식에 따라** **Byte와 Character을 서로 변환**해주는 역할을 한다.
  - **Encoding 방식은 반드시 명시해주어야 한다**

## Text Input/Output example

``java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

// .DAT 파일은 ..
/*  응용 프로그램에서만 사용하도록 설계되었으며 사용자가 수동으로 열 수 없다. 
 * 일반 텍스트, 또는 바이너리로 저장된 정보를 갖고 있으며, 동작 프로그램의 인코딩 방식에 따라 내부 내용이 디코딩되며 구현된다.
 * 즉, 파일을 만든 프로그램과 관련된 특정 정보를 저장하는 일반 데이터 파일이다.
 * 일반 텍스트로 구성된 DAT 파일) 표준 텍스트 편집기로 열 수 있으며, 내용 확인도 가능
 * 일반 텍스트로 구성되지 않은 DAT 파일) 표준 텍스트 편집기로 열 수 있지만, 내용 알아볼 수 없음
*/

public class TextFile {
	
	public static void main(String[] args) throws IOException {
	
		Employee staff[] = new Employee[3];
		staff[0] = new Employee("Carl Cracker", 75000.0, 1987, 12, 15);
		staff[1] = new Employee("Harray Hacker", 50000.0, 1989,10, 1);
		staff[2] = new Employee("Tony Tester", 40000.0, 1990, 3, 15);
		
		// PrintWriter
		// out : employee.dat 파일에 UTF-8 방식으로 character를 encoding하여 적어 넣겠다!
		// writeData(staff, out) : staff 정보를 out에 적어 넣어달라!
		try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")){
			writeData(staff, out);
		}
		
		// FileInputStream
		// in : emloyee.dat 파일에 있는 UTF-8로 encoding된 정보를 가져오겠다!
		// readData(in) : in에 있는 정보를 staff로 옮겨 오겠다!
		try(Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")){
			Employee[] newStaff = readData(in);
			
			for(Employee e : newStaff)
				System.out.println(e);
		}
	}
	
	private static void writeData(Employee[] employees, PrintWriter out) throws IOException{
		out.println(employees.length);
		for(Employee e : employees)
			writeEmployee(out, e);
	}
	
	private static void writeEmployee(PrintWriter out, Employee e) {
		out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
	}
	
	private static Employee[] readData(Scanner in) {
		int n = in.nextInt();
		in.nextLine();
		Employee[] employees = new Employee[n];
		for(int i=0; i<n; ++i) {
			employees[i] = readEmployee(in);
		}
		
		return employees;
	}
	
	public static Employee readEmployee(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		String name = tokens[0];
		double salary = Double.parseDouble(tokens[1]);
		LocalDate hireDate = LocalDate.parse(tokens[2]);
		int year = hireDate.getYear();
		int month = hireDate.getMonthValue();
		int day = hireDate.getDayOfMonth();
		return new Employee(name, salary, year, month, day);
	}
	
}
```
