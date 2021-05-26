# Event Handling


## Action
  - Action event는 여러가지 방식으로 생성된다 : button, menu, toolbar, keystroke ...
  - 여러가지 source에 동일 listener 부착 가능
  - 동일 source에 여러 listener 부착 가능

### 1. Action interface
  - 하나의 command를 여러 event source들에 부착시킬 수 있는 interface이다.
  - 즉, **listener code(action 처리하는 handler 코드)와 action properties(action 처리에 필요)를 결합시킨다.**

```java
// Action Interface method들

void actionPerformed(ActionEvent event)  // event 감지
void setEnabled(false)                   // action Enable 시킴 
boolean isEnabled()                      // action Enable 여부 확인
void putValue(String key, Object value)  // property put
Object getValue(String key)              // property get

// Action Interface method의 사용
yellowAction.putValue(Action.SHORT_DESCRIPTION, "Set panel color to yellow"); // pre-defined key
yellowAction.putValue("color", Color.YELLOW);                                 // user-defined key

```


## KeyBoard Commands
  - KeyBoard를 통해 명령 내림
  - KeyStroke는 focus된 component로 전달된다
    - tab키를 통해서 focus된 component 변경
    - space bar키를 통해서 focus된 component event 발생  
  - input map, Action map으로 분리하기에, keyboard command에 따른 action mapping은 indirect하다.

```java
// KeyBoard Command 설정

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ActionFrame extends JFrame{
	private JPanel buttonPanel;
	private static final int D_WIDTH = 300;
	private static final int D_HEIGHT = 200;
	
	public ActionFrame() {
		
		// Frame size setting
		setSize(D_WIDTH, D_HEIGHT);
		buttonPanel = new JPanel();
		
		// Action 객체 생성
		// ColorAction <- listener code + property들 <- AbstractAction Interface 구현
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow-ball.gif"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("blue-ball.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("red-ball.gif"), Color.RED);
		
		// Button에 Action 객체(Event Listener) 추가
		// 즉, 각 button 안에 <- action 구현 객체 <- property와 listener 코드 결합된 상태
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		
		// Frame에 buttonPanel 부착
		add(buttonPanel);
		
		// key name -> name에 연관 : ctrl+Y 누른 경우 == panel.yellow key와 연관 지어라.
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		
		// name -> action name에 연관 (key -> name -> action) : panel.yellow key의 경우 yellowAction 실행시켜라.
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}
	
	// (local inner class) ColorAction <- AbstractAction 상속
	public class ColorAction extends AbstractAction{
		public ColorAction(String name, Icon icon, Color c) {
			// Action.NAME ,,, 은 predefined key 값이다.
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
			// "color"라는 사용자 설정 key 값에서 c(색) 데이터를 넣어라.
			putValue("color", c);
		}
		
		public void actionPerformed(ActionEvent event) {
			Color c = (Color)getValue("color");
			buttonPanel.setBackground(c);
		}
	}
}

// main에서의 사용

import java.awt.EventQueue;
import javax.swing.*;

public class ActionFrameTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			JFrame frame = new ActionFrame();
			frame.setName("ActionFrameTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

## Mouse events
 
### 1. MouseListener method
  - mouse로 발생한 event에 반응한다
  - **void mousePressed(Mouse event)** : 마우스를 눌렀을 때
  - **void mouseReleased(Mouse event)** : 마우스 누름에서 손을 뗄 때
  - **void mouseClicked(Mouse event)** : mousePressed + mouseClicked
  - 한 번 마우스 클릭하면, 3가지 이벤트가 발생하는 것이다.

### 2. MouseMotionListener
  - mouse의 움직임에 반응한다.
  - mouseMoved method : 마우스 움직임
  - mouseDragged() method : 마우스 클릭 후 움직임

### 3. MouseEvent method
  - mouse event는 여러 method를 갖고 있다.
  - int getX()
  - int getY()
  - Point getPoint()
  - int getClickCount()
    - 천천히 여러번 click 한 것은 mouse event가 여러번 발생
    - 빠르게 여러번 click 한 것은 mouse event는 한 번, clickCount는 여러번

### 4. Adapter
  - Listener method들을 dummy로 정의해 둔 class이다.
  - method들을 일일이 모두 정의해두기 힘드므로, adapter 사용
  - MouseAdapter
  - MouseMotionAdapter

```java
// JComponent 확장한 MouseComponent
// mouse와 상호작용하게 되는 화면 내 사각형들에 대한 

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;

