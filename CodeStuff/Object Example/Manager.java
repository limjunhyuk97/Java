package equals;

import java.util.Objects;

public class Manager extends Employee {
	
	private double bonus;
	
	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		this.bonus = 0;
	}
	
	public double getSalary() {
		double baseSalary  = super.getSalary();
		return bonus + baseSalary;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
  // super를 통해서 super class field들 비교
	public boolean equals(Object other) {
		if(!super.equals(other))
			return false;
		
		Manager otherE = (Manager)other;
		return this.bonus == otherE.bonus;
	}
	
  // super 이용해서 super class field에 대한 hash 값 가져옴
	public int hashCode() {
		return super.hashCode() + 17 * new Double(bonus).hashCode();
	}
	
  // super 이용
	public String toString() {
		return super.toString() + ", [bonus : " + this.bonus +"]";
	}
	
}
