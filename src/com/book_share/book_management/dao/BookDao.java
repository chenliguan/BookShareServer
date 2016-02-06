package com.book_share.book_management.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.book_management.entity.Book;
import com.book_share.core.dao.BaseDao;
import com.book_share.core.util.Page;

/**
 * @description 图书Dao层
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Repository
public class BookDao extends BaseDao<Book> implements IBookDao {

	/**
	 * @description 检索图书
	 * @param customerName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<Book> findBookPageByUser(
			BookQueryConditionDTO queryConditionDTO, int start, int limit,
			String sort, String dir) {
		// 1.拼接 统计满足查询条件结果集总数 HQL
		StringBuilder countHQLStringBuilder = new StringBuilder();
		countHQLStringBuilder.append("SELECT count(*) FROM Book book");
		if (queryConditionDTO.getUserId() != null) {
			countHQLStringBuilder
					.append(" WHERE (:userId IS NULL OR LOWER(book.user.userId) = :userId)");
			countHQLStringBuilder
					.append(" AND (:bookCode IS NULL OR LOWER(book.bookCode) LIKE :bookCode)");
		} else {
			countHQLStringBuilder
					.append(" WHERE (:bookCode IS NULL OR LOWER(book.bookCode) LIKE :bookCode)");
		}
		countHQLStringBuilder
				.append(" AND (:bookName IS NULL OR LOWER(book.bookName) LIKE :bookName)");
		countHQLStringBuilder
				.append(" AND (:bookAuthor IS NULL OR LOWER(book.bookAuthor) LIKE :bookAuthor)");
		countHQLStringBuilder
				.append(" AND (:isBorrowed IS NULL OR LOWER(book.isBorrowed) LIKE :isBorrowed)");
		countHQLStringBuilder
				.append(" AND (:id IS NULL OR LOWER(book.id) LIKE :id)");

		// 2.通过toString方法变为完整 String
		String countHQLString = countHQLStringBuilder.toString();
		Query countQuery = sessionFactory.getCurrentSession().createQuery(
				countHQLString);
		if (queryConditionDTO.getUserId() != null) {
			countQuery.setParameter("userId",queryConditionDTO.getUserId() == null ? null
							: queryConditionDTO.getUserId());
			countQuery.setParameter("bookCode",queryConditionDTO.getBookCode() == null ? null
					: queryConditionDTO.getBookCode());
		} else {
			countQuery.setParameter("bookCode",queryConditionDTO.getBookCode() == null ? null
					: queryConditionDTO.getBookCode());
		}
		countQuery.setParameter("bookName",queryConditionDTO.getBookName() == null ? null
				: queryConditionDTO.getBookName());
		countQuery.setParameter("bookAuthor",queryConditionDTO.getBookAuthor() == null ? null
				: queryConditionDTO.getBookAuthor());
		countQuery.setParameter("isBorrowed",queryConditionDTO.getIsBorrowed() == null ? null
				: queryConditionDTO.getIsBorrowed());
		countQuery.setParameter("id",queryConditionDTO.getId() == null ? null
				: queryConditionDTO.getId());
		
		// 3.执行查询 得到满足条件的总记录数
		long totalCount = (Long) countQuery.list().get(0);

		// 4.如果没有满足条件的纪录 则返回空页面对象
		if (totalCount < 1) {
			return new Page<Book>();
		}
		// ------------------------------------------------------------------------------------
		// 4.如果有满足条件的纪录 则进行结果集查询 并返回有 结果集合和总记录数 的 页面对象
		StringBuilder hqlStringBuilder = new StringBuilder();
		hqlStringBuilder.append("FROM Book book left join fetch book.user");
		if (queryConditionDTO.getUserId() != null) {
			hqlStringBuilder
					.append(" WHERE (:userId IS NULL OR LOWER(book.user.userId) = :userId)");
			hqlStringBuilder
					.append(" AND (:bookCode IS NULL OR LOWER(book.bookCode) LIKE :bookCode)");
		} else {
			hqlStringBuilder
					.append(" WHERE (:bookCode IS NULL OR LOWER(book.bookCode) LIKE :bookCode)");
		}
		hqlStringBuilder
				.append(" AND (:bookName IS NULL OR LOWER(book.bookName) LIKE :bookName)");
		hqlStringBuilder
				.append(" AND (:bookAuthor IS NULL OR LOWER(book.bookAuthor) LIKE :bookAuthor)");
		hqlStringBuilder
				.append(" AND (:isBorrowed IS NULL OR LOWER(book.isBorrowed) LIKE :isBorrowed)");
		hqlStringBuilder
				.append(" AND (:id IS NULL OR LOWER(book.id) LIKE :id)");

		hqlStringBuilder.append(" Order By book." + sort + " " + dir);
		String hqlString = hqlStringBuilder.toString();

		Query query = sessionFactory.getCurrentSession().createQuery(hqlString);
		if (queryConditionDTO.getUserId() != null) {
			query.setParameter("userId",queryConditionDTO.getUserId() == null ? null
							: queryConditionDTO.getUserId());
			query.setParameter("bookCode",queryConditionDTO.getBookCode() == null ? null
					: queryConditionDTO.getBookCode());
		} else {
			query.setParameter("bookCode",queryConditionDTO.getBookCode() == null ? null
					: queryConditionDTO.getBookCode());
		}
		query.setParameter("bookName",queryConditionDTO.getBookName() == null ? null
				: queryConditionDTO.getBookName());
		query.setParameter("bookAuthor",queryConditionDTO.getBookAuthor() == null ? null
				: queryConditionDTO.getBookAuthor());
		query.setParameter("isBorrowed",queryConditionDTO.getIsBorrowed() == null ? null
				: queryConditionDTO.getIsBorrowed());
		query.setParameter("id",queryConditionDTO.getId() == null ? null
				: queryConditionDTO.getId());
		
		// 进行分页查询
		List<Book> lists = query.setFirstResult(start).setMaxResults(limit)
				.list();
		return new Page<Book>(lists, totalCount, limit);
	}

}
