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
import cn.edu.cwnu.studentmanage.domain.StudentRemark;
import cn.edu.cwnu.studentmanage.domain.StudentRemarkVO;
import cn.edu.cwnu.studentmanage.domain.StudentWeiji;
import cn.edu.cwnu.studentmanage.domain.StudentWeijiVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentWeijiService;
import cn.edu.cwnu.studentmanage.service.StudentWeijiVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentWeiji controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentWeiji")
public class StudentWeijiController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentWeijiController.class);
	@Resource private StudentWeijiService studentWeijiService;
	@Resource private StudentWeijiVOService studentWeijiVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	/**
	 * 通过学号和学期搜索信息
	 * @param studentWeijiVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentWeijiSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentWeijiSearch(StudentWeijiVO studentWeijiVO,Page<StudentWeijiVO> page,Model view) throws Exception{
		try {
			//view.addAttribute("studentWeiji",studentWeijiVO);
			
			if(studentWeijiVO.getXuehao() == null || "".equals(studentWeijiVO.getXuehao())){
				view.addAttribute("page",studentWeijiVOService.selectPage(studentWeijiVO,page));			
				return "studentWeiji/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentWeijiVO.getXuehao());
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			studentWeijiVO.setStudentId(list.get(0).getId());
			studentWeijiVO.setXueqi(studentWeijiVO.getXueqi());
			
			
			
			view.addAttribute("page",studentWeijiVOService.selectPage(studentWeijiVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentWeiji/list";
	}
	
	
	
	
	
	
	
	
	
	/**
	 * 列表展示
	 * @param studentWeiji 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentWeijiVO studentWeijiVO,Page<StudentWeijiVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentWeiji",studentWeijiVO);
			view.addAttribute("page",studentWeijiVOService.selectPage(studentWeijiVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentWeiji/list";
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
				StudentWeiji studentWeiji = studentWeijiService.selectEntry(id);
				if(studentWeiji == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentWeiji.getStudentId().toString()));
				if(sbi == null){
					return null;
				}
				StudentWeijiVO studentWeijiVO =converToVO(studentWeiji);
				
				studentWeijiVO.setXuehao(sbi.getXuehao());
				studentWeijiVO.setName(sbi.getName());
				
				
				view.addAttribute("studentWeiji",studentWeijiVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentWeiji/edit";
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
			int res = studentWeijiService.deleteByKey(id);
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
			StudentWeiji studentWeiji = studentWeijiService.selectEntry(id);
			if(studentWeiji == null) {
				return null;
			}
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentWeiji.getStudentId().toString()));
			if(sbi == null){
				return null;
			}
			StudentWeijiVO studentWeijiVO =converToVO(studentWeiji);
			
			studentWeijiVO.setXuehao(sbi.getXuehao());
			studentWeijiVO.setName(sbi.getName());
			
			view.addAttribute("studentWeiji",studentWeijiVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentWeiji/view";
	}
	
	/**
	 * 保存方法
	 * @param studentWeiji 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentWeijiVO studentWeijiVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentWeijiVO.getXuehao()) || StringUtils.isEmpty(studentWeijiVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentWeijiVO.getXuehao());
    		sbi.setName(studentWeijiVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentWeijiVO.getStudentId()) && studentWeijiVO.getStudentId()!=null){
    			if(!studentWeijiVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    		}
    		
    		StudentWeiji studentWeiji =converToPO(studentWeijiVO);
    		if("".equals(studentWeijiVO.getStudentId()) || studentWeijiVO.getStudentId()==null){
    			studentWeiji.setStudentId(list.get(0).getId());
    		}	    		
    		
    		
			int res = studentWeijiService.saveOrUpdate(studentWeiji);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}
	
	
	/**
	 * 转换格式
	 * StudentChengWeiji  to  StudentChengWeijiVO
	 * @param StudentChengWeiji
	 * @return
	 */
	public StudentWeijiVO converToVO(StudentWeiji studentWeiji){
		StudentWeijiVO WeijiVO=new StudentWeijiVO();
		WeijiVO.setId(studentWeiji.getId());
		WeijiVO.setStudentId(studentWeiji.getStudentId());
		WeijiVO.setXueqi(studentWeiji.getXueqi());
		WeijiVO.setWeijiInfo(studentWeiji.getWeijiInfo());
		WeijiVO.setResult(studentWeiji.getResult());
		
		WeijiVO.setWeijiTime(studentWeiji.getWeijiTime());
		WeijiVO.setUpdateTime(new Date());
		
		return WeijiVO;
	}
	
	
	/**
	 * VO 转换成 PO
	 * studentChengWeijiVO TO studentWeiji
	 * @param studentWeijiVO
	 * @return
	 */
	public StudentWeiji converToPO(StudentWeijiVO studentWeijiVO){
		StudentWeiji studentWeiji =new StudentWeiji();
		studentWeiji.setId(studentWeijiVO.getId());
		studentWeiji.setStudentId(studentWeijiVO.getStudentId());
		studentWeiji.setXueqi(studentWeijiVO.getXueqi());
		studentWeiji.setUpdateTime(new Date());
		studentWeiji.setWeijiInfo(studentWeijiVO.getWeijiInfo());
		studentWeiji.setResult(studentWeijiVO.getResult());
		
		studentWeiji.setWeijiTime(studentWeijiVO.getWeijiTime());
		
		
		return studentWeiji;
	}
	
	
}