package qq2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class qq_window extends JFrame {
	
	private JButton log_in;
    private JButton registered;
    private JFrame qq;

    private JTextField ipname;
    private JPasswordField password;
    
    private SendThread sendThread;
    private ReceiveThread receiveThread;
    //------------------------------
    private String MY_IP;
    private int MY_port;
    private String MY_ipname;
    private String MY_password;
    
    private String server_IP = "10.90.16.35";
    private int server_port = 8080;
    //----------------------------------
    private JButton registered1;
    private JFrame qq1;

    private JTextField ipname1;
    private JPasswordField password1;
    private JPasswordField password2;
    private JTextField name1;
    //---------------------------------
    private JFrame qq2;
    //----------------------------------------
    private JButton send;
    private JButton log_out;
    private JFrame qq3;

    private JTextField ipname3;
    private JTextArea text1;
    private JTextArea text2;
    //----------------------------------
    private int bo = 0;
    //----------------------------------------
    private void GUIini(){
        qq = new JFrame("QQ");
        qq.setSize(300,200);
		qq.setLayout(new FlowLayout());
		
		//JPanel aa = new JPanel(new GridLayout(2, 1));
        qq.add(new JLabel("账号:"));
        ipname = new JTextField(20);
        ipname.setPreferredSize(new Dimension(10, 30));
        qq.add(ipname);
        
        qq.add(new JLabel("密码:"));
        password = new JPasswordField(20);
        password.setPreferredSize(new Dimension(10, 30));
        qq.add(password);
        
        log_in = new JButton("登录");
        log_in.setBounds(0,0,500,500);
        qq.add(log_in);
        
        registered = new JButton("注册");
        registered.setBounds(0,0,500,500);
        qq.add(registered);
        
        //qq.add(qq);
        
        qq.setVisible(true);
        qq.setResizable(false);
        qq.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    
    private void GUIini1(){
    	
    	qq1 = new JFrame("QQ");
        qq1.setSize(300,200);
		qq1.setLayout(new FlowLayout());
		
		//JPanel aa = new JPanel(new GridLayout(2, 1));
        qq1.add(new JLabel("账号:"));
        ipname1 = new JTextField(20);
        ipname1.setPreferredSize(new Dimension(10, 30));
        qq1.add(ipname1);
        
        qq1.add(new JLabel("密码:"));
        password1 = new JPasswordField(20);
        password1.setPreferredSize(new Dimension(10, 30));
        qq1.add(password1);
        
        qq1.add(new JLabel("确认密码:"));
        password2 = new JPasswordField(20);
        password2.setPreferredSize(new Dimension(10, 30));
        qq1.add(password2);
        
        qq1.add(new JLabel("昵称:"));
        name1 = new JTextField(20);
        name1.setPreferredSize(new Dimension(10, 30));
        qq1.add(name1);
        
        registered1 = new JButton("注册");
        registered1.setBounds(0,0,500,500);
        qq1.add(registered1);
        
        //qq.add(qq);
        
        qq1.setVisible(true);
        qq1.setResizable(false);
        //qq1.setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    }
    
    public void GUIini2(String s){
    	qq2 = new JFrame("QQ");
    	qq2.setSize(100,100);
		qq2.setLayout(new FlowLayout());
		qq2.add(new JLabel(s));
		
		qq2.setVisible(true);
        qq2.setResizable(false);
    }
    
    private void GUIini3(){
    	qq3 = new JFrame("QQ对话");
        qq3.setSize(500,500);
		qq3.setLayout(new FlowLayout());
		
		qq3.add(new JLabel("对方账号:"));
        ipname3 = new JTextField(35);
        ipname3.setPreferredSize(new Dimension(10, 30));
        qq3.add(ipname3);
        
        qq3.add(new JLabel("接收消息:"));
        JPanel panel = new JPanel();
		panel.setSize(450,100);
		text1 = new JTextArea(10,43);
        text1.setEditable(false);
        text1.setAutoscrolls(true);
        JScrollPane jsp = new JScrollPane(text1);
        panel.add(jsp);
        qq3.add(panel);
        
        qq3.add(new JLabel("发送消息:"));
        JPanel panel1 = new JPanel();
		panel1.setSize(450,100);
		text2 = new JTextArea(5,43);
        //text2.setEditable(false);
        text2.setAutoscrolls(true);
        JScrollPane jsp1 = new JScrollPane(text2);
        panel1.add(jsp1);
        qq3.add(panel1);
        
        send = new JButton("发送");
        send.setBounds(0,0,500,500);
        qq3.add(send);
        
        log_out = new JButton("注销账号");
        log_out.setBounds(0,0,500,500);
        qq3.add(log_out);
        
        qq3.setVisible(true);
        qq3.setResizable(false);
        qq3.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ActionIni() {
    	
    	log_in.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MY_ipname = ipname.getText();
            	MY_password = password.getText();
            	String s =MY_IP + "$" + MY_port + "$" + "log_in|" + MY_ipname + "|" + MY_password;
            	sendThread.senMessage(server_IP,server_port,s);
            }
        });
    	
    	registered.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIini1();
                ActionIni1();
            }
        });
    	
    	
    	
    	/*log_out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	String s = "";
            	sendThread.senMessage(server_IP,server_port,s);
            }
        });*/
    	
    }
    
    private void ActionIni1() {
    	registered1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(password1.getText().equals(password2.getText()))
            	{
            		String s = MY_IP + "$" + MY_port + "$" + "registered|" + ipname1.getText() + "|" + password1.getText() + "|" + name1.getText();
            		//GUIini2(s);
            		sendThread.senMessage(server_IP,server_port,s);
            		
            	}
            	else
            	{
            		GUIini2("密码不一致");
            	}
            }
        });
    }
    
    private void ActionIni2() {
    	send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	String s = MY_IP + "$" + MY_port + "$" + "send|" + ipname3.getText() + "|" + MY_ipname + ":" + text2.getText();
            	sendThread.senMessage(server_IP,server_port,s);
            	text1.setText(text1.getText() + text2.getText() + ":" + ipname3.getText() + "\n");
            }
        });
    }

    private void ThreadIni() {
        // TODO Auto-generated method stub
        sendThread = new SendThread(0, this);
        receiveThread = new ReceiveThread(this);
    }
    //构造函数
    public qq_window() {
        GUIini();
        //GUIini3();
        ActionIni();
        ThreadIni();
    }

    public void printError(String err){
        System.out.println("Error occur:" + err);
    }
    
    public void set_MY_IP(String s,int a)
    {
    	//GUIini2(s+a);
    	MY_IP = s;
    	MY_port = a;
    }
    
    public void printf_text(String string) {
		// TODO Auto-generated method stub
		if(bo == 0)
		{
			if(string.equals("登录成功"))
			{
				bo = 1;
				GUIini3();
				ActionIni2();
			}
			else
			{
				GUIini2(string);
			}
		}
		else
		{
			text1.setText(text1.getText() + string + "\n");
		}
	}

    //放远，这个是整个程序的起点
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new qq_window();
    }

}