/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentRemark;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentRemarkDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentRemarkService;

/**
 * StudentRemarkService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentRemarkService")
public class StudentRemarkServiceImpl extends BaseServiceImpl<StudentRemark,Integer> implements StudentRemarkService {
	
	@Resource private StudentRemarkDao studentRemarkDao;
	
	public BaseDao<StudentRemark,Integer> getDao() {
		return studentRemarkDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentRemark studentRemark) {
		return super.insertEntryCreateId(studentRemark);
	}
}