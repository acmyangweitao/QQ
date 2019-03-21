package qq2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ReceiveThread extends Thread {

    private ServerSocket server;
    private qq_window window;

    public ReceiveThread(qq_window window) {
        this.window = window;
        try {
            server = new ServerSocket(0);
            
            
            //window.setLocalPort(server.getLocalPort());
            start();
            //window.GUIini2("123456789");
            window.set_MY_IP(InetAddress.getLocalHost().getHostAddress(),server.getLocalPort());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            window.printError("连接出错");
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                String s = ( new BufferedReader( new InputStreamReader( socket.getInputStream() ) ) ).readLine() ;//消息
                
                window.printf_text(s);
                
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("线程将接受到的数据写入对话框出错");
            }
        }
    }
}