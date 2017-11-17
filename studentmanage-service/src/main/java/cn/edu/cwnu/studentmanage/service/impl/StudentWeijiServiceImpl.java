/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentWeiji;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentWeijiDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentWeijiService;

/**
 * StudentWeijiService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentWeijiService")
public class StudentWeijiServiceImpl extends BaseServiceImpl<StudentWeiji,Integer> implements StudentWeijiService {
	
	@Resource private StudentWeijiDao studentWeijiDao;
	
	public BaseDao<StudentWeiji,Integer> getDao() {
		return studentWeijiDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentWeiji studentWeiji) {
		return super.insertEntryCreateId(studentWeiji);
	}
}