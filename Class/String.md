# String

## String(문자열) 구현
  - C / C++ 처럼 문자의 배열을 통하여 문자열을 구현하지 않는다.
  - String 객체를 생성하여 문자열을 다룬다.
  - 만들어진 문자열들은 String class의 instance들이다.
  - String은 immutable한 class이다. : 재할당은 가능하지만, 값의 변경은 불가능하다.
  - String = ""은 null의 할당이 아니라, ""(길이 : 0) 문자열의 할당이다.
  
## String format / String.format(...)
  - String.format(...) : 출력하고자하는 내용을 String에 넣고자 할때 사용할 수 있다.
  
## String substring / String.substr(s, l)
  - String.substr(시작 인덱스 s, 자를 길이 l)
  - n 번째 index부터, 길이 l만큼을 잘라서 반환한다.
  
## String concatenation / String + String
  - + 연산자를 통해서 두개의 String 접합
  
## String comparison / String.equals(String), String.equalsIgnoreCase(String)
  - bool String.equals(String) : String 두개 비교
  - bool String.equalsIgnoreCase(String) : String 두개 대소문자 무시 비교
  - bool String == String : String 두개가 참조하는 실체가 같은지 비교
    - 같은 리터럴일 경우 : String == String
    - new로 새로운 객체를 생성해 버린 경우 : String != new String(...)
  
## String length / String.length() , String.codePointCount(s ,e)
  - code point : 문자와 대응되는 유니코드 값, code unit : 문자를 실제로 나타내기 위해 대응시키는 값들
  - int String.length() : String의 code unit 갯수 반환
  - int String.codePointCount(startIdx s, String.length()) : String의 code point 갯수 반환 (진짜 문자열의 길이)
  
## String i-th code unit, point / String.charAt() , String.codePointAt()
  - char String.charAt(i) : i 번째 code unit의 char형 문자를 도출한다.
  - int String.offsetCodePoints(0, i) : 0부터 시작했을 때, i번째 codePoint의 위치를 도출한다.
  - int String.codePointAt(i) : i번째 code point의 int형 값을 도출한다.
  
## String get all code point / String.codePoints().toArray()
  - String.codePoints().toArray() : String내의 모든 코드 포인트들을 int형의 배열로 반환한다.
  
## String API
  - String class가 제공하는 모든 method들 확인 가능!