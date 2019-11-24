package com.doheum.sb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.BoardDAO;
import com.doheum.sb.vo.BoardVO;
import com.doheum.sb.vo.UserVO;

@WebServlet("/del")
public class BoardDelServlet extends LoginNeedServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doGetProc(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {		
		String str = request.getParameter("i_board"); // "3aa"
		
		HttpSession session = request.getSession();
		
		//로그인 정보 가져오기
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");		
		String uid = loginUser.getUid();
				
		int i_board = Utils.parseStringToInt(str); //3
		if(i_board == 0) { 
			//예외처리 나중에
			response.sendRedirect("list");
			return;
		}
		
		BoardVO param = new BoardVO();
		param.setI_board(i_board);
		param.setUid(uid);
		
		int result = BoardDAO.delBoard(param);
		
		if(result == 0) {
			//예외처리 나중에
			response.sendRedirect("detail?err=1&i_board=" + str);
			return;
		}		
		response.sendRedirect("list");
	}

}




