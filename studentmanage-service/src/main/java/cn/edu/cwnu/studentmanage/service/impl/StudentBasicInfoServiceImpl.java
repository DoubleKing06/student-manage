/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.dao.StudentBasicInfoDao;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;

/**
 * StudentBasicInfoService 实现类
 * @author kkliu
 * @since 2017-11-15
 */
@Service("studentBasicInfoService")
public class StudentBasicInfoServiceImpl extends BaseServiceImpl<StudentBasicInfo,Integer> implements StudentBasicInfoService {
	
	@Resource private StudentBasicInfoDao studentBasicInfoDao;
	
	public BaseDao<StudentBasicInfo,Integer> getDao() {
		return studentBasicInfoDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(StudentBasicInfo studentBasicInfo) {
		return super.insertEntryCreateId(studentBasicInfo);
	}
}