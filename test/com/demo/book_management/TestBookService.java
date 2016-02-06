package com.demo.book_management;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.book_share.book_management.dto.BookDTO;
import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.book_management.service.IBookService;
import com.book_share.book_management.util.IsBorrowed;
import com.book_share.core.util.Page;
import com.demo.util.BaseTest;

/**
 * @description Book测试类
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Transactional
@Rollback(value = false)
public class TestBookService extends BaseTest {

	@Autowired
	private IBookService bookService;

	/**
	 * @description 测试增加
	 * 
	 */
	 @Test
	public void prepareTestData() {
		for (int i = 1; i < 4; i++) {

			BookDTO ec = new BookDTO();

			if (i == 1) {
				ec.setUserId(13751338740L);
				ec.setUserName("小邦");
				ec.setBookName("Ios");
				ec.setBookCode("0006");
				ec.setBookAuthor("陶");
				ec.setBookProfile("这是一本入门的ios书籍");
				ec.setIsBorrowed(IsBorrowed.YES);
			} else  if (i == 2){
				ec.setUserId(13751338740L);
				ec.setUserName("小明");
				ec.setBookName("JavaEE");
				ec.setBookCode("0007");
				ec.setBookAuthor("神");
				ec.setBookProfile("这是一本入门的JavaEE书籍");
				ec.setIsBorrowed(IsBorrowed.NO);
			} else if(i == 3 | i == 0) {
				ec.setUserId(13751338740L);
				ec.setUserName("小爷");
				ec.setBookName("PHP");
				ec.setBookCode("0008");
				ec.setBookAuthor("李刚");
				ec.setBookProfile("这是一本入门的PHP书籍");
				ec.setIsBorrowed(IsBorrowed.NO);
			}

			ec.setCreaterTime(new Date());
			ec.setLastUpdateTime(new Date());
			ec.setCreater("小邦");

			bookService.createBook(ec);
		}
	}

	/**
	 * @description 测试查询
	 * 
	 */
//	 @Test
	public void testUserPage() {
//		BookDTO bookDTO = new BookDTO();
//		bookDTO.setUserId(13751338740L);

		int start = 0;
		int limit = 30;
		String sort = "id";
		String dir = "desc";
		BookQueryConditionDTO queryConditionDTO = new BookQueryConditionDTO();
		queryConditionDTO.setUserId(13751338740L);
		
		// 查询
		Page<BookDTO> page = bookService.findBookPage(queryConditionDTO, start, limit, sort, dir);
		
		for (BookDTO e : page.getLists()) {
			System.out.println(e);
		}
	}

	/**
	 * @description 测试更新 注意：需要回滚
	 */
//	@Test
	public void testUpdateUser() {
		BookDTO book = new BookDTO();
		
		int start = 0;
		int limit = 30;
		String sort = "id";
		String dir = "desc";
		BookQueryConditionDTO queryConditionDTO = new BookQueryConditionDTO();
		queryConditionDTO.setUserId(13751338740L);
		
		// 查询
		Page<BookDTO> page = bookService.findBookPage(queryConditionDTO, start, limit, sort, dir);
		for (BookDTO e : page.getLists()) {
			book = e;
			System.out.println(e);
		}
		
        // 修改
		book.setRemake("小小");
		book.setCreater("小小");
		System.out.println(book);

		bookService.updateBook(book);
	}
	
	/**
	 * @description 测试删除图书
	 * 
	 */
//	 @Test
	public void testRemoveUserPage() {

		 bookService.removeBook(3L);
	}

}
