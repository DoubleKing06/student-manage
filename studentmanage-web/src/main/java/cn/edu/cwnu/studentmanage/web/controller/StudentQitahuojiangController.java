/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiang;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiangVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangService;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentQitahuojiang controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentQitahuojiang")
public class StudentQitahuojiangController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentQitahuojiangController.class);
	@Resource private StudentQitahuojiangService studentQitahuojiangService;
	@Resource private StudentQitahuojiangVOService studentQitahuojiangVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	/**
	 * 通过学号和学期搜索信息
	 * @param studentQitahuojiangVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentQitahuojiangSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentQitahuojiangSearch(StudentQitahuojiangVO studentQitahuojiangVO,Page<StudentQitahuojiangVO> page,Model view) throws Exception{
		try {
			//view.addAttribute("studentQitahuojiang",studentQitahuojiangVO);
			
			if(studentQitahuojiangVO.getXuehao() == null || "".equals(studentQitahuojiangVO.getXuehao())){
				view.addAttribute("page",studentQitahuojiangVOService.selectPage(studentQitahuojiangVO,page));			
				return "studentQitahuojiang/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentQitahuojiangVO.getXuehao());
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			studentQitahuojiangVO.setStudentId(list.get(0).getId());
			studentQitahuojiangVO.setXueqi(studentQitahuojiangVO.getXueqi());
			
			
			
			view.addAttribute("page",studentQitahuojiangVOService.selectPage(studentQitahuojiangVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentQitahuojiang/list";
	}
	
	
	
	
	/**
	 * 列表展示
	 * @param studentQitahuojiang 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentQitahuojiangVO studentQitahuojiangVO,Page<StudentQitahuojiangVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentQitahuojiang",studentQitahuojiangVO);
			view.addAttribute("page",studentQitahuojiangVOService.selectPage(studentQitahuojiangVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentQitahuojiang/list";
	}
	
	/**
	 * 响应新增(修改)页面
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String edit(@PathVariable Integer id,Model view) throws Exception{
		try {
			if(id != null && id > 0) {
				StudentQitahuojiang studentQitahuojiang = studentQitahuojiangService.selectEntry(id);
				if(studentQitahuojiang == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					throw new Exception("无此学生其他获奖信息");
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentQitahuojiang.getStudentId().toString()));
				if(sbi == null){
					throw new Exception("基本信息表无此学生");
				}
				StudentQitahuojiangVO studentQitahuojiangVO =converToVO(studentQitahuojiang);
				
				studentQitahuojiangVO.setXuehao(sbi.getXuehao());
				studentQitahuojiangVO.setName(sbi.getName());		
				
				view.addAttribute("studentQitahuojiang",studentQitahuojiangVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentQitahuojiang/edit";
	}
	
	/**
	 * 通过编号删除对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Message del(@PathVariable Integer id,Model view) throws Exception{
    	Message msg = null;
    	try {
			int res = studentQitahuojiangService.deleteByKey(id);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure(e.getMessage());
		}finally{
		}

		return msg;
	}
	
	/**
	 * 通过编号查看对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String view(@PathVariable Integer id,Model view) throws Exception{
		try {
			StudentQitahuojiang studentQitahuojiang = studentQitahuojiangService.selectEntry(id);
			if(studentQitahuojiang == null) {
				return null;
			}
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentQitahuojiang.getStudentId().toString()));
			if(sbi == null){
				return null;
			}
			StudentQitahuojiangVO studentQitahuojiangVO =converToVO(studentQitahuojiang);
			
			studentQitahuojiangVO.setXuehao(sbi.getXuehao());
			studentQitahuojiangVO.setName(sbi.getName());	
			
			
			
			
			
			view.addAttribute("studentQitahuojiang",studentQitahuojiangVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentQitahuojiang/view";
	}
	
	/**
	 * 保存方法
	 * @param studentQitahuojiang 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentQitahuojiangVO studentQitahuojiangVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentQitahuojiangVO.getXuehao()) || StringUtils.isEmpty(studentQitahuojiangVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentQitahuojiangVO.getXuehao());
    		sbi.setName(studentQitahuojiangVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		if(list.size()!=1 || !studentQitahuojiangVO.getName().equals(list.get(0).getName()) || !studentQitahuojiangVO.getXuehao().equals(list.get(0).getXuehao())){
    			throw new Exception("学号与姓名不匹配");
    		}    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentQitahuojiangVO.getStudentId()) && studentQitahuojiangVO.getStudentId()!=null){
    			if(!studentQitahuojiangVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    			if("".equals(studentQitahuojiangVO.getXueqi()) || studentQitahuojiangVO.getXueqi()==null){
    				throw new Exception("学期不能为空");
    			}
    			
    		}
    		StudentQitahuojiang studentQitahuojiangTemp =new StudentQitahuojiang();
    		studentQitahuojiangTemp.setStudentId(studentQitahuojiangVO.getStudentId());
    		studentQitahuojiangTemp.setXueqi(studentQitahuojiangVO.getXueqi());
    		
    		List<StudentQitahuojiang> listTemp = studentQitahuojiangService.selectEntryList(studentQitahuojiangTemp);
    		if(3<=listTemp.size()){
    			throw new Exception("本学期已经添加超过3次");
    		}
    		
    		StudentQitahuojiang studentQitahuojiang =converToPO(studentQitahuojiangVO);
    		if("".equals(studentQitahuojiangVO.getStudentId()) || studentQitahuojiangVO.getStudentId()==null){
    			studentQitahuojiang.setStudentId(list.get(0).getId());
    		}	
    		
			int res = studentQitahuojiangService.saveOrUpdate(studentQitahuojiang);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure(e.getMessage());
		}finally{
		}
		return msg;
	}
	
	
	/**
	 * 转换格式
	 * StudentChengQitahuojiang  to  StudentChengQitahuojiangVO
	 * @param StudentChengQitahuojiang
	 * @return
	 */
	public StudentQitahuojiangVO converToVO(StudentQitahuojiang studentQitahuojiang){
		StudentQitahuojiangVO qtVO=new StudentQitahuojiangVO();
		qtVO.setId(studentQitahuojiang.getId());
		qtVO.setStudentId(studentQitahuojiang.getStudentId());
		qtVO.setXueqi(studentQitahuojiang.getXueqi());
		qtVO.setOtherhuojianginfo(studentQitahuojiang.getOtherhuojianginfo());
		qtVO.setUpdateTime(new Date());
		
		return qtVO;
	}
	
	
	/**
	 * VO 转换成 PO
	 * studentChengQitahuojiangVO TO studentQitahuojiang
	 * @param studentQitahuojiangVO
	 * @return
	 */
	public StudentQitahuojiang converToPO(StudentQitahuojiangVO studentQitahuojiangVO){
		StudentQitahuojiang studentQitahuojiang =new StudentQitahuojiang();
		studentQitahuojiang.setId(studentQitahuojiangVO.getId());
		studentQitahuojiang.setStudentId(studentQitahuojiangVO.getStudentId());
		studentQitahuojiang.setXueqi(studentQitahuojiangVO.getXueqi());
		studentQitahuojiang.setUpdateTime(new Date());
		studentQitahuojiang.setOtherhuojianginfo(studentQitahuojiangVO.getOtherhuojianginfo());
		return studentQitahuojiang;
	}	
	
	
	
	
	
}