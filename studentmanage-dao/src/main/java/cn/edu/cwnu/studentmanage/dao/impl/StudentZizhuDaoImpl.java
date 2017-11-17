/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.dao.impl;

import org.springframework.stereotype.Repository;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.dao.base.BaseDaoImpl;
import cn.edu.cwnu.studentmanage.dao.StudentZizhuDao;

/**
 * StudentZizhuDao 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Repository("studentZizhuDao")
public class StudentZizhuDaoImpl extends BaseDaoImpl<StudentZizhu,Integer> implements StudentZizhuDao {
	private final static String NAMESPACE = "cn.edu.cwnu.studentmanage.dao.StudentZizhuDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}
		
}