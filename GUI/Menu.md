# Menu

## 00. Menus
  - Menu Bar : menu들을 나열해 놓은 bar / JMenuBar
  - Menu : menu item들의 묶음
  - Menu item : menu item 각각. 명령들 / JMenu
    - sub Menu : 각 item들의 하위 항목들 / JMenuItem
    - JMenu를 하위항목으로써 add로 붙일 수도 있다.

```java
// Frame <- MenuBar
JMenuBar menuBar = new JMenuBar();
frame.setJMenuBar(menuBar);

// JMenuBar <- JMenus
JMenu editMenu = new JMenu("Edit");
menuBar.add(editMenu);

// JMenu <- JMenuItem
JMenuItem pasteItem = new JMenuItem("Paste");
editMenu.add(pasteItem);

// JMenu <- JMenu
JMenu optionsMenu = new JMenu("Options");
editMenu.add(optionsMenu);
```

## 01. Menu Item Actions
  - Action object를 사용하여 menu에 의해 발생하는 action을 구현할 수 있다.
  - Menu의 이름설정, Event Listener 구현, 하위 menu adding 에 관한 일체의 과정을 Action 객체로 표현 가능

```java
// Action 객체 형성 (익명 객체 이용)
Action exitAction = new AbstractAction("Exit"){
  public void actionPerformed(ActionEvent event){
    System.exit(0);
  }
}

// Action 객체 이용해서 바로 추가 가능
JMenuItem exitItem = new JMenuItem(exitAction);
fileMenu.add(exitItem);
```


## 02. Special Menu Items
  - add icon
  - add icon to Action
  - checkBox in Menu Item : JCheckBoxMenuItem()
  - RadioButton in Menu Item : JRadioButtonMenuItem()

```java
// CheckBox in menu
JCheckBoxMenuItem readonlyItem

// RadioButton in menu
ButtonGroup group = new ButtonGroup();
JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
insertItem.setSelected(true); // 기본 selected
JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
// group 추가 따로, Menu추가 따로.
group.add(insertItem);
group.add(overtypeItem);
optionsMenu.add(insertItem);
optionsMenu.add(overtypeItem);
```

## 03. Pop-up Menus
  - 특정 component에 대해서, 마우스 오른쪽 버튼을 눌렀을 때 Pop-Up menu 등장하게 한다.
  - **Pop-Up menu를 구성**하고, **구성한 Pop-Up menu를 적용시킬 component에 적용해준다.**


## 04. KeyBoard Mnemonics and Accelerators
  - KeyBoard Key를 통한 menu에 대한 제어 기능 : Mnemonic, Accelerators

## 05. Enabling and Disabling Menu Items
  - menuItem을 able disable 시키기 위한 방식

## 06. Example

```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuFrame extends JFrame{
	
	// 객체 전체에서 사용되는지 여부에 따라서, field에 선언되었다.
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 200;
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
	
	// AbstractAction : Action속의 여러가지 method를 default로 구현해 놨기에 사용
	class TestAction extends AbstractAction{
		public TestAction(String name) {
			super(name);
		}
		public void actionPerformed(ActionEvent event) {
			System.out.println(getValue(Action.NAME) + "selected");
		}
	}
	
	// constructor 안에서 menu의 모든 기능을 구현하는 작업이 진행 중이다.
	@SuppressWarnings("deprecation")
	public MenuFrame(){
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		
		// File menu 내부
		
		// New menuItem 추가
		// AbstractAction 구현 class 객체 -> 적용
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(new TestAction("New"));
		
		// Open menuItem 추가 + accelerator 추가( setAccelerator() )
		// JMenu.add() return -> JMenuItem ( .setAccelerator()추가 위해서 return 뽑아냄 )
		JMenuItem openItem = fileMenu.add(new TestAction("Open"));
		openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		
		// 분리선 추가
		fileMenu.addSeparator();
		
		// save menuItem 추가
		// AbstractAction 구현 class 객체 -> Action type 변수 담기 -> 적용
		saveAction = new TestAction("Save");
		JMenuItem saveItem = fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		
		// saveAs menuItem 추가
		saveAsAction = new TestAction("Save As");
		fileMenu.add(saveAsAction);
		
		// 분리선 추가
		fileMenu.addSeparator();
		
		// exit menuItem 추가
		// anonymous class를 사용
		fileMenu.add(new AbstractAction("Exit"){
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		
		// Edit menu 내부 [하위 항목부터 구성하여 -> 상위 항목들에 붙이는 방식으로 구현]
		
		// check box 추가
		readonlyItem = new JCheckBoxMenuItem("Read-only");
		readonlyItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// readOnlyItem이라는 check box의 체크 여부와 반대되는 boolean 값
				boolean saveOK = !readonlyItem.isSelected();
				// menu Item 'save'에 들어가있는 saveAction, 
				// menu Item 'saveAs'에 들어가있는 saveAsAction,
				// 들을 enable, disable 시키는 과정.
				saveAction.setEnabled(saveOK);
				saveAsAction.setEnabled(saveOK);
			}
		});
		
		// radio button 추가 : insert, overtype
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
		group.add(overtypeItem);
		group.add(insertItem);
		
		// img 추가
		Action cutAction = new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));		
		Action copyAction = new TestAction("Copy");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
		Action pasteAction = new TestAction("Paste");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		
		JMenu optionMenu = new JMenu("Options");
		optionMenu.add(readonlyItem);
		optionMenu.addSeparator();
		optionMenu.add(insertItem);
		optionMenu.add(overtypeItem);
		
		editMenu.addSeparator();
		editMenu.add(optionMenu);
		
		
		// Help menu 내부 [mnemonic]
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		JMenuItem Index = helpMenu.add(new TestAction("Help"));
		Index.setMnemonic(KeyEvent.VK_I);
		
		Action aboutAction = new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_A));

		helpMenu.add(aboutAction);
		helpMenu.add(Index);
		
		
		// 전체 구성 마무리
		JMenuBar menuBar = new JMenuBar();
		// frame에 menuBar 적용
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		
		// Pop-Up menu 형성 [앞에서 구현한 menu 기능들을 다시 모아서 pop-up 구현]
		popup = new JPopupMenu();
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		
		JPanel panel = new JPanel();
		// panel에 popup을 적용할 수 있게 부착 
		panel.setComponentPopupMenu(popup);
		
		add(panel);
	}
	
}
```
