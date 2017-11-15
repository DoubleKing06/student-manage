/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentQitahuojiang
 * @author kkliu
 * @since 2017-11-15
 */
public class StudentQitahuojiang extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private Date time;
	private String huojianginfo;
	private Date updateTime;

	public StudentQitahuojiang(){
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
	 * 获取 获奖时间
	 * @return
	 */
	public Date getTime(){
		return time;
	}
	
	/**
	 * 设置 获奖时间
	 * @param time
	 */
	public void setTime(Date time){
		this.time = time;
	}

	/**
	 * 获取 获奖信息
	 * @return
	 */
	public String getHuojianginfo(){
		return huojianginfo;
	}
	
	/**
	 * 设置 获奖信息
	 * @param huojianginfo
	 */
	public void setHuojianginfo(String huojianginfo){
		this.huojianginfo = huojianginfo;
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
		StringBuffer buf = new StringBuffer("StudentQitahuojiang=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("time=").append(getTime()).append(", ");
				buf.append("huojianginfo=").append(getHuojianginfo()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}