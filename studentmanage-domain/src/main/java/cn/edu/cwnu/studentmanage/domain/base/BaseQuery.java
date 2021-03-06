/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 基础查询类
 * @author kkliu
 * @since 2017-11-15
 */
class BaseQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private transient Integer startIndex;// 开始索引
	
	private transient Integer endIndex;// 结束索引
	
	private transient String orderField;// 排序字段
	
	private transient String orderFieldType;// 排序字段类型
	
	private transient Map<String, Object> queryData;// 查询扩展
	
	private transient String keyword;// 关键则查询
	
	@JSONField(serialize=false)
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	
	@JSONField(serialize=false)
	public Integer getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	
	//每页显示条数
	@JSONField(serialize=false)
	public Integer getPageSize() {
		if(endIndex != null && startIndex != null) {
			return endIndex - startIndex;
		}
		return null;
	}
	
	@JSONField(serialize=false)
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	@JSONField(serialize=false)
	public String getOrderFieldType() {
		if("DESC".equalsIgnoreCase(orderFieldType) || "ASC".equalsIgnoreCase(orderFieldType)) {
			return orderFieldType.toUpperCase();
		}
		return null;
	}
	
	@JSONField(serialize=false)
	public String getOrderFieldNextType() {
		if("ASC".equalsIgnoreCase(orderFieldType)) {
			return "DESC";
		} 
		return "ASC";
	}

	public void setOrderFieldType(String orderFieldType) {
		this.orderFieldType = orderFieldType;
	}
	
	@JSONField(serialize=false)
	public Map<String, Object> getQueryData() {
		if(queryData != null && queryData.size() > 0) {
			return queryData;
		}
		return null;
	}
	
	//添加其它查询数据
	public void addQueryData(String key,Object value) {
		if(queryData == null) {
			queryData = new HashMap<String, Object>();
		}
		queryData.put(key, value);
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public String getkeyword() {
		return keyword;
	}
}
