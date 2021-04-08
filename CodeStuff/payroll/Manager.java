// 두 번의 상속이 일어난 경우
public class Manager extends SalariedEmployee {
	
	private double bonus;
	
	public Manager(String name, String ssn,
			double weeklySalary) {
		
		super(name, ssn, weeklySalary);
		
		bonus = 0.0;
	}
	
	// getter, setter method 넣기
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	// override method earnings in CommissionEmployee
	@Override
	public double getEarnings() {
		return super.getEarnings() + bonus;
	}
	
}
