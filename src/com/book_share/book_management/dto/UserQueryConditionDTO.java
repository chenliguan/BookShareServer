package com.book_share.book_management.dto;

import com.book_share.book_management.util.UserSchool;

/**
 * @description 用户客户端查询DTO
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public class UserQueryConditionDTO {

	// 用户账号（手机号码）
	private Long userId;
	// 密码
	private String passWord;
	// 用户名称
	private String userName;
	// 用户学校
	private UserSchool userSchool;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserSchool getUserSchool() {
		return userSchool;
	}

	public void setUserSchool(UserSchool userSchool) {
		this.userSchool = userSchool;
	}

	@Override
	public String toString() {
		return "UserQueryConditionDTO [userId=" + userId + ", passWord="
				+ passWord + ", userName=" + userName + ", userSchool="
				+ userSchool + "]";
	}

}
