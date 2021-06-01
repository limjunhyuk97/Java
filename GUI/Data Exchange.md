# Data Exchange
  - dialog box와, main application 간의 정보 전달

## Example

![communication diagram](https://user-images.githubusercontent.com/59442344/120222567-17bd9b00-c27b-11eb-838e-28c5d2092eec.png)

```java
// 1. DataExchangeFrame (main frame)
public class DataExchangeFrame extends JFrame{
	
	public static final int TEXT_ROWS = 20;
	public static final int TEXT_COLUMNS = 40;
	private PasswordChooser dialog = null;
	private JTextArea textArea;
	
	public DataExchangeFrame() {
		
		JMenuBar mbar = new JMenuBar();
		setJMenuBar(mbar);
		
		JMenu fileMenu = new JMenu("File");
		mbar.add(fileMenu);
		JMenuItem connectItem = new JMenuItem("Connect");
		connectItem.addActionListener(new ConnectAction());
		fileMenu.add(connectItem);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		
		pack();
	}
	
	// frame 생성 시에 Connect menu Item에 어떤 ActionListener 속성을 부여할 것인가?
	// connectAction 누르면 panel 생성
	// panel의 showDialog method가 invoke 되면, panel을 기반으로 만들어진 dialog가 생성된다.
	private class ConnectAction implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(dialog == null) dialog = new PasswordChooser();
			dialog.setUser(new User("yourname", null));
			
			if(dialog.showDialog(DataExchangeFrame.this, "Connect")) {
				User u = dialog.getUser();
				textArea.append("User name = " + u.getName() + ", password = " + (new String(u.getPassword())) + "\n");
			}
		}
	}
	
}

// 2. PasswordChooser (panel, 자신을  dialog 생성)
public class PasswordChooser extends JPanel{
	
	private JTextField username;
	private JPasswordField password;
	private JButton okButton;
	private boolean ok;
	private JDialog dialog;
	
	public PasswordChooser() {
		
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 2));
		
		// Label과 JTextField, JPasswordField add
		panel.add(new JLabel("User name : "));
		panel.add(username = new JTextField(""));
		panel.add(new JLabel("Password : "));
		panel.add(password = new JPasswordField(""));
		
		add(panel, BorderLayout.CENTER);
		
		okButton = new JButton("OK");
		okButton.addActionListener(event->{
			ok = true;
			dialog.setVisible(false);
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(event->{
			dialog.setVisible(false);
		});
		
		// button add
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setUser(User u) {
		username.setText(u.getName());
	}
	
	public User getUser() {
		return new User(username.getText(), password.getPassword());
	}
	
	public boolean showDialog(Component parent, String title) {
		
		ok = false;
		
		Frame owner = null;
		if(parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		
		if(dialog == null || dialog.getOwner() != owner) {
			// dialog에 owner에 대한 정보와, modal한 속성(true)을 부여한다.
			dialog = new JDialog(owner, true);
			// dialog도 마찬가지로 frame처럼 각종 component를 add할 수 있다.
			dialog.add(this);
			dialog.getRootPane().setDefaultButton(okButton);
			dialog.pack();
		}
		
		dialog.setTitle(title);	
		dialog.setVisible(true);	// false가 될때까지 대기, false는 OK, Cancel button click을 통해서..
		return ok;
	}
	
}

// 3. User (Data class)
public class User {
	private String name;
	private char[] password;
	
	public User(String name, char[] password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(char []password) {
		this.password = password;
	}
}

// 4. main testing class
public class DataExchangeTest {
	public static void main(String[] args) {
		
		EventQueue.invokeLater(()->{
			DataExchangeFrame frame = new DataExchangeFrame();
			frame.setTitle("Data Exchange Test");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
		
	}
}
```


