# Component


## Swing Component
<img src="https://user-images.githubusercontent.com/59442344/119953628-25ec8c80-bfd9-11eb-8922-b4b5e366ec7b.png" height=90% width=90%>
  - component의 attribute, method, event, eventListener : 필요할 때마다, 그때 그때 찾아서 쓰자!
  - component 생성 -> 필요한 값 설정 -> event handler 설정

## 1. Text
  - GUI에서는 text도 event 방식으로 처리한다.
  - ActionListener, ActionEvent로 event 처리

### Text Field
  - 단일 line을 입력받을 때
  - Text Field construction : JTextField()
  - getText(), setText()
  - password : **JPassWordField(보안 때문에 char형 배열로 입력, String 객체는 공유되기 때문)"
  - labeling : JLabel

### Text Area
  - multi line을 입력받을 때
  - Text Area construction : JTextArea(n ,m)
  - getText(), setText()
  - line wrapping : textArea.setLineWrap(true)
  - scroll pane : JScrollPane()

### Text Example 
```java
// Text Component Frame

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextComponentFrame extends JFrame{
	public static final int TEXTAREA_ROWS = 8;
	public static final int TEXTAREA_COLUMNS = 20;
	
	public TextComponentFrame() {
		
		JTextField textField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		
		// NORTH에 northPanel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,2));
		northPanel.add(new JLabel("User name : ", SwingConstants.RIGHT));
		northPanel.add(textField);
		northPanel.add(new JLabel("Password : ", SwingConstants.RIGHT));
		northPanel.add(passwordField);
		add(northPanel, BorderLayout.NORTH);
		
		// CENTER에 scrollPane
		// TextArea -> ScrollPane -> JFrame 순서로 붙이는 것
		JTextArea textArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane, BorderLayout.CENTER);
		
		// SOUTH에 southPanel
		// text area에 text 추가 위한 button 추가
		JPanel southPanel = new JPanel();
		JButton insertButton = new JButton("Insert");
		southPanel.add(insertButton);
		insertButton.addActionListener((ActionEvent event)->{
			textArea.append("User name: " + textField.getText()
			+" Password: " + new String(passwordField.getPassword()) +"\n");
		});
		add(southPanel, BorderLayout.SOUTH);
		pack();
	}
	
}

// main
import java.awt.EventQueue;

import javax.swing.JFrame;

public class TextComponentTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			TextComponentFrame frame = new TextComponentFrame();
			frame.setTitle("User Info");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

## 2. Check Boxes
  - ActionListener, ActionEvent로 event 처리
  - box 하나의 check와, unchecked 여부

### Check Box
  - check box construction : JCheckBox("string")
  - isSelected(), setSelected()

### Check Box Example
```java
// CheckBox Frame

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckBoxFrame extends JFrame{

	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int FONTSIZE = 24;

	public CheckBoxFrame() {
		
		// CENTER에 label 생성
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.BOLD, FONTSIZE));
		add(label, BorderLayout.CENTER);
		
		// ActionListener listener 정의
		// button click에 따라서 글자의 font를 바꾼다.
		ActionListener listener = (event)->{
			int mode = 0;
			if(bold.isSelected()) mode += Font.BOLD;
			if(italic.isSelected()) mode += Font.ITALIC;
			label.setFont(new Font("Serif", mode, FONTSIZE));
		};
		
		// SOUTH에 buttonPanel 생성
		JPanel buttonPanel = new JPanel();
		
		// buttonPanel <- bold checkbox 붙임
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		bold.setSelected(true);
		buttonPanel.add(bold);
		
		// buttonPanel <- Italic checkbox 붙임
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener); 
		buttonPanel.add(italic);
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
}

// main

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CheckBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			CheckBoxFrame frame = new CheckBoxFrame();
			frame.setTitle("CheckBoxTest");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

## 3. Radio Buttons
  - ActionListener, ActionEvent로 event 처리
  - 여러 개의 box들 중에서 하나를 check

