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
		Integer result = (Integer)request.getAttribute("result");		
		System.out.println("result : " + result);
				
		if(result != null) {
			String msg;
			switch(result) {
			case 2:
				msg = "중복된 아이디가 있습니다.";
				break;
			case 3:
				msg = "내용이 너무 긴 값이 있습니다.";
				break;
			default:
				msg = "에러가 발생되었습니다.";
				break;
			}
			request.setAttribute("msg",  msg);
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/join.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		String nm = request.getParameter("nm");
	
		System.out.println("uid: " + uid);
		System.out.println("upw" + upw);
		System.out.println("nm: " + nm);
		
		upw = Utils.encryptSHA256(upw);
		System.out.println("upw : " + upw);
		
		UserVO uVO = new UserVO();
		uVO.setUid(uid);
		uVO.setUpw(upw);
		uVO.setNm(nm);
		
		int result = UserDAO.insertUser(uVO);	
		
		if(result == 1) {
			response.sendRedirect("login");
		} else {
			request.setAttribute("vo", uVO);
			request.setAttribute("result", result);
			doGet(request, response);
		}
	
	}
}





