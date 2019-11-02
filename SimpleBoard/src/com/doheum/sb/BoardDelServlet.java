package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del")
public class BoardDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("i_board"); // "3"
		
		int i_board = Utils.parseStringToInt(str); //3
		if(i_board == 0) { 
			//예외처리 나중에
			return;
		}
		
		int result = SBDao.delBoard(i_board);
		
		if(result == 0) {
			//예외처리 나중에
			return;
		}
		//삭제처리
		//DELETE FROM t_board WHERE i_board = ?
		
		response.sendRedirect("list");
	}

}




