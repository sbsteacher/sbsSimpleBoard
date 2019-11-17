package com.doheum.sb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer result = (Integer)request.getAttribute("result");
		
		if(result != null) {
			String msg;
			switch(result) {
			case 2:
				msg = "비밀번호를 확인해 주세요";
				break;
			case 3:
				msg = "아이디가 존재하지 않습니다.";
				break;
			default:
				msg = "에러가 발생되었습니다. ";
				break;
			}
			request.setAttribute("msg", msg);
		}
		
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		
		System.out.println("uid : " + uid);
		System.out.println("upw : " + upw);
		
		UserVO vo = new UserVO(); //C
		vo.setUid(uid);
		vo.setUpw(upw);
		
		LoginResultVO result = UserDAO.login(vo);
		if(result.getResult() == 1) {
			//로그인 완료 처리			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", result.getVo());
			
			response.sendRedirect("list");
		} else {
			request.setAttribute("vo", vo);
			request.setAttribute("result", result);
			doGet(request, response);
		}
		
		System.out.println("result : " + result);
		
	}

}







