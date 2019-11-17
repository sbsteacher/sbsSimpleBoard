package com.doheum.sb.dao;

import static com.doheum.sb.dao.CommonAPI.getCon;
import static com.doheum.sb.dao.CommonAPI.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import com.doheum.sb.UserVO;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping;


public class UserDAO {

	//1: 회원가입완료, 2:중복된 아이디 존재, 3:값이 너무 깁니다. 0:나머지 에러
	public static int insertUser(UserVO vo) {
		int result = 0;
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
			
			result = ps.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 아이디가 있다!!!");
			result = 2;
		} catch(MysqlDataTruncation e) {
			System.out.println("내용이 길다!!!");
			result = 3;
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close(con, ps);
		}
		return result;
	}
}
