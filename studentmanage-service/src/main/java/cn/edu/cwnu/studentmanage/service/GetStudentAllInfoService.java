/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;

/**
 * StudentZizhuService接口
 * @author kkliu
 * @since 2017-11-17
 */


public interface GetStudentAllInfoService  {
	/**
	 * 拼接数据
	 * @param student_id
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	StudentGrowupInfoVO getStudentAllInfo(Long student_id) throws IOException, Exception;
	
	
	/**
	 * 转换拼接的数据为页面需要的VO
	 * @param student_id
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	StudentGrowupInfoVO getStudentAllInfoVO(Long student_id) throws IOException, Exception;
	
	
	/**
	 * 通过拿到的学生信息生成pdf
	 * @param student_id
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	ByteArrayOutputStream getPdfOfStudentAllInfo(Long student_id) throws IOException, Exception;
	

	
}