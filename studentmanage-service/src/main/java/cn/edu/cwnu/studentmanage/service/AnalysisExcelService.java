package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.common.Message;

public interface AnalysisExcelService {
	//解析学生基本信息表
	public Message analysisStudentBasicInfo(InputStream stream) throws Exception;
	//解析学生成绩表
	public Message analysisStudentChengji(InputStream stream) throws Exception;
	//解析学业状况
	public Message analysisStudentXueye(InputStream stream) throws Exception;
	//解析资助状况
	public Message analysisStudentZizhu(InputStream stream) throws Exception;
	//解析评优评奖
	public Message analysisStudentPingjiang(InputStream stream) throws Exception;

}
