package com.jdbcex.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String passWord = "webdb";
	
	// 생성자
	public BookDao() {}

	// 메서드 - getter/setter

	// 메서드 - 일반
	private void getConnection() {
				
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, passWord);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
	}
	
	private void close() {
		
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
	
	// 책 등록
	public int bookInsert(String bookTitle, String bookPubs, int authorId) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO book "
						 + "VALUES(seq_book_id.nextval, ?, ?, sysdate, ?) ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, bookPubs);
			pstmt.setInt(3, authorId);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4. 결과처리
//			System.out.println(count + "건 등록되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();

		return count;
	}
	
	// 책 삭제
	public int bookDelete(int bookId) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "DELETE FROM book ";
			query += " WHERE book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, bookId);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4. 결과처리
//			System.out.println(count + "건 삭제되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return count;
	}
	
	// 책 전체 리스트
	public List<BookVo> bookSelect() {
		
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
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
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩 --> 물음표 매칭 x
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			while(rs.next()) {
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
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return bookList;
	}
	
	// 책 한 권 조회
	public BookVo bookSelectOne(int bookId) {
		
		BookVo bookVo = new BookVo();
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
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
			pstmt.setInt(1, bookId);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			if(rs.next()) {
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

				bookVo.setBookId(bookId);
				bookVo.setTitle(bookTitle);
				bookVo.setPubs(bookPubs);
				bookVo.setPubDate(bookPubDate);
				bookVo.setAuthorId(authorVo);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return bookVo;
	}
	
	// 책 수정
	public int bookUpdate(int bookId, String pubs) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE book ";
			query += "   SET pubs = ? ";
			query += " WHERE book_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, pubs);
			pstmt.setInt(2, bookId);
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 4. 결과처리
//			System.out.println(count + "건 수정되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return count;
	}
	
}
