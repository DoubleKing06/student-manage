/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.web.controller;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

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
			StudentGrowupInfoVO studentGrowupInfoVO = getetStudentAllInfoService.getStudentAllInfo(Long.valueOf(id.toString()));
			/**
			 * 开始拼接数据
			 */
			Map<String,XueQiInfo> xueQiInfoMap = (Map<String, XueQiInfo>) studentGrowupInfoVO.getXueQiInfo();
			Set<String> itMap = xueQiInfoMap.keySet();
			Map<String,String> mapTemp =new HashMap<String,String>();
			for (String key : itMap) {
				StringBuffer sBuffer = new StringBuffer("	");
				XueQiInfo xq = xueQiInfoMap.get(key);
				/**
				 * 成绩相关
				 */
				//专业排名
				if(xq.getZhuanyePaiming()!=null){
					sBuffer.append("专业排名："+xq.getZhuanyePaiming()+"/"+studentGrowupInfoVO.getBanjiCount()+",");
				}
				//综合排名
				if(xq.getZonghePaiming()!=null){
					sBuffer.append("综合排名："+xq.getZonghePaiming()+"/"+studentGrowupInfoVO.getNianjiCount()+",");
				}
				//补考科目
				if(xq.getBukaokemu()!=null){
					sBuffer.append("补考科目:"+xq.getBukaokemu()+",");
				}
				/**
				 * 资助相关
				 */
				//国家奖学金
				if(xq.getGjjxj()!=null){
					sBuffer.append(xq.getGjjxj()+",");
				}
				//国家励志奖学金
				if(xq.getGjlzjxj()!=null){
					sBuffer.append(xq.getGjlzjxj()+",");
				}
				//国家助学金
				if(xq.getGjzxj()!=null){
					sBuffer.append(xq.getGjzxj()+",");
				}
				
				/**
				 * 评奖相关
				 */
				//奖学金
				if(xq.getJiangxuejin()!=null){
					sBuffer.append(xq.getJiangxuejin()+",");
				}
				//单项奖学金
				if(xq.getDanxiangjiangxuejin()!=null){
					sBuffer.append(xq.getDanxiangjiangxuejin()+",");
				}
				//学优
				if(xq.getXueyou()!=null){
					sBuffer.append(xq.getXueyou()+",");
				}
				//团优
				if(xq.getTuanyou()!=null){
					sBuffer.append(xq.getTuanyou()+",");
				}
				//优秀大学毕业生
				if(xq.getYxdxbys()!=null){
					sBuffer.append(xq.getYxdxbys()+",");
				}
				
				if(xq.getOtherhuojianginfo()!=null){
					List<String> qthj =xq.getOtherhuojianginfo();
					for(int i=0;i<qthj.size();i++){
						String bd =(i==qthj.size()-1)?"。":",";
						sBuffer.append(qthj.get(i)+bd);
					}
				}
				sBuffer = sBuffer.deleteCharAt(sBuffer.length()-1).append("。");
				String sBufferTemp = sBuffer.toString().replace("无,", "").replace("无", "").replace(",。", "。");
				mapTemp.put(key, sBufferTemp);
			}
			studentGrowupInfoVO.setXueQiInfo(mapTemp);
			if(studentGrowupInfoVO == null) {
				return null;
			}
			view.addAttribute("studentGrowupInfoVO",studentGrowupInfoVO);
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
	
}