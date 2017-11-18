package cn.edu.cwnu.studentmanage.dao;

import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;

public interface StudentGrowupInfoDao extends BaseDao<StudentBasicInfo,Integer>{
	
	/**
	 * 获得学生成长记录信息
	 * @param 学号 学生学号
	 * @return
	 */
	public StudentGrowupInfoVO getStudentGrowupInfo(Integer xuehao);

}
