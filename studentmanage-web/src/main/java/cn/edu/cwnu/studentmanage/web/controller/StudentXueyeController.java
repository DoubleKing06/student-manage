/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.edu.cwnu.studentmanage.web.CustomDateEditor;
import org.springframework.web.context.request.WebRequest;
import org.springframework.stereotype.Controller;
import cn.edu.cwnu.studentmanage.domain.StudentXueye;
import cn.edu.cwnu.studentmanage.domain.common.Message;
import cn.edu.cwnu.studentmanage.domain.common.Page;
import cn.edu.cwnu.studentmanage.service.StudentXueyeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *studentXueye controller层
 * @author kkliu
 * @since 2017-11-15
 */
@Controller
@RequestMapping(value = "/studentXueye")
public class StudentXueyeController{
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentXueyeController.class);
	@Resource private StudentXueyeService studentXueyeService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	/**
	 * 列表展示
	 * @param studentXueye 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(StudentXueye studentXueye,Page<StudentXueye> page,Model view) throws Exception{
		try {
			view.addAttribute("studentXueye",studentXueye);
			view.addAttribute("page",studentXueyeService.selectPage(studentXueye,page));			
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
				view.addAttribute("studentXueye",studentXueye);
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
				return null;
			}
			view.addAttribute("studentXueye",studentXueye);
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
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
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
	}
	
}