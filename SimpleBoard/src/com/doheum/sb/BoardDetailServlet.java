package com.doheum.sb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.doheum.sb.dao.BoardDAO;

@WebServlet("/detail")
public class BoardDetailServlet extends LoginNeedServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGetProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		String err = request.getParameter("err");
		
		System.out.println("err : " + err);
		
		int i_board = Integer.parseInt(str_board);
		
		List<CommentVo> list = BoardDAO.getCommentList(i_board);
		request.setAttribute("cmtList", list);
		
		//request.setAttribute("commentList", SBDao.getCommentList(i_board));
		
		if(err != null) {
			switch(err) {
			case "1":
				request.setAttribute("msg", "삭제 오류가 발생하였습니다.");
				break;
			}
		} else { //err 이 null인 경우만 cnt 값을 올려준다.
			
			String post = (String)request.getAttribute("post");			
			if(post == null) {
				BoardDAO.plusCnt(i_board);	
			}
		}
				
		BoardVo vo = BoardDAO.getBoardDetail(i_board);
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("WEB-INF/jsp/detail.jsp").forward(request, response);
	}	
	
	//댓글달기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_comment = request.getParameter("i_comment");
		
		if(str_comment.equals("0")) { //댓글 등록
			String str_board = request.getParameter("i_board");		
			int i_board = Utils.parseStringToInt(str_board);
			
			if(i_board == 0) { //문제발생!!
				
			}		
			//int i_board = Integer.parseInt(request.getParameter("i_board"));
				
			String cmt = request.getParameter("comment");
			System.out.println("cmt : " + cmt);
			System.out.println("i_board : " + i_board);		
			CommentVo vo = new CommentVo();
			vo.setI_board(i_board);
			vo.setCmt(cmt);		
			BoardDAO.insertComment(vo);
		} else { //댓글 삭제
			int i_comment = Integer.parseInt(str_comment);
			
			BoardDAO.delComment(i_comment);
		}
		
		request.setAttribute("post", "");
		doGet(request, response);
		
	}

}








