/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentChengjiDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;

/**
 * StudentChengjiService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentChengjiService")
public class StudentChengjiServiceImpl extends BaseServiceImpl<StudentChengji,Integer> implements StudentChengjiService {
	
	@Resource private StudentChengjiDao studentChengjiDao;
	
	public BaseDao<StudentChengji,Integer> getDao() {
		return studentChengjiDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentChengji studentChengji) {
		return super.insertEntryCreateId(studentChengji);
	}
}