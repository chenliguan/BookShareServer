package com.book_share.book_management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book_share.book_management.dao.IBookDao;
import com.book_share.book_management.dto.BookDTO;
import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.book_management.entity.Book;
import com.book_share.book_management.util.CopyProperties;
import com.book_share.core.util.Page;

/**
 * @description 图书业务服务层实现
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Transactional
@Service
public class BookService implements IBookService {

	@Autowired
	private IBookDao bookDao;

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
			int start, int limit, String sort, String dir) {
		List<BookDTO> dtoList = null;
		Page<Book> bookPage = bookDao.findBookPageByUser(queryConditionDTO,
				start, limit, sort, dir);

		if (bookPage != null) {
			dtoList = new ArrayList<BookDTO>();
			for (Book c : bookPage.getLists()) {
				BookDTO dto = new BookDTO();
				BookDTO.Entity2Dto(c, dto);

				dtoList.add(dto);
			}
		}
		return new Page<BookDTO>(dtoList, bookPage.getTotalCounts(), limit);
	}

	/**
	 * @description 新建图书信息（图书上传）
	 * @param BookDTO
	 */
	public void createBook(BookDTO bookDTO) {
		Book book = new Book();
		BookDTO.Dto2Entity(bookDTO, book);
		bookDao.save(book);
	}

	/**
	 * @description 更新图书信息（修改图书）
	 * @param BookDTO
	 */
	public void updateBook(BookDTO bookDTO) {
		// User
		Book temp = (Book) bookDao.getById(bookDTO.getId());
		System.out.println(temp);
		// temp 持久化 与数据库同步
		if (temp != null) {
			// 前端的对象数据覆盖到持久化状态的对象中再更新 bookDTO->temp
//			BeanUtils.copyProperties(bookDTO, temp);
			CopyProperties.copyPropertiesIgnoreNull(bookDTO, temp);
			// 关联关系
			bookDao.update(temp);
		}
	}

	/**
	 * @description 删除图书信息
	 * @param id
	 */
	public void removeBook(Long id) {
		Book book = bookDao.getById(id);
		if(book!=null){
			//关联关系维护 O
			bookDao.delete(book);
		}
	}

}
