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
import cn.edu.cwnu.studentmanage.domain.StudentPingjiangVO;
import cn.edu.cwnu.studentmanage.domain.StudentZizhu;
import cn.edu.cwnu.studentmanage.domain.StudentZizhuVO;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentBasicInfoService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuService;
import cn.edu.cwnu.studentmanage.service.StudentZizhuVOService;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;

/**
 *studentZizhu controller层
 * @author kkliu
 * @since 2017-11-17
 */
@Controller
@RequestMapping(value = "/studentZizhu")
public class StudentZizhuController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentZizhuController.class);
	@Resource private StudentZizhuService studentZizhuService;
	@Resource private StudentZizhuVOService studentZizhuVOService;
	@Resource private StudentBasicInfoService studentBasicInfoService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	
	/**
	 * 通过学生学号和学期查询
	 * @param studentZizhuVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentZizhuSearch" ,method = {RequestMethod.GET,RequestMethod.POST})
	public String studentZizhuSearch(StudentZizhuVO studentZizhuVO,Page<StudentZizhuVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentZizhu",studentZizhuVO);
			
			if(studentZizhuVO.getXuehao() == null || "".equals(studentZizhuVO.getXuehao())){
				view.addAttribute("page",studentZizhuVOService.selectPage(studentZizhuVO,page));			
				return "studentZizhu/list";
			}
			
			StudentBasicInfo studentBasicInfo =new StudentBasicInfo();
			studentBasicInfo.setXuehao(studentZizhuVO.getXuehao());
			List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(studentBasicInfo);
			studentZizhuVO.setStudentId(list.get(0).getId());
			studentZizhuVO.setXueqi(studentZizhuVO.getXueqi());
			
			
			
			view.addAttribute("page",studentZizhuVOService.selectPage(studentZizhuVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentZizhu/list";
	}
	/**
	 * 通过条件导出excel
	 * @param studentZizhuVO
	 * @param page
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/studentZizhuExport" ,method = {RequestMethod.GET,RequestMethod.POST})
	public void studentZizhuExport(StudentZizhuVO studentZizhuVO, HttpServletResponse response, Model view) throws Exception{
		HSSFWorkbook wb = studentZizhuVOService.studentZizhuExport(studentZizhuVO);
		
		response.setContentType("application/x-excel;charset=gbk");  
        response.setHeader("Content-Disposition", "attachment; filename="+ new String("学生资助情况.xls".getBytes("gbk"),"ISO-8859-1"));  
        wb.write(response.getOutputStream());
	}
	
	
	
	
	
	
	/**
	 * 列表展示
	 * @param studentZizhu 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentZizhuVO studentZizhuVO,Page<StudentZizhuVO> page,Model view) throws Exception{
		try {
			view.addAttribute("studentZizhu",studentZizhuVO);
			view.addAttribute("page",studentZizhuVOService.selectPage(studentZizhuVO,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "studentZizhu/list";
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
				StudentZizhu studentZizhu = studentZizhuService.selectEntry(id);
				if(studentZizhu == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				
				StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentZizhu.getStudentId().toString()));
				if(sbi == null){
					return null;
				}
				StudentZizhuVO studentZizhuVO =converToVO(studentZizhu);
				
				studentZizhuVO.setXuehao(sbi.getXuehao());
				studentZizhuVO.setName(sbi.getName());	
				
				
				
				view.addAttribute("studentZizhu",studentZizhuVO);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentZizhu/edit";
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
			int res = studentZizhuService.deleteByKey(id);
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
			StudentZizhu studentZizhu = studentZizhuService.selectEntry(id);
			if(studentZizhu == null) {
				return null;
			}
			
			StudentBasicInfo sbi = studentBasicInfoService.selectEntry(Integer.valueOf(studentZizhu.getStudentId().toString()));
			if(sbi == null){
				return null;
			}
			StudentZizhuVO studentZizhuVO =converToVO(studentZizhu);
			
			studentZizhuVO.setXuehao(sbi.getXuehao());
			studentZizhuVO.setName(sbi.getName());	
			
			
			
			view.addAttribute("studentZizhu",studentZizhuVO);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "studentZizhu/view";
	}
	
	/**
	 * 保存方法
	 * @param studentZizhu 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(StudentZizhuVO studentZizhuVO,Model view) throws Exception{
    	Message msg= null;
    	try {
    		
    		StudentBasicInfo sbi=new StudentBasicInfo();
    		if(StringUtils.isEmpty(studentZizhuVO.getXuehao()) || StringUtils.isEmpty(studentZizhuVO.getName())){
    			throw new Exception("学号或姓名不能为空");
    		}
    		sbi.setXuehao(studentZizhuVO.getXuehao());
    		sbi.setName(studentZizhuVO.getName());
    		List<StudentBasicInfo> list = studentBasicInfoService.selectEntryList(sbi);
    		if(list.isEmpty()){
    			throw new Exception("学号与姓名不匹配");
    		}
    		
    		/**
    		 * 需要校验studentID的正确性
    		 */
    		if(!"".equals(studentZizhuVO.getStudentId()) && studentZizhuVO.getStudentId()!=null){
    			if(!studentZizhuVO.getStudentId().equals(list.get(0).getId())){
    				throw new Exception("学号与姓名不正确");
    			}
    		}
    		
    		StudentZizhu studentZizhu =converToPO(studentZizhuVO);
    		if("".equals(studentZizhuVO.getStudentId()) || studentZizhuVO.getStudentId()==null){
    			studentZizhu.setStudentId(list.get(0).getId());
    		}	
    		
    		
			int res = studentZizhuService.saveOrUpdate(studentZizhu);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}
	
	
/**
 * 转换格式  studentZizhu To studentZizhuVO
 * @param studentZizhu
 * @return
 */
	public StudentZizhuVO converToVO(StudentZizhu studentZizhu){
		StudentZizhuVO zzVO=new StudentZizhuVO();
		zzVO.setId(studentZizhu.getId());
		zzVO.setStudentId(studentZizhu.getStudentId());
		zzVO.setXueqi(studentZizhu.getXueqi());
		zzVO.setGjjxj(studentZizhu.getGjjxj());
		zzVO.setGjlzjxj(studentZizhu.getGjlzjxj());
		zzVO.setGjzxj(studentZizhu.getGjzxj());
		zzVO.setOther(studentZizhu.getOther());
		
		zzVO.setUpdateTime(new Date());
		return zzVO;
	}
	
	
/**
 * 转换格式  studentZizhuVO To studentZizhu
 * @param studentZizhuVO
 * @return
 */
	public StudentZizhu converToPO(StudentZizhuVO studentZizhuVO){
		StudentZizhu studentZizhu =new StudentZizhu();
		studentZizhu.setId(studentZizhuVO.getId());
		studentZizhu.setStudentId(studentZizhuVO.getStudentId());
		studentZizhu.setXueqi(studentZizhuVO.getXueqi());
		studentZizhu.setGjjxj(studentZizhuVO.getGjjxj());
		studentZizhu.setGjlzjxj(studentZizhuVO.getGjlzjxj());
		studentZizhu.setGjzxj(studentZizhuVO.getGjzxj());
		studentZizhu.setOther(studentZizhuVO.getOther());
		studentZizhu.setUpdateTime(new Date());
		return studentZizhu;
	}	
	
	
	
	
	
}