package com.book_share.book_management.dto;

import com.book_share.book_management.util.IsBorrowed;

/**
 * @description 书籍客户端查询DTO
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public class BookQueryConditionDTO {

	// 图书Id
	private Long id;
	// 书籍编号
	private String bookCode;
	// 书籍名称
	private String bookName;
	// 书籍作者
	private String bookAuthor;
	// 是否被借
	private IsBorrowed isBorrowed;
	
	// 用户Id
	private Long userId;
	// 姓名
	private String userName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public IsBorrowed getIsBorrowed() {
		return isBorrowed;
	}

	public void setIsBorrowed(IsBorrowed isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	@Override
	public String toString() {
		return "BookQueryConditionDTO [bookCode=" + bookCode + ", bookName="
				+ bookName + ", bookAuthor=" + bookAuthor + ", isBorrowed="
				+ isBorrowed + ", userId=" + userId + ", userName=" + userName
				+ "]";
	}
}
