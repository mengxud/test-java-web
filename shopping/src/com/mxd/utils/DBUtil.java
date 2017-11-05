package com.mxd.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	private static Connection ct = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	
	private static String url = "";
	private static String drivername = "";
	private static String username = "";
	private static String password = "";
	
	static{
		try{
			Properties properties = new Properties();
			InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("com/mxd/utils/dbinfo.properties");
			properties.load(is);
			
			drivername = properties.getProperty("driver");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			url = properties.getProperty("url");
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Connection getConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3307/shopping", "root", "root");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return ct;
	}
	
	public static void close(ResultSet rs,Statement ps,Connection ct)  
	{  
	    if(rs!=null)  
	    {     
	        try{  
	            rs.close();  
	        }catch(Exception e)  {  
	        	e.printStackTrace();  
	        }  
	        rs=null;//使用垃圾回收  
	    }  
	    if(ps!=null){  
	        try{  
	            ps.close();  
	        }catch(SQLException e){  
	            e.printStackTrace();  
	        }  
	        ps=null;  
	    }  
	      
	    if(ct!=null){  
	        try{  
	        	ct.close();  
	        }catch(SQLException e){  
	            e.printStackTrace();  
	        }  
	        ct=null;  
	    }  
	          
	}  public static void main(String args [])  
	{  
	      
	    System.out.println(drivername);  
	    System.out.println(username);  
	    System.out.println(password);  
	    System.out.println(url);  
	}  
	  
	
}
