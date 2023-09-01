package com.jdbcex.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectOne {

	public static void main(String[] args) {
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
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
			int no = 9;
			String query = "";
			query += "SELECT b.book_id ";
			query += "		 ,b.title ";
			query += "		 ,b.pubs ";
			query += "		 ,b.pub_date ";
			query += "		 ,a.author_id ";
			query += "		 ,a.author_name ";
			query += "		 ,a.author_desc ";
			query += "  FROM book b, author a ";
			query += " WHERE b.author_id = a.author_id ";
			query += "   AND b.book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩 --> 물음표 매칭 x
			pstmt.setInt(1, no);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			if(rs.next()) {
				int bookId = rs.getInt(1);
				String bookTitle = rs.getString(2);
				String bookPubs = rs.getString(3);
				Date bookPubDate = rs.getDate(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);
				
				/* ========== */
				
				AuthorVo authorVo = new AuthorVo();
				authorVo.setAuthorId(authorId);
				authorVo.setAuthorName(authorName);
				authorVo.setAuthorDesc(authorDesc);
				
				BookVo bookVo = new BookVo();
				bookVo.setBookId(bookId);
				bookVo.setTitle(bookTitle);
				bookVo.setPubs(bookPubs);
				bookVo.setPubDate(bookPubDate);
				bookVo.setAuthorId(authorVo);
				
				bookList.add(bookVo);
			}
			
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
			
			for(int i = 0; i < bookList.size(); i++) {
				System.out.println("book_id : " + bookList.get(i).getBookId()
						+ ", title : " + bookList.get(i).getTitle()
						+ ", pubs : " + bookList.get(i).getPubs()
						+ ", pub_date : " + bookList.get(i).getPubDate()
						+ ", author : " + bookList.get(i).getAuthorId());
			}
			
		}
		
	}
}
