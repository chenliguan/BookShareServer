package com.book_share.book_management.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @description CopyProperties工具类
 * @author ZhouRuiBang
 * @date 2016-2-3 下午1:35:12
 * @version 1.0
 */
public class CopyProperties {

	/**
	 * BeanUtils.copyProperties在拷贝属性时忽略空值
	 * @param src
	 * @param target
	 */
	public static void copyPropertiesIgnoreNull(Object src, Object target) {
		BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
	}
	
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
