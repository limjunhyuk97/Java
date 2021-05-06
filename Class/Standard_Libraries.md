# Java Standard libraries

## [java.lang.\*](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/package-summary.html)
  - import할 필요 없음.
  - Fundamental class들이 담겨 있음.

### 1. [java.lang.Math](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/Math.html)
  - static method들로만 이루어져 있다.
  - 사용에 있어 굳이 객체를 만들 필요가 없다.

### 2. [java.lang.StringBuilder](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/StringBuilder.html)
  - String class의 alternative이다.
  - String이랑 다르게, mutable한 String을 만들 수 있게 해준다.

### 3. [java.lang.String](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/String.html)

### 4. [java.lang.System](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/System.html)

### 5. [java.lang.Comparable](https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html)

### 6. [java.lang.Number](https://docs.oracle.com/javase/8/docs/api/java/lang/Number.html)
  - 수에 대한 wrapper class의 direct super class이다.

## [java.util.\*](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/package-summary.html)
  - can find all collections and data structures available in java
  
### 1. [java.util.Scanner](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Scanner.html)

### 2. [java.util.Calander](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Calendar.html)

### 3. [java.util.Arrays](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Arrays.html)

### 4. [java.util.Date](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Date.html)

### 5. [java.util.GregorianCalendar](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/GregorianCalendar.html#%3Cinit%3E())

### 6. [java.util.Comparator](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html)

## [java.math.\*](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/math/package-summary.html)
  - provides functionalityfor working with arbitrary-precision decimal and integer values

### 1. [java.math.BigDecimal](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/math/BigDecimal.html)


### 2. [java.math.BigInteger](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/math/BigInteger.html)

## [java.time.\*](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/time/package-summary.html)

### 1. [java.time.LocalDate](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/time/LocalDate.html)
  - Factory method를 이용해서 instance를 생성한다.
  - 지역의 현재시간을 사용할 수 있게 해준다.

## [java.awt.\*](https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/java/awt/package-summary.html)

### 1. [java.awt.ActionListener](https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/java/awt/event/ActionListener.html)

### 2. [java.axt.ActionEvent](https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/java/awt/event/ActionEvent.html)

## javax.swing.*

### 1. [javax.swing.Timer](https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/javax/swing/Timer.html)

## java.io.*
  - reading files, working with pipes, streams and similar

## java.nio.*

## java.net.*
  - used for working with sockets, creating connections, creating network applications
