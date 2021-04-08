package employee;

public class HourlyEmployee extends Employee {
	
	private double wage;
	private double hours;
	
	public HourlyEmployee(String name, String ssn,
			double wage, double hours) {
		super(name, ssn);
		
		if(wage < 0.0)
			throw new IllegalArgumentException(
					"Hourly wage must be >=0.0");
		
		if((hours<0.0) || (hours>68.0))
			throw new IllegalArgumentException(
					"Hours worked must be >=0.0 and <=68.0");
		
		this.wage = wage;
		this.hours = hours;
	}
	
	// getter, setter method ³Ö±â
	
	// override abtract method earnings in Employee
	@Override
	public double getEarnings() {
		if(hours <= 40)
			return wage * hours;
		else
			return 40*wage + (hours - 40)*wage*1.5;
	}
	
}
