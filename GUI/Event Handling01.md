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

### 1. Button 사용 예시의 구현
  - Event Source : Button 포함된 JFrame 형성
  - Button click event에 따라 JPanel 색상 바꾸는 Event Listener 

```java
// Frame 
package button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Button이 존재하는 JFrame 객체 
public class ButtonFrame extends JFrame{
	
	private JPanel buttonPanel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ButtonFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		// button 만들고
		JButton yellowButton = new JButton("Yellow");
		JButton blueButton = new JButton("Blue");
		JButton redButton = new JButton("red");
		
		// panel 만들고
		buttonPanel = new JPanel();
		
		// panel에 button 붙이기
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		
		// frame에 panel component 붙이기
		// frame 속에서 add 했기 때문에, 자기 자신에게 붙이는 것
		add(buttonPanel);
		
		// button click event에 대한 event Listener (ActionListener) 객체 형성
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction blueAction = this.new ColorAction(Color.BLUE);
		ColorAction redAction = this.new ColorAction(Color.RED);
		
		// button들과 event Listener 결합
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}

	// Button이 생성하는 event에 대한 Event Listener 형성
	// 해당 Frame에만 해당하는 사항이기 때문에, private inner class를 생성
	private class ColorAction implements ActionListener{
	
		private Color backgroundColor;
		
		// backgroundColor 뭘로 받을지 생성자로 설정한다.
		public ColorAction(Color c) {
			backgroundColor = c;
		}
		
		// event에 대한 행동을 결정하는 actionPerformed method를 구현한다.
		// panel의 background 색상을 변경한다.
		public void actionPerformed(ActionEvent evnet) {
			buttonPanel.setBackground(backgroundColor);
		}
		
	}
	
}

// Frame 사용
import java.awt.EventQueue;
import javax.swing.JFrame;

public class ButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame = new ButtonFrame();
			frame.setTitle("Button test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

### 2. Lambda expression
  - Lambda expression을 이용하여 Event Listener를 더 잘 간결히 표현할 수 있다.
  - 이를 바탕으로 JFrame 내부에 EventListener add한, JButton 붙인, JPanel을 JFrame에 붙이기.

```java
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.JButton;

public class ButtonFrame2 extends JFrame{
	
	private JPanel panel;
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	
	public ButtonFrame2 () {
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		panel = new JPanel();
		
		makeButton("Yellow", Color.YELLOW);
		makeButton("REd", Color.RED);
		makeButton("BLUE", Color.BLUE);
		
		add(panel);
	}
	
  // lambda expression을 사용하여 간결하게 표현
	public void makeButton(String name, Color backgroundColor) {
		JButton button = new JButton(name);
		button.addActionListener((ActionEvent event)->{
      // backgroundColor가 외부에서 capture되어 저장된 것이다.
			panel.setBackground(backgroundColor);
		});
		panel.add(button);
	}
}

```
