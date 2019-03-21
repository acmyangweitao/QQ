package qq1;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SendThread {
	
	private qq_window qq;
	
	private String remoteIP = "";
    private int port = 0;
    private String message = "";
	
	public SendThread(qq_window qq)
	{
		this.qq = qq;
	}

	public void notRun() {
        InetSocketAddress isa = new InetSocketAddress(remoteIP, port);
        try {
            Socket socket = new Socket();
            socket.connect(isa);
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
            
            qq.printf_text("");
            
            System.out.println("将数据写入到流中");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            message = "";
        }
    }
	
	public void senMessage(String host,int port,String message){
        qq.printf_text("发送"+host+":"+port+":"+message+"\n");
		remoteIP = host;
        this.port = port;
        this.message = message;
        notRun();
    }
	
}
