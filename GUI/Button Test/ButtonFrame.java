import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonFrame extends JFrame
{
   private JPanel buttonPanel;
   private JPanel colorPanel;
   private static final int DEFAULT_WIDTH = 300;
   private static final int DEFAULT_HEIGHT = 200;

   public ButtonFrame()
   {      
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      
      buttonPanel = new JPanel();
      colorPanel = new JPanel();
      BorderLayout panelLayout = new BorderLayout();
      buttonPanel.setLayout(panelLayout);
      
      makeButton("Red", Color.RED, BorderLayout.NORTH);
      makeButton("Yellow", Color.YELLOW, BorderLayout.WEST);
      makeButton("Green", Color.GREEN, BorderLayout.EAST);
      makeButton("Blue", Color.BLUE, BorderLayout.SOUTH);
      buttonPanel.add(colorPanel, BorderLayout.CENTER);

      // add panel to frame
      add(buttonPanel);
     
   }
   
   private void makeButton(String name, Color c, Object layout) {
	   JButton button = new JButton(name);
	   buttonPanel.add(button, layout);
	   button.addActionListener((ActionEvent event)->{
		   colorPanel.setBackground(c);
	   });
   }
  
}
