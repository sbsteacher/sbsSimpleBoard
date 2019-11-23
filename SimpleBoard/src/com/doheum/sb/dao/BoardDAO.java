package com.doheum.sb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.doheum.sb.BoardVo;
import com.doheum.sb.CommentVo;
import static com.doheum.sb.dao.CommonAPI.getCon;
import static com.doheum.sb.dao.CommonAPI.close;

public class BoardDAO {	
	// 글쓰기
	public static int insertBoard(BoardVo vo) {
		int result = 0;
		String query = " INSERT INTO t_board (title, content, uid) "
							+ " VALUES (?, ?, ?) ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getUid());
			
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CommonAPI.close(con, ps);
		}

		return result;
	}

	// 글 리스트 가져오기
	public static List<BoardVo> getBoardList(int sIdx, int showCnt) {
		List<BoardVo> list = new ArrayList();
		
		String query = " SELECT A.i_board, A.title, A.content "
				+ " , A.regdatetime, A.cnt, B.nm "
				+ " FROM t_board A "
				+ " INNER JOIN t_user B "
				+ " ON A.uid = B.uid "
				+ " ORDER BY i_board DESC "
				+ " LIMIT ?, ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = CommonAPI.getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, sIdx);
			ps.setInt(2, showCnt);
			rs = ps.executeQuery();
						
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String regDateTime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				String nm = rs.getString("nm");
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setRegDateTime(regDateTime);
				vo.setCnt(cnt);
				vo.setNm(nm);
				
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return list;
	}
	
	public static int getTotalPagingCnt(int showCnt) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT CEIL(COUNT(*) / ?) FROM t_board ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, showCnt);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		
		return result;
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
	
	public static void insertComment(CommentVo vo) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = " INSERT INTO t_comment " + 
					   " (i_board, cmt, uid) " + 
				       " VALUES " +
				       " (?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, vo.getI_board());
			ps.setString(2, vo.getCmt());
			ps.setString(3,  vo.getUid());
			ps.execute();
			
		} catch (Exception e) {		
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
	}
	
	//댓글 리스트 가져오기
	public static List<CommentVo> getCommentList(int i_board) {
		List<CommentVo> list = new ArrayList();
		
		String query = " SELECT * " + 
				"FROM t_comment " + 
				"WHERE i_board = ? " + 
				"ORDER BY i_comment DESC ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, i_board);	
			
			rs = ps.executeQuery();
						
			while (rs.next()) {
				CommentVo vo = new CommentVo();				
				vo.setI_comment(rs.getInt("i_comment"));
				vo.setCmt(rs.getString("cmt"));
				vo.setR_datetime(rs.getString("r_datetime"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static void delComment(int i_cmt) {
		Connection con = null;
		PreparedStatement ps = null;

		String query = " DELETE FROM t_comment WHERE i_comment = ? ";

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, i_cmt);
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(con, ps);
		}
	}	
}







