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
 * @since 2017-11-17
 */
public class StudentQitahuojiang extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private String xueqi;
	private String otherhuojianginfo;
	private Date updateTime;

	public StudentQitahuojiang(){
		//默认无参构造方法
	}

	/**
	 * 获取 学生编号
	 * @return
	 */
	public Long getStudentId(){
		return studentId;
	}
	
	/**
	 * 设置 学生编号
	 * @param studentId
	 */
	public void setStudentId(Long studentId){
		this.studentId = studentId;
	}

	/**
	 * 获取 获奖时间
	 * @return
	 */
	public String getXueqi(){
		return xueqi;
	}
	
	/**
	 * 设置 获奖时间
	 * @param xueqi
	 */
	public void setXueqi(String xueqi){
		this.xueqi = xueqi;
	}

	/**
	 * 获取 获奖信息
	 * @return
	 */
	public String getOtherhuojianginfo(){
		return otherhuojianginfo;
	}
	
	/**
	 * 设置 获奖信息
	 * @param otherhuojianginfo
	 */
	public void setOtherhuojianginfo(String otherhuojianginfo){
		this.otherhuojianginfo = otherhuojianginfo;
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
				buf.append("xueqi=").append(getXueqi()).append(", ");
				buf.append("otherhuojianginfo=").append(getOtherhuojianginfo()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}