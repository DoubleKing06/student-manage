/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentZizhuDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;

/**
 * StudentZizhuService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentZizhuService")
public class StudentZizhuServiceImpl extends BaseServiceImpl<StudentZizhu,Integer> implements StudentZizhuService {
	
	@Resource private StudentZizhuDao studentZizhuDao;
	
	public BaseDao<StudentZizhu,Integer> getDao() {
		return studentZizhuDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentZizhu studentZizhu) {
		return super.insertEntryCreateId(studentZizhu);
	}
}