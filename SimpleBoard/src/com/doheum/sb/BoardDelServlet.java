package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doheum.sb.dao.BoardDAO;

@WebServlet("/del")
public class BoardDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("i_board"); // "3aa"
		
		int i_board = Utils.parseStringToInt(str); //3
		if(i_board == 0) { 
			//예외처리 나중에
			response.sendRedirect("list");
			return;
		}
		
		int result = BoardDAO.delBoard(i_board);
		
		if(result == 0) {
			//예외처리 나중에
			response.sendRedirect("detail?err=1&i_board=" + str);
			return;
		}
		//삭제처리
		//DELETE FROM t_board WHERE i_board = ?
		
		response.sendRedirect("list");
	}

}




