# Package

## Pacakge 역할

  - class를 체계적으로 관리하기 위해 사용함.
  - 물리적으로 봤을 때는 **파일 시스템의 폴더 기능.**
  - 기능적으로 봤을 때는 **class를 유일하게 만들어주는 식별자 역할**
    - 상위패키지.하위패키지.클래스

## Package 선언

  - **상위패키지명.하위패키지명**
    - .도트를 이용해서 상위, 하위 포함관계를 바탕으로 패키지를 선언할 수 있다.
  - package에 대한 정보 또한, class에 대한 정보의 일부이므로 (class 존재의 유일성을 만들어주기에), **class를 다른 패키지로 함부로 옮길 수 없다.**
  - **package이름은 소문자로 작성하는 것이 관례**
 
```java
 
   package chapter02.sec03.test01
   // chapter02 package 내부의, sec03 package 내부의, test01 package
 
```
 
## Package 사용 (Import 문)

  - class파일의 package 선언과 class 선언 사이에 들어감.
 
### 1. **Import의 방식 (3)**
  - **다른 package에서 사용되는 class들을 사용하고 싶으면 package전체나, class를 import해와야 한다.**
  - package 내부 전체 import : **import package1.package2.\***
  - package 내부 특정 class import : **import package2.package3.classname**
  - package 내부 특정 static member 사용 : **import static package1.\***

```java

package practice;

import static java.lang.System.*;

public class StaticImportTest {
	
	public static void main(String[] args) {
		
		out.println("static member import");
		exit(0);
		
	}
	
}


```

### 2. **Import Class 사용 방식 (2+1) **
  - 만약, **다른 package, class name 중복이면, 정확한 위치 기술해 줘야 한다.**
  - class의 fully qualified name 사용
  - class의 import statement 사용 (class 이름만으로 사용)

```java

package ...

import hankook.*
import kumho.*
import hyundai.engine.engine

class Car{
  
  hankook.Tire HTire new hankook.Tire();
  kumho.Tire KTire new kumho.Tire();
 
}
// 이클립스 : 'ctrl + shift + O' import package 자동 import

```



