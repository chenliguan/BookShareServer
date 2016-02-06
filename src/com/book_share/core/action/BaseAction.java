package com.book_share.core.action;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport
{

	private static final long serialVersionUID = 4685106414382477147L;
	//分页查询
	protected int start = 0;//起始索引从0开始
	protected int limit = 30;
	
	//排序
	protected String sort="id";
	protected String  dir="DESC";
	
	public void setStart(int start) {
		this.start = start;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	//Ajax交互
	protected boolean success=false;//操作成功时返回true,默认为false
	protected boolean failure=false;// 操作失败时返回true,默认为false

	protected String message=""; //提示信息

	//因为set有特殊含义，所以设置方法不适合适用set开头，则 自定义
	//响应 success:true
	public void responseSuccess() {
		this.success = true;
		this.failure = false;
	}
	//响应 failure:true
	public void responseFailure() {
		this.success = false;
		this.failure = true;
	}
	//响应 success:true,message="自定义信息，例如要返回的内容,系统消息"
	public void responseSuccessAndMessage(String message) {
		this.message = message;
		this.success = true;
	}
	//响应 failure:true,message="自定义信息，例如系统异常，或者需要返回的内容"
	public void responseFailureAndMessage(String message) {
		this.message = message;
		this.failure = true;
	}
}
