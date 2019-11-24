package com.doheum.sb.dao;

import static com.doheum.sb.dao.CommonAPI.close;
import static com.doheum.sb.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.doheum.sb.vo.LoginResultVO;
import com.doheum.sb.vo.UserVO;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;


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
	
	//0:에러발생, 1:로그인 성공, 2:비밀번호 틀림, 3:아이디가 없음
	public static LoginResultVO login(UserVO vo) {
		LoginResultVO result = new LoginResultVO();
				
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_user WHERE uid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getUid());
			
			rs = ps.executeQuery();
			if(rs.next()) {
				String dbUpw = rs.getString("upw");
				String dbNm = rs.getString("nm");
				
				if(vo.getUpw().equals(dbUpw)) { // 로그인 성공
					result.setResult(1);
					//비밀번호는 빼고, 이름은 넣을겁니다.
					vo.setUpw("");
					vo.setNm(dbNm);
					
					result.setVo(vo);
					
				} else { // 비밀번호 틀림
					result.setResult(2);					
				}
				
			} else { //아이디가 없음
				result.setResult(3);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		
		return result;
	}
}











