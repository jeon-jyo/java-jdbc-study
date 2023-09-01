package com.jdbcex.ex01;

import java.sql.Date;

public class BookVo {

	// 필드
	private int bookId;
	private String title;
	private String pubs;
	private Date pubDate;
	private AuthorVo authorId;
	
	// 생성자
	public BookVo() {}

	public BookVo(int bookId, String title, String pubs, Date pubDate, AuthorVo authorId) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pubDate = pubDate;
		this.authorId = authorId;
	}

	// 메서드 - getter/setter
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public AuthorVo getAuthorId() {
		return authorId;
	}

	public void setAuthorId(AuthorVo authorId) {
		this.authorId = authorId;
	}

	// 메서드 - 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pubDate=" + pubDate
				+ ", authorId=" + authorId + "]";
	}
	
}
