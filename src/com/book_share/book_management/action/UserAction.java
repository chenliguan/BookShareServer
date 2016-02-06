package com.book_share.book_management.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.book_share.book_management.dto.UserDTO;
import com.book_share.book_management.dto.UserQueryConditionDTO;
import com.book_share.book_management.service.IUserService;
import com.book_share.core.action.BaseAction;
import com.book_share.core.util.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @description UserAction
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Controller
public class UserAction extends BaseAction implements ModelDriven<UserDTO>,
		ParameterAware {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;

	// 声明返回前端的数据 (Getter)
	private long totalCount = 0;

	// queryDTO提供get,set内部属性
	private UserQueryConditionDTO queryDTO = new UserQueryConditionDTO();

	private List<UserDTO> ecList = new ArrayList<UserDTO>();

	private UserDTO userDTO = new UserDTO();

	public void setQueryDTO(UserQueryConditionDTO queryDTO) {
		this.queryDTO = queryDTO;
	}

	@Override
	public UserDTO getModel() {
		return userDTO;
	}

	private Map<String, String[]> parameters;

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;

	}

	// 为BaseAction中Ajax交互属性 提供get方法（才可以正确序列化为JSON）
	public boolean getSuccess() {
		return success;
	}

	public boolean getFailure() {
		return failure;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 登录
	 * http://localhost:8080/bookShare/loginAction.action?userId=13751338740&passWord=123456
	 */
	public String login() {
		try {
		    // 通过userId查询出用户
			queryDTO.setUserId(userDTO.getUserId());
			Page<UserDTO> page = userService.findUserPage(queryDTO, start,
					limit, sort, dir);
			UserDTO userD = page.getLists().get(0);

			if (!userD.equals(null)) {
				// 存在id
				if (userD.getPassWord().equals(userDTO.getPassWord())) {
					responseSuccessAndMessage("登录成功");
				} else {
					responseFailureAndMessage("密码错误，请重新输入密码");
				}
			} else {
				responseFailureAndMessage("不存在该用户，请注册后再登录");
			}
		} catch (Exception e) {
			// failure:true,success:false,message:e.getMessage()
			responseFailureAndMessage("加载失败");
		}
		return SUCCESS;
	}

	/**
	 * 注册 http://localhost:8080/bookShare/findUserAction.action?userId=
	 * 13751338740
	 */
	public String register() {
		try {
			UserQueryConditionDTO queryD = new UserQueryConditionDTO();
			// 不设定条件查询所有用户数据
			Page<UserDTO> page = userService.findUserPage(queryD, start, limit,
					sort, dir);
			String[] userIds = null;
			try {
				// 参数中的userId
				userIds = parameters.get("userId");
				for (UserDTO e : page.getLists()) {
					if (!e.getUserId().equals(userIds[0])) {
						// 创建用户
						userService.createUser(userDTO);
						responseSuccessAndMessage("注册成功");
						break;
					}
				}
			} catch (Exception e) {
				// failure:true,success:false,message:e.getMessage()
				responseFailureAndMessage("该用户已经注册，请直接登录");
			}
		} catch (Exception e) {
			// failure:true,success:false,message:e.getMessage()
			responseFailureAndMessage("加载失败");
		}
		return SUCCESS;
	}

	/**
	 * 根据各种条件查看用户明细信息，返回集合
	 * http://localhost:8080/bookShare/findUserAction.action?userId=13751338740
	 */
	public String findUserBy() {
		try {
			// bookDTO捕获的数据赋值给queryDTO
			BeanUtils.copyProperties(userDTO, queryDTO);

			Page<UserDTO> page = userService.findUserPage(queryDTO, start,
					limit, sort, dir);
			// 前端Grid需要数据
			totalCount = page.getTotalCounts();
			ecList = page.getLists();
			responseSuccessAndMessage("请求用户明细成功");
		} catch (Exception e) {
			responseFailureAndMessage("加载失败");// failure:true,success:false,message:e.getMessage()
		}
		return SUCCESS;
	}

	/**
	 * 更新用户资料（修改密码）
	 * http://localhost:8080/bookShare/updateUserAction.action?userId=13751338741&oldPass=123456&creater=%E5%B0%8F%E6%98%8E
	 */
	public String updateUser() {
		try {
		    // 通过userId查询出用户
			queryDTO.setUserId(userDTO.getUserId());
			Page<UserDTO> page = userService.findUserPage(queryDTO, start,
					limit, sort, dir);
			UserDTO userD = page.getLists().get(0);
			// 取出旧密码
			String[] oldPass = parameters.get("oldPass");
			
			if (!userD.equals(null)) {
				if(userD.getPassWord().equals(oldPass[0])) {
					// 存在id，密码匹配， 更新用户
					userService.updateUser(userDTO);
					responseSuccessAndMessage("更新成功");
				} else {
					responseFailureAndMessage("密码错误");
				}
			} else {
				responseFailureAndMessage("不存在该用户");
			}
		} catch (Exception e) {
			// failure:true,success:false,message:e.getMessage()
			responseFailureAndMessage("加载失败");
		}

		return SUCCESS;
	}
	
	/**
	 * 新建用户资料
	 * http://localhost:8080/bookShare/createUserAction.action?userId
	 * =13751338743 {"failure":false,"message":"","success":true}
	 */
	public String createUser() {
		try {
			userService.createUser(userDTO);
			responseSuccess();
		} catch (Exception e) {
			responseFailureAndMessage(e.getMessage());
		}

		return SUCCESS;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public UserQueryConditionDTO getQueryDTO() {
		return queryDTO;
	}

	public List<UserDTO> getEcList() {
		return ecList;
	}

}
