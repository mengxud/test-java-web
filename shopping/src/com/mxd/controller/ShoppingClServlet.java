package com.mxd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxd.service.MyCart;

public class ShoppingClServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7729255389121552850L;

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		String type = request.getParameter("type");
		
		if(type.equals("add")){
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.addBook(bid);
			
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("totalSum", myCart.getTotalSum());
			request.setAttribute("bookList", myCart.showMyCart());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		if(type.equals("delete")){
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			myCart.delBook(bid);
			
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("totalSum", myCart.getTotalSum());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		if(type.equals("update")){
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			String[] bookids = request.getParameterValues("bid");
			String[] booknums = request.getParameterValues("booknum");
			for(int i = 0;i < bookids.length;i++){
				myCart.updateBook(bookids[i],booknums[i]);
			}
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("totalSum", myCart.getTotalSum());
			request.setAttribute("bookList", myCart.showMyCart());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		if(type.equals("return")){
			MyCart myCart = (MyCart) request.getSession().getAttribute("myCart");
			request.setAttribute("totalPrice", myCart.getTotalPrice());
			request.setAttribute("totalSum", myCart.getTotalSum());
			request.setAttribute("bookList", myCart.showMyCart());
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		
		
	}

}
