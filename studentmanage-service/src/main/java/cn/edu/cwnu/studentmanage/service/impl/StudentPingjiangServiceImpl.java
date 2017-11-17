/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentPingjiangDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;

/**
 * StudentPingjiangService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentPingjiangService")
public class StudentPingjiangServiceImpl extends BaseServiceImpl<StudentPingjiang,Integer> implements StudentPingjiangService {
	
	@Resource private StudentPingjiangDao studentPingjiangDao;
	
	public BaseDao<StudentPingjiang,Integer> getDao() {
		return studentPingjiangDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentPingjiang studentPingjiang) {
		return super.insertEntryCreateId(studentPingjiang);
	}
}