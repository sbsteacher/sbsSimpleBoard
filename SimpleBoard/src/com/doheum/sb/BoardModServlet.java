package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//수정화면 띄우기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(str_board);
		
		BoardVo vo = SBDao.getBoardDetail(i_board);
		request.setAttribute("vo", vo);
		
		request.getRequestDispatcher("WEB-INF/jsp/mod.jsp").forward(request, response);
	}

	//수정하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
