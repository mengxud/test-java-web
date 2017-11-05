package com.mxd.service;

import java.util.ArrayList;

import com.mxd.domain.Users;
import com.mxd.utils.SqlHelper;

public class UsersService {
	
	public boolean checkUsers(Users user){
		ArrayList al = new ArrayList();
		SqlHelper sqlHelper = new SqlHelper();
		String sql = "select * from users where name = ? and pwd = ?";
		String[] paras={user.getName(),user.getPwd()}; 
		al = sqlHelper.executeQuery(sql,paras);
		if(al.size()==0){
			return false;
		}else{
			Object[] objects=(Object[])al.get(0);
            //把对象数组封装到Users对象   
			user.setId(Integer.parseInt(objects[0].toString()));
            user.setName((String)objects[1]);  
            user.setEmail((String)objects[3]);  
            user.setGrade(Integer.parseInt(objects[5].toString()));  
            //在用户登陆后同时还需要取出该用户的其他信息,故封装到一个对象  
            return true;  
		}
	}
}
