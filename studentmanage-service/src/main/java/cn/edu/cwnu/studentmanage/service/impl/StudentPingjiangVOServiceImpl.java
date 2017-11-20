/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentPingjiangDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentPingjiangService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentPingjiangVOService")
public class StudentPingjiangVOServiceImpl extends BaseServiceImpl<StudentPingjiangVO,Integer> implements StudentPingjiangVOService {
	
	@Resource private StudentPingjiangDaoVO studentPingjiangDaoVO;
	
	public BaseDao<StudentPingjiangVO,Integer> getDao() {
		return studentPingjiangDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentPingjiangVO studentPingjiangVO) {
		return super.insertEntryCreateId(studentPingjiangVO);
	}
}