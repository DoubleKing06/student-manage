/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.base;

import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.edu.cwnu.studentmanage.common.exception.AppException;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.common.Page;

/**
 * service实现类
 * @author kkliu
 * @since 2017-11-17
 * @param <T> 实体
 * @param <KEY> 主键
 */
public abstract class BaseServiceImpl<T, KEY extends Serializable> implements BaseService<T, KEY> {
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

	/**
	 * 获取DAO操作类
	 */
	public abstract BaseDao<T, KEY> getDao();
	
	@SuppressWarnings("unchecked")
	public int insertEntry(T...t) {
		return getDao().insertEntry(t);
	}
	
	public int insertEntryCreateId(T t) {
	    return getDao().insertEntryCreateId(t);
	}
	@SuppressWarnings("unchecked")
	public int deleteByKey(KEY...key) {
		return getDao().deleteByKey(key);
	}
	
	public int deleteByCondtion(T condtion) {
		return getDao().deleteByKey(condtion);
	}
	
	public int updateByKey(T condtion) {
		return getDao().updateByKey(condtion);
	}
	
	@SuppressWarnings("unchecked")
	public int saveOrUpdate(T t) {
		Long id = 0L;
		try {
			Class<?> clz = t.getClass();
			id = (Long)clz.getMethod("getId").invoke(t);
		} catch (Exception e) {
			LOGGER.warn("获取对象主键值失败!");
		}
		if(id != null && id > 0) {
			return this.updateByKey(t);
		} 
		return this.insertEntry(t);
	}

	public T selectEntry(KEY key) {
		return getDao().selectEntry(key);
	}
	@SuppressWarnings("unchecked")
	public List<T> selectEntryList(KEY...key) {
		return getDao().selectEntryList(key);
	}
	
	public List<T> selectEntryList(T condtion) {
		return getDao().selectEntryList(condtion);
	}
	
	public Page<T> selectPage(T condtion, Page<T> page) {
		try {
			Class<?> clz = condtion.getClass();
			clz.getMethod("setStartIndex", Integer.class).invoke(condtion, page.getStartIndex());
			clz.getMethod("setEndIndex", Integer.class).invoke(condtion, page.getEndIndex());
		} catch (Exception e) {
			throw new AppException("设置分页参数失败", e);
		}
		Integer size = getDao().selectEntryListCount(condtion);
		if(size == null || size <= 0) {
			return page;
		}
		page.setTotalCount(size);
		page.setResult(this.selectEntryList(condtion));
		return page;
	}
}
