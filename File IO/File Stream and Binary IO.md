# File Stream and Bianry IO


## 01. File and Streams
  - java는 **File**을 **sequential stream of byte(일련의 byte code 연속)로** 본다.

![stream2](https://user-images.githubusercontent.com/59442344/120750311-1b824380-c541-11eb-88f0-3f6ead558c21.png)
  - FileInputStream (low level): Byte 단위로 data를 불러온다.
  - DataInputStream (high level): Byte 단위의 data를 type 단위로 조합해서 Java Program에 제공한다.
  - DataOutputStream (high level): type 단위를 Byte 단위로 쪼갠다.
  - FileOutputStream (low level) : Byte 단위로 쪼개진 것을 File에 입력한다.

### 1. Input Stream 
  - 특정 source(file, socket, array, network connection, memory block)에서 program으로 들여오는 byte sequence 

### 2. Output Stream
  - program에서 특정 source(file, socket, array, network connection, memory block)로 내보내는 byte sequence


## 02. Java API for Input and Output Streams

### 1. Byte-Based stream
  - 입출력을 binary 형식으로 진행한다. (image, file ...)
  - binary file을 읽고, 쓰는데 사용된다.

![Byte-Based stream](https://user-images.githubusercontent.com/59442344/120751295-c2b3aa80-c542-11eb-985d-387942883e43.png)

### 2. Character-Based stream
  - 입출력을 character encoding 방식에 따라서 진행한다 (text)
  - text file을 읽고, 쓰는데 사용된다.

![Character-Based stream](https://user-images.githubusercontent.com/59442344/120752885-54bcb280-c545-11eb-992f-9c6d31ff74fe.png)

  - **InputStreamReader**는 1에서의 Byte Stream 과 character stream의 bridge 역할을 한다.
  - **InputStreamReader**는 다양한 encoding 방식을 갖는 byte stream을 character로 바꿀 수 있도록 만들어진 것이다.
    - new InputStreamReader(System.in) : 키보드에서 character들을 읽어들이는 방식
    - new InputStreamReader(FileInputStream 객체) : file의 input stream에서 character들을 읽어들이는 방식 
  - **FileReader**는 특정 file에 들어있는 character가 encode된 방식을 바탕으로 program에 character들을 옮겨온다.
    - Byte stream을 읽어온다
    - 특정 파일의 encoding 방식에 따라, Byte Stream을 program의 encoding 방식에 따른 character로 바꾼다. 

### 3. Byte-Based, Character-Based, Scanner
  - **Byte-Based Stream 생성(FileStream 생성 -> DataStram 넣어주기) : 데이터를 byte 단위로 꺼내고 밀어넣는다.**
    - FileInputStream, FileOutputStream
    - DataInputStream(FileInputStream 객체)
    - DataOutputStream(FileOutputStream 객체) 
  - **Character-Based Stream 넣어주기[이때, encoding 방식 정의] : 데이터를 character 단위로 꺼내고 밀어넣는다.**
    - PrintWriter(파일명, encoding 방식) 
    - **Scanner에서도** Stream에서 어떤 방식으로 꺼내올지에 대한 **encoding 방식 정의 가능**

## 03. Obtaining Stream
  - Stream을 얻어오는 방식!

```java
// java.nio.file.Files
Path path = Paths.get(filenameString);
Path path = Path.of(filenameString);
InputStream in = Files.newInputStream(path);
OutputStream out = Files.newOutputStream(path);

// 일반적인 방식
InputStream in = new FileInputStream(filenameString);
OutputStream out = new FileOutputStream(filenameString);

```











