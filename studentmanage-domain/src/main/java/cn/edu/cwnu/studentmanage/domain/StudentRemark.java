/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentRemark
 * @author kkliu
 * @since 2017-11-15
 */
public class StudentRemark extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private Date remarkTime;
	private Date updateTime;

	public StudentRemark(){
		//默认无参构造方法
	}

	/**
	 * 获取 studentId
	 * @return
	 */
	public Integer getStudentId(){
		return studentId;
	}
	
	/**
	 * 设置 studentId
	 * @param studentId
	 */
	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	/**
	 * 获取 备注时间
	 * @return
	 */
	public Date getRemarkTime(){
		return remarkTime;
	}
	
	/**
	 * 设置 备注时间
	 * @param remarkTime
	 */
	public void setRemarkTime(Date remarkTime){
		this.remarkTime = remarkTime;
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
		StringBuffer buf = new StringBuffer("StudentRemark=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("remark=").append(getRemark()).append(", ");
				buf.append("remarkTime=").append(getRemarkTime()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}