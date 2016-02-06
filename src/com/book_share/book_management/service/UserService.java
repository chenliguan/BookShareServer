package com.book_share.book_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book_share.book_management.dao.IUserDao;
import com.book_share.book_management.dto.UserDTO;
import com.book_share.book_management.dto.UserQueryConditionDTO;
import com.book_share.book_management.entity.User;
import com.book_share.book_management.util.CopyProperties;
import com.book_share.core.util.Page;

/**
 * @description 用户业务服务层实现
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Transactional
@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	/**
	 *  检索用户信息
	 */
	@Override
	public Page<UserDTO> findUserPage(UserQueryConditionDTO queryConditionDTO,
			int start, int limit, String sort, String dir) {
		List<UserDTO> dtoList = null;
		Page<User> userPage = userDao.findUserPage(queryConditionDTO,
				start, limit, sort, dir);

		if (userPage != null) {
			dtoList = new ArrayList<UserDTO>();
			for (User c : userPage.getLists()) {
				UserDTO dto = new UserDTO();
				UserDTO.Entity2Dto(c, dto);

				dtoList.add(dto);
			}
		}
		return new Page<UserDTO>(dtoList, userPage.getTotalCounts(), limit);
	}

	/**
	 * 新建用户资料（注册用户）
	 */
	@Override
	public void createUser(UserDTO userDTO) {
		User user = new User();
		UserDTO.Dto2Entity(userDTO, user);
		userDao.save(user);
	}

	/**
	 *  更新用户资料（修改密码）  
	 */
	@Override
	public void updateUser(UserDTO userDTO) {
		//User
		User temp = (User) userDao.getById(userDTO.getUserId());
		//temp 持久化 与数据库同步 
		if(temp!=null){
			//前端的对象数据覆盖到持久化状态的对象中再更新  userDTO->temp
//			BeanUtils.copyProperties(userDTO, temp);
			CopyProperties.copyPropertiesIgnoreNull(userDTO, temp);
			//关联关系
			userDao.update(temp);
		}
	}

}
