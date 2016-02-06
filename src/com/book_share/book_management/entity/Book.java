package com.book_share.book_management.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.book_share.book_management.util.IsBorrowed;
import com.book_share.core.entity.BaseDomainEntity;

/**
 * @description 图书类
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12 
 * @version 1.0
 */
@Entity
@Table(name = "t_book")
public class Book extends BaseDomainEntity {
	
	// id
	protected Long id;

	// 书籍编号 
	private String bookCode;
	// 书籍名称
	private String bookName;
	// 书籍作者
	private String bookAuthor;
	// 书籍类别
	private String bookCollections;
	// 出版社名称
	private String publisherName;
	// 出版社日期
	private Date publisherDate;
	// 照片链接
	private String photo;
	// 图书简介
	private String bookProfile;
	// 是否被借
	private IsBorrowed isBorrowed;
	
	// 关联关系
	private User user;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}
	
	public String getBookCode() {
		return bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public String getBookCollections() {
		return bookCollections;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public Date getPublisherDate() {
		return publisherDate;
	}

	public String getPhoto() {
		return photo;
	}

	public String getBookProfile() {
		return bookProfile;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookCollections(String bookCollections) {
		this.bookCollections = bookCollections;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public void setPublisherDate(Date publisherDate) {
		this.publisherDate = publisherDate;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setBookProfile(String bookProfile) {
		this.bookProfile = bookProfile;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Enumerated(EnumType.STRING)
	public IsBorrowed getIsBorrowed() {
		return isBorrowed;
	}

	public void setIsBorrowed(IsBorrowed isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

}
