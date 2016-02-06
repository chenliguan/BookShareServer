package com.book_share.book_management.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.book_share.book_management.util.UserSchool;
import com.book_share.core.entity.BaseDomainEntity;

/**
 * @description 用户类
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12 
 * @version 1.0
 */
@Entity
@Table(name = "t_user")
public class User extends BaseDomainEntity {
	
	// id 用户账号（手机号码）
	private Long userId;
	// 密码
	private String passWord;
	// 用户名称
	private String userName;
	// 用户学校
	private UserSchool userSchool;
	// 班级
	private String classes;
	// 地址
	private String adress;
	// 头像链接
	private String bitmap;
	// 用户简介
	private String userProfile;
	
	// 关联图书
	List<Book> books = new ArrayList<Book>();

	// 用户：图书 = 1：n
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<Book> getBooks() {
		return books;
	}

	@Id
	public Long getUserId() {
		return userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getUserName() {
		return userName;
	}

	@Enumerated(EnumType.STRING)
	public UserSchool getUserSchool() {
		return userSchool;
	}

	public String getClasses() {
		return classes;
	}

	public String getAdress() {
		return adress;
	}

	public String getBitmap() {
		return bitmap;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserSchool(UserSchool userSchool) {
		this.userSchool = userSchool;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", passWord=" + passWord
				+ ", userName=" + userName + ", userSchool=" + userSchool
				+ ", classes=" + classes + ", adress=" + adress + ", bitmap="
				+ bitmap + ", userProfile=" + userProfile + ", books=" + books
				+ "]";
	}
}
