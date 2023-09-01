package com.jdbcex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

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
	public AuthorDao() {}

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
	
	// 작가 등록
	public int authorInsert(String authorName, String authorDesc) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO author "
						 + "VALUES(seq_author_id.nextval, ?, ?) ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, authorName);
			pstmt.setString(2, authorDesc);
			
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
	
	// 작가 삭제
	public int authorDelete(int authorId) {
		
		int count = -1;
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "DELETE FROM author ";
			query += " WHERE author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, authorId);
			
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
	
	// 작가 전체 리스트
	public List<AuthorVo> authorSelect() {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT author_id ";
			query += "		 ,author_name ";
			query += "		 ,author_desc ";
			query += "  FROM author ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩 --> 물음표 매칭 x
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			while(rs.next()) {
				int authorId = rs.getInt(1);
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);

				AuthorVo authorVo = new AuthorVo();
				authorVo.setAuthorId(authorId);
				authorVo.setAuthorName(authorName);
				authorVo.setAuthorDesc(authorDesc);
				
				authorList.add(authorVo);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return authorList;
	}
	
	// 작가 한 명 조회
	public AuthorVo authorSelectOne(int authorId) {
		
		AuthorVo authorVo = new AuthorVo();
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT author_id ";
			query += "		 ,author_name ";
			query += "		 ,author_desc ";
			query += "  FROM author ";
			query += " WHERE author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩 --> 물음표 매칭 x
			pstmt.setInt(1, authorId);
			
			// 실행
			rs = pstmt.executeQuery();
			
			// 4. 결과처리
			if(rs.next()) {
				String authorName = rs.getString(2);
				String authorDesc = rs.getString(3);

				authorVo.setAuthorId(authorId);
				authorVo.setAuthorName(authorName);
				authorVo.setAuthorDesc(authorDesc);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}
		
		this.close();
		
		return authorVo;
	}
	
	// 작가 수정
	public int authorUpdate(int authorId, String authorDesc) {
		
		int count = -1;
		
		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE author ";
			query += "   SET author_desc = ? ";
			query += " WHERE author_id = ? ";
			
			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, authorDesc);
			pstmt.setInt(2, authorId);
			
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
