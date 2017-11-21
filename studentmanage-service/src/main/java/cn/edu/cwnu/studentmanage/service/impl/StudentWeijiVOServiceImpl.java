/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentWeijiDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentWeijiVO;
import cn.edu.cwnu.studentmanage.service.StudentWeijiVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentWeijiService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentWeijiVOService")
public class StudentWeijiVOServiceImpl extends BaseServiceImpl<StudentWeijiVO,Integer> implements StudentWeijiVOService {
	
	@Resource private StudentWeijiDaoVO studentWeijiDaoVO;
	
	public BaseDao<StudentWeijiVO,Integer> getDao() {
		return studentWeijiDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentWeijiVO studentWeijiVO) {
		return super.insertEntryCreateId(studentWeijiVO);
	}
}