import java.util.Comparator;
import java.util.Objects;
import java.util.Arrays;

public class ComparableByLambda{
	
	public static void main(String[] args) {
		
		// Student List
		Student[] sList = new Student[5];
		for(int i=0; i<5; ++i) {
			sList[i] = new Student();
			sList[i].score = (int)(Math.random() * 100) + 1;
		}
		sList[0].name= "tom";
		sList[1].name= "jack";
		sList[2].name= "megan";
		sList[3].name= "chris";
		sList[4].name= "brown";
		
		// Student List sorting by lambda expression
		// Comparator의 compare() method에 따라서 재정의 요구된다.
		Arrays.sort(sList, (Object s1, Object s2)->{ 
			Student ns1 = (Student)s1;
			Student ns2 = (Student)s2;
			return ns1.name.compareTo(ns2.name);
		});		
		for(Student i : sList) {
			System.out.println(i);
		}
		
		System.out.println();
		
    // 성적 순에 따른 정렬 (내림 차순) : ns2.score - ns1.score
    // 성적 순에 따른 정렬 (오름 차순) : ns1.score - ns2.score
		Arrays.sort(sList, (Object s1, Object s2)->{
			Student ns1 = (Student) s1;
			Student ns2 = (Student) s2;
			
			return ns2.score - ns1.score;
		});
		for(Student i : sList) {
			System.out.println(i);
		}
	
	}
}

class Student{
	public String name;
	public int score;
	
	public String toString() {
		return "name : " + name + ", score : " + score;
	}
}
