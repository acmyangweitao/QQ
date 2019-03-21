package qq1;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class qq_window {
	private SendThread sendThread;
	private ReceiveThread receiveThread;
	
	private JFrame qq_win;
	private JTextArea text;
	private JButton Open_service;
	private JButton Close_service;
	
	private void GUIini()
	{
		qq_win = new JFrame();
		qq_win.setSize(500,1000);
		qq_win.setLayout(new FlowLayout());
		qq_win.setDefaultCloseOperation(3);//关闭窗口
		
		qq_win.add( new JLabel("消息框") );
		JPanel panel = new JPanel();
		panel.setSize(450,900);
		text = new JTextArea(50,43);
        text.setEditable(false);
        text.setAutoscrolls(true);
        JScrollPane jsp = new JScrollPane(text);
        panel.add(jsp);
        qq_win.add(panel);
        
        /*Open_service = new JButton();
        qq_win.add(Open_service);
        Open_service.setSize(100,100);
        
        Close_service = new JButton();
        qq_win.add(Close_service);
        Close_service.setSize(100,100);*/
		
        qq_win.setVisible(true);
        qq_win.setResizable(false);
        qq_win.setDefaultCloseOperation(qq_win.EXIT_ON_CLOSE);
	}
	
	private void ThreadIni() 
	{
        // TODO Auto-generated method stub
        sendThread = new SendThread(this);
        receiveThread = new ReceiveThread(this);
    }
	
	public qq_window()
	{
		GUIini();
		ThreadIni();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new qq_window();
	}

	public void printf_text(String string) {
		// TODO Auto-generated method stub
		text.setText(text.getText() + string);
	}

}
