package employee;

public abstract class Employee {
	
	private final String name;
	private final String ssn;
	
	public Employee(String name, String ssn) {
		this.name = name;
		this.ssn = ssn;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public abstract double getEarnings();  
	
}
