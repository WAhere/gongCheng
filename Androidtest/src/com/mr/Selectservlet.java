package com.mr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Selectservlet
 */
public class Selectservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 //查找数据，输入起点和终点，返回所有路径.
		 String origin=request.getParameter("origin");
		 String point=request.getParameter("point");
		 List<Train> emplist=new ArrayList<Train>();
		 emplist=selecttrain.selects(origin, point);
	      request.setAttribute("tickets", emplist);
	      request.getRequestDispatcher("Tickets.jsp").forward(request, response);
	}

}
