package com.doheum.sb;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.doheum.sb.dao.BoardDAO;
import com.doheum.sb.vo.BoardVO;
import com.doheum.sb.vo.UserVO;

@WebServlet("/write")
public class BoardWriteServlet extends LoginNeedServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGetProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		System.out.println("title : " + title);
		
		request.setAttribute("title", "글쓰기");
		request.setAttribute("target", "write");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/template.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		System.out.println("-- doPost --");
		String grp = request.getParameter("grp");
		String seq = request.getParameter("seq");
		String floor = request.getParameter("floor");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//int intContent = Integer.parseInt(content);
		
		System.out.println("title : " + title);
		System.out.println("content : " + content);
		
		//int, char, float, byte, short, boolean, double 
		
		BoardVO vo = new BoardVO(title, content);
		
		//누가 작성하는 글인지 uid값을 세팅!!
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		vo.setUid(loginUser.getUid());
		vo.setGrp(Integer.parseInt(grp));	
		
		if(grp.equals("0")) {			
			vo.setSeq(Integer.parseInt(seq));
			vo.setFloor(Integer.parseInt(floor));
			BoardDAO.insertBoard(vo);
			//grp값 등록
			BoardDAO.updGrp();
		
		} else { //답글
			vo.setSeq(Integer.parseInt(seq) + 1);
			vo.setFloor(Integer.parseInt(floor) + 1);
			BoardDAO.updSeq(vo);
			BoardDAO.insertBoard(vo);
		}
		
		
		response.sendRedirect("list");
	}

}











