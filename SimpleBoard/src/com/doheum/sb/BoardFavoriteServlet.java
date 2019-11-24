package com.doheum.sb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.BoardDAO;
import com.doheum.sb.vo.FavoriteVO;
import com.doheum.sb.vo.UserVO;

@WebServlet("/favorite")
public class BoardFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str_iboard = request.getParameter("i_board");
		
		int i_board = Utils.parseStringToInt(str_iboard);
		
		if(i_board == 0) {			
			return;
		}
		HttpSession session = request.getSession();		
		UserVO loginUser = (UserVO)session.getAttribute("loginUser"); 
		FavoriteVO param = new FavoriteVO();
		param.setI_board(i_board);
		param.setUid(loginUser.getUid());
		
		boolean isFavorite = BoardDAO.isFavorite(param);
		
		//isFavorite값이 true면 삭제!
		//isFavorite값이 false면 등록!
		BoardDAO.regdelFavorite(isFavorite, param);
		
		response.sendRedirect("detail?p=1&err=0&i_board=" + str_iboard);
	}
}





