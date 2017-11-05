package com.mxd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mxd.domain.Book;
import com.mxd.domain.Users;
import com.mxd.utils.DBUtil;

public class OrderService {
	
	private  Connection ct = null;
	private  ResultSet rs = null;
	private  PreparedStatement ps = null;
	
	public void submitOrder(MyCart myCart,Users user){
		String sql = "insert into orders(userid,totalPrice,orderDate) values(?,?,date(now()))";
		
		try {
			ct = DBUtil.getConnection();
			ct.setAutoCommit(false);
			//为了保证订单号正确，需将事务隔离级别升级
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			ps = ct.prepareStatement(sql);
			ps.setInt(1,user.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			//得到刚刚生成的订单号
			sql = "select LAST_INSERT_ID() FROM orders";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next()){
				orderId=rs.getInt(1);
			}
			//把订单细节表也要生成
			ArrayList al = myCart.showMyCart();
			for(int i = 0;i<al.size();i++){
				Book book = (Book)al.get(i);
				sql = "insert into ordersitem (ordersId,bookId,bookNum) values(?,?,?)";
				ps = ct.prepareStatement(sql);
				ps.setInt(1,orderId);
				ps.setInt(2,book.getId());
				ps.setInt(3,book.getShoppingNum());
				ps.execute();
			}
			ct.commit();
			
		} catch (Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, ct);
		}
	}
}
