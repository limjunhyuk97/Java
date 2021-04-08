public class PayrollSystemTest {
	
	public static void main(String[] args) {
		
		SalariedEmployee se = new SalariedEmployee("John", "111-11-1111", 800.00);
		HourlyEmployee he = new HourlyEmployee("Karen", "222-22-2222", 16.75, 40);
		CommissionEmployee ce = new CommissionEmployee("Sue", "333-33-3333", 10000, .06);
		Manager boss = new Manager("Bob", "444-44-4444", 2500.0);
		
		System.out.println("Employees processed Individually : ");
		
		// %n 은 줄바꿈..!
		System.out.printf("%n%s%s : $%,.2f%n%n", se.getName(), " earned", se.getEarnings());
		System.out.printf("%s%s : $%,.2f%n%n",he.getName(), " earned", se.getEarnings());
		System.out.printf("%s%s : $%,.2f%n%n",ce.getName(), " earned", ce.getEarnings());
		System.out.printf("%s%s : $%,.2f%n%n",boss.getName(), " earned", boss.getEarnings());
		
		Employee[] employee = new Employee[4];
		employee[0] = se;
		employee[1] = he;
		employee[2] = ce;
		employee[3] = boss;
		
		System.out.println("Employees processed polymorphically : %n%n");
		
		for(Employee curEm : employee) {
			System.out.println(curEm.getName());
			
			// Manager class에만 존재하는 method를 실행시키기 위해서..
			// instanceof를 통해서 강제타입변환을 진행해주고, method를 호출해준다!
			// 일종의 code pattern이다.
			if(curEm instanceof Manager) {
				Manager tmpEm = (Manager)curEm;
				tmpEm.setBonus(100.0);
			}
			System.out.printf("%s earned $%,.2f%n%n",
					curEm.getName(), curEm.getEarnings());
		}
	}

}
