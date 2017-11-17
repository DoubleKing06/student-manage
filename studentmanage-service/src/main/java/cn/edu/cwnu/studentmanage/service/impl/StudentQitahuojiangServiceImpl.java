/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiang;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentQitahuojiangDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangService;

/**
 * StudentQitahuojiangService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentQitahuojiangService")
public class StudentQitahuojiangServiceImpl extends BaseServiceImpl<StudentQitahuojiang,Integer> implements StudentQitahuojiangService {
	
	@Resource private StudentQitahuojiangDao studentQitahuojiangDao;
	
	public BaseDao<StudentQitahuojiang,Integer> getDao() {
		return studentQitahuojiangDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentQitahuojiang studentQitahuojiang) {
		return super.insertEntryCreateId(studentQitahuojiang);
	}
}