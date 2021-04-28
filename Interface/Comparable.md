# Comparable

## Comparable interface (generic/X interface + compareTo 재정의)
  - **sort 사용하려면 Comparable interface를 implement 하고, compareTo()를 재정의 해야한다.**
    - **Comparable interface** : public interface Comparable : int compareTo(Object other)
    - **generic한 Comparable interface** : public interface Comparable<T> : int compareTo(T other)
  - .equals()에서 같다고 정의한 기준과 compatible해야한다. (equals가 true인 상태와 compareTo()가 0인 상태가 일치해야 한다!)
  - Comparable interface는 다음과 같은 모습을 한다. (lambda expression)

```java
// Comparable interface
public interface Comparable{
  int compareTo(Object other);
}

// generic Comparable interface
public interface Comparable<T>{
  int compareTo(T other);
}
```

## Comparable interface 예시
  - compareTo() 메서드 return 값이
    - 양수면 바꾸고
    - 0이나 음수면 안바꾼다
  - java.lang.Comparable<T> : int compareTo(T other)
  - java.util.Arrays : static sort(Object[] a)
  - java.lang.Double : static int compare(double x, double y)

```java
package comparable;

public class Employee implements Comparable<Employee>{
	
	private String name;
	private double salary;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
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
	
	public String toString() {
		return "Name : " + this.name + ", Salary : " + this.salary;
	}
}


package comparable;

import java.util.*;

public class EmployeeSortTest {
	public static void main(String[] args) {
		var staff = new Employee[3];
		
		staff[0] = new Employee("Lim", 35000);
		staff[1] = new Employee("Jun", 40000);
		staff[2] = new Employee("Hyuk", 37000);
		
		Arrays.sort(staff);
		
		for(Employee e : staff) {
			System.out.println(e.toString());
		}
	}
}
```
