package com.doheum.sb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.BoardDAO;
import com.doheum.sb.vo.BoardVO;
import com.doheum.sb.vo.CommentVO;
import com.doheum.sb.vo.UserVO;

@WebServlet("/detail")
public class BoardDetailServlet extends LoginNeedServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGetProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_board = request.getParameter("i_board");
		String err = request.getParameter("err");
		
		System.out.println("err : " + err);
		
		int i_board = Integer.parseInt(str_board);
		
		List<CommentVO> list = BoardDAO.getCommentList(i_board);
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
		
		HttpSession session = request.getSession();		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser"); 
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setUid(loginUser.getUid());
				
		BoardVO vo = BoardDAO.getBoardDetail(param);
		request.setAttribute("vo", vo);
		
		request.setAttribute("title", "디테일");
		request.setAttribute("target", "detail");
		request.getRequestDispatcher("WEB-INF/jsp/template.jsp").forward(request, response);
	}	
	
	//댓글달기
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_comment = request.getParameter("i_comment");
		
		//누가 작성하는 글인지 uid값을 세팅!!
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");	
		CommentVO vo = new CommentVO();
		
		if(str_comment.equals("0")) { //댓글 등록
			String str_board = request.getParameter("i_board");		
			int i_board = Utils.parseStringToInt(str_board);
			
			if(i_board == 0) { //문제발생!!
				
			}		
			//int i_board = Integer.parseInt(request.getParameter("i_board"));
				
			String cmt = request.getParameter("comment");
			System.out.println("cmt : " + cmt);
			System.out.println("i_board : " + i_board);		
			
			vo.setI_board(i_board);
			vo.setCmt(cmt);				
			vo.setUid(loginUser.getUid());
			
			BoardDAO.insertComment(vo);
		} else { //댓글 삭제
			
			int i_comment = Integer.parseInt(str_comment);	
			vo.setI_comment(i_comment);
			vo.setUid(loginUser.getUid());
			
			int result = BoardDAO.delComment(vo);
			
			if(result == 0) {
				request.setAttribute("msg", "댓글을 삭제할 수 없습니다.");
			}
		}
		
		request.setAttribute("post", "");
		doGet(request, response);
		
	}

}








