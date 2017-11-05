package com.mxd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxd.domain.Users;
import com.mxd.service.MyCart;
import com.mxd.service.OrderService;

public class SubmitOrderServlet extends HttpServlet {

	
	private static final long serialVersionUID = -3038650800876331645L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setContentType("text/html");
		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		Users user = (Users)request.getSession().getAttribute("user");
		OrderService os = new OrderService();
		os.submitOrder(myCart, user);
		request.getRequestDispatcher("/WEB-INF/orderOk.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	this.doGet(request, response);
	}

}
