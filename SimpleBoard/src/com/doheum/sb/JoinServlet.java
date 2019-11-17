package com.doheum.sb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doheum.sb.dao.UserDAO;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/join.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String nm = request.getParameter("nm");
	
		System.out.println("uid: " + uid);
		System.out.println("upw" + upw);
		System.out.println("nm: " + nm);
				
		UserVO uVO = new UserVO();
		uVO.setUid(uid);
		uVO.setUpw(upw);
		uVO.setNm(nm);
		
		UserDAO.insertUser(uVO);	
	
	}
}




