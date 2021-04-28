package equals;

import java.time.*;
import java.util.Objects;

public class Employee {
	private String name;
	private double salary;
	private LocalDate hireDay;
	
	public Employee(String name, double salary, int year, int month, int day) {
		this.name = name;
		this.salary = salary;
		this.hireDay = LocalDate.of(year, month, day);
	}
	
	public double getSalary() {
		return this.salary;
	}
	
  // equals 재정의
	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null) return false;
		if(getClass() != other.getClass()) return false;
	  
    // Object로 generic하지 않게 받았을 때 비교하려면, 반드시 타입변환 해줘야 함.
		Employee otherE = (Employee)other;
		return Objects.equals(name, otherE.name) && this.salary == otherE.salary && Objects.equals(hireDay, otherE.hireDay);
	}
	
  // hash code 재정의
	public int hashCode() {
		return Objects.hash(name, salary, hireDay);
	}
	
  // toString 재정의
	public String toString() {
		return getClass().getName() + " [name : " + this.name + "], [salary : " + this.salary +"], [hireDay : " + this.hireDay + "]"; 
	}
}
