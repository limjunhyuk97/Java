# Layout Manager

## Layout Manager
  - Swing에서 **component를 container에 배치**하는 방식
    - **writing code** hand by hand
    - **using drag-and-drop** (visual GUI builder)
    - **choosing layout manager** : layout manager에게 배치를 맡긴다.
  - Layout manager는 **container 내의 component의 position과 size를 결정**
  - 화면이 복잡해지면, **각 component -> panel에 추가 -> frame에 추가** 과정을 거친다.

### 1. Layout Manager 종류
  - **Flow Layout**
  - **Border Layout**
  - **Grid Layout**
  - Grid Bag Layout
  - Box Layout
  - Card Layout
  - Spring Layout
  - Group Layout

## Flow Layout
![flow layout](https://user-images.githubusercontent.com/59442344/119921416-ee65ec00-bfa8-11eb-9f33-b59ad2146432.png)
  - put components in a row
  - components sized at their preferred size
    - preferred size는 layout manager가 각 component에 대해서 **getPreferredSize() 메소드** 호출하여 불러옴
    - 사용자가 필요에 의해서 preferred size overriding하여 변경 가능.
  - JPanel의 default

```java
MyFrame f = new JFrame();

// Flow Layout 객체를 좌측 정렬기준으로 생성
FlowLayout fl = new FlowLayout(FlowLayout.LEFT);

// 해당 Layout를 Frame에 적용
f.setLayout(fl);
```

## Border Layout
![border layout](https://user-images.githubusercontent.com/59442344/119921422-f02faf80-bfa8-11eb-9a56-5f40d656b23d.png)
  - components grow to fill the available area (자동으로 component size를 조정한다.)
  - NORTH, WEST, CENTER, EAST, SOUTH의 5가지 영역
  - JFrame의 default이다.

```java
// Panel에 Button 붙이기
JPanel panel = new JPanel();
panel.add(yellowButton);
panel.add(blueButton);
panel.add(redButton);

// 해당 Panel을 Frame에 붙이기
frame.add(panel, BorderLayout.SOUTH);
```

## Grid Layout
![grid layout](https://user-images.githubusercontent.com/59442344/119921419-eefe8280-bfa8-11eb-895c-1a69aa218fb7.png)
  - 격자 구조의 layout : all components in rows and columns = 행 갯수와, 열 갯수로 grid 생성
  - add 할 때는 일반적으로 add한 순서대로, 위에서부터 아래로 채워진다. 
  - all components are given the same size
  - all components grow to fit the entire cell
  - add 할 때는 일반적으로 add한 순서대로, 위에서부터 아래로 채워진다. 
```java
panel.setLayout(new GridLayout(4, 4));
panel.add(new JButton("1"));
panel.add(new JButton("2"));
..
```
  - row나 column count가 0 이라면, row나 column의 수를 계속 늘려나갈 수 있다.
```java
toolbar.setLayout(new GridLayout(0, 2));
// (1, 2) (3, 4) (5, 6) ... 계속 추가 가능
```

## Example : Calculator

```java
// 1. Calculator class
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Calculator {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			CalculatorFrame calculator = new CalculatorFrame();
			calculator.setName("Calculator");
			calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			calculator.setVisible(true);
		});
	}
}

// 2. CalculatorFrame class
import javax.swing.JFrame;

public class CalculatorFrame extends JFrame{
	public CalculatorFrame() {
		add(new CalculatorPanel());
		pack();
	}
}

// 3. CalculatorPane class
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalculatorPanel extends JPanel{
	
	private JButton display;	// Button으로 display 를 만듬
	private JPanel panel;		// 나머지 grid 부분
	private double result;
	private String lastCommand;	// 새로운 값에 대한 push가 들어오면, last command를 바탕으로 이전 값, 이후 값에 대한 계산 진행
	private boolean start;		// 처음 숫자 입력받는 부분 구분해주기 위한 flag
	
	public CalculatorPanel() {
		
		setLayout(new BorderLayout());
		
		result = 0;
		lastCommand ="=";					// 처음 n (사칙연산) 에서는 n을 그냥 보여주기로 함
		start = true;
		
		// add the display
		display = new JButton("0");
		display.setEnabled(false);			// Button 기능 상실
		add(display, BorderLayout.NORTH);
		
		ActionListener insert = new InsertAction();
		ActionListener command = new CommandAction(); 
		
		// add the button in a 4 By 4 Grid
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,4));
		
		// 숫자는 insert : InsertAction
		// 문자는 command : CommandAction
		addButton("7", insert);
		addButton("8", insert);
		addButton("9", insert);
		addButton("/", command);
		
		addButton("4", insert);
		addButton("5", insert);
		addButton("6", insert);
		addButton("*", command);
		
		addButton("1", insert);
		addButton("2", insert);
		addButton("3", insert);
		addButton("-", command);
		
		addButton("0", insert);
		addButton(".", insert);
		addButton("=", command);
		addButton("+", command);
		
		add(panel, BorderLayout.CENTER);
	}
	
	public void addButton(String name, ActionListener listener) {
		JButton button = new JButton(name);
		button.addActionListener(listener);
		panel.add(button);		
	}
	
	// 계산과 출력
	void calculate(double x) {
		if(lastCommand.equals("+")) result += x;
		else if(lastCommand.equals("-")) result -= x;
		else if(lastCommand.equals("*")) result *= x;
		else if(lastCommand.equals("/")) result /= x;
		else if(lastCommand.equals("=")) result = x;
		display.setText("" + result);
	}
	
	// 연산자에 대한 입력이 끝나면 start = true , 연산처리가 벌어짐 & 출력
	// 숫자에 대한 입력이 끝나면 start = false , 출력
	
	// 수에 대한 입력 출력하기
	private class InsertAction implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String input = event.getActionCommand();
			
			// 양수 입력 시에는 항상 start 가 true
			if(start) {
				display.setText("");
				start = false;
			}
			
			// 음수 입력 시에는 항상 start가 false
			display.setText(display.getText() + input);
		}
	}
	
	// 사칙연산기호에 대한 연산처리 + 음수 입력에 대한 입력 출력하기
	private class CommandAction implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			
			// 음수를 처리하는 방식
			if(start) {
				// 음수 입력?
				if(command.equals("-")) {
					display.setText(command);
					start = false;
				}
				// 움수입력 X?
				else
					lastCommand = command;
			}
			
			// 연산 처리
			else {
				// display로부터 수를 가져온다.
				calculate(Double.parseDouble(display.getText()));
				lastCommand = command;
				// 다시 수가 들어와야 한다는 것을 표시.
				start = true;
			}
			
		}
	}
}
```







