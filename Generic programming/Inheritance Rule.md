# Inheritance rule for generic types

## 1. Type parameter inheritance와 class inheritance의 관계
  - **type parameter의 상속**과 **class간의 상속**은 **아무런 관계가 없다!**

## 2. Upper Bounded WildCard
  - **type parameter의 상속**과 **sub class로 내려가는 상속관계**를 연결시키기 위해서 사용한다.
  - classA <? extends superClass> classA_타입_변수 : 
  - **Upper Bounded WildCard와 generic class, method의 사용**
    - (generic class) field에 값 초기화, getter은 문제 없다 + setter는 문제 발생.
    - (generic method) method parameter, method 값 return은 문제 없다.
    - **왜 set이 안될까?** : class 내부에서, upper bound, 더 상위 type의 argument를 받을 지 모르기 때문이다.

```java
public class WIldCard {
	
	public static void main(String[] args) {
		
		Employee em;
		Manager mg;
		
		// Employee type으로 받을 건데, 그 상위의 것들도 받겠다!
		Pair<? extends Employee> pair1 = new Pair(new Manager("Tom", 23, 3000), new Manager("Jack", 45, 5000));
		Pair<? extends Employee> pair2 = DuplicateEmployee(pair1);
		
		// 서로 다른 class type을 넣는 것은 불가능하다!
		// Pair<? extends Employee> pair2 = new Pair(new Manager("Tom", 23, 3000), new Employee("Jack", 45));
		
		////////////// pair1 //////////////
		//// SET
		// pair.setFirst(new Manager("Bill", 34, 6000));
		
		
		//// GET
		// 받아올 수 있다.
		em = pair1.getFirst();
		
		// Employee type으로 받을 건데, 그 하위의 것들도 받겠다!
		// mg = pair1.getSecond();
		
		
		//// FIELD
		em = pair1.second;
		
		// 강제 타입 변환은 가능하다.
		mg = (Manager) pair1.second;
		
		
		//// Method parameter
		Print(pair1);
		Print(pair2);
		
		
		//// 번지 확인
		System.out.println(pair1.hashCode());
		System.out.println(pair2.hashCode());
	}
	
	// Pair<? extends Employee> 로 parameter 전달 가능
	public static void Print(Pair<? extends Employee> pair) {
		System.out.println(pair.getFirst());
		System.out.println(pair.getSecond());
	}
	
	// Pair<? extends Employee>로 return 가능
	public static Pair<? extends Employee> DuplicateEmployee(Pair <? extends Employee> pair){
		return new Pair(new Employee(pair.first.getName(), pair.first.getSalary()), new Employee(pair.second.getName(), pair.second.getSalary()));
	}
	
}

class Employee{
	private String name;
	private int salary;
	
	public Employee(String name, int salary){
		this.name = name;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSalary() {
		return salary;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String toString() {
		return name + " " + salary;
	}
	
}

class Manager extends Employee{
	private int bonus;
	
	public Manager(String name, int salary, int bonus) {
		super(name, salary);
		this.bonus = bonus;
	}
	
	public int getBonus() {return bonus;}
	
	public void setBonus(int bonus) {this.bonus = bonus;}
	
	public String toString() {
		return super.getName() + " " + super.getSalary() + " " + bonus;
	}
}
```
## 3. Lower Bounded WildCard
  - **type parameter의 상속**과 **super class로 올라가는 상속관계** 연결시키기 위해서 사용한다.
  - classA <? super superClass> classA_타입_변수
  - **Lower Bounded WildCard와 generic class, method의 사용**
    - (generic class) field에 값 초기화, setter는 문제 없다 + getter는 문제 발생.
    - (generic method) method parameter, method 값 return은 문제 없다.
    - **왜 get이 안될까?** : class 외부에서, lower bound, 더 하위 type의 argument를 받을 지 모르기 때문이다.
    - (예) LocalDate -> ChronoLocalDate -> Comparable 이므로.. : **T extends Comparable<? super T>** 로 표현.
      - **즉, T 포함, T에 대한 superclass들 중에 Comparable 속성을 implement하고 있는 경우의 T!**
      - static <T extends Comparable<? super T> > void sort(List<T> list)
      - static <T> void sort(List<T> list, Comparator<? super T> c)
     
