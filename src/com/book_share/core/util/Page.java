package com.book_share.core.util;

import java.util.ArrayList;
import java.util.List;

public class Page<T> 
{

	private List<T> lists;

	private long totalCounts;
	
	private int pageSize;

//	private static int startIndex = 1;
//	
//	//���� ��ʼ��������̬����������Ҫnew��
//	public static  int getStartIndex(int pageIndex, int pageSize) {
//		if(pageIndex>0){
//			startIndex = (pageIndex-1)*pageSize;	 //���ݳ־ò� API��setFirstResult��ʼ����Ϊ0
//			//startIndex = (pageIndex-1)*pageSize+1; //ĳЩ�־ò��� API����ʼ����Ϊ1
//		}
//		return startIndex;
//	}
	
	//��ȡ��ҳ�� 
	public long getPageTotals() {
		return totalCounts % pageSize == 0 ? totalCounts / pageSize : totalCounts / pageSize + 1;  
	}  

	//���첿�֣�
	//��װ�������+�ܼ�¼��
	public Page(List<T> lists,long totalCounts, int pageSize)
	{
		this.lists = lists;
		this.totalCounts = totalCounts;
		this.pageSize = pageSize;
	}
	//��װ���ա�ҳ�棨��ֹnullָ���쳣��
	public Page(){
		this.lists = new ArrayList<T>();
		this.totalCounts = 0;
	}
	
//	public static Page<Object> nullPage(){
//		return new Page(new ArrayList<Object>(),0);
//	}
	
	//get��set
	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(int totalCounts) {
		this.totalCounts = totalCounts;
	}

}
