package com.doheum.sb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doheum.sb.dao.BoardDAO;

@WebServlet("/mod")
public class BoardModServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//수정화면 띄우기
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(str_board);
		
		BoardVo vo = (BoardVo)request.getAttribute("vo");
		
		if(vo == null) {
			vo = BoardDAO.getBoardDetail(i_board);
			request.setAttribute("vo", vo);
		}		
		request.getRequestDispatcher("WEB-INF/jsp/mod.jsp").forward(request, response);
	}

	//수정하기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		int i_board = Utils.parseStringToInt(str_board);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo(i_board, title, content);
		int result = BoardDAO.modBoard(vo);
		
		if(result == 0) { //수정 실패 (수정 화면으로 이동)
			request.setAttribute("vo", vo);
			request.setAttribute("msg", "수정을 실패하였습니다.");
			
			doGet(request, response);
		} else { //수정 완료!! (디테일 화면으로 이동)
			response.sendRedirect("detail?i_board=" + str_board);
		}
	}

}








