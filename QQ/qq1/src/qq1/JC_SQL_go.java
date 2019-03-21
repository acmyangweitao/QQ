package qq1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JC_SQL_go {
	public static final String url = "jdbc:kingbase://127.0.0.1/DB814";  //MOMO改为自己数据库的名字  
    public static final String name = "com.Kingbase.Driver";    
    public static final String user = "SYSTEM";    
    public static final String password = "123456";  //这里的用户名和密码大家应该都一样，不一样的设置为自己的就好
    
    public Connection conn = null;    
    public PreparedStatement pst = null;    
	
    public JC_SQL_go(String sql) {  
        try {    
            //Class.forName(name);//指南中的这个方法运行不成功  
            DriverManager.registerDriver(new com.kingbase.Driver());  
            conn = DriverManager.getConnection(url, user, password);//获取连接    
            pst = conn.prepareStatement(sql);//准备执行语句   
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
