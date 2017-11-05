package com.mxd.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlHelper {
	private static Connection ct = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	
	public ArrayList executeQuery(String sql,String []paras){
		ArrayList al = new ArrayList();
		try{
			ct = DBUtil.getConnection();
			ps = ct.prepareStatement(sql);
			//给SQL问号赋值
			for(int i = 0;i < paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData(); 
			//用法rs可以的到有多少列  
            int columnNum=rsmd.getColumnCount();  
            //循环从a1中取出数据封装到ArrayList  
            while(rs.next()){  
                Object []objects=new Object[columnNum];  
                for(int i=0;i<objects.length;i++){  
                    objects[i]=rs.getObject(i+1); //返回对象数组  
                }  
                al.add(objects);  
            }  
            
           
            return al;
		}catch(Exception e){
			 e.printStackTrace();  
             throw new RuntimeException(e.getMessage());  
		}finally{  
            DBUtil.close(rs,ps,ct);
              
        }  
	}
	
	public ResultSet executeQuery(String sqlstr){  
        //Statement stmt = null;  
        try{  
            //得到连接  
            ct=DBUtil.getConnection();  
            ps=ct.prepareStatement(sqlstr);  
            //stmt = ct.createStatement();  
            //创建结果集  
             rs = ps.executeQuery(sqlstr);   
            //将结果集返回  
            return rs;  
        }catch(SQLException e)  
        {  
            System.out.print("错误");  
        }  
        return null;  
    }  
}
