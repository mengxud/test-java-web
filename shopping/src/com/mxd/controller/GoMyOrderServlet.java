package com.mxd.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxd.service.MyCart;

public class GoMyOrderServlet extends HttpServlet {

	
	private static final long serialVersionUID = -1763468363213226401L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
		request.setAttribute("bookList", myCart.showMyCart());
		request.setAttribute("totalPrice", myCart.getTotalPrice());
		request.setAttribute("totalSum", myCart.getTotalSum());
		request.getRequestDispatcher("/WEB-INF/myOrder.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

      this.doGet(request, response);		
	}

}
