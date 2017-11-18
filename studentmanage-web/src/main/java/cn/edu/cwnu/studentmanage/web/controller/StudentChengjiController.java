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
import cn.edu.cwnu.studentmanage.domain.StudentChengji;
import cn.edu.cwnu.studentmanage.domain.StudentChengjiVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentChengji controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentChengji")
public class StudentChengjiController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentChengjiController.class);
	@Resource private StudentChengjiService studentChengjiService;
	@Resource private StudentChengjiVOService studentChengjiVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	/**
	 * 列表展示
	 * @param studentChengji 实体对象
	 * @param page 分页对象
	 * @return
	 */
/*	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentChengji studentChengji,Page<StudentChengji> page,Model view) throws Exception{
		try {
			view.addAttribute("studentChengji",studentChengji);
			view.addAttribute("page",studentChengjiService.selectPage(studentChengji,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentChengji/list";
	}*/
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentChengjiVO studentChengjiVO,Page<StudentChengjiVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentChengji",studentChengjiVO);
			view.addAttribute("page",studentChengjiVOService.selectPage(studentChengjiVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentChengji/list";
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
				StudentChengji studentChengji = studentChengjiService.selectEntry(id);
				if(studentChengji == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				view.addAttribute("studentChengji",studentChengji);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentChengji/edit";
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
			int res = studentChengjiService.deleteByKey(id);
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
			StudentChengji studentChengji = studentChengjiService.selectEntry(id);
			if(studentChengji == null) {
				return null;
			}
			view.addAttribute("studentChengji",studentChengji);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentChengji/view";
	}
	
	/**
	 * 保存方法
	 * @param studentChengji 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentChengjiVO studentChengjiVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentChengjiVO.getXuehao()) && StringUtils.isEmpty(studentChengjiVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentChengjiVO.getXuehao());
    		sbi.setName(studentChengjiVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		StudentChengji studentChengji =new StudentChengji();
    		studentChengji.setStudentId(list.get(0).getId());
    		studentChengji.setZhuanyePaiming(studentChengjiVO.getZhuanyePaiming());
    		studentChengji.setZonghePaiming(studentChengjiVO.getZonghePaiming());
    		studentChengji.setXueqi(studentChengjiVO.getXueqi());
    		studentChengji.setBukaokemu(studentChengjiVO.getBukaokemu());
			int res = studentChengjiService.saveOrUpdate(studentChengji);
			
			//studentChengjiVOService.selectEntryList(studentChengjiVO);
			
			
			
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}
	
}