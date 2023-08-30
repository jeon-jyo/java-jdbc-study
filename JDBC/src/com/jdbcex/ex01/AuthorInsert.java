package com.jdbcex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";			/* ip:port:구분자 -> 로컬:오라클포트:구분자 */
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String name = "남궁성";
			String desc = "개발자";
			String query = "INSERT INTO author "
						 + "VALUES(seq_author_id.nextval, ?, ?) ";			/* 쿼리문을 문자열로 */
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			
			// 실행
			int count = pstmt.executeUpdate();							/* insert, update, delete */
			
			// 4. 결과처리
			System.out.println(count + "건 등록되었습니다.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			
			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				
			} catch (SQLException e) {
				System.out.println("error : " + e);
			}
		}
		
	}
}
