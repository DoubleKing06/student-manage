/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentXueyeDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentXueyeVO;
import cn.edu.cwnu.studentmanage.service.StudentXueyeVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentXueyeService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentXueyeVOService")
public class StudentXueyeVOServiceImpl extends BaseServiceImpl<StudentXueyeVO,Integer> implements StudentXueyeVOService {
	
	@Resource private StudentXueyeDaoVO studentXueyeDaoVO;
	
	public BaseDao<StudentXueyeVO,Integer> getDao() {
		return studentXueyeDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentXueyeVO studentXueyeVO) {
		return super.insertEntryCreateId(studentXueyeVO);
	}
}