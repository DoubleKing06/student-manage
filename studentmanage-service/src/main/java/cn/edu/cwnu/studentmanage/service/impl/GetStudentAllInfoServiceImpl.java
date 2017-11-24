package cn.edu.cwnu.studentmanage.service.impl;

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
			Iterator<StudentChengji> it =chengjiList.iterator();
			while(it.hasNext()){
				if(!sg.getXueQiInfo().containsKey(it.next().getXueqi())){
					
					XueQiInfo xueqiInfo =new XueQiInfo();
					StudentChengji cj =it.next();
					xueqiInfo.setZhuanyePaiming(cj.getZhuanyePaiming());
					xueqiInfo.setZonghePaiming(cj.getZonghePaiming());
					xueqiInfo.setBukaokemu(cj.getBukaokemu());
					xueQiInfoMap.put(it.next().getXueqi(), xueqiInfo);
					sg.setXueQiInfo(xueQiInfoMap);
				}
			}
		}
		
		/**
		 * 学生评奖信息
		 */
		List<StudentPingjiang> pjList = getStudentPingjiang(student_id);
		if(!pjList.isEmpty()){
			Iterator<StudentPingjiang> pj =pjList.iterator();
			while(pj.hasNext()){
				StudentPingjiang pjTemp =pj.next();
				if(xueQiInfoMap.containsKey(pjTemp.getXueqi())){
					XueQiInfo xqInfo =xueQiInfoMap.get(pjTemp.getXueqi());
					xqInfo.setJiangxuejin(pjTemp.getJiangxuejin());
					xqInfo.setDanxiangjiangxuejin(pjTemp.getDanxiangjiangxuejin());
					xqInfo.setXueyou(pjTemp.getXueyou());
					xqInfo.setTuanyou(pjTemp.getTuanyou());
					xqInfo.setYxdxbys(pjTemp.getYxdxbys());
					if(pjTemp.getDxxx()!=null && !"".equals(pjTemp.getDxxx())){
						sg.setDxxx(pjTemp.getDxxx());
					}
					xueQiInfoMap.put(pjTemp.getXueqi(), xqInfo);
				}else{
					XueQiInfo xueqiInfo =new XueQiInfo();
					xueqiInfo.setJiangxuejin(pjTemp.getJiangxuejin());
					xueqiInfo.setDanxiangjiangxuejin(pjTemp.getDanxiangjiangxuejin());
					xueqiInfo.setXueyou(pjTemp.getXueyou());
					xueqiInfo.setTuanyou(pjTemp.getTuanyou());
					xueqiInfo.setYxdxbys(pjTemp.getYxdxbys());
					if(pjTemp.getDxxx()!=null && !"".equals(pjTemp.getDxxx())){
						sg.setDxxx(pjTemp.getDxxx());
					}
					xueQiInfoMap.put(pjTemp.getXueqi(), xueqiInfo);
				}
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
				if(xueQiInfoMap.containsKey(zzTemp.getXueqi())){
					XueQiInfo xqInfo =xueQiInfoMap.get(zzTemp.getXueqi());
					xqInfo.setGjjxj(zzTemp.getGjjxj());
					xqInfo.setGjlzjxj(zzTemp.getGjlzjxj());
					xqInfo.setGjzxj(zzTemp.getGjzxj());
					xqInfo.setOther(zzTemp.getOther());
					xueQiInfoMap.put(zzTemp.getXueqi(), xqInfo);
				}else{
					XueQiInfo xueqiInfo =new XueQiInfo();
					xueqiInfo.setGjjxj(zzTemp.getGjjxj());
					xueqiInfo.setGjlzjxj(zzTemp.getGjlzjxj());
					xueqiInfo.setGjzxj(zzTemp.getGjzxj());
					xueqiInfo.setOther(zzTemp.getOther());
					xueQiInfoMap.put(zzTemp.getXueqi(), xueqiInfo);
				}
			}
			sg.setXueQiInfo(xueQiInfoMap);
		}
		
		
		/**
		 * 其他获奖信息
		 */
		List<StudentQitahuojiang> qthjList=getStudentQitahuojiang(student_id);
		if(!qthjList.isEmpty()){
			Iterator<StudentQitahuojiang> qt =qthjList.iterator();
			while(qt.hasNext()){
				StudentQitahuojiang qthj =qt.next();
				if(xueQiInfoMap.containsKey(qthj.getXueqi())){
					XueQiInfo xqInfo =xueQiInfoMap.get(qthj.getXueqi());
					xqInfo.getOtherhuojianginfo().add(qthj.getOtherhuojianginfo());
					xueQiInfoMap.put(qthj.getXueqi(), xqInfo);
				}else{
					XueQiInfo xueqiInfo =new XueQiInfo();
					xueqiInfo.getOtherhuojianginfo().add(qthj.getOtherhuojianginfo());
					xueQiInfoMap.put(qthj.getXueqi(), xueqiInfo);
				}
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
