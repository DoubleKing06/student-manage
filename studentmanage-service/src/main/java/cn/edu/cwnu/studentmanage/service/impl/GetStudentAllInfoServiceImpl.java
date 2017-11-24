package cn.edu.cwnu.studentmanage.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.cwnu.studentmanage.dao.base.BaseDao;
import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiang;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;
import cn.edu.cwnu.studentmanage.service.GetStudentAllInfoService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;
import cn.edu.cwnu.studentmanage.service.base.BaseServiceImpl;

@Service("getStudentAllInfoService")
public class GetStudentAllInfoServiceImpl extends BaseServiceImpl<StudentBasicInfo,Integer> implements GetStudentAllInfoService {
	@Resource private StudentBasicInfoService studentBasicInfoService;
	@Resource private StudentChengjiService studentChengjiService;
	@Resource private StudentXueyeService studentXueyeService;
	@Resource private StudentZizhuService studentZizhuService;
	@Resource private StudentPingjiangService studentPingjiangService;
	@Resource private StudentQitahuojiangService studentQitahuojiangService;
	
	@Override
	public StudentGrowupInfoVO getStudentAllInfo(Long student_id) throws Exception {
		StudentGrowupInfoVO sg = new StudentGrowupInfoVO();
		
		
		/**
		 * 学生基本信息
		 */
		StudentBasicInfo studentBasicInfo = getStudentBasicInfo(Integer.valueOf(student_id.toString()));
		if(null == studentBasicInfo){
			throw new Exception("无此学生");
		}
		sg.setXuehao(studentBasicInfo.getXuehao());
		sg.setName(studentBasicInfo.getName());
		sg.setZhuanye(studentBasicInfo.getZhuanye());
		sg.setBanji(studentBasicInfo.getBanji());
		
		/**
		 * 学业信息
		 */
		List<StudentXueye> StudentXueye =getStudentXueye(student_id);
		if(!StudentXueye.isEmpty()){
			sg.setCet4(StudentXueye.get(0).getCet4());
			sg.setPutonghua(StudentXueye.get(0).getPutonghua());
			sg.setSanbizi(StudentXueye.get(0).getSanbizi());
		}
		
		
		
		
		
		
		
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDao<StudentBasicInfo, Integer> getDao() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 根据student_id查询学生基本信息
	 * @param student_id
	 * @return
	 */
	public StudentBasicInfo getStudentBasicInfo(Integer student_id){
		return studentBasicInfoService.selectEntry(student_id);
	}
	
	/**
	 * 根据学生id查询学生学业信息
	 * @param student_id
	 * @return
	 */
	public List<StudentXueye> getStudentXueye(Long student_id){
		StudentXueye studentXueye =new StudentXueye();
		studentXueye.setStudentId(student_id);
		return studentXueyeService.selectEntryList(studentXueye);
	}
	
	/**
	 * 根据学生ID查询学生成绩信息
	 * @param student_id
	 * @return
	 */
	public List<StudentChengji> getStudentChengji(Long student_id){
		
		StudentChengji studentChengji =new StudentChengji();
		studentChengji.setStudentId(student_id);
		return studentChengjiService.selectEntryList(studentChengji);
	}
	
	/**
	 * 根据学生ID查询学生p评奖信息
	 * @param student_id
	 * @return
	 */
	public List<StudentPingjiang> getStudentPingjiang(Long student_id){
		StudentPingjiang studentPingjiang =new StudentPingjiang();
		studentPingjiang.setStudentId(student_id);
		return studentPingjiangService.selectEntryList(studentPingjiang);
	}
	
	/**
	 * 根据学生ID查询学生资助信息
	 * @param student_id
	 * @return
	 */
	public List<StudentZizhu> getStudentZizhu(Long student_id){
		
		StudentZizhu studentZizhu =new StudentZizhu();
		studentZizhu.setStudentId(student_id);
		return studentZizhuService.selectEntryList(studentZizhu);
	}
	
	/**
	 * 根据学生ID查询学生资助信息
	 * @param student_id
	 * @return
	 */
	public List<StudentQitahuojiang> getStudentQitahuojiang(Long student_id){
		
		
		StudentQitahuojiang studentQitahuojiang =new StudentQitahuojiang();
		studentQitahuojiang.setStudentId(student_id);
		return studentQitahuojiangService.selectEntryList(studentQitahuojiang);
	}
	
	
	
	

}
