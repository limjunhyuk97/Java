# Cloneable


## Cloneable interface
  - **깊은 복사가 이루어지도록 도와주는 interface**
  - interface 자체에는 메소드가 없음
  - **Cloneable interface를 implement** 하고, **Object class**의 **protected method**인 **clone() 메소드**를 재정의 한다.
  - **protected로 정의되어 있기에**, **clone() 메소드 사용하려면, Cloneable interface를 implement하고 public으로 redefine해주는 과정이 필요**하다.

## clone() method

### clone() 메소드를 허용하지 않을 것인가? 깊은 복사를 이용할 것인가? 얕은 복사를 이용할 것인가?

### 1. 얕은 복사만 허용
  - clone() 메소드는 기본적으로 번지 값을 공유하는 얕은 복사를 지원한다.
  - 객체의 field들이 immutable한 경우 얕은 복사만으로도 문제 없다.

### 2. 깊은 복사로 재정의
  - 객체의 field들이 mutable한 경우 깊은 복사가 필요하다.
  - 이를 위해서 clone() 메소드를 재정의하는 과정이 필요하다.

### 3. clone() 시도 방지


## Cloneable example

```java
// Cloneable class
package clonable;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Comparable<Employee>, Cloneable{
	
	private String name;
	private double salary;
	private Date hireDay;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
		this.hireDay = new Date();
	}
	
	// custom object를 위한 깊은 복사용 clone을 재정의 해주는 과정.
	public Employee clone() throws CloneNotSupportedException{
		// Object.clone() 호출 + 얕은 복사 시행
		Employee cloned = (Employee)super.clone();
		// hireDay field에 대해서 깊은 복사 시행
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
