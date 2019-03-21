package qq1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveThread  extends Thread {
	
	private ServerSocket server;
	private qq_window qq;
	
	public ReceiveThread(qq_window qq)
	{
		this.qq = qq;
		try {
            server = new ServerSocket(8080);
            //qq.setLocalPort(server.getLocalPort());
            start();
            qq.printf_text("�����������ɹ�\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            qq.printf_text("���ӳ���");
        }
		
	}
	
	public void run() {
        // TODO Auto-generated method stub
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                
                //socket.getRemoteSocketAddress();//IP���˿�
                String s = ( new BufferedReader( new InputStreamReader( socket.getInputStream() ) ) ).readLine() ;//��Ϣ
                
                qq.printf_text(socket.getRemoteSocketAddress() + ":" + s + "\n");
                //IP$�˿�$��Ϣ
                String[] ss = s.split("\\$");
                
                (new JC_SQL_text() ).ready(ss[0], (Integer.parseInt(ss[1])) ,ss[2],qq);
                
                
                
                //qq.printf_text("");
                
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("�߳̽����ܵ�������д��Ի������");
            }
        }
    }
	

}
