package com.doheum.sb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		String err = request.getParameter("err");
		
		System.out.println("err : " + err);
		
		int i_board = Integer.parseInt(str_board);
		
		if(err != null) {
			switch(err) {
			case "1":
				request.setAttribute("msg", "삭제 오류가 발생하였습니다.");
				break;
			}
		} else { //err 이 null인 경우만 cnt 값을 올려준다.
			SBDao.plusCnt(i_board);
		}
				
		BoardVo vo = SBDao.getBoardDetail(i_board);
		request.setAttribute("vo", vo);
		
		request.getRequestDispatcher("WEB-INF/jsp/detail.jsp").forward(request, response);
	}	
	
	//댓글달기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");		
		int i_board = Integer.parseInt(str_board);
		String cmt = request.getParameter("comment");

		System.out.println("cmt : " + cmt);
		System.out.println("i_board : " + i_board);
		
	}

}








