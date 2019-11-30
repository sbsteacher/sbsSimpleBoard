package com.doheum.sb.dao;

import static com.doheum.sb.dao.CommonAPI.close;
import static com.doheum.sb.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.doheum.sb.vo.BoardVO;
import com.doheum.sb.vo.CommentVO;
import com.doheum.sb.vo.FavoriteVO;
import com.doheum.sb.vo.PrevNextVO;

public class BoardDAO {	
	// 글쓰기
	public static int insertBoard(BoardVO vo) {
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
	public static List<BoardVO> getBoardList(int sIdx, int showCnt) {
		List<BoardVO> list = new ArrayList();
		
		String query = " SELECT " + 
				"  A.i_board, A.title, A.regdatetime, A.cnt, C.nm " + 
				" , ifnull(B.cnt, 0) as favorite_cnt " + 
				" FROM t_board A " + 
				" LEFT JOIN ( " + 
				"	SELECT i_board, COUNT(i_board) AS cnt " + 
				"	FROM t_favorite " + 
				"	GROUP BY i_board " + 
				" ) B " + 
				" ON A.i_board = B.i_board " + 
				" INNER JOIN t_user C " + 
				" ON A.uid = C.uid " + 
				" ORDER BY A.i_board DESC " +
				" LIMIT ?, ? ";

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
				BoardVO vo = new BoardVO();
				int i_board = rs.getInt("i_board");
				String title = rs.getString("title");
				String regDateTime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				String nm = rs.getString("nm");
				int favorite = rs.getInt("favorite_cnt");
				
				vo.setI_board(i_board);
				vo.setTitle(title);
				vo.setRegDateTime(regDateTime);
				vo.setCnt(cnt);
				vo.setNm(nm);
				vo.setFavorite(favorite);
				
				list.add(vo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return list;
	}
	
	public static int getTotalPagingCnt(final int showCnt) {
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
	public static BoardVO getBoardDetail(final BoardVO param) {
		BoardVO vo = new BoardVO();
		String query = " SELECT A.*, B.nm, IFNULL(C.i_board, 0) AS favorite " + 
				" FROM t_board A " + 
				" INNER JOIN t_user B " + 
				" ON A.uid = B.uid " +
				" LEFT JOIN t_favorite C "	 +
				" ON A.i_board = C.i_board " +
				" AND C.uid = ? " +
				" WHERE A.i_board = ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getCon();
			ps = con.prepareStatement(query);			
			ps.setString(1, param.getUid());
			ps.setInt(2, param.getI_board());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regDateTime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				String uid = rs.getString("uid"); //detail.jsp 에서 수정, 삭제 버튼 나타나게 할지 안할지 결정하기 위한 값
				String nm = rs.getString("nm");
				int favorite = rs.getInt("favorite");
				
				vo.setI_board(param.getI_board());
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDateTime(regDateTime);
				vo.setCnt(cnt);
				vo.setUid(uid);
				vo.setNm(nm);
				vo.setFavorite(favorite);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return vo;
	}
	
	
	public static PrevNextVO getPrevNext(final BoardVO param) {
		PrevNextVO vo = null;
		
		String sql = " SELECT (SELECT ifnull(MAX(i_board), 0) FROM t_board WHERE i_board < ?) as next " +				
				" , (SELECT ifnull(MIN(i_board), 0) FROM t_board WHERE i_board > ?) as prev FROM dual ";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_board());
			rs = ps.executeQuery();
			if(rs.next()) {
				int prev = rs.getInt("prev");
				int next = rs.getInt("next");
				vo = new PrevNextVO(prev, next);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return vo;
	}
	

	// 글삭제
	public static int delBoard(BoardVO param) {
		int result = 0; // 디폴트 삭제 못 했다

		Connection con = null;
		PreparedStatement ps = null;

		String query = " DELETE FROM t_board WHERE i_board = ? AND uid = ? ";

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, param.getI_board());
			ps.setString(2, param.getUid());
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
	public static int modBoard(BoardVO vo) {
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
	
	public static void insertComment(CommentVO vo) {
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
	public static List<CommentVO> getCommentList(int i_board) {
		List<CommentVO> list = new ArrayList();
		
		String query = " SELECT A.i_comment, A.cmt, A.r_datetime, B.uid, B.nm " 
				+	" FROM t_comment A "
				+ " INNER JOIN t_user B "
				+ " ON A.uid = B.uid "
				+ " WHERE i_board = ?  "
				+ " ORDER BY i_comment DESC  ";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, i_board);	
			
			rs = ps.executeQuery();
						
			while (rs.next()) {
				CommentVO vo = new CommentVO();				
				vo.setI_comment(rs.getInt("i_comment"));
				vo.setCmt(rs.getString("cmt"));
				vo.setR_datetime(rs.getString("r_datetime"));
				vo.setNm(rs.getString("nm"));
				vo.setUid(rs.getString("uid"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return list;
	}
	
	public static int delComment(CommentVO vo) {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;

		String query = " DELETE FROM t_comment WHERE i_comment = ? AND uid = ? ";

		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setInt(1, vo.getI_comment());
			ps.setString(2, vo.getUid());
			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			close(con, ps);
		}
		
		return result;
	}	
	
	//해당 글에 좋아요 되어 있는지 없는지 리턴 해주는 메소드
	public static boolean isFavorite(FavoriteVO param) {
		boolean result = false;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_favorite WHERE i_board = ? AND uid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setString(2,  param.getUid());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}
	
	public static void regdelFavorite(boolean isFavorite, FavoriteVO param) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_favorite (i_board, uid) VALUES (?, ?) ";
		
		if(isFavorite) {
			sql = " DELETE FROM t_favorite WHERE i_board = ? AND uid = ? ";
		}
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setString(2, param.getUid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
	}
}







