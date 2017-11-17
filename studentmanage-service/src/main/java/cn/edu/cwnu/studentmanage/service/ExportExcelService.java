package cn.edu.cwnu.studentmanage.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;

public interface ExportExcelService {
	
	//导出基本信息表
	public StudentBasicInfo exportStudentGrowupInfo(InputStream stream) throws IOException;

}
