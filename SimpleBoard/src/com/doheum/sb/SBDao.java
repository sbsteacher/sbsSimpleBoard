package com.doheum.sb;

import java.sql.*;
import java.util.*;

public class SBDao {
	
	private static Connection getCon() throws Exception {
		//URL = "jdbc:mysql://localhost:3306/jsp?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
		
		final String URL = "jdbc:mysql://192.168.1.4:3306/jsp2?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
		final String USER = "teacher"; //root
		final String PW = "1234";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(URL, USER, PW);
		System.out.println("DB 접속 성공!!");
		return con;
	}

	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(con, ps);
	}

	private static void close(Connection con, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 글쓰기
	public static int insertBoard(BoardVo vo) {
		int result = 0;
		String query = " INSERT INTO t_board" + " (title, content)" + " VALUES" + " (?, ?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}

		return result;
	}

	// 글 리스트 가져오기
	public static List<BoardVo> getBoardList() {
		List<BoardVo> list = new ArrayList();
		
		String query = " SELECT i_board, title, regdatetime, cnt FROM t_board ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
						
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String regDateTime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setRegDateTime(regDateTime);
				vo.setCnt(cnt);
				
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return list;
	}

	// 글 디테일 가져오기
	public static BoardVo getBoardDetail(int i_board) {
		BoardVo vo = new BoardVo();
		String query = " SELECT * FROM t_board WHERE i_board = ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, i_board);
			rs = ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDateTime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDateTime(regDateTime);
				vo.setCnt(cnt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return vo;
	}

	// 글삭제
	public static int delBoard(int i_board) {
		int result = 0; // 디폴트 삭제 못 했다

		Connection con = null;
		PreparedStatement ps = null;

		String query = " DELETE FROM t_board WHERE i_board = ? ";

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, i_board);
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(con, ps);
		}
		// 로직처리, 삭제가 잘 됐다면 result = 1;

		return result;
	}

	// 글수정
	public static int modBoard(BoardVo vo) {
		int result = 0; // 0은 수정 실패 값

		Connection con = null;
		PreparedStatement ps = null;

		String query = " UPDATE t_board " 
					+ " SET title = ? " 
					+ " , content = ? " 
					+ " WHERE i_board = ? ";

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getI_board());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}

		return result;
	}
	
	public static void plusCnt(int i_board) {
		Connection con = null;
		PreparedStatement ps = null;

		String sql = " UPDATE t_board SET cnt = cnt + 1 "
				   + " WHERE i_board = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, i_board);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
