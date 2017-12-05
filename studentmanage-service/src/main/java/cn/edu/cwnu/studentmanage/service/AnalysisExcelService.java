package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.common.Message;

public interface AnalysisExcelService {
	//解析学生基本信息表
	public String analysisStudentBasicInfo(InputStream stream) throws Exception;
	//解析学生成绩表
	public String analysisStudentChengji(InputStream stream) throws Exception;
	//解析学业状况
	public String analysisStudentXueye(InputStream stream) throws Exception;
	//解析资助状况
	public String analysisStudentZizhu(InputStream stream) throws Exception;
	//解析评优评奖
	public String analysisStudentPingjiang(InputStream stream) throws Exception;

}
