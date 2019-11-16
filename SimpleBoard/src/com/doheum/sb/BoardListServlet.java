package com.doheum.sb;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("list!!!");
		
		String strPage = request.getParameter("page");
		if(strPage == null) {
			strPage = "1";
		}
		
		int page = Integer.parseInt(strPage);
		System.out.println("page : " + page);
		 
		int showCnt = 5;
		int sIdx = (page - 1) * showCnt;
		List<BoardVo> list = SBDao.getBoardList(sIdx, showCnt);
		request.setAttribute("data", list);		
			
		int totalPagingCnt = SBDao.getTotalPagingCnt(showCnt);
		request.setAttribute("totalPagingCnt", totalPagingCnt);
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/list.jsp");
		rd.forward(request, response);
	}
}
