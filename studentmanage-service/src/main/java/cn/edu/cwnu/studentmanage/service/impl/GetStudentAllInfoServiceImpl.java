package cn.edu.cwnu.studentmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import cn.edu.cwnu.studentmanage.domain.vo.XueQiInfo;
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
		 * 设置年级总人数
		 */
		StudentBasicInfo studentTemp =new StudentBasicInfo();
		studentTemp.setNianji(studentBasicInfo.getNianji());
		Integer nianjiCount = studentBasicInfoService.selectEntryListCount(studentTemp);
		sg.setNianjiCount(nianjiCount.toString());
		
		/**
		 * 设置班级总人数
		 */
		studentTemp.setBanji(studentBasicInfo.getBanji());
		Integer banjiCount = studentBasicInfoService.selectEntryListCount(studentTemp);
		sg.setBanjiCount(banjiCount.toString());
		
		
		/**
		 * 学业信息
		 */
		List<StudentXueye> StudentXueye =getStudentXueye(student_id);
		if(!StudentXueye.isEmpty()){
			sg.setCet4(StudentXueye.get(0).getCet4());
			sg.setPutonghua(StudentXueye.get(0).getPutonghua());
			sg.setSanbizi(StudentXueye.get(0).getSanbizi());
		}
		/**
		 * 学生成绩信息
		 */
		Map<String,XueQiInfo> xueQiInfoMap =new HashMap<String,XueQiInfo>();
		List<StudentChengji> chengjiList = getStudentChengji(student_id);
		if(!chengjiList.isEmpty()){
			for(int i=0;i<chengjiList.size();i++){
				StudentChengji it =chengjiList.get(i);
				XueQiInfo xueQiInfo;
				if(sg.getXueQiInfo()==null || sg.getXueQiInfo().get(it.getXueqi()) == null){
					xueQiInfo =new XueQiInfo();
				}else{
					xueQiInfo = (XueQiInfo) sg.getXueQiInfo().get(it.getXueqi());
				}
					xueQiInfo.setZhuanyePaiming(it.getZhuanyePaiming());
					xueQiInfo.setZonghePaiming(it.getZonghePaiming());
					xueQiInfo.setBukaokemu(it.getBukaokemu());
					xueQiInfoMap.put(it.getXueqi(), xueQiInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
		}
		
		/**
		 * 学生评奖信息
		 */
		List<StudentPingjiang> pjList = getStudentPingjiang(student_id);
		if(!pjList.isEmpty()){
			Iterator<StudentPingjiang> pj =pjList.iterator();
			while(pj.hasNext()){
				StudentPingjiang pjTemp =pj.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(pjTemp.getXueqi())){
					xqInfo =xueQiInfoMap.get(pjTemp.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				xqInfo.setJiangxuejin(pjTemp.getJiangxuejin());
				xqInfo.setDanxiangjiangxuejin(pjTemp.getDanxiangjiangxuejin());
				xqInfo.setXueyou(pjTemp.getXueyou());
				xqInfo.setTuanyou(pjTemp.getTuanyou());
				xqInfo.setYxdxbys(pjTemp.getYxdxbys());
				if(pjTemp.getDxxx()!=null && !"".equals(pjTemp.getDxxx())){
					sg.setDxxx(pjTemp.getDxxx());
				}
				xueQiInfoMap.put(pjTemp.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);

			
		}
		
		
		
		/**
		 * 学生资助信息
		 */
		List<StudentZizhu> ziZhuList = getStudentZizhu(student_id);
		if(!ziZhuList.isEmpty()){
			Iterator<StudentZizhu> zz =ziZhuList.iterator();
			while(zz.hasNext()){
				StudentZizhu zzTemp =zz.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(zzTemp.getXueqi())){
					xqInfo =xueQiInfoMap.get(zzTemp.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				xqInfo.setGjjxj(zzTemp.getGjjxj());
				xqInfo.setGjlzjxj(zzTemp.getGjlzjxj());
				xqInfo.setGjzxj(zzTemp.getGjzxj());
				xqInfo.setOther(zzTemp.getOther());
				xueQiInfoMap.put(zzTemp.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
		}
		
		
		/**
		 * 其他获奖信息
		 */
		List<StudentQitahuojiang> qthjList=getStudentQitahuojiang(student_id);
		if(!qthjList.isEmpty()){
			Iterator<StudentQitahuojiang> qt =qthjList.iterator();
			List<String> otherhuojianginfoList = new ArrayList<String>();
			while(qt.hasNext()){
				StudentQitahuojiang qthj =qt.next();
				XueQiInfo xqInfo;
				if(xueQiInfoMap.containsKey(qthj.getXueqi())){
					xqInfo =xueQiInfoMap.get(qthj.getXueqi());
				}else{
					xqInfo =new XueQiInfo();
				}
				if(xqInfo.getOtherhuojianginfo() == null){
					otherhuojianginfoList.add(qthj.getOtherhuojianginfo());
					xqInfo.setOtherhuojianginfo(otherhuojianginfoList);
				}else{
					xqInfo.getOtherhuojianginfo().add(qthj.getOtherhuojianginfo());
				}
				xueQiInfoMap.put(qthj.getXueqi(), xqInfo);
			}
			sg.setXueQiInfo(xueQiInfoMap);
			
		}
		
		// TODO Auto-generated method stub
		return sg;
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
