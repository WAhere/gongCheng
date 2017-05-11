package com.mr;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Buyservlet
 */
public class Buyservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 //查找数据，输入起点和终点，返回所有路径.
		 String car_id=request.getParameter("car_id");
		 String num=request.getParameter("num");
		 boolean isbuy=false;
		 isbuy=Buyticket.buytickets(car_id, num);
		 response.setContentType("text/html");
		 response.setCharacterEncoding("utf-8");
		 PrintWriter out=response.getWriter();
		 if(isbuy){
			 out.println("<result>success</result>");
		 }else{
			 out.println("<result>default</result>");
		 }
		 out.flush();
		 out.close();
	}
	
}
