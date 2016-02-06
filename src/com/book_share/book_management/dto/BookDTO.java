package com.book_share.book_management.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.book_share.book_management.entity.Book;
import com.book_share.book_management.entity.User;
import com.book_share.book_management.util.IsBorrowed;

/**
 * @description 书籍DTO
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public class BookDTO {

	protected Long id;

	protected String creater;
	protected Date createrTime;

	protected String lastUpdater;
	protected Date lastUpdateTime;

	protected String remake;

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
	// 1.数据传输（简化实体模型：把关联对象的数据 简化为同一层次）
	private Long userId; // 同一个Grid，Form都是不可以同时存在两个相同的列id
	private String userName;

	// 3.维护关联关系
	// 后台数据库 返回 数据 到前端：数据拆分 find查询用例
	static public void Entity2Dto(Book entity, BookDTO dto) {
		if (entity != null) {
			BeanUtils.copyProperties(entity, dto);
			// 拆分关联对象数据 到 dto
			if (entity.getUser() != null) {
				// 将User表的userId赋值给BookDTO的userId
				dto.setUserId(entity.getUser().getUserId());
				dto.setUserName(entity.getUser().getUserName());
			}

		}
	}

	// 前端表单数据 持久化/更新 后台数据库：保存与更新 用例
	static public void Dto2Entity(BookDTO dto, Book book) {
		if (dto != null) {
			BeanUtils.copyProperties(dto, book);
			// 对象 关联关系维护
			if (dto.getUserId() != null) {
				User user = new User();
				// 将BookDTO表的userId赋值给User的userId
				user.setUserId(dto.getUserId());

				book.setUser(user);
			}
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public String getLastUpdater() {
		return lastUpdater;
	}

	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
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

	public String getBookCollections() {
		return bookCollections;
	}

	public void setBookCollections(String bookCollections) {
		this.bookCollections = bookCollections;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public Date getPublisherDate() {
		return publisherDate;
	}

	public void setPublisherDate(Date publisherDate) {
		this.publisherDate = publisherDate;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getBookProfile() {
		return bookProfile;
	}

	public void setBookProfile(String bookProfile) {
		this.bookProfile = bookProfile;
	}

	public IsBorrowed getIsBorrowed() {
		return isBorrowed;
	}

	public void setIsBorrowed(IsBorrowed isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", creater=" + creater + ", createrTime="
				+ createrTime + ", lastUpdater=" + lastUpdater
				+ ", lastUpdateTime=" + lastUpdateTime + ", remake=" + remake
				+ ", bookCode=" + bookCode + ", bookName=" + bookName
				+ ", bookAuthor=" + bookAuthor + ", bookCollections="
				+ bookCollections + ", publisherName=" + publisherName
				+ ", publisherDate=" + publisherDate + ", photo=" + photo
				+ ", bookProfile=" + bookProfile + ", isBorrowed=" + isBorrowed
				+ ", userId=" + userId + ", userName=" + userName + "]";
	}
}
