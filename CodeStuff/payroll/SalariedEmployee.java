public class SalariedEmployee extends Employee {
	
	private double weeklySalary;
	
	public SalariedEmployee(String name, String ssn,
			double weeklySalary) {
		super(name, ssn);
		
		if(weeklySalary < 0.0)
			throw new IllegalArgumentException(
					"weeklySalary wage must be >=0.0");
	
		this.weeklySalary = weeklySalary;
	}
	
	// getter, setter method 넣기
	
	public void raiseSalary(double rate) {
		weeklySalary += (weeklySalary * rate / 100);
	}
	
	// override abtract method earnings in Employee
	@Override
	public double getEarnings() {
		return weeklySalary;
	}
	
}
