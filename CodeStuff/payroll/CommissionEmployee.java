package employee;

public class CommissionEmployee extends Employee {
	
	private double grossSales;
	private double commissionRate;
	
	public CommissionEmployee(String name, String ssn, double grossSales, double commissionRate) {
		super(name, ssn);
		
		if(commissionRate <= 0.0 || commissionRate >=1.0)
			throw new IllegalArgumentException(
					"Commission rate must be > 0.0 and <1.0");
		
		
		if(grossSales < 0.0)
			throw new IllegalArgumentException(
					"Gross sales must be >= 0.0");
	
		this.grossSales = grossSales;
		this.commissionRate = commissionRate;
	}
	
	// getter, setter method...
	
	// Override abstract method earnings in Employee
	
	@Override
	public double getEarnings() {
		return commissionRate * grossSales;
	}
	
}
