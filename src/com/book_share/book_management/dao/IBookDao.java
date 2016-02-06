package com.book_share.book_management.dao;

import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.book_management.entity.Book;
import com.book_share.core.dao.IBaseDao;
import com.book_share.core.util.Page;

/**
 * @description 图书Dao层接口
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public interface IBookDao extends IBaseDao<Book> {

	/**
	 * @description 检索图书
	 * @param queryConditionDTO
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return
	 */
	public Page<Book> findBookPageByUser(BookQueryConditionDTO queryConditionDTO, int start, int limit,
			String sort, String dir);
}
