/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.cwnu.studentmanage.domain.StudentZizhuVO;
import cn.edu.cwnu.studentmanage.service.base.BaseService;

/**
 * StudentZizhuService接口
 * @author kkliu
 * @since 2017-11-17
 */
public interface StudentZizhuVOService extends BaseService<StudentZizhuVO,Integer> {
	HSSFWorkbook studentZizhuExport(StudentZizhuVO studentZizhuVO) throws IOException;		

	
}