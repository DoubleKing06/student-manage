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
import cn.edu.cwnu.studentmanage.domain.StudentChengjiVO;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentXueyeVO;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentXueyeVO;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentXueyeVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentXueye controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentXueye")
public class StudentXueyeController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentXueyeController.class);
	@Resource private StudentXueyeService studentXueyeService;
	@Resource private StudentXueyeVOService studentXueyeVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	
	@RequestMapping(value = "/studentXueyeSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentXueyeSearch(StudentXueyeVO studentXueyeVO,Page<StudentXueyeVO> page,Model view) throws Exception{
		try {
			//view.addAttribute("studentXueye",studentXueyeVO);
			
			if(studentXueyeVO.getXuehao() == null || "".equals(studentXueyeVO.getXuehao())){
				view.addAttribute("page",studentXueyeVOService.selectPage(studentXueyeVO,page));			
				return "studentXueye/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentXueyeVO.getXuehao());
			
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			
			
			
			
			studentXueyeVO.setStudentId(list.get(0).getId());
			
			
			
			view.addAttribute("page",studentXueyeVOService.selectPage(studentXueyeVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentXueye/list";
	}
	
	/**
	 * 列表展示
	 * @param studentXueye 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentXueyeVO studentXueyeVO,Page<StudentXueyeVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentXueye",studentXueyeVO);
			view.addAttribute("page",studentXueyeVOService.selectPage(studentXueyeVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentXueye/list";
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
				StudentXueye studentXueye = studentXueyeService.selectEntry(id);
				if(studentXueye == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentXueye.getStudentId().toString()));
				if(sbi == null){
					return null;
				}
				StudentXueyeVO studentXueyeVO =converToVO(studentXueye);
				
				studentXueyeVO.setXuehao(sbi.getXuehao());
				studentXueyeVO.setName(sbi.getName());	
				
				view.addAttribute("studentXueye",studentXueyeVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentXueye/edit";
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
			int res = studentXueyeService.deleteByKey(id);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
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
			StudentXueye studentXueye = studentXueyeService.selectEntry(id);
			if(studentXueye == null) {
				throw new Exception("无此学生学业信息");
			}
			
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentXueye.getStudentId().toString()));
			if(sbi == null){
				throw new Exception("基本信息表无此学生");
			}
			StudentXueyeVO studentXueyeVO =converToVO(studentXueye);
			
			studentXueyeVO.setXuehao(sbi.getXuehao());
			studentXueyeVO.setName(sbi.getName());
			view.addAttribute("studentXueye",studentXueyeVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentXueye/view";
	}
	
	/**
	 * 保存方法
	 * @param studentXueye 实体对象
	 * @return
	 */
/*	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentXueye studentXueye,Model view) throws Exception{
    	Message msg= null;
    	try {
			int res = studentXueyeService.saveOrUpdate(studentXueye);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}*/
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentXueyeVO studentXueyeVO,Model view) throws Exception{
		Message msg= null;
		try {
			StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentXueyeVO.getXuehao()) || StringUtils.isEmpty(studentXueyeVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentXueyeVO.getXuehao());
    		sbi.setName(studentXueyeVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentXueyeVO.getStudentId()) && studentXueyeVO.getStudentId()!=null){
    			if(!studentXueyeVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    		}
    		
    		StudentXueye studentXueye =converToPO(studentXueyeVO);
    		if("".equals(studentXueyeVO.getStudentId()) || studentXueyeVO.getStudentId()==null){
    			studentXueye.setStudentId(list.get(0).getId());
    		}
			
			int res = studentXueyeService.saveOrUpdate(studentXueye);
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
	 * StudentChengXueye  to  StudentChengXueyeVO
	 * @param StudentChengXueye
	 * @return
	 */
	public StudentXueyeVO converToVO(StudentXueye studentXueye){
		StudentXueyeVO xyVO=new StudentXueyeVO();
		xyVO.setId(studentXueye.getId());
		xyVO.setStudentId(studentXueye.getStudentId());
		xyVO.setCet4(studentXueye.getCet4());
		xyVO.setSanbizi(studentXueye.getSanbizi());
		xyVO.setPutonghua(studentXueye.getPutonghua());
		xyVO.setUpdateTime(new Date());
		return xyVO;
	}
	
	
	/**
	 * VO 转换成 PO
	 * studentChengXueyeVO TO studentXueye
	 * @param studentXueyeVO
	 * @return
	 */
	public StudentXueye converToPO(StudentXueyeVO studentXueyeVO){
		StudentXueye studentXueye =new StudentXueye();
		studentXueye.setStudentId(studentXueyeVO.getStudentId());
		studentXueye.setCet4(studentXueyeVO.getCet4());
		studentXueye.setSanbizi(studentXueyeVO.getSanbizi());
		studentXueye.setPutonghua(studentXueyeVO.getPutonghua());
		studentXueye.setId(studentXueyeVO.getId());
		studentXueye.setUpdateTime(new Date());
		return studentXueye;
	}	
	
	
	
	
}