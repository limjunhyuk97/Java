import java.util.*;

// Employee Object sorting
public class EmployeeSortTest {
	public static void main(String[] args) {
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("Tom", 35000); 
		staff[1] = new Employee("Jack", 75000);
		staff[2] = new Employee("Pearl", 38000);
		
    // Arrays.sort를 이용하려면 staff 배열 내 요소들(Employee class)이 "Comparable" interface의 구현 class여야 한다.
		Arrays.sort(staff);
		
		for(Employee e : staff) {
			System.out.println("name : " + e.getName() + ", salary : "+e.getSalary());
		}
		
		
	}
}

// Employee class type implements Comparable
public class Employee implements Comparable<Employee> {
	private String name;
	private double salary;
	
	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent/100;
	}
	
  // Comparable interface의 compareTo()를 Override해서 적용한다.
  // 마치 CPP의 operation overloading과 비슷한 느낌이다.
	public int compareTo(Employee other) {
          // Double.compare(n1, n2) : n1 > n2, 양수 / n1 == n2, 0 / n1 < n2, 음수 return
          return Double.compare(salary, other.salary);
	}
}

