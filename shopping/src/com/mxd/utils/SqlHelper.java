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
			//��SQL�ʺŸ�ֵ
			for(int i = 0;i < paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData(); 
			//�÷�rs���Եĵ��ж�����  
            int columnNum=rsmd.getColumnCount();  
            //ѭ����a1��ȡ�����ݷ�װ��ArrayList  
            while(rs.next()){  
                Object []objects=new Object[columnNum];  
                for(int i=0;i<objects.length;i++){  
                    objects[i]=rs.getObject(i+1); //���ض�������  
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
            //�õ�����  
            ct=DBUtil.getConnection();  
            ps=ct.prepareStatement(sqlstr);  
            //stmt = ct.createStatement();  
            //���������  
             rs = ps.executeQuery(sqlstr);   
            //�����������  
            return rs;  
        }catch(SQLException e)  
        {  
            System.out.print("����");  
        }  
        return null;  
    }  
}
