# Generic method
  - 같은 코드, 다른 type을 method에서!

```java
class ArrayAlg{
  public static <T> T getMiddle(T[] a){
    return a[a.length/2];
  }
}
```

## generic method 호출
  - **method 명 앞에 actual type을 명시해주어야** 한다.
  - 특정 경우 **actual type을 명시하지 않아도, compiler가 type infer할 수 있다.**
    - 같은 T에 대해서 type을 암묵적으로 알 수 있는 경우!

```java
// type 명시
String middle = ArrayAlg<String>getMiddle(words);

// type inference
String middle = ArrayAlg.getMiddle(words);

```
