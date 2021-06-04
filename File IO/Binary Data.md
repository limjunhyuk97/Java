# Binary Data

## 1. Reading and Writing Primitive Types
  - 보통 숫자를 문자로 표현하는 것이 숫자를 binary로 표현하는 것보다 비효율적이다.
    - (예) 1234를 문자로 표현하면 1/2/3/4 8byte, binary로 표현하면 1234 4byte.
  - **이런 *primitive type*을 효과적으로 전달받기 위한 method를 정의한 class가 존재.**
    - DataInput / DataOutput이라는 interface의 구현 class 2개가 존재한다.
    - DataInput in = new **DataInputStream**(Files.newInputStream(path));
    - DataOutput out = new **DataOutputStream**(Files.newOutputStream(path));
    - 각각은 inputStream, outputStream을 wrapping 하고 있다!
    - stream을 wrapping하여, stream에서 정보를 하나씩 끌어오는 것!
  - **method**
```java
readInt()
writeInt()
readLine()
writeString()
...
```

## 2. Random Access Files
  - data stream을 **sequential하게만 읽지 않을 수 있다. 읽거나, 쓰고 있는 position을 넘나들 수 있다.**
```java
// 방금 읽은 integer 값을 1증가 시키는 방법!
int value = file.readInt();
file.seek(file.getFilePointer()-4);
file.writeInt(value+1);
```
  - **DataInput, DataOutput interface가 구현되어 있어**, byte-based read write하면서, primitive type 입출력이 가능하다.
  - **read와 write를 동시에 할 수 있다.**
```java
RandomAccessFile file = new RandomAccessFile(filenameString, "rw");
```

## 3. Example : Random Access Files
```java
// TextFile class에서는 모든 정보를 String 형식으로 받았다면,
// 이번에는 primitive type들을 살려서 받을 것이다.
public class RandomAccessTest {
	
	public static void main(String[] args) {
		
		Employee staff[] = new Employee[3];
		staff[0] = new Employee("Carl Cracker", 75000.0, 1987, 12, 15);
		staff[1] = new Employee("Harray Hacker", 50000.0, 1989,10, 1);
		staff[2] = new Employee("Tony Tester", 40000.0, 1990, 3, 15);
		
		// employee.dat 파일에 적어넣을 때는 DataOutputStream을 쓰겠다
		try(DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))){
			for(Employee e : staff) {
				writeData(out, e);
			}
		}
		catch(IOException e) {}
		
		// employee.dat 파일에서 읽어올 때는 RandomAccessFile을 쓰겠다.
		try(RandomAccessFile in = new RandomAccessFile("employee.dat", "r")){
			int n = (int)(in.length() / Employee.RECORD_SIZE);
			Employee[] newStaff = new Employee[n];
			for(int i=n; i>=0; --i) {
				in.seek(i * Employee.RECORD_SIZE);
				newStaff[i] = readData(in);
			}
			for(Employee e : newStaff)
				System.out.println(e);
		}
		catch(IOException e) {}
	}
	
	public static void writeData(DataOutput out, Employee e) throws IOException{
		// name 크기가 들쭉날쭉 할 것이기 때문에! NAME_SIZE로 최대 크기를 결정한다.
		DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
		out.writeDouble(e.getSalary());
		LocalDate hireDay = e.getHireDay();
		out.writeInt(hireDay.getYear());
		out.writeInt(hireDay.getMonthValue());
		out.writeInt(hireDay.getDayOfMonth());
	}
	
	public static Employee readData(DataInput in) throws IOException{
		String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
		double salary = in.readDouble();
		int y = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		return new Employee(name, salary, y, m, d);
	}
	
}

// DataIO
public class DataIO {
	
	public static String readFixedString(int size, DataInput in) throws IOException{
		StringBuilder b = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while(more && i< size) {
			char ch = in.readChar();
			i++;
			if(ch ==0) more = false;
			else b.append(ch);
		}
		in.skipBytes(2*(size - i));
		return b.toString();
	}
	
	public static void writeFixedString(String name, int size, DataOutput out) throws IOException{
		char ch;
		for(int i=0;i<size; ++i) {
			ch = 0;
			if(name.length() > i) ch = name.charAt(i);
			out.writeChar(ch);
		}
	}
}

// Employee
public class Employee {
	
	private final String name;
	private double salary;
	private final LocalDate hireDay;
	public static final int NAME_SIZE = 40;
	public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;
	
	public Employee(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		hireDay = LocalDate.of(year, month, day);
	}
	
	public Employee(String n, double s, LocalDate date) {
		this(n, s, date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}
	
	public String getName() {return name;}
	public double getSalary() {return salary;}
	public LocalDate getHireDay() {
		return LocalDate.of(hireDay.getYear(), hireDay.getMonthValue(), hireDay.getDayOfMonth());
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString() {
		return name + " : " + salary + " : " + hireDay;
	}
	
}
```
