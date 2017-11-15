/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentXueyeDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;

/**
 * StudentXueyeService 实现类
 * @author kkliu
 * @since 2017-11-15
 */
@Service("studentXueyeService")
public class StudentXueyeServiceImpl extends BaseServiceImpl<StudentXueye,Integer> implements StudentXueyeService {
	
	@Resource private StudentXueyeDao studentXueyeDao;
	
	public BaseDao<StudentXueye,Integer> getDao() {
		return studentXueyeDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentXueye studentXueye) {
		return super.insertEntryCreateId(studentXueye);
	}
}