// Mouse Component 설정 : square 추가, 삭제 기능
public class MouseComponent extends JComponent{
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private static final int SIDELENGTH = 10;
	private ArrayList<Rectangle2D> squares;	// square 집합
	private Rectangle2D current;			// mouse가 잡고 있는 square 
	
	public MouseComponent() {
		
		// 사각형들을 위한 설정
		squares = new ArrayList();
		current = null;
		
		// component에 listener를 부착한다.
		addMouseMotionListener(new MouseMotionHandler());
		addMouseListener(new MouseHandler());
	}
	
	public Dimension getPreferredSize() {return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
	 
	// 그려져야할 상황이 발생할 때마다, paintComponent가 호출됨
	public void paintComponent(Graphics g) {
		
		// Graphics2D를 통해서 그리는 것!
		Graphics2D g2 = (Graphics2D)g;
		
		// draw all squares
		for(Rectangle2D r: squares)
			g2.draw(r);
	}
	
	// mouse pointer를 포함하는 square가 있는지 찾는 과정
	public Rectangle2D find(Point2D p) {
		for(Rectangle2D r : squares) {
			if(r.contains(p)) return r;
		}
		
		return null; 
	}
	
	// mouse pointer로 click했을 때, square 생성
	public void add(Point2D p) {
		
		double x = p.getX();
		double y = p.getY();
		
		current = new Rectangle2D.Double(x-SIDELENGTH/2, y-SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
		
		// arrayList에 추가
		squares.add(current);
		
		// calls paintComponent() : 그림 그려지는 것은 paintComponent()가 호출될때만 가능하다.
		repaint();
	}
	
	// 
	public void remove(Rectangle2D s) {
		
		// 대상이 되는 rectangle이 없다면
		if(s==null) return;
		
		// 대상이 되는 rectangle이 있다면
		if(s == current) current = null;
		squares.remove(s);
		repaint();
	}
	
	// MouseListener의 각 method들을 dummy 설정해 놓은 MouseAdapter를 extend
	// Mouse click 관련 동작에 따라 event를 발생시킨다.
	private class MouseHandler extends MouseAdapter{
		
		// press와 add와 연관 지음
		public void mousePressed(MouseEvent event) {
			// click 위치에 square가 존재한다면, 그 square를 current에 할당
			// click 위치에 square가 존재하지 않는다면? square를 새로 추가함
			current = find(event.getPoint());
			if(current == null) add(event.getPoint());
		}
		
		// click과 remove와 연관 지음
		public void mouseClicked(MouseEvent event) {
			// click 위치에 square가 존재한다면? square를 제거
			current = find(event.getPoint());
			if(current != null && event.getClickCount() >= 2)
				remove(current);
		}
	}
	
	// MouseMotionListener를 extend함
	// Mouse 움직임에 따라 주기적으로 event를 발생시킨다.
	private class MouseMotionHandler extends MouseMotionAdapter{
		
		// rectangle 안에 mouse가 존재하게 된다면 
		public void mouseMoved(MouseEvent event) {
			if(find(event.getPoint())==null)
				setCursor(Cursor.getDefaultCursor());
			else
				setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		
		public void mouseDragged(MouseEvent event) {
			
			// mouse click된 current square를 움직임
			if(current != null) {
				int x = event.getX();
				int y = event.getY();
				
				current.setFrame(x - SIDELENGTH/2 , y - SIDELENGTH/2, SIDELENGTH, SIDELENGTH);
				repaint();
			}
				
		}
	}
}


// Frame class 
import javax.swing.JFrame;

// Frame에 MouseComponent 인스턴스 추가
public class MouseFrame extends JFrame{
	public MouseFrame() {
		add(new MouseComponent());
		pack();
	}
}
```

## AWT Event and Listener Classes

### 1. event hierarchy

<img src="https://user-images.githubusercontent.com/59442344/119598140-5982b780-be1d-11eb-9d3a-95f923073ba9.png" width=50% height=50%>

  - Semantic event : application program 만들때 
     - Action Event : button, textfield, checkbox, menu ..
     - Adjustment Event : scrollbar ..
     - Item Event : combo box ..
  - Low-level event : 상위 event를 생성시키기 위해 필요한 event
    - KeyEvent, MouseEvent, MouseWheelEvent, FocusEvent, WindowEvent .. 











