# Menu


## 00. Menus
  - Menu Bar : menu들을 나열해 놓은 bar / JMenuBar
  - Menu : menu item들의 묶음
  - Menu item : menu item 각각. 명령들 / JMenu
    - sub Menu : 각 item들의 하위 항목들 / JMenuItem
    - JMenu를 하위항목으로써 add로 붙일 수도 있다.


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


## 03. Pop-up Menus
  - 특정 component에 대해서, 마우스 오른쪽 버튼을 눌렀을 때 pop up menu 등장하게 한다.
  - 이 경우 JPopupMenu


## 04. KeyBoard Mnemonics and Acclerators
 

## 05. Enabling and Disabling Menu Items











