package com.javaex.phonebook03;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

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
	
	// 등록
	public int personInsert(String name, String hp, String company) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "INSERT INTO person "
					+ "VALUES(seq_person_id.NEXTVAL, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, name);
			pstmt.setString(2, hp);
			pstmt.setString(3, company);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 삭제
	public int personDelete(int no) {

		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "DELETE FROM person ";
			query += " WHERE id = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setInt(1, no);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 한 명 조회
	public PersonVo personSelectOne(int no) {

		PersonVo personVo = new PersonVo();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT id ";
			query += "		 ,name ";
			query += "		 ,hp ";
			query += "		 ,company ";
			query += "  FROM person ";
			query += " WHERE id = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩 --> 물음표 매칭 x
			pstmt.setInt(1, no);

			// 실행
			rs = pstmt.executeQuery();

			// 4. 결과처리
			while(rs.next()) {
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);

				personVo.setId(no);
				personVo.setName(name);
				personVo.setHp(hp);
				personVo.setCompany(company);
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return personVo;
	}
	
	// 수정
	public int personUpdate(String updateStr , int columnNum, int no) {

		int count = -1;

		this.getConnection();

		String columnStr = null;
		if(columnNum == 1) {
			columnStr = "name";
		} else if(columnNum == 2) {
			columnStr = "hp";
		} else if(columnNum == 3) {
			columnStr = "company";
		}

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "UPDATE person ";
			query += "   SET " + columnStr + " = ? ";
			query += " WHERE id = ? ";

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩
			pstmt.setString(1, updateStr);
			pstmt.setInt(2, no);

			// 실행
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return count;
	}
	
	// 리스트 + 검색
	public List<PersonVo> personSelect(String word) {

		List<PersonVo> personList = new ArrayList<PersonVo>();

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "SELECT id ";
			query += "		 ,name ";
			query += "		 ,hp ";
			query += "		 ,company ";
			query += "  FROM person ";
			/**********************************************************************/
			if(!word.equals("")) {	// word가 ""가 아니면 ==> word가 있으면 검색
				query += " WHERE name LIKE '%' || ? || '%' ";
//				query += " WHERE name LIKE ? ";
			}

			pstmt = conn.prepareStatement(query);
			// ?를 바인딩**********************************************************************
			if(!word.equals("")) {	// word가 ""가 아니면 ==> word가 있으면 검색
				pstmt.setString(1, word);
//				pstmt.setString(1, "%" + word + "%");
			}

			// 실행
			rs = pstmt.executeQuery();

			// 4. 결과처리
			while(rs.next()) {

				int id = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String company = rs.getString(4);

				PersonVo personVo = new PersonVo();
				personVo.setId(id);
				personVo.setName(name);
				personVo.setHp(hp);
				personVo.setCompany(company);

				personList.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error : " + e);
		}

		this.close();

		return personList;
	}

}
