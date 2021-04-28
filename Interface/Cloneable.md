# Cloneable


## clone() method
  - clone() method 자체는 **Object class 내에 있다.**
  - clone() method는 **Object 객체를 반환한다. 타입변환이 요구된다.**
  - clone() method는 **객체 내 field 값의 복사를 유도**한다.
    - primitive type field나 immutable한 field 객체에 대해서는 copy가 아닌 clone이 문제없이 가능하다.
    - mutable한 field 객체에 대해서는 reference 값의 복사를 유도한 것이기에, deep copy를 위해서 한단계 더 들어간 cloning이 요구된다.
    - e.g. " hireDay.clone() "는 hireDay 내부의 field 값의 복사를 유도한다! = Employee의 field : hireDay의 field들(primitive type) clone해서 전달.
  - Cloneable interface는 개발자가 cloning에 대해서 이해하고 있음을 나타내는 tag에 불과하며, exception 발생시키는 수단이다.


### 1. 얕은 복사만 허용
  - clone() 메소드는 기본적으로 번지 값을 공유하는 얕은 복사를 지원한다.
  - 객체의 field들이 immutable한 경우 얕은 복사만으로도 문제 없다.

### 2. 깊은 복사로 재정의
  - 객체의 field들이 mutable한 경우 깊은 복사가 필요하다.
  - 이를 위해서 clone() 메소드를 재정의하는 과정이 필요하다.

### 3. clone() 시도 방지


## Cloneable example

```java
package clonable;

import java.util.Date;
import java.util.GregorianCalendar;

// Cloneable이라는 interface를 implement한 것은, merely serves as a tag. (그저 tag에 지나지 않는다!)
// 개발자가 이 class 내의 cloning process를 인지하고 있다는 것과 같다.
public class Employee implements Comparable<Employee>, Cloneable{
	
	private String name;
	private double salary;
	// Date는 mutable한 class이다.
	private Date hireDay;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		this.hireDay = new Date();
	}
	
	// custom object를 
	public Employee clone() throws CloneNotSupportedException{
		// Object.clone() 호출 + 얕은 복사 시행
		// clone() 메소드가 언제나 Object 형을 반환하기 때문에, (Employee)를 통한 형변환이 요구된다..!
		Employee cloned = (Employee)super.clone();
		// hireDay field에 대해서는 깊은 복사 시행
		cloned.hireDay = (Date) hireDay.clone();
		return cloned;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent/100;
		salary += raise;
	}
	
	public int compareTo(Employee other) {
		return Double.compare(salary, other.salary);
	}
	
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month-1, day).getTime();
		hireDay.setTime(newHireDay.getTime());
	}
	
	public String toString() {
		return "Name : " + this.name + ", Salary : " + this.salary + ", HireDay : "+this.hireDay;
	}
	
}


// Cloneable test class
package clonable;

public class CloneTest {
	public static void main(String[] args) {
		try {
			Employee original = new Employee("John Q Public", 50000);
			original.setHireDay(2000, 1,1);
			Employee copy = original.clone();
			
			copy.raiseSalary(10);
			copy.setHireDay(2002, 12, 31);
			
			System.out.println("original : " + original);
			System.out.println("copy : " + copy);
		}
		catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}


```