### Radio Button
  - radio button construction
    - ButtonGroup 생성 : ButtonGroup()
    - ButtonGroup에 radioButton 추가 : add(new JRadioButton("string", boolean))
  - layout 적용
    - ButtonGroup은 하나의 선택만 가능하게하는 기능만 담당.
    - layout 적용 필요시, JPanel에 add 시킨다. 
    - **group.add() 하는 것과, panel.add() 하는 것이 독립적으로 이루어져야 한다!**
  - ButtonGroup을 통해서 event 정의 불가능. radio button 하나하나 마다 event에 대한 정의 요구됨. 

### Radio Button Example
```java
// RadioButton Frame

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonFrame extends JFrame{
	
	private JPanel buttonPanel;
	private ButtonGroup group;
	private JLabel label;
	private static final int DEFAULT_SIZE = 36;
	
	public RadioButtonFrame() {
		
		// add the sample text label
		label = new JLabel("The quick brown fox jumps over the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);
		
		// add the radio buttons
		buttonPanel = new JPanel();
		group = new ButtonGroup();
		addRadioButton("Small", 8);
		addRadioButton("Medium", 12);
		addRadioButton("Large", 18);
		addRadioButton("Extra large", 36);
		
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
	}
	
	public void addRadioButton(String name, int size) {
		// 초기 형성 시에, DEFAULT_SIZE와 같은 것만 selected 되게 설정
		boolean selected = (size == DEFAULT_SIZE);
		JRadioButton rb = new JRadioButton(name, selected);
		rb.addActionListener((event)->{
			label.setFont(new Font("Serif", Font.PLAIN, size));
		});
		
		// group에 add하는 것과
		// layout을 위해 buttonPanel에 add하는 것은 별개의 행위이다.
		group.add(rb);
		buttonPanel.add(rb);
	}
}

// main
import java.awt.EventQueue;

import javax.swing.JFrame;

public class RadioButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			RadioButtonFrame frame = new RadioButtonFrame();
			frame.setTitle("User Info");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

## 4. Combo Boxes
  - ActionListener, ActionEvent로 event 처리 (ItemEvent라는 것도 존재)
  - radio box보다 choice 수가 많은 경우에 drop down 방식을 이용하여 list에서 선택할 수 있게 한다.
  - 개발자의 editing을 통해서 text field와 list of choice를 엮을 수 있다.
  - editing으로 list of choice를 근본적으로 바꿀 수는 없다.

### Combo Box
  - ComboBox construction : JComboBox<T> generic class로 생성한다.
  - getter
    - (T)getSelectedItem() : return type가 Object(형 변환 요구) / editable
    - getItemAt(getSelectedIndex()) : return type가 T(subtitute된 type으로 자동으로 반환) / if not editable

### Combo Box Example
```java
// ComboBox Frame

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboBoxFrame extends JFrame{
	
	private JComboBox<String> faceCombo;
	private JLabel label;
	private static final int DEFAULT_SIZE = 24;
	
	public ComboBoxFrame() {
		
		// label -> frame : CENTER
		label = new JLabel("The quick brown fox jumps over the lazy dog");
		label.setFont(new Font("Serif", Font.PLAIN, DEFAULT_SIZE));
		add(label, BorderLayout.CENTER);
		
		// combo box와 face name들 만들기
		faceCombo = new JComboBox<>();
		faceCombo.addItem("Serif");
		faceCombo.addItem("San Serif");
		faceCombo.addItem("Monospaced");
		faceCombo.addItem("Dialog");
		faceCombo.addItem("DialogInput");
		
		// combo box listener를 형성
		// 각각의 Item들에 대해서가 아니라, 한번에 지정
		faceCombo.addActionListener((event)->{
			label.setFont(
					new Font(faceCombo.getItemAt(faceCombo.getSelectedIndex()),
					Font.PLAIN,
					DEFAULT_SIZE)
					);
		});
		
		// comboBox -> panel -> frame : SOUTH
		JPanel comboPanel = new JPanel();
		comboPanel.add(faceCombo);
		add(comboPanel, BorderLayout.SOUTH);
		pack();
		
	}
}

//main
import java.awt.EventQueue;

import javax.swing.JFrame;

public class ComboBoxTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			ComboBoxFrame frame = new ComboBoxFrame();
			frame.setTitle("User Info");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}
}
```

