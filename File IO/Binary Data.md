# Binary Data

## 1. Reading and Writing Primitive Types
  - 보통 숫자를 문자로 표현하는 것이 숫자를 binary로 표현하는 것보다 비효율적이다.
    - (예) 1234를 문자로 표현하면 1/2/3/4 8byte, binary로 표현하면 1234 4byte.
  - **이런 primitive type을 효과적으로 전달받기 위한 method를 정의한 class가 존재.**
    - DataInput / DataOutput이라는 interface의 구현 class 2개가 존재한다.
    - DataInput in = new DataInputStream(Files.newInputStream(path));

## 2. Random Access Files

## 3. Example : Random Access Files
