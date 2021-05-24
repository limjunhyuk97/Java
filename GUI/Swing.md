# GUI


## Java에서의 GUI
  - 수업에서 다루는 단순한 내용들에 대해서만 

### 1. AWT(Abstract Window Toolkit)
  - peer based GUI toolkit
  - 다양한 OS에서 사용되는 공통된 요소들을 뽑아서 구현하고,
  - 실제 button, textbox 같은 component는 OS에 기반하여 구현함. (운영체제에 native하다.) : heavy weight component
  - architecture independent하지 않고, inconsistent하다.

### 2. Swing
  - UI widget(component)들을 java로 구현
  - light weight component
  - 각 OS에서의 component 모습을 mimic할 수 있다. (Pluggable look and feel)
  - consistent하다. (cross platform look을 제공)
  - AWT를 기반으로 만들어졌다.


## Displaying Frame

### 1. JFrame (from Swing)
  - Swing component이지만, host OS에 기반하는 창이다.    

### 2. Event Dispatch Thread
  - Thread : 하나의 program 실행 흐름.
  - **GUI에서는 기본적으로 Thread가 두개 이상**이다.
  - 적절한 순간에 JVM이 event dispatch thread를 생성해서 실행시킨다. : 동시에 두가지 이상의 실행 (**concurrent**)
  - **Event Dispatch Thread**
    - event handling code가 실행되는 thread이다. ('Swing event hadling code runs')  
    - event queue에 들어와 있는 event들을 하나씩 꺼내서 실행시키는 thread이다.
    - main thread가 종료되어도 event dispatch thread는 계속 동작한다.
    - **Swing method를 호출하는 대다수의 코드들이 이 EDT에서 실행된다.**
    - EDT에서 해당 Swing method들을 호출하는 것이 Thread safe하다.

### 3. Frame properties and method
<img src="https://user-images.githubusercontent.com/59442344/119348977-86798200-bcd8-11eb-82db-50287f3cadc2.png" height=30% width=30%>

  - JFrame의 많은 method들이 상위 level에서 상속받은 것이다.
  - Frame 크기 정하기 / Frame 내 Component 위치 정하기
    - screen size를 얻어서, screen size에 상대적인 frame 만들기 : getScreenSize
    - preferred size를 이용하여 component 위치 정하기 : pack()

```java
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class SizedFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame = new SizedFrame();
			frame.setTitle("Sized Frame");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class SizedFrame extends JFrame{
	public SizedFrame() {
		
		// Dimension을 통해서 Screen size 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		// Screen 내에서 Frame에디에 둘 것인가? - platform의 방식에 맡긴다.
		setSize(screenWidth/2, screenHeight/2);
		setLocationByPlatform(true);
		
		// img 생성의 방식
		Image img = new ImageIcon("icon.gif").getImage();
		setIconImage(img);
		
	}
}

// component 별로 고유한 preferred size를 이용하여 자동으로 frame 내 component 배치
frame.pack();
```


## Drawing on a Component
  - Frame 속을 채우는 것 : Component를 Drawing하는 것. **그린 Component를 Frame에 붙이는 것이다!**
  - Frame은 여러가지 Pane으로 구성되어 있다. : **Pane == container**
    - root pane, layered pane, menu bar, content pane, glass pane ..

### 1. content pane
  - component들을 content pane에 붙이는 것이다.
  - Adding component : frame.add(comp)
  - making component : JComponent를 extend한다.
  - drawing : Graphics라는 Object에 다양한 component 존재, paintComponent 자동으로 호출됨
  - component size 지정 : getPreferredSize()

```java
class MyComponent extends JComponent{
  // paintComponent를 통해서 그림을 그림
  // 필요한 상황에서 자동으로 호출됨.
  public void paintComponent(Graphics g){
    code for drawing..
  }
}
```

### 2. paintComponent
  - **window가 redraw되는 상황에서 paintComponent가 호출**(실행된다.)
    - window가 처음 생성될 때
    - window 크기가 변했을 때 
    - window가 minimize되었다가 재생성 되었을 때
    - overlapping 된 window가 사라졌을 때
  - **paintComponent는 자동으로 호출되고, Graphics를 parameter type으로** 갖는다.

### 3. Graphics object
  - drawing에 필요한 다양한 설정들을 갖고 있다 : size, font, color, ...
  - pixel 단위로 screen display에 필요한 measurement가 수행됨

```java
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.*;

public class DrawTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame = new DrawFrame();
			frame.setTitle("Draw Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}

class DrawFrame extends JFrame{
	public DrawFrame() {
		add(new DrawComponent());
		pack();
	}
}

class DrawComponent extends JComponent{
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		
		// 1. Rectangle 그리기
		Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rect);
		
		// 2. Ellipse 그리기
		Ellipse2D ellipse = new Ellipse2D.Double();
		// 앞에서 만든 rectangle 속에 ellipse를 만들어라.
		ellipse.setFrame(rect);
		g2.draw(ellipse);
		
		// diagonal line 그리기
		g2.draw(new Line2D.Double(leftX, topY, leftX+width, topY+height));
		
		// 3. Ellipse와 동일 중심에 circle 그리기
		double centerX = ellipse.getCenterX();
		double centerY = ellipse.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY+radius);
		g2.draw(circle);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
}
```











