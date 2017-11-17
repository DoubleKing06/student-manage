/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentXueye
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentXueye extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private Float cet4;
	private String sanbizi;
	private String putonghua;
	private Date updateTime;

	public StudentXueye(){
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
	 * 获取 cet4
	 * @return
	 */
	public Float getCet4(){
		return cet4;
	}
	
	/**
	 * 设置 cet4
	 * @param cet4
	 */
	public void setCet4(Float cet4){
		this.cet4 = cet4;
	}

	/**
	 * 获取 三笔字
	 * @return
	 */
	public String getSanbizi(){
		return sanbizi;
	}
	
	/**
	 * 设置 三笔字
	 * @param sanbizi
	 */
	public void setSanbizi(String sanbizi){
		this.sanbizi = sanbizi;
	}

	/**
	 * 获取 普通话
	 * @return
	 */
	public String getPutonghua(){
		return putonghua;
	}
	
	/**
	 * 设置 普通话
	 * @param putonghua
	 */
	public void setPutonghua(String putonghua){
		this.putonghua = putonghua;
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
		StringBuffer buf = new StringBuffer("StudentXueye=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("cet4=").append(getCet4()).append(", ");
				buf.append("sanbizi=").append(getSanbizi()).append(", ");
				buf.append("putonghua=").append(getPutonghua()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}