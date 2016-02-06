package com.book_share.book_management.service;

import com.book_share.book_management.dto.UserDTO;
import com.book_share.book_management.dto.UserQueryConditionDTO;
import com.book_share.core.util.Page;

/**
 * @description 用户业务服务层接口
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public interface IUserService {

	/**
	 * @description 检索用户信息
	 * @param queryConditionDTO
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return
	 */
	public Page<UserDTO> findUserPage(UserQueryConditionDTO queryConditionDTO,
			int start, int limit, String sort, String dir);
	
//	/**
//	 * @description 根据Id查看用户明细信息，返回对象
//	 * @param userDTO
//	 * @return
//	 */
//	public UserDTO findUserById(UserDTO userDTO);
	
	/**
	 * @description 新建用户资料（注册用户）
	 * @param userDTO
	 */
	public void createUser(UserDTO userDTO);

	/**
	 * @description 更新用户资料（修改密码）
	 * @param userDTO
	 */
	public void updateUser(UserDTO userDTO);

}
