/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.service.base.BaseService;

/**
 * StudentPingjiangService接口
 * @author kkliu
 * @since 2017-11-17
 */
public interface StudentPingjiangVOService extends BaseService<StudentPingjiangVO,Integer> {
	
	HSSFWorkbook studentPingjiangExport(StudentPingjiangVO studentPingjiangVO) throws  IOException;
	
}