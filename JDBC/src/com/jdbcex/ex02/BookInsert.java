package com.jdbcex.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String bookTitle = "Java의 정석";
			String bookPubs = "도우출판";
			int bookAuthorId = 7;
			String query = "INSERT INTO book "
						 + "VALUES(seq_book_id.nextval, ?, ?, sysdate, ?) ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, bookPubs);
			pstmt.setInt(3, bookAuthorId);
			
			// 실행
			int count = pstmt.executeUpdate();
			
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
