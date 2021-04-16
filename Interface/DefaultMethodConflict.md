# Default method conflict

## [Default method conflict](https://github.com/limjunhyuk97/java_study/blob/master/Interface/Interface_Inheritance.md)
  - 다중 상속의 문제라고 생각하면 된다.

### 1. class가 상속하고 있는 상위의 interface와, class에 동일 method가 다르게 재정의 되었다면 어떻게 할 것인가?
  - interface clash rule로 해결한다.


### 2. 현재 class가 상속하고 있는 상위의 interface들에서 동일 default method가 다르게 재정의 되었다면 어떻게 할것인가?
  - superclasses win rule로 해결한다.


## conflict의 해결방안

### 1. Interface clash rule
  - 또 재정의 할거면 상관 없다.
  - 재정의하지 않고, 상위 interface의 default 내용을 끌어다 쓸 것이라면, 선택해줘야 한다.

### 2. superclasses win rule
  - 상속하는 interface와 class에 같은 method가 재정의 되어있다면, class의 method를 사용하게 된다.





