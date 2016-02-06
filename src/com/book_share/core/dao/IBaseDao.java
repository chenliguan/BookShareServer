package com.book_share.core.dao;

import java.util.List;

import com.book_share.core.util.Page;

public interface IBaseDao<T>
{
	public void save(T t);
	public void update(T t);
	public void delete(T t);
	
	public T getById(Long id);
	public T loadById(Long id);
	
	
	public List<T> findAll() ;
	public Page<T> findPage(int start,int limit) ;
	
	public List<T> findByHQL(String hql, Object... params);
}
