package com.book_share.book_management.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.book_share.book_management.entity.User;
import com.book_share.book_management.util.UserSchool;

/**
 * @description 用户DTO
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public class UserDTO {

	// 后台数据库 返回 数据 到前端：数据拆分 find查询用例
	static public void Entity2Dto(User entity, UserDTO dto) {
		if (entity != null) {
			BeanUtils.copyProperties(entity, dto);

		}
	}

	// 前端表单数据 持久化/更新 后台数据库：保存与更新 用例
	static public void Dto2Entity(UserDTO dto, User entity) {
		if (dto != null) {
			BeanUtils.copyProperties(dto, entity);

		}
	}

	protected String creater;
	protected Date createrTime;

	protected String lastUpdater;
	protected Date lastUpdateTime;

	protected String remake;

	// 用户账号（手机号码）
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

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getBitmap() {
		return bitmap;
	}

	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "UserDTO [creater=" + creater + ", createrTime=" + createrTime
				+ ", lastUpdater=" + lastUpdater + ", lastUpdateTime="
				+ lastUpdateTime + ", remake=" + remake + ", userId=" + userId
				+ ", passWord=" + passWord + ", userName=" + userName
				+ ", userSchool=" + userSchool + ", classes=" + classes
				+ ", adress=" + adress + ", bitmap=" + bitmap
				+ ", userProfile=" + userProfile + "]";
	}

}
