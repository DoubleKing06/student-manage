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
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiangVO;
import cn.edu.cwnu.studentmanage.domain.StudentRemark;
import cn.edu.cwnu.studentmanage.domain.StudentRemarkVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentRemarkService;
import cn.edu.cwnu.studentmanage.service.StudentRemarkVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentRemark controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentRemark")
public class StudentRemarkController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentRemarkController.class);
	@Resource private StudentRemarkService studentRemarkService;
	@Resource private StudentRemarkVOService studentRemarkVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	/**
	 * 通过学号和学期搜索信息
	 * @param studentRemarkVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentRemarkSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentRemarkSearch(StudentRemarkVO studentRemarkVO,Page<StudentRemarkVO> page,Model view) throws Exception{
		try {
			//view.addAttribute("studentRemark",studentRemarkVO);
			
			if(studentRemarkVO.getXuehao() == null || "".equals(studentRemarkVO.getXuehao())){
				view.addAttribute("page",studentRemarkVOService.selectPage(studentRemarkVO,page));			
				return "studentRemark/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentRemarkVO.getXuehao());
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			studentRemarkVO.setStudentId(list.get(0).getId());
			studentRemarkVO.setXueqi(studentRemarkVO.getXueqi());
			
			
			
			view.addAttribute("page",studentRemarkVOService.selectPage(studentRemarkVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentRemark/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 列表展示
	 * @param studentRemark 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentRemarkVO studentRemarkVO,Page<StudentRemarkVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentRemark",studentRemarkVO);
			view.addAttribute("page",studentRemarkVOService.selectPage(studentRemarkVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentRemark/list";
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
				StudentRemark studentRemark = studentRemarkService.selectEntry(id);
				if(studentRemark == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentRemark.getStudentId().toString()));
				if(sbi == null){
					return null;
				}
				StudentRemarkVO studentRemarkVO =converToVO(studentRemark);
				
				studentRemarkVO.setXuehao(sbi.getXuehao());
				studentRemarkVO.setName(sbi.getName());
				
				view.addAttribute("studentRemark",studentRemarkVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentRemark/edit";
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
			int res = studentRemarkService.deleteByKey(id);
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
			StudentRemark studentRemark = studentRemarkService.selectEntry(id);
			if(studentRemark == null) {
				throw new Exception("无此学生备注信息");
			}
			
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentRemark.getStudentId().toString()));
			if(sbi == null){
				throw new Exception("基本信息表无此学生");
			}
			StudentRemarkVO studentRemarkVO =converToVO(studentRemark);
			
			studentRemarkVO.setXuehao(sbi.getXuehao());
			studentRemarkVO.setName(sbi.getName());
			
			view.addAttribute("studentRemark",studentRemarkVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentRemark/view";
	}
	
	/**
	 * 保存方法
	 * @param studentRemark 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentRemarkVO studentRemarkVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentRemarkVO.getXuehao()) || StringUtils.isEmpty(studentRemarkVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentRemarkVO.getXuehao());
    		sbi.setName(studentRemarkVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		if(list.size()!=1 || !studentRemarkVO.getName().equals(list.get(0).getName()) || !studentRemarkVO.getXuehao().equals(list.get(0).getXuehao())){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentRemarkVO.getStudentId()) && studentRemarkVO.getStudentId()!=null){
    			if(!studentRemarkVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    		}
    		
    		StudentRemark studentRemark =converToPO(studentRemarkVO);
    		if("".equals(studentRemarkVO.getStudentId()) || studentRemarkVO.getStudentId()==null){
    			studentRemark.setStudentId(list.get(0).getId());
    		}	
    		
			int res = studentRemarkService.saveOrUpdate(studentRemark);
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
	 * StudentChengRemark  to  StudentChengRemarkVO
	 * @param StudentChengRemark
	 * @return
	 */
	public StudentRemarkVO converToVO(StudentRemark studentRemark){
		StudentRemarkVO remarkVO=new StudentRemarkVO();
		remarkVO.setId(studentRemark.getId());
		remarkVO.setStudentId(studentRemark.getStudentId());
		remarkVO.setXueqi(studentRemark.getXueqi());
		remarkVO.setRemark(studentRemark.getRemark());
		remarkVO.setRemarkTime(studentRemark.getRemarkTime());
		remarkVO.setUpdateTime(new Date());
		
		return remarkVO;
	}
	
	
	/**
	 * VO 转换成 PO
	 * studentChengRemarkVO TO studentRemark
	 * @param studentRemarkVO
	 * @return
	 */
	public StudentRemark converToPO(StudentRemarkVO studentRemarkVO){
		StudentRemark studentRemark =new StudentRemark();
		studentRemark.setId(studentRemarkVO.getId());
		studentRemark.setStudentId(studentRemarkVO.getStudentId());
		studentRemark.setXueqi(studentRemarkVO.getXueqi());
		studentRemark.setUpdateTime(new Date());
		studentRemark.setRemark(studentRemarkVO.getRemark());
		studentRemark.setRemarkTime(studentRemarkVO.getRemarkTime());
		
		
		return studentRemark;
	}	
	
	
	
	
}