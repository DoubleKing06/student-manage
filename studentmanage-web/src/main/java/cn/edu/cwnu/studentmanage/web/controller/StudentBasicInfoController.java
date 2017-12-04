/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
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
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.domain.StudentQitahuojiang;
import cn.edu.cwnu.studentmanage.domain.StudentRemark;
import cn.edu.cwnu.studentmanage.domain.StudentWeiji;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.domain.vo.StudentGrowupInfoVO;
import cn.edu.cwnu.studentmanage.service.GetStudentAllInfoService;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentChengjiService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;
import cn.edu.cwnu.studentmanage.service.StudentQitahuojiangService;
import cn.edu.cwnu.studentmanage.service.StudentRemarkService;
import cn.edu.cwnu.studentmanage.service.StudentWeijiService;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;
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
	@Resource private GetStudentAllInfoService getStudentAllInfoService;
	@Resource private StudentChengjiService studentChengjiService;
	@Resource private StudentXueyeService studentXueyeService;
	@Resource private StudentZizhuService studentZizhuService;
	@Resource private StudentPingjiangService studentPingjiangService;
	@Resource private StudentQitahuojiangService studentQitahuojiangService;
	@Resource private StudentRemarkService studentRemarkService;
	@Resource private StudentWeijiService studentWeijiService;
	
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
					throw new Exception("基本信息表无此学生");
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
			
			StudentChengji cj =new StudentChengji();
			cj.setStudentId(Long.valueOf(String.valueOf(id)));
			studentChengjiService.deleteByCondtion(cj);
			
			StudentXueye xy =new StudentXueye();
			xy.setStudentId(Long.valueOf(String.valueOf(id)));
			studentXueyeService.deleteByCondtion(xy);
			
			StudentPingjiang pj =new StudentPingjiang();
			pj.setStudentId(Long.valueOf(String.valueOf(id)));
			studentPingjiangService.deleteByCondtion(pj);
			
			StudentZizhu zz =new StudentZizhu();
			zz.setStudentId(Long.valueOf(String.valueOf(id)));
			studentZizhuService.deleteByCondtion(zz);
			
			StudentQitahuojiang qthj =new StudentQitahuojiang();
			qthj.setStudentId(Long.valueOf(String.valueOf(id)));
			studentQitahuojiangService.deleteByCondtion(qthj);
			
			StudentRemark re =new StudentRemark();
			re.setStudentId(Long.valueOf(String.valueOf(id)));
			studentRemarkService.deleteByCondtion(re);
			
			StudentWeiji wj =new StudentWeiji();
			wj.setStudentId(Long.valueOf(String.valueOf(id)));
			studentWeijiService.deleteByCondtion(wj);
			
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
				throw new Exception("基本信息表无此学生");
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

	/**
	 * 通过编号查看对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value={"/getinfo"},method=RequestMethod.GET)
	public String getinfo( Integer id,String text,Model view) throws Exception{
		List type=StringUtils.isEmpty(text)?null: Arrays.asList(text.split(","));
		try {
			StudentGrowupInfoVO studentGrowupInfoVO = getStudentAllInfoService.getStudentAllInfoVO(Long.valueOf(id.toString()),type);
			view.addAttribute("gu",studentGrowupInfoVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}
		
		return "studentBasicInfo/growUpView";
	}
	
	
	/**
	 * 获取学生成长记录表PDF
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/getPdf",method=RequestMethod.GET)
	public void getPdf(Integer id,HttpServletResponse response,String text,Model view) throws Exception{
		List type=StringUtils.isEmpty(text)?null: Arrays.asList(text.split(","));
		try {
			ByteArrayOutputStream temp = getStudentAllInfoService.getPdfOfStudentGrowUp(Long.valueOf(String.valueOf(id)),type);
			StudentBasicInfo st =studentBasicInfoService.selectEntry(id);
			String fileName ="学生成长记录信息_"+st.getName()+".pdf";
			
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(fileName.getBytes("gbk"),"ISO-8859-1"));  
			response.setContentLength(temp.size());
			
			
			
			temp.writeTo(response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}
		
	}
	
	/**
	 * 获取学生基本信息PDF
	 * @param id
	 * @param response
	 * @param view
	 * @throws Exception
	 */
	@RequestMapping(value="/getBasicPdf",method=RequestMethod.GET)
	public void getBasicPdf(Integer id,HttpServletResponse response,String text,Model view) throws Exception{
		List type=StringUtils.isEmpty(text)?null: Arrays.asList(text.split(","));
		try {
			ByteArrayOutputStream temp = getStudentAllInfoService.getPdfOfStudentBasicInfo(Long.valueOf(String.valueOf(id)),type);
			StudentBasicInfo st =studentBasicInfoService.selectEntry(id);
			String fileName ="学生基本信息_"+st.getName()+".pdf";
			
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename="+ new String(fileName.getBytes("gbk"),"ISO-8859-1"));  
			response.setContentLength(temp.size());
			
			
			
			temp.writeTo(response.getOutputStream());
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}
		
	}
	
}