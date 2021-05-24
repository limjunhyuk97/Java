# Event Handling
  - GUI program은 event driven program이다.


## Basic of Event Handling

### 1. Event Source
  - Event를 만들어내는 다양한 source
  - source마다 만들어내는 event가 다르기 때문에, **source 마다 엮여있는 Event Listener가 다르다.**
  - **source와 엮여있는 Event Listener를 구현한 sub class의 instance를 add하면, event 감지가 가능**해진다.
  - 예) button, window

### 2. Event Object
  - Event 발생 시에, Event 정보를 전달하는 요소
  - 예) ActionEvent, WindowEvent

### 3. Event Listener
  - Event 발생 시에, Event를 처리하는 코드
  - event의 종류마다 다른 Listener interface가 있다!
  - 예) ActionListener interface(의 구현)

```java
// ActionListener를 implement한 MyListener class 생성 (EventListener 생성)
class MyListener implements ActionListener{
  public void actionPerformed(ActionEvent event){
    // button click에 의해 발생한 event 처리!
  }
}

// 1. listner 객체가 Event Listener 이다.
// 2. button 객체가 Event Source 이다.
// 3. ActionEvent의 인스턴스, event가 Event object 이다.

// Event Source (button 객체) 안에 여러개의 Event Listener를 집어넣을 수 있다.
// 즉, event에 반응하는 방법을 event source 객체에 넣음으로써 사용자가 정의할 수 있다.
ActionListener listener = new MyListener();
JButton button = new JButton("OK");
button.addActionListener(listener);
```


## Button Test

### 1. Button 만들기

### 2. 만든 Button을 JPanel에 붙이기














