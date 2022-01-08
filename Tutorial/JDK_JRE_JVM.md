# JDK, JRE, JVM

- [참고](https://iamsang.com/blog/2012/08/19/introduction-to-java-bytecode/)

## Java code의 작성 및 실행

1. **JDK 기반으로 작성한 Java Source Code(.java)**
2. **Compiler** : jdk 에 들어있다. / (.java -> .class)
4. **Java Byte Code(.class)**
5. **JVM/JRE 내부의 JIT Compiler** : 동적 컴파일링 수행. 즉, 바이트 코드 파일을 읽으면서 인터프리팅을 수행. / (.class -> machine code)
6. **Operating system**

## JDK (Java Development Kit)

- java 언어를 사용한 개발을 위한 도구를 포함한다. (.java 를 컴파일링하는 컴파일러 포함)
- JDK를 설치하면 JRE도 같이 설치된다.

## JVM (Java Virtual Machine)

- Java Source Code로 부터 만들어지는 Java Byte Code를 실행시킬 수 있다.
- 플랫폼에 의존적이다. (MacOS, Windows 에서의 JVM은 각각 다르다.)
- 컴파일된 Byte Code는 어떤 플랫폼에서든지 실행 가능하다.
- JRE에서 동작한다.

## JRE (Java Runtime Environment)

- compile된 Java Byte Code를 JVM이 실행시킬 때 필요한 환경
- java 프로그램을 실행시키기 위해서는 반드시 필요하다.
- java 프로그래밍 도구를 갖고 있지 않다. (jdk <- jre가 안에 들어있음)
