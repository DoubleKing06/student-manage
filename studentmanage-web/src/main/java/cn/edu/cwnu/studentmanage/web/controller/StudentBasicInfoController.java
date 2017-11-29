/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;
import cn.edu.cwnu.studentmanage.domain.vo.XueQiInfo;
import cn.edu.cwnu.studentmanage.service.GetStudentAllInfoService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentBasicInfo controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentBasicInfo")
public class StudentBasicInfoController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentBasicInfoController.class);
	@Resource private StudentBasicInfoService studentBasicInfoService;
	@Resource private GetStudentAllInfoService getetStudentAllInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	/**
	 * 列表展示
	 * @param studentBasicInfo 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentBasicInfo studentBasicInfo,Page<StudentBasicInfo> page,Model view) throws Exception{
		try {
			view.addAttribute("studentBasicInfo",studentBasicInfo);
			view.addAttribute("page",studentBasicInfoService.selectPage(studentBasicInfo,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentBasicInfo/list";
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
				StudentBasicInfo studentBasicInfo = studentBasicInfoService.selectEntry(id);
				if(studentBasicInfo == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				view.addAttribute("studentBasicInfo",studentBasicInfo);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentBasicInfo/edit";
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
			int res = studentBasicInfoService.deleteByKey(id);
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
			StudentBasicInfo studentBasicInfo = studentBasicInfoService.selectEntry(id);
			if(studentBasicInfo == null) {
				return null;
			}
			view.addAttribute("studentBasicInfo",studentBasicInfo);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentBasicInfo/view";
	}
	/**
	 * 通过编号查看对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/getinfo/{id}",method=RequestMethod.GET)
	public String getinfo(@PathVariable Integer id,Model view) throws Exception{
		try {
			StudentGrowupInfoVO studentGrowupInfoVO = getetStudentAllInfoService.getStudentAllInfoVO(Long.valueOf(id.toString()));
			view.addAttribute("gu",studentGrowupInfoVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}
		
		return "studentBasicInfo/growUpView";
	}
	
	
	/**
	 * 通过编号查看对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/getPdf/{id}",method=RequestMethod.GET)
	public void getPdf(@PathVariable Integer id,HttpServletResponse response,Model view) throws Exception{
		try {
			ByteArrayOutputStream temp = getetStudentAllInfoService.getPdfOfStudentAllInfo(Long.valueOf(String.valueOf(id)));
			
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename="+ new String("学生成长记录.pdf".getBytes("gbk"),"ISO-8859-1"));  
			response.setContentLength(temp.size());
			
			
			
			temp.writeTo(response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}
		
	}
	
	/**
	 * 保存方法
	 * @param studentBasicInfo 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentBasicInfo studentBasicInfo,Model view) throws Exception{
    	Message msg= null;
    	try {
			int res = studentBasicInfoService.saveOrUpdate(studentBasicInfo);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}
	
}