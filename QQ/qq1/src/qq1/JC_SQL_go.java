package qq1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JC_SQL_go {
	public static final String url = "jdbc:kingbase://127.0.0.1/DB814";  //MOMO��Ϊ�Լ����ݿ������  
    public static final String name = "com.Kingbase.Driver";    
    public static final String user = "SYSTEM";    
    public static final String password = "123456";  //������û�����������Ӧ�ö�һ������һ��������Ϊ�Լ��ľͺ�
    
    public Connection conn = null;    
    public PreparedStatement pst = null;    
	
    public JC_SQL_go(String sql) {  
        try {    
            //Class.forName(name);//ָ���е�����������в��ɹ�  
            DriverManager.registerDriver(new com.kingbase.Driver());  
            conn = DriverManager.getConnection(url, user, password);//��ȡ����    
            pst = conn.prepareStatement(sql);//׼��ִ�����   
            System.out.print("yes");  
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }  
    
    public void close() {    
        try {    
            this.conn.close();    
            this.pst.close();    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }    
}
