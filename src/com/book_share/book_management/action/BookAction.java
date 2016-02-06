package com.book_share.book_management.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.book_share.book_management.dto.BookDTO;
import com.book_share.book_management.dto.BookQueryConditionDTO;
import com.book_share.book_management.service.IBookService;
import com.book_share.core.action.BaseAction;
import com.book_share.core.util.Page;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @description BookAction
 * @author ZhouRuiBang
 * @date 2016-1-29 下午1:35:12
 * @version 1.0
 */
@Controller
public class BookAction extends BaseAction implements ModelDriven<BookDTO>, ParameterAware {

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IBookService bookService;

	// 声明返回前端的数据 (Getter)
	private long totalCount = 0;

	// queryDTO提供get,set内部属性
	private BookQueryConditionDTO queryDTO = new BookQueryConditionDTO();

	private List<BookDTO> ecList = new ArrayList<BookDTO>();

	private BookDTO bookDTO = new BookDTO();
	
	@Override
	public BookDTO getModel() {
		return bookDTO;
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
	 *  根据各种条件查看图书明细信息，返回集合
	 *  http://localhost:8080/bookShare/findBookAction.action?userId=13751338740
	 *  http://localhost:8080/bookShare/findBookAction.action?bookAuthor=神
	 *  http://localhost:8080/bookShare/findBookAction.action?isBorrowed=NO&bookCode=0002
	 */
	public String findBookBy() {
		try {
			// bookDTO捕获的数据赋值给queryDTO
			BeanUtils.copyProperties(bookDTO, queryDTO);
			
			Page<BookDTO>  page = bookService.findBookPage(queryDTO, start, limit,sort,dir);
			//前端Grid需要数据
			totalCount =page.getTotalCounts();
			ecList = page.getLists();
			responseSuccess();//success:true,failure:false,message:''
		} catch (Exception e) {
			responseFailureAndMessage(e.getMessage());//failure:true,success:false,message:e.getMessage()
		}
		return SUCCESS;
	}

//	/**
//	 *  更新图书信息（修改图书）
//	 *  http://localhost:8080/bookShare/updateBookAction.action?Id=4&creater=小明
//	 */
//	public String updateBook() {
//		try {
//			String[] Ids = parameters.get("Id");
//			bookDTO.setId(Long.parseLong(Ids[0]));
//			
//			bookService.updateBook(bookDTO);
//			responseSuccess();
//		} catch (Exception e) {
//			responseFailureAndMessage(e.getMessage());
//		}
//
//		return SUCCESS;
//	}

	/**
	 *  更新图书信息（修改图书）
	 *  http://localhost:8080/bookShare/updateBookAction.action?id=4&creater=小明
	 */
	public String updateBook() {
		
		try {
		    // 通过id查询出图书
			queryDTO.setId(bookDTO.getId());
			Page<BookDTO> page = bookService.findBookPage(queryDTO, start, limit,sort,dir);
			BookDTO bookD = page.getLists().get(0);
		   
			if (!bookD.equals(null)) {
				bookService.updateBook(bookDTO);
				responseSuccessAndMessage("更新成功");
			} else {
				responseFailureAndMessage("不存在该图书");
			}
		} catch (Exception e) {
			// failure:true,success:false,message:e.getMessage()
			responseFailureAndMessage("加载失败");
		}

		return SUCCESS;
	}

	/**
	 * 新建图书信息（图书上传）
	 * http://localhost:8080/bookShare/createBookAction.action?creater=小冠冠
	 */
	public String createBook() {
		try {
			bookService.createBook(bookDTO);
			responseSuccessAndMessage("新增图书成功");
		} catch (Exception e) {
			responseSuccessAndMessage("新增图书失败");
		}

		return SUCCESS;
	}

	/**
	 * 删除图书信息
	 * http://localhost:8080/bookShare/updateBookAction.action?id=4
	 */
	public String removeBook() {
		try {
			String[] Ids = parameters.get("id");
			
			for (int i = 0; i < Ids.length; i++) {
				bookService.removeBook(Long.parseLong(Ids[i]));
			}
			responseSuccess();
		} catch (Exception e) {
			responseFailureAndMessage(e.getMessage());
		}

		return SUCCESS;
	}
	
	public long getTotalCount() {
		return totalCount;
	}

	public BookQueryConditionDTO getQueryDTO() {
		return queryDTO;
	}

	public List<BookDTO> getEcList() {
		return ecList;
	}

}
