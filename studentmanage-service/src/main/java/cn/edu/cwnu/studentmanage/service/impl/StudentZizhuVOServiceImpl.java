/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentZizhuDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentZizhuVO;
import cn.edu.cwnu.studentmanage.service.StudentZizhuVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentZizhuService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentZizhuVOService")
public class StudentZizhuVOServiceImpl extends BaseServiceImpl<StudentZizhuVO,Integer> implements StudentZizhuVOService {
	
	@Resource private StudentZizhuDaoVO studentZizhuDaoVO;
	
	public BaseDao<StudentZizhuVO,Integer> getDao() {
		return studentZizhuDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentZizhuVO studentZizhuVO) {
		return super.insertEntryCreateId(studentZizhuVO);
	}
}