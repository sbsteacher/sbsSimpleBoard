package com.doheum.sb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.BoardDAO;
import com.doheum.sb.vo.BoardVO;
import com.doheum.sb.vo.UserVO;

@WebServlet("/list")
public class BoardListServlet extends LoginNeedServlet {
	private static final long serialVersionUID = 1L;   
	final int showCnt = 10;
  	
	@Override
	protected void doGetProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("list!!!");		
		HttpSession session = request.getSession();		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");		
		Integer p = (Integer)session.getAttribute("p");
		
	
		
		if(loginUser == null) {
			response.sendRedirect("login");
			return;
		}
		
		String strPage = request.getParameter("page");
		if(strPage == null) {
			strPage = "1";
			
		}		
		int page = Integer.parseInt(strPage);		
		session.setAttribute("p", page);
		System.out.println("page : " + page);
		 
		
		int sIdx = (page - 1) * showCnt;
		List<BoardVO> list = BoardDAO.getBoardList(sIdx, showCnt);
		request.setAttribute("data", list);		
			
		int totalPagingCnt = BoardDAO.getTotalPagingCnt(showCnt);
		request.setAttribute("totalPagingCnt", totalPagingCnt);
		
		request.setAttribute("title", "리스트");
		request.setAttribute("target", "list");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
		
	}
}
