package com.demo.book_management;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.book_share.book_management.dto.UserDTO;
import com.book_share.book_management.service.IUserService;
import com.book_share.book_management.util.UserSchool;
import com.demo.util.BaseTest;

/**
 * @description User测试类
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Transactional
@Rollback(value = false)
public class TestUserService extends BaseTest {

	@Autowired
	private IUserService userService;

	/**
	 * @description 测试增加用户
	 * 
	 */
	 @Test
	public void prepareTestData() {
		for (int i = 1; i < 3; i++) {

			UserDTO ec = new UserDTO();

			if (i == 1) {
				ec.setUserId(13751338740L);
				ec.setPassWord("123456");
				ec.setUserName("小邦");
				ec.setUserSchool(UserSchool.DONGGUANINSTITUTEOFTECHNOLOGY);
				ec.setClasses("软件1班");
				ec.setAdress("宿舍14513");
			} else if(i == 2){
				ec.setUserId(11111111111L);
				ec.setPassWord("123456");
				ec.setUserName("小明");
				ec.setUserSchool(UserSchool.DONGGUANVOCATIONALANDTECHNICALCOLLEGE);
				ec.setClasses("软件1班");
				ec.setAdress("宿舍14514");
			} else {
				ec.setUserId(22222222222L);
				ec.setPassWord("222222");
				ec.setUserName("小爷");
				ec.setUserSchool(UserSchool.DONGGUANINSTITUTEOFTECHNOLOGY);
				ec.setClasses("软件3班");
				ec.setAdress("宿舍14514");
			}

			ec.setCreaterTime(new Date());
			ec.setLastUpdateTime(new Date());
			ec.setCreater("小邦");

			userService.createUser(ec);
		}
	}

	/**
	 * @description 测试查询用户
	 * 
	 */
	// @Test
	public void testUserPage() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(13751338740L);

		// 查询
//		UserDTO user = userService.findUserById(userDTO);
//		System.out.println(user);
	}

	/**
	 * @description 测试更新密码 注意：需要回滚
	 */
//	@Test
//	public void testUpdateUser() {
//		UserDTO userDTO = new UserDTO();
//		userDTO.setUserId(13751338740L);
//		// 查询
//		UserDTO user = userService.findUserById(userDTO);
//		System.out.println(user);
//		// 修改
//		user.setPassWord("654321");
//		user.setRemake("小小");
//		user.setAdress("松山湖14513");
//		System.out.println(user);
//
//		userService.updateUser(user);
//	}
}