```java
public class LowerBoundedWildCards {
	
	public static void main(String[] args) {
		
		Manager[] arr= new Manager[5];
		arr[0] = new Manager("Tom", 3000, 3000);
		arr[1] = new Manager("Bob", 2000, 2000);
		arr[2] = new Manager("Jack", 1500, 1500);
		arr[3] = new Manager("Michael", 4000, 4000);
		arr[4] = new Manager("Michelle", 2400, 2400);
	
		Pair<Employee> employee = new Pair<Employee>();
		
		// employee는 상위의 것이다.
		// erase 성질을 통해서 super type을 받는 것을 허용하기 위해서!
		minmaxBonus(arr, employee);
		
		// 출력시에 Manager 형식으로 출력된다!
		// java에서 실제 객체 따라서 method 호출되기 때문에!
		System.out.println(employee);
		
		/////////////////////////////////////////////////////////
		
		Employee em;
		Manager mg;
		
		// Employee type으로 받을 건데, 그 상위의 것들도 받겠다!
		Pair<? super Manager> pair1 = new Pair(new Manager("Tom", 23, 3000), new Manager("Jack", 45, 5000));
		
		// 서로 다른 class type을 넣는 것은 불가능하다!
		// Pair<? extends Employee> pair2 = new Pair(new Manager("Tom", 23, 3000), new Employee("Jack", 45));
		
		////////////// pair1 //////////////
		//// SET
		pair1.setFirst(new Manager("Bill", 34, 6000));
		
		
		//// GET
		// 외부로 꺼내오는게 불가능
		// em = pair1.getFirst();
		
		// Employee type으로 받을 건데, 그 상위의 것들도 받겠다!
		// mg = pair1.getSecond();
		
		
		//// FIELD
		// 외부로 꺼내오는게 불가능
		// getter 사용 못하는 것과 마찬가지의 이유
		em = (Employee)pair1.second;
		
		// 강제 타입 변환은 가능하다.
		mg = (Manager) pair1.second;
		
		
		//// Method parameter
		Print(pair1);
		
	}
	
	public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
		if(a.length == 0) return;
		
		Manager min = a[0];
		Manager max = a[0];
		
		for(int i=0; i<a.length; ++i) {
			if(a[i].getBonus() < min.getBonus()) min = a[i];
			if(a[i].getBonus() > max.getBonus()) max = a[i];
		}
		
		result.setFirst(min);
		result.setSecond(max);
	}
	
	public static Pair<? super Manager> minmaxBonus(Manager[] a) {
		if(a.length == 0) return null;
		
		Manager min = a[0];
		Manager max = a[0];
		Pair result = new Pair();
		
		for(int i=0; i<a.length; ++i) {
			if(a[i].getBonus() < min.getBonus()) min = a[i];
			if(a[i].getBonus() > max.getBonus()) max = a[i];
		}
		
		result.setFirst(min);
		result.setSecond(max);
		
		return result;
	}
	
	// Pair<? super Employee> 로 parameter 전달 가능
	public static void Print(Pair<? super Manager> pair) {
		System.out.println(pair.getFirst());
		System.out.println(pair.getSecond());
	}
		
	// Pair<? super Employee>로 getter 이용 불가능!
	/*
	public static Pair<? super Employee> DuplicateEmployee(Pair <? super Employee> pair){
		return new Pair(new Employee(pair.first.getName(), pair.first.getSalary()), new Employee(pair.second.getName(), pair.second.getSalary()));
	}
	*/
	
}
```

## 3. UnBounded WildCard
  - 사용이 상당히 제한된다.
  - 모든 경우 Object로 받아야 한다.
  - 어떤 type에도 종속되지 않는 작업을 수행할 때 사용한다.




