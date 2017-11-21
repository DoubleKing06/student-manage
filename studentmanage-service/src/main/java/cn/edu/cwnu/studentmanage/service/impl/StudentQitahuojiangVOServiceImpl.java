/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.cwnu.studentmanage.dao.StudentQitahuojiangDaoVO;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiangVO;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangVOService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

/**
 * StudentQitahuojiangService 实现类
 * @author kkliu
 * @since 2017-11-17
 */
@Service("studentQitahuojiangVOService")
public class StudentQitahuojiangVOServiceImpl extends BaseServiceImpl<StudentQitahuojiangVO,Integer> implements StudentQitahuojiangVOService {
	
	@Resource private StudentQitahuojiangDaoVO studentQitahuojiangDaoVO;
	
	public BaseDao<StudentQitahuojiangVO,Integer> getDao() {
		return studentQitahuojiangDaoVO;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentQitahuojiangVO studentQitahuojiangVO) {
		return super.insertEntryCreateId(studentQitahuojiangVO);
	}
}