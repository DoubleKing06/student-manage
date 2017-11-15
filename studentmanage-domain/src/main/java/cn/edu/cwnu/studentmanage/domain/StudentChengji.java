/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentChengji
 * @author kkliu
 * @since 2017-11-15
 */
public class StudentChengji extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Integer studentId;
	private String xueqi;
	private Integer zhuanyePaiming;
	private Integer zonghePaiming;
	private Integer bukaokemu;
	private Date updateTime;

	public StudentChengji(){
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
	 * 获取 学期
	 * @return
	 */
	public String getXueqi(){
		return xueqi;
	}
	
	/**
	 * 设置 学期
	 * @param xueqi
	 */
	public void setXueqi(String xueqi){
		this.xueqi = xueqi;
	}

	/**
	 * 获取 专业成绩排名
	 * @return
	 */
	public Integer getZhuanyePaiming(){
		return zhuanyePaiming;
	}
	
	/**
	 * 设置 专业成绩排名
	 * @param zhuanyePaiming
	 */
	public void setZhuanyePaiming(Integer zhuanyePaiming){
		this.zhuanyePaiming = zhuanyePaiming;
	}

	/**
	 * 获取 综合成绩排名
	 * @return
	 */
	public Integer getZonghePaiming(){
		return zonghePaiming;
	}
	
	/**
	 * 设置 综合成绩排名
	 * @param zonghePaiming
	 */
	public void setZonghePaiming(Integer zonghePaiming){
		this.zonghePaiming = zonghePaiming;
	}

	/**
	 * 获取 补考科目
	 * @return
	 */
	public Integer getBukaokemu(){
		return bukaokemu;
	}
	
	/**
	 * 设置 补考科目
	 * @param bukaokemu
	 */
	public void setBukaokemu(Integer bukaokemu){
		this.bukaokemu = bukaokemu;
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
		StringBuffer buf = new StringBuffer("StudentChengji=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("xueqi=").append(getXueqi()).append(", ");
				buf.append("zhuanyePaiming=").append(getZhuanyePaiming()).append(", ");
				buf.append("zonghePaiming=").append(getZonghePaiming()).append(", ");
				buf.append("bukaokemu=").append(getBukaokemu()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}