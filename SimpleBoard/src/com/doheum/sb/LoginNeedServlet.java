package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginNeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//로그인이 안되어 있으면 return true
	//로그인이 되어 있으면 return false
	protected boolean logoutCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");		
		if(loginUser == null) {
			response.sendRedirect("login");
			return true;
		}		
		return false;
	}

}
