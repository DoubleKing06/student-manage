/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentRemarkDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentRemarkVO;
import cn.edu.cwnu.studentmanage.service.StudentRemarkVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentRemarkService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentRemarkVOService")
public class StudentRemarkVOServiceImpl extends BaseServiceImpl<StudentRemarkVO,Integer> implements StudentRemarkVOService {
	
	@Resource private StudentRemarkDaoVO studentRemarkDaoVO;
	
	public BaseDao<StudentRemarkVO,Integer> getDao() {
		return studentRemarkDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentRemarkVO studentRemarkVO) {
		return super.insertEntryCreateId(studentRemarkVO);
	}
}