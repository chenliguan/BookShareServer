package com.book_share.core.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.book_share.core.util.Page;

@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {
	
	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	private Class<T> clazz;

	public BaseDao() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];

	}

	public void save(T t) {
		// begin Transaction

		this.getCurrentSession().save(t);

	}

	public void update(T t) {
		this.getCurrentSession().update(t);
	}

	public void delete(T t) {
		this.getCurrentSession().delete(t);
	}

	public T getById(Long id) {
		return this.getCurrentSession().get(this.clazz, id);
	}

	public T loadById(Long id) {
		return this.getCurrentSession().load(this.clazz, id);
	}

	public List<T> findAll() {
		return this.getCurrentSession()
				.createQuery("from " + this.clazz.getName()).list();
	}

	public List<T> findByHQL(String hql, Object... params) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++) {
			query.setParameter(i, params);
		}
		return query.list();
	}

	@Override
	public Page<T> findPage(int start, int limit) {

		long totalCounts = Long.parseLong(""
				+ this.getCurrentSession()
						.createQuery(
								"select count(*) from " + this.clazz.getName())
						.list().get(0));

		if (totalCounts < 1) {
			return new Page<T>();
		}

		List<T> lists = this.getCurrentSession()
				.createQuery("from " + this.clazz.getName())
				.setFirstResult(start).setMaxResults(limit).list();
		return new Page<T>(lists, totalCounts, limit);

	}
}
