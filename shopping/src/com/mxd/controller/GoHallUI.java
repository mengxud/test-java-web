package com.mxd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mxd.domain.Book;
import com.mxd.domain.Users;
import com.mxd.service.BookService;
import com.mxd.service.UsersService;
import com.mxd.service.MyCart;

public class GoHallUI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3513038588623213505L;

	/**
	 * Constructor of the object.
	 */
	public GoHallUI() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//如果直接跳转hall界面判断有没有Session
		if(request.getSession().getAttribute("user")!=null){
			BookService bs = new BookService();
			ArrayList<Book> al = bs.getAllBook();
			request.setAttribute("book", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return;
		}
		
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		Users user = new Users();
		user.setName(username);
		user.setPwd(passwd);
		
		UsersService us = new UsersService();
		if(us.checkUsers(user)){
			//把用户存在Session
			request.getSession().setAttribute("user", user);
			//登陆成功给用户创建一个购物车
			MyCart myCart = new MyCart();
			request.getSession().setAttribute("myCart", myCart);
			
			BookService bs = new BookService();
			ArrayList<Book> al = bs.getAllBook();
			request.setAttribute("book", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response); 
		}else{
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
