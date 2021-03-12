# Array

  - **배열 선언**   
    - 배열 변수는 참조 변수에 속하므로, **배열은 힙 영역에 생성**되고, **배열 변수는 힙영역의 배열을 참조하는 것**이다.
    - **1차원 배열**
      - 타입[] 배열명
      - 타입 배열명[]
    - **2차원 배열**
      - 타입[][] 배열명
      - 타입 배열명[][]

```java

// 형식1. 타입[] 변수;
int[] intArray;
double[] doubleArray;

// 형식2. 타입 변수[];
int intArray[];
String stringArray[];

```

  - **배열 생성**   
    - **1차원 배열**
    - 값 목록을 이용한 생성과, new 연산자를 이용한 생성의 두가지 방식이 있다.
    - **값 목록을 이용한 생성**
      - **{값0, 값1, 값2, 값3, ,,, }** : **초기화**
      - 배열 변수의 선언과, 값 목록을 통한 변수 저장은 같은 실행문 내에서 일어나야 한다. (생성과 초기화 동시에 일어나는 경우)
      - 마찬가지 이유로, 메소드로의 배열 목록 전달 또한 값 목록을 통해 일어날 수 없다.
    - **new 연산자를 이용한 생성**
      - **new 타입[] {값0, 값1, 값2, ,,, }** : **초기화**
      - 배열 변수의 선언과, 값 목록을 통한 변수 저장을 서로 다른 실행문에서 일어날 수 있게 해준다. (생성과 초기화 별도로 일어나는 경우)
      - 마찬가지 이유로, 메소드로의 배열 목록 전달을 new 연산자를 통해 시킬 수 있다.
      - **new 타입[길이]** : **길이 지정**
      - 값 목록을 통해 값들을 넣지는 않았지만, 길이만 정할 때 사용.
      - 생성 시에 자동으로 기본 값으로 초기화 된다. (boolean 값은 false로 초기화 됨.)   
    - **2차원 배열**
    - **값 목록을 이용한 생성**
      - {{값0, 값1, 값2}, {값0, 값1, 값2} ,,, } : **초기화**
    - **new 연사자를 이용한 생성**
      - 배열의 행마다 열의 길이가 다르게 2차원 배열을 생성할 수 있다.
      - new 타입[][] {{값0, 값1, 값2}, {값3, 값4, 값5, 값6}}; : **초기화**
      - new 타입[길이][]; (행 별로 길이 다르게 설정 가능) : **길이 지정**
      - new 타입[길이][길이]; (n * m 길이 배열 설정)  : **길이 지정**
  
  - **배열 길이**
    - **.length**
    - 배열 객체 내의 length 필드에 접근하는 것이다.

```java

// 값 목록을 이용한 생성
int[] arr = {10, 20 ,30};

// 값 목록을 이용하여 변수 선언 이후에 별도로 변수에 저장할 수는 없다.
int[] arr2;
arr2 = {10, 20 ,30};

// new 연산자를 이용하여 변수 선언 이후에 별도로 변수에 저장 가능하다.
int[] arr2;
arr2 = new int[] {10, 20, 30};

// 메소드로의 전달 - 값 목록 X
add(int[] scores) { ... }
add( {10, 20, 30}); // 컴파일 오류

// 메소드로의 전달 - new 연산자 O
add(int[] scores) { ... }
add( new int[] {10, 20, 30} );


// 2차원 배열을 만들어 줄때
int intArray[][] = new int[2][];
int intArray[][] = new int[3][4];
String[][] StringArray = new String[3][];

```
  - **main 메소드의 기본 인자, String[] args 의 의미.**
    - 기본적인 의미는 String 배열인 args라는 배열변수를 받아서 main 메소드에 전달하라는 것이다.
    - **명령라인(명령 프롬프트)에서 공백으로 구분된 문자열 배열을 따로 전달**한다면 main에서 받아들일 것이다.
    - 따로 문자열 배열을 전달하지 않으면 args로 전달된 것이 없는 것이다.
      - java -cp bin [package_name].[class_name] 문자열0 문자열1 문자열2 ... 문자열 n-1   
      
  - **배열을 함수 인자로 전달할 때**
    - 1차원 배열을 받는지, 2차원 배열을 받는지를 매개인자에 명시해줘야 한다.
    
```java

package StringEqualsExample;

public class ArrayReferenceObjectExample {
	
	public static void main(String[] args) {
		
		int intArray[][] = new int[2][];
		intArray[0] = new int[3];
		intArray[1] = new int[6];
		
		add2D(intArray);
	}
	
	static int add1D(int[] intArr) {
		int sum = 0;
		for(int i=0 ; i<intArr.length; ++i)
			sum += intArr[i];
		return sum;
	}
	
	static int add2D(int[][] intArr) {
		int sum = 0;
		
		for(int i=0; i<intArr.length; ++i) {
			for(int j=0; j<intArr[i].length; ++j) {
				intArr[i][j] = i + j;
				sum += intArr[i][j];
				System.out.print(intArr[i][j] + " ");
			}
			System.out.println();
		}
		return sum;
	}
}

```

  - **배열의 복사**
    - **for문을 이용**한 직접 복사 방식
    - **System.arraycopy(Object src, int srcPos, Object dest, int destPos, int len)**를 이용한 복사 방식
      - src : source object , 원본 객체
      - srcPos : sourcePosition , 원본에서 복사 시작할 위치
      - dest : destination, 복사본 객체
      - destPos : destinationPosition, 복사본에서 복붙 시작할 위치
      - len : length, srcPos부터 복사할 갯수

  - **향상된 for문**
    - for( 배열 원소 받을 인자 : 배열 명)
    - 배열 내를 순회하며 꺼낼 수 있는 인자를 모두 

```java

package StringEqualsExample;

public class exam04 {
	public static void main(String[] args) {
		
		int arr[] = new int[5];
		int max = 0;
		
		for(int i : arr) {
			i = (int)(Math.random() * 10);
			System.out.print(i +" ");
			if(max < i)
				max = i;
		}
		
		System.out.println("\nmax value : " + max);
	}
}

```
