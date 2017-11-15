package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;
import java.io.InputStream;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;

public interface AnalysisExcelService {
	//解析学生基本信息表
	public StudentBasicInfo analysisStudentBasicInfo(InputStream stream) throws IOException;

}
