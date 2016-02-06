package com.book_share.book_management.service;

import com.book_share.book_management.dto.BookDTO;
import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.core.util.Page;

/**
 * @description 图书业务服务层接口
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
public interface IBookService {

	/**
	 * @description 检索图书信息
	 * @param queryConditionDTO
	 * @param start
	 * @param limit
	 * @param sort
	 * @param dir
	 * @return
	 */
	public Page<BookDTO> findBookPage(BookQueryConditionDTO queryConditionDTO,
			int start, int limit, String sort, String dir);
	
	/**
	 * @description 新建图书信息（图书上传）
	 * @param BookDTO
	 */
	public void createBook(BookDTO bookDTO);

	/**
	 * @description 更新图书信息（修改图书）
	 * @param BookDTO
	 */
	public void updateBook(BookDTO bookDTO);

    /**
     * @description 删除图书信息
     * @param id
     */
	public void removeBook(Long id);
}
