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
    //ע��|�˺�|����|�ǳ�
    //��¼|�˺�|����
    //����|�˺�|��Ϣ
    public void ready(String host,int port,String ss,qq_window qq)
    {
    	host_ = host;
        port_ = port;
        qq_ = qq;
    	String[] aa = ss.split("\\|");
    	
    	if(aa[0].equals("registered"))
    	{
    		//Ҫִ�е�SQL��䣬�ĳ��Լ��ı�ʲô��    
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
    
    //ע��|�˺�|����|�ǳ�-�˺�
    private void registered(String[] ss)
    {
    	sql = "select QQ_IPNAME from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
    	db1 = new JC_SQL_go(sql);//�������ݿ����
    	
    	try {    
            ret = db1.pst.executeQuery();//ִ����䣬ret�ǽ�� 
            //ret.next();
            //qq_.printf_text(ss[1]);
            
            if(!ret.next())
            {
            	//String id_name = ret.getString(1);
            	//qq_.printf_text(id_name);
            	ret.close();
                db1.close();//�ر����� 
            	
            	sql = "INSERT INTO \"QQ\".IP_NAME(QQ_IPNAME,QQ_PASSWORD,QQ_NAME) VALUES ('" +ss[1]+ "','"+ss[2]+"','" +ss[3]+ "')";
            	db1 = new JC_SQL_go(sql);//�������ݿ����
            	//qq_.printf_text("asdasdsdfasdf");
            	try {    
            		db1.pst.execute();//ִ����䣬ret�ǽ�� 
            		//qq_.printf_text("asdasdsdfasdf");
                    //String id_name = ret.getString(1);
                    ( new SendThread(qq_) ).senMessage(host_,port_,"ע��ɹ�");
                    ret.close();
                    db1.close();//�ر�����
            	}catch (SQLException e) {    
                    e.printStackTrace();    
                }
            	
            }
            else
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"�û����Ѵ���");
            }
            
            ret.close();    
            db1.close();//�ر�����    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }
    }
    
    //��¼|�˺�|����-����
    private void log_in(String[] ss)
    {
    	//qq_.printf_text(ss[1] + "---"+ss[2]);
    	sql = "select QQ_IPNAME"
    		+ " from \"QQ\".IP_NAME "
    		+ "WHERE QQ_IPNAME = " + "\'" + ss[1] + "\'" + " AND QQ_PASSWORD = " + "\'" + ss[2] + "\'";
    	db1 = new JC_SQL_go(sql);//�������ݿ����

        try {
            ret = db1.pst.executeQuery();//ִ����䣬ret�ǽ��
            
            
            
            if(!ret.next())
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"�˻����������");
            }
            else
            {
            	String mm = ret.getString(1);
                qq_.printf_text(mm);
                
            	ret.close();    
                db1.close();//�ر�����    
            	
            	sql = "UPDATE \"QQ\".IP_NAME SET QQ_IP = '" + host_ + "',QQ_PORT = '" + port_ + "' WHERE QQ_IPNAME = '" +ss[1]+ "'";
            	db1 = new JC_SQL_go(sql);//�������ݿ����    
            	try {    
                    db1.pst.execute();//ִ����䣬ret�ǽ��  
                    //String mm = ret.getString(1);
                    
                    //ret.close();    
                    db1.close();//�ر�����    
            	}catch (SQLException e) {    
                    e.printStackTrace();    
                }
            	
            	( new SendThread(qq_) ).senMessage(host_,port_,"��¼�ɹ�");
            }
            
            ret.close();    
            db1.close();//�ر�����    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    }
    
    //����|�˺�|��Ϣ-IP���˿�
    private void send(String[] ss)
    {
    	sql = "select QQ_IPNAME from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
    	db1 = new JC_SQL_go(sql);//�������ݿ����
    	
    	try {
            ret = db1.pst.executeQuery();//ִ����䣬ret�ǽ��  
            
            
            
            if(ret.next())
            {
            	String mm = ret.getString(1);
            	//qq_.printf_text(ss[2]);
            	ret.close();    
                db1.close();//�ر�����    
                
            	sql = "select QQ_IP,QQ_PORT from \"QQ\".IP_NAME WHERE QQ_IPNAME = " + "'" + ss[1] + "'";
            	db1 = new JC_SQL_go(sql);//�������ݿ����
            	
            	try {
                    ret = db1.pst.executeQuery();//ִ����䣬ret�ǽ��  
                    
                    
                    
                    if(!ret.next())
                    {
                    	( new SendThread(qq_) ).senMessage(host_,port_,"�Է������ߣ���Ϣ����ʧ��");
                    }
                    else
                    {
                    	String mm_ = ret.getString(1);
                    	qq_.printf_text(ss[2]);
                    	( new SendThread(qq_) ).senMessage( ( ret.getString(1) ).substring(0, 13),ret.getInt(2),ss[2]);
                    }
                    
                    ret.close();    
                    db1.close();//�ر�����    
                } catch (SQLException e) {    
                    e.printStackTrace();    
                }
            }
            else
            {
            	( new SendThread(qq_) ).senMessage(host_,port_,"�Է��˻������ڣ���Ϣ����ʧ��");
            }
            
            ret.close();    
            db1.close();//�ر�����    
        } catch (SQLException e) {    
            e.printStackTrace();    
        }    
    	
    	
    }

}
