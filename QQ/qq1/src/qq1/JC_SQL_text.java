package qq1;



import java.sql.ResultSet;
import java.sql.SQLException;

public class JC_SQL_text {
	private String sql = null;    
	private JC_SQL_go db1 = null;    
	private ResultSet ret = null;
    String host_;
    int port_;
    qq_window qq_;
    //private String operating = "";
    //private String news = "";
    //注册|账号|密码|昵称
    //登录|账号|密码
    //发送|账号|消息
    public void ready(String host,int port,String ss,qq_window qq)
    {
    	host_ = host;
        port_ = port;
        qq_ = qq;
    	String[] aa = ss.split("\\|");
    	
    	if(aa[0].equals("registered"))
    	{
    		//要执行的SQL语句，改成自己的表什么的    
    		registered(aa);
    	}
    	else if(aa[0].equals("log_in"))
    	{
    		log_in(aa);
    	}
    	else if(aa[0].equals("send"))
    	{
    		send(aa);
    	}
    	else
    	{
    		qq_.printf_text(aa[0]);
    	}
        
    }
    
    //注册|账号|密码|昵称-账号
    private void registered(String[] ss)
    {
    	sql = "select QQ_IPNAME from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
    	db1 = new JC_SQL_go(sql);//创建数据库对象
    	
    	try {    
            ret = db1.pst.executeQuery();//执行语句，ret是结果 
            //ret.next();
            //qq_.printf_text(ss[1]);
            
            if(!ret.next())
            {
            	//String id_name = ret.getString(1);
            	//qq_.printf_text(id_name);
            	ret.close();
                db1.close();//关闭连接 
            	
            	sql = "INSERT INTO \"QQ\".IP_NAME(QQ_IPNAME,QQ_PASSWORD,QQ_NAME) VALUES ('" +ss[1]+ "','"+ss[2]+"','" +ss[3]+ "')";
            	db1 = new JC_SQL_go(sql);//创建数据库对象
            	//qq_.printf_text("asdasdsdfasdf");
            	try {    
            		db1.pst.execute();//执行语句，ret是结果 
            		//qq_.printf_text("asdasdsdfasdf");
                    //String id_name = ret.getString(1);
                    ( new SendThread(qq_) ).senMessage(host_,port_,"注册成功");
                    ret.close();
                    db1.close();//关闭连接
            	}catch (SQLException e) {    
                    e.printStackTrace();    
                }
            	
            }
            else
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"用户名已存在");
            }
            
            ret.close();    
            db1.close();//关闭连接    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }
    }
    
    //登录|账号|密码-密码
    private void log_in(String[] ss)
    {
    	//qq_.printf_text(ss[1] + "---"+ss[2]);
    	sql = "select QQ_IPNAME"
    		+ " from \"QQ\".IP_NAME "
    		+ "WHERE QQ_IPNAME = " + "\'" + ss[1] + "\'" + " AND QQ_PASSWORD = " + "\'" + ss[2] + "\'";
    	db1 = new JC_SQL_go(sql);//创建数据库对象

        try {
            ret = db1.pst.executeQuery();//执行语句，ret是结果
            
            
            
            if(!ret.next())
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"账户或密码错误");
            }
            else
            {
            	String mm = ret.getString(1);
                qq_.printf_text(mm);
                
            	ret.close();    
                db1.close();//关闭连接    
            	
            	sql = "UPDATE \"QQ\".IP_NAME SET QQ_IP = '" + host_ + "',QQ_PORT = '" + port_ + "' WHERE QQ_IPNAME = '" +ss[1]+ "'";
            	db1 = new JC_SQL_go(sql);//创建数据库对象    
            	try {    
                    db1.pst.execute();//执行语句，ret是结果  
                    //String mm = ret.getString(1);
                    
                    //ret.close();    
                    db1.close();//关闭连接    
            	}catch (SQLException e) {    
                    e.printStackTrace();    
                }
            	
            	( new SendThread(qq_) ).senMessage(host_,port_,"登录成功");
            }
            
            ret.close();    
            db1.close();//关闭连接    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }
    
    //发送|账号|消息-IP，端口
    private void send(String[] ss)
    {
    	sql = "select QQ_IPNAME from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
    	db1 = new JC_SQL_go(sql);//创建数据库对象
    	
    	try {
            ret = db1.pst.executeQuery();//执行语句，ret是结果  
            
            
            
            if(ret.next())
            {
            	String mm = ret.getString(1);
            	//qq_.printf_text(ss[2]);
            	ret.close();    
                db1.close();//关闭连接    
                
            	sql = "select QQ_IP,QQ_PORT from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
            	db1 = new JC_SQL_go(sql);//创建数据库对象
            	
            	try {
                    ret = db1.pst.executeQuery();//执行语句，ret是结果  
                    
                    
                    
                    if(!ret.next())
                    {
                    	( new SendThread(qq_) ).senMessage(host_,port_,"对方不在线，消息发送失败");
                    }
                    else
                    {
                    	String mm_ = ret.getString(1);
                    	qq_.printf_text(ss[2]);
                    	( new SendThread(qq_) ).senMessage( ( ret.getString(1) ).substring(0, 13),ret.getInt(2),ss[2]);
                    }
                    
                    ret.close();    
                    db1.close();//关闭连接    
                } catch (SQLException e) {    
                    e.printStackTrace();    
                }
            }
            else
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"对方账户不存在，消息发送失败");
            }
            
            ret.close();    
            db1.close();//关闭连接    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    	
    	
    }

}
