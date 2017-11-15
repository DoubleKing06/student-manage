/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentWeiji
 * @author kkliu
 * @since 2017-11-15
 */
public class StudentWeiji extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private Date weijiTime;
	private String weijiInfo;
	private String result;
	private Date updateTime;

	public StudentWeiji(){
		//默认无参构造方法
	}

	/**
	 * 获取 学生编号
	 * @return
	 */
	public Integer getStudentId(){
		return studentId;
	}
	
	/**
	 * 设置 学生编号
	 * @param studentId
	 */
	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	/**
	 * 获取 违纪时间
	 * @return
	 */
	public Date getWeijiTime(){
		return weijiTime;
	}
	
	/**
	 * 设置 违纪时间
	 * @param weijiTime
	 */
	public void setWeijiTime(Date weijiTime){
		this.weijiTime = weijiTime;
	}

	/**
	 * 获取 违纪情况
	 * @return
	 */
	public String getWeijiInfo(){
		return weijiInfo;
	}
	
	/**
	 * 设置 违纪情况
	 * @param weijiInfo
	 */
	public void setWeijiInfo(String weijiInfo){
		this.weijiInfo = weijiInfo;
	}

	/**
	 * 获取 违纪备注
	 * @return
	 */
	public String getResult(){
		return result;
	}
	
	/**
	 * 设置 违纪备注
	 * @param result
	 */
	public void setResult(String result){
		this.result = result;
	}

	/**
	 * 获取 更新时间
	 * @return
	 */
	public Date getUpdateTime(){
		return updateTime;
	}
	
	/**
	 * 设置 更新时间
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}
	@Override
    public String toString() {
		StringBuffer buf = new StringBuffer("StudentWeiji=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("weijiTime=").append(getWeijiTime()).append(", ");
				buf.append("weijiInfo=").append(getWeijiInfo()).append(", ");
				buf.append("result=").append(getResult()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}