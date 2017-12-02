package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;

public interface AnalysisExcelService {
	//解析学生基本信息表
	public StudentBasicInfo analysisStudentBasicInfo(InputStream stream) throws Exception;
	//解析学生成绩表
	public List analysisStudentChengji(InputStream stream) throws Exception;
	//解析学业状况
	public List analysisStudentXueye(InputStream stream) throws Exception;
	//解析资助状况
	public List analysisStudentZizhu(InputStream stream) throws Exception;
	//解析评优评奖
	public List analysisStudentPingjiang(InputStream stream) throws Exception;

}
