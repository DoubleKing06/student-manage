/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentChengjiDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentChengjiVO;
import cn.edu.cwnu.studentmanage.service.StudentChengjiVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentChengjiService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentChengjiVOService")
public class StudentChengjiServiceVOImpl extends BaseServiceImpl<StudentChengjiVO,Integer> implements StudentChengjiVOService {
	
	@Resource private StudentChengjiDaoVO studentChengjiDaoVO;
	
	public BaseDao<StudentChengjiVO,Integer> getDao() {
		return studentChengjiDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentChengjiVO studentChengjiVO) {
		return super.insertEntryCreateId(studentChengjiVO);
	}
}