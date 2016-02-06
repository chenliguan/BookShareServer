package com.book_share.book_management.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.book_share.book_management.dto.UserQueryConditionDTO;
import com.book_share.book_management.entity.User;
import com.book_share.core.dao.BaseDao;
import com.book_share.core.util.Page;

/**
 * @description 用户Dao层
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Repository
public class UserDao extends BaseDao<User> implements IUserDao {

	/**
	 * @description 检索用户
	 * @param queryConditionDTO
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<User> findUserPage(UserQueryConditionDTO queryConditionDTO,
			int start, int limit, String sort, String dir) {
		// 1.拼接 统计满足查询条件结果集总数 HQL
		StringBuilder countHQLStringBuilder = new StringBuilder();
		countHQLStringBuilder.append("SELECT count(*) FROM User user");
		countHQLStringBuilder
				.append(" WHERE (:userId IS NULL OR LOWER(user.userId) LIKE :userId)");
		countHQLStringBuilder
				.append(" AND (:passWord IS NULL OR LOWER(user.passWord) LIKE :passWord)");
		countHQLStringBuilder
				.append(" AND (:userName IS NULL OR LOWER(user.userName) LIKE :userName)");
		countHQLStringBuilder
				.append(" AND (:userSchool IS NULL OR LOWER(user.userSchool) LIKE :userSchool)");

		// 2.通过toString方法变为完整 String
		String countHQLString = countHQLStringBuilder.toString();
		Query countQuery = sessionFactory.getCurrentSession().createQuery(
				countHQLString);
		countQuery.setParameter("userId",
				queryConditionDTO.getUserId() == null ? null
						: queryConditionDTO.getUserId());
		countQuery.setParameter("passWord",
				queryConditionDTO.getPassWord() == null ? null
						: queryConditionDTO.getPassWord());
		countQuery.setParameter("userName",
				queryConditionDTO.getUserName() == null ? null
						: queryConditionDTO.getUserName());
		countQuery.setParameter("userSchool",
				queryConditionDTO.getUserSchool() == null ? null
						: queryConditionDTO.getUserSchool());

		// 3.执行查询 得到满足条件的总记录数
		long totalCount = (Long) countQuery.list().get(0);

		// 4.如果没有满足条件的纪录 则返回空页面对象
		if (totalCount < 1) {
			return new Page<User>();
		}
		// ------------------------------------------------------------------------------------
		// 4.如果有满足条件的纪录 则进行结果集查询 并返回有 结果集合和总记录数 的 页面对象
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM User user");
		hqlStringBuilder
				.append(" WHERE (:userId IS NULL OR LOWER(user.userId) LIKE :userId)");
		hqlStringBuilder
				.append(" AND (:passWord IS NULL OR LOWER(user.passWord) LIKE :passWord)");
		hqlStringBuilder
				.append(" AND (:userName IS NULL OR LOWER(user.userName) LIKE :userName)");
		hqlStringBuilder
				.append(" AND (:userSchool IS NULL OR LOWER(user.userSchool) LIKE :userSchool)");

		hqlStringBuilder.append(" Order By user." + sort + " " + dir);
		String hqlString = hqlStringBuilder.toString();

		Query query = sessionFactory.getCurrentSession().createQuery(hqlString);
		query.setParameter("userId",
				queryConditionDTO.getUserId() == null ? null
						: queryConditionDTO.getUserId());
		query.setParameter("passWord",
				queryConditionDTO.getPassWord() == null ? null
						: queryConditionDTO.getPassWord());
		query.setParameter("userName",
				queryConditionDTO.getUserName() == null ? null
						: queryConditionDTO.getUserName());
		query.setParameter("userSchool",
				queryConditionDTO.getUserSchool() == null ? null
						: queryConditionDTO.getUserSchool());

		// 进行分页查询
		List<User> lists = query.setFirstResult(start).setMaxResults(limit)
				.list();
		return new Page<User>(lists, totalCount, limit);
	}

}
