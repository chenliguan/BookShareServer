package com.book_share.book_management.dao;

import com.book_share.book_management.dto.UserQueryConditionDTO;
import com.book_share.book_management.entity.User;
import com.book_share.core.dao.IBaseDao;
import com.book_share.core.util.Page;

/**
 * @description 用户Dao层接口
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public interface IUserDao extends IBaseDao<User> {

	/**
	 * @description 检索用户
	 * @param queryConditionDTO
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return
	 */
	public Page<User> findUserPage(UserQueryConditionDTO queryConditionDTO, int start, int limit,
			String sort, String dir);
}
