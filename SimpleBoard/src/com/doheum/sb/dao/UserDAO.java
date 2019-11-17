package com.doheum.sb.dao;

import static com.doheum.sb.dao.CommonAPI.getCon;
import static com.doheum.sb.dao.CommonAPI.close;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.doheum.sb.UserVO;


public class UserDAO {

	public static void insertUser(UserVO vo) {
		String query = " INSERT INTO t_user (uid, upw, nm) " 
							+ " VALUES (?, ?, ?) ";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getCon();
			ps = con.prepareStatement(query);
			ps.setString(1, vo.getUid());
			ps.setString(2, vo.getUpw());
			ps.setString(3, vo.getNm());
			
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}

	}
}
