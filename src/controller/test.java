package controller;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class MyFrame extends JFrame{
	public MyFrame() {
		super("MyFrame");
		
		JPanel buttonPanel=new JPanel();
		
		JButton button=new JButton("Button");
		JRadioButton rbutton=new JRadioButton("wtmp");
		
		JLabel label=new JLabel("Log BlockChain Project");
		add(label);
		
		buttonPanel.add(button);
		buttonPanel.add(rbutton);
		add(buttonPanel);
		
		
		setSize(500, 500);
		setVisible(true);
		setLocation(100,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
}

public class test {	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame f=new MyFrame();
	}
}
