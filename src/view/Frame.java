package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame{

	private JPanel contentPane;
	private JTextField txtLocalIp;
	public static JTextArea consoleTextArea;
	private JCheckBox wtmpCheckBox;
	private JCheckBox utmpCheckBox;
	private JCheckBox btmpCheckBox;
	private JCheckBox dpkgCheckBox;
	private JCheckBox authCheckBox;
	private JCheckBox userCheckBox;
	private JCheckBox daemonCheckBox;
	private JCheckBox bootCheckBox;
	private JCheckBox messagesCheckBox;
	private JCheckBox secureCheckBox;
	private JScrollPane jscrollPane;

	public static void run() {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() {
				try 
				{
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Frame() {
		
		this.setSize(750,500);
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Log BlockChain Project");
		getContentPane().setLayout(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//text area, scroll
		consoleTextArea = new JTextArea();
		jscrollPane=new JScrollPane(consoleTextArea);
		jscrollPane.setBounds(12, 10, 428, 441);
		contentPane.add(jscrollPane);
		consoleTextArea.setLineWrap(true);
		consoleTextArea.setBounds(12, 10, 428, 441);
		consoleTextArea.setCaretPosition(consoleTextArea.getDocument().getLength());
		
		txtLocalIp = new JTextField();
		txtLocalIp.setBounds(543, 22, 167, 30);
		contentPane.add(txtLocalIp);
		txtLocalIp.setColumns(10);
		
		//left line checkbox
		wtmpCheckBox = new JCheckBox("wtmp");
		wtmpCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		wtmpCheckBox.setBounds(475, 115, 57, 23);
		wtmpCheckBox.setSelected(true);
		contentPane.add(wtmpCheckBox);
		
		btmpCheckBox = new JCheckBox("btmp");
		btmpCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		btmpCheckBox.setBounds(475, 150, 100, 23);
		btmpCheckBox.setSelected(true);
		contentPane.add(btmpCheckBox);
		
		dpkgCheckBox = new JCheckBox("dpkg.log");
		dpkgCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		dpkgCheckBox.setBounds(475, 185, 100, 23);
		contentPane.add(dpkgCheckBox);
		
		daemonCheckBox = new JCheckBox("daemon.log");
		daemonCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		daemonCheckBox.setBounds(475, 220, 110, 23);
		contentPane.add(daemonCheckBox);

		messagesCheckBox = new JCheckBox("messages.log");
		messagesCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		messagesCheckBox.setBounds(475, 255, 150, 23);
		contentPane.add(messagesCheckBox);
		
		secureCheckBox = new JCheckBox("secure.log");
		secureCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		secureCheckBox.setBounds(475, 290, 150, 23);
		secureCheckBox.setSelected(true);
		contentPane.add(secureCheckBox);
		
		//right line checkbox
		utmpCheckBox = new JCheckBox("utmp");
		utmpCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		utmpCheckBox.setBounds(600, 115, 115, 23);
		utmpCheckBox.setSelected(true);
		contentPane.add(utmpCheckBox);
		
		userCheckBox = new JCheckBox("user.log");
		userCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		userCheckBox.setBounds(600, 150, 115, 23);
		userCheckBox.setSelected(true);
		contentPane.add(userCheckBox);
		
		authCheckBox = new JCheckBox("auth.log");
		authCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		authCheckBox.setBounds(600, 185, 115, 23);
		contentPane.add(authCheckBox);
		
		bootCheckBox = new JCheckBox("boot.log");
		bootCheckBox.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		bootCheckBox.setBounds(600, 220, 115, 23);
		contentPane.add(bootCheckBox);
		
		//label
		JLabel localIPLabel = new JLabel("Local IP");
		localIPLabel.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		localIPLabel.setBounds(463, 25, 72, 23);
		contentPane.add(localIPLabel);
		
		JLabel optionLabel = new JLabel("Option");
		optionLabel.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		optionLabel.setBounds(463, 70, 81, 30);
		contentPane.add(optionLabel);
		
		//bottom button
		JButton startButton = new JButton("Start log enrollment");
		startButton.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 15));
		startButton.setBounds(475, 333, 235, 30);
		startButton.addActionListener(new StartListener());
		contentPane.add(startButton);
		
		JButton retryButton = new JButton("Re-start what failed");
		retryButton.setFont(new Font("³ª´®½ºÄù¾î", Font.PLAIN, 15));
		retryButton.setBounds(475, 373, 235, 30);
		retryButton.addActionListener(new RetryListener());
		contentPane.add(retryButton);
		
		JButton controlServerButton = new JButton("Start web server");
		controlServerButton.setFont(new Font("³ª´®°íµñÄÚµù", Font.PLAIN, 15));
		controlServerButton.setBounds(475, 413, 235, 30);
		controlServerButton.addActionListener(new ControlServerListener());
		contentPane.add(controlServerButton);
	}
	
	public class StartListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton Button=(JButton)e.getSource();
			String selected="";
			
			if(Button.getText().equals("Start log enrollment"))
			{
				Button.setText("Stop log enrollment");
				consoleTextArea.append("[INFO] Local IP : "+txtLocalIp.getText()+"\n");
				selected=getLogName();
				consoleTextArea.append("[INFO] Start log enrollment to Log BlockChain"+"\n");
				consoleTextArea.append("\n");
				
				//call kindOfLog.EnrollLogBlock
				try 
				{
					Control.start(txtLocalIp.getText(), selected);
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}else {
				Button.setText("Start log enrollment");
			}
		}
	}
	
	public class RetryListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton Button=(JButton)e.getSource();

			if(Button.getText().equals("Re-start what failed"))
			{
				Button.setText("Stop what failed");
			}else {
				Button.setText("Re-start what failed");
			}
			
		}
	}
	
	public class ControlServerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton Button=(JButton)e.getSource();
			
			if(Button.getText().equals("Start web server"))
			{
				try {
					Control.serverStart();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				Button.setText("Stop web server");
			}else
			{
				Button.setText("Start web server");
				try {
					Control.serverStop();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void callTextArea(String content) {
		consoleTextArea.append((content+"\n"));
	}
	
	private String getLogName() {
		String selected="";
		
		//wtmp
		if(wtmpCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/wtmp"+"\n");
			selected+="wtmp/";
		}
		if(utmpCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/run/utmp"+"\n");
			selected+="utmp/";
		}
		if(btmpCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/btmp"+"\n");
			selected+="btmp/";
		}
		if(dpkgCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/dpkg"+"\n");
			selected+="dpkg/";
		}
		if(authCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/auth"+"\n");
			selected+="auth/";
		}
		if(userCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/user"+"\n");
			selected+="user/";
		}
		if(daemonCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/daemon"+"\n");
			selected+="daemon/";
		}
		if(bootCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/boot"+"\n");
			selected+="boot/";
		}
		if(messagesCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/messages"+"\n");
			selected+="messages/";
		}
		if(secureCheckBox.isSelected())
		{
			consoleTextArea.append("[INFO] Selected /var/log/secure"+"\n");
			selected+="secure/";
		}
		return selected;
	}
}



