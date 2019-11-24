package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.vo.UserVO;

public abstract class LoginNeedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	abstract protected void doGetProc(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");		
		if(loginUser == null) {
			response.sendRedirect("login");
			return;
		}		
		doGetProc(request, response);
	}

}
