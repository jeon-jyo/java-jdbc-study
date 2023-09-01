package com.jdbcex.ex02;

import java.util.List;

public class MainApp {

	public static void main(String[] args) {
		
		int count = -1;
		
		AuthorDao authorDao = new AuthorDao();
		BookDao bookDao = new BookDao();
		
		// 작가 등록
//		count = authorDao.authorInsert("작가1", "신인작가");
//		System.out.println(count + "건 등록되었습니다.");
//		count = authorDao.authorInsert("작가2", "신인작가");
//		System.out.println(count + "건 등록되었습니다.");
		
		// 작가 삭제
//		count = authorDao.authorDelete(11);
//		System.out.println(count + "건 삭제되었습니다.");
		
		// 작가 전체 리스트
		List<AuthorVo> authorList = authorDao.authorSelect();
		for(int i = 0; i < authorList.size(); i++) {
			System.out.println("id : " + authorList.get(i).getAuthorId()
					+ ", name : " + authorList.get(i).getAuthorName()
					+ ", desc : " + authorList.get(i).getAuthorDesc());
		}
		
		// 작가 한 명 조회
		AuthorVo author = authorDao.authorSelectOne(9);
		System.out.println("id : " + author.getAuthorId()
				+ ", name : " + author.getAuthorName()
				+ ", desc : " + author.getAuthorDesc());
		
		// 작가 수정
//		count = authorDao.authorUpdate(9, "경력작가");
//		System.out.println(count + "건 수정되었습니다.");
		
		System.out.println();
		System.out.println("============================================================");
		System.out.println();
		
		// 책 등록
//		count = bookDao.bookInsert("책 제목1", "출판사1", 9);
//		System.out.println(count + "건 등록되었습니다.");
//		count = bookDao.bookInsert("책 제목2", "출판사2", 9);
//		System.out.println(count + "건 등록되었습니다.");

		// 책 삭제
//		count = bookDao.bookDelete(11);
//		System.out.println(count + "건 삭제되었습니다.");
		
		// 책 전체 리스트
		List<BookVo> bookList = bookDao.bookSelect();
		for(int i = 0; i < bookList.size(); i++) {
			System.out.println("book_id : " + bookList.get(i).getBookId()
					+ ", title : " + bookList.get(i).getTitle()
					+ ", pubs : " + bookList.get(i).getPubs()
					+ ", pub_date : " + bookList.get(i).getPubDate()
					+ ", author_id : " + bookList.get(i).getAuthorId().getAuthorId());
		}
		
		// 책 한 권 조회
		BookVo book = bookDao.bookSelectOne(12);
		System.out.println("book_id : " + book.getBookId()
				+ ", title : " + book.getTitle()
				+ ", pubs : " + book.getPubs()
				+ ", pub_date : " + book.getPubDate()
				+ ", author_id : " + book.getAuthorId().getAuthorId());
		
		// 책 수정
//		count = bookDao.bookUpdate(12, "자유출판사");
//		System.out.println(count + "건 수정되었습니다.");
		
	}
}
