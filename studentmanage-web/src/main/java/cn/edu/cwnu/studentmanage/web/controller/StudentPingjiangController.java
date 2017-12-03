/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import cn.edu.cwnu.studentmanage.domain.StudentPingjiang;
import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangService;
import cn.edu.cwnu.studentmanage.service.StudentPingjiangVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentPingjiang controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentPingjiang")
public class StudentPingjiangController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentPingjiangController.class);
	@Resource private StudentPingjiangService studentPingjiangService;
	@Resource private StudentPingjiangVOService studentPingjiangVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	/**
	 * 通过学生学号和学期查询
	 * @param studentChengjiVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentPingjiangSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentPingjiangSearch(StudentPingjiangVO studentPingjiangVO,Page<StudentPingjiangVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentPingjiang",studentPingjiangVO);
			
			if(studentPingjiangVO.getXuehao() == null || "".equals(studentPingjiangVO.getXuehao())){
				view.addAttribute("page",studentPingjiangVOService.selectPage(studentPingjiangVO,page));			
				return "studentPingjiang/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentPingjiangVO.getXuehao());
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			studentPingjiangVO.setStudentId(list.get(0).getId());
			studentPingjiangVO.setXueqi(studentPingjiangVO.getXueqi());
			
			
			
			view.addAttribute("page",studentPingjiangVOService.selectPage(studentPingjiangVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentPingjiang/list";
	}
	
	
	
	/**
	 * 通过查询条件导出评优评奖信息
	 * @param studentPingjiangVO
	 * @param response
	 * @param view
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentPingjiangExport" ,method = {RequestMethod.GET,RequestMethod.POST})
	public void studentPingjiangExport(StudentPingjiangVO studentPingjiangVO, HttpServletResponse response, Model view) throws Exception{
		HSSFWorkbook wb = studentPingjiangVOService.studentPingjiangExport(studentPingjiangVO);
		
		response.setContentType("application/x-excel;charset=gbk");  
        response.setHeader("Content-Disposition", "attachment; filename="+ new String("学生评优评奖信息.xls".getBytes("gbk"),"ISO-8859-1"));  
        wb.write(response.getOutputStream());
	}
	
	
	
	
	/**
	 * 列表展示
	 * @param studentPingjiang 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentPingjiangVO studentPingjiangVO,Page<StudentPingjiangVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentPingjiang",studentPingjiangVO);
			view.addAttribute("page",studentPingjiangVOService.selectPage(studentPingjiangVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentPingjiang/list";
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
				StudentPingjiang studentPingjiang = studentPingjiangService.selectEntry(id);
				if(studentPingjiang == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					throw new Exception("无此学生评奖信息");
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentPingjiang.getStudentId().toString()));
				if(sbi == null){
					throw new Exception("基本信息表无此学生");
				}
				StudentPingjiangVO studentPingjiangVO =converToVO(studentPingjiang);
				
				studentPingjiangVO.setXuehao(sbi.getXuehao());
				studentPingjiangVO.setName(sbi.getName());				
				
				view.addAttribute("studentPingjiang",studentPingjiangVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentPingjiang/edit";
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
			int res = studentPingjiangService.deleteByKey(id);
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
			StudentPingjiang studentPingjiang = studentPingjiangService.selectEntry(id);
			if(studentPingjiang == null) {
				return null;
			}
			
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentPingjiang.getStudentId().toString()));
			if(sbi == null){
				return null;
			}
			StudentPingjiangVO studentPingjiangVO =converToVO(studentPingjiang);
			
			studentPingjiangVO.setXuehao(sbi.getXuehao());
			studentPingjiangVO.setName(sbi.getName());
			
			
			
			view.addAttribute("studentPingjiang",studentPingjiangVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentPingjiang/view";
	}
	
	/**
	 * 保存方法
	 * @param studentPingjiang 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentPingjiangVO studentPingjiangVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentPingjiangVO.getXuehao()) || StringUtils.isEmpty(studentPingjiangVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentPingjiangVO.getXuehao());
    		sbi.setName(studentPingjiangVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentPingjiangVO.getStudentId()) && studentPingjiangVO.getStudentId()!=null){
    			if(!studentPingjiangVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    		}
    		
    		StudentPingjiang studentPingjiang =converToPO(studentPingjiangVO);
    		if("".equals(studentPingjiangVO.getStudentId()) || studentPingjiangVO.getStudentId()==null){
    			studentPingjiang.setStudentId(list.get(0).getId());
    		}	
			int res = studentPingjiangService.saveOrUpdate(studentPingjiang);
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
	 * StudentChengPingjiang  to  StudentChengPingjiangVO
	 * @param StudentChengPingjiang
	 * @return
	 */
	public StudentPingjiangVO converToVO(StudentPingjiang studentPingjiang){
		StudentPingjiangVO pjVO=new StudentPingjiangVO();
		pjVO.setId(studentPingjiang.getId());
		pjVO.setStudentId(studentPingjiang.getStudentId());
		pjVO.setXueqi(studentPingjiang.getXueqi());
		pjVO.setJiangxuejin(studentPingjiang.getJiangxuejin());
		pjVO.setDanxiangjiangxuejin(studentPingjiang.getDanxiangjiangxuejin());
		pjVO.setXueyou(studentPingjiang.getXueyou());
		pjVO.setTuanyou(studentPingjiang.getTuanyou());
		pjVO.setYxdxbys(studentPingjiang.getYxdxbys());
		pjVO.setDxxx(studentPingjiang.getDxxx());
		pjVO.setUpdateTime(new Date());
		return pjVO;
	}
	
	
	/**
	 * VO 转换成 PO
	 * studentChengPingjiangVO TO studentPingjiang
	 * @param studentPingjiangVO
	 * @return
	 */
	public StudentPingjiang converToPO(StudentPingjiangVO studentPingjiangVO){
		StudentPingjiang studentPingjiang =new StudentPingjiang();
		studentPingjiang.setId(studentPingjiangVO.getId());
		studentPingjiang.setStudentId(studentPingjiangVO.getStudentId());
		studentPingjiang.setXueqi(studentPingjiangVO.getXueqi());
		studentPingjiang.setJiangxuejin(studentPingjiangVO.getJiangxuejin());
		studentPingjiang.setDanxiangjiangxuejin(studentPingjiangVO.getDanxiangjiangxuejin());
		studentPingjiang.setXueyou(studentPingjiangVO.getXueyou());
		studentPingjiang.setTuanyou(studentPingjiangVO.getTuanyou());
		studentPingjiang.setYxdxbys(studentPingjiangVO.getYxdxbys());
		studentPingjiang.setDxxx(studentPingjiangVO.getDxxx());
		studentPingjiang.setUpdateTime(new Date());
		return studentPingjiang;
	}	
	
	
	
	
	
	
	
	
}