/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentZizhu
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentZizhu extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private String xueqi;
	private String gjjxj;
	private String gjlzjxj;
	private String gjzxj;
	private String other;
	private Date updateTime;

	public StudentZizhu(){
		//默认无参构造方法
	}

	/**
	 * 获取 studentId
	 * @return
	 */
	public Long getStudentId(){
		return studentId;
	}
	
	/**
	 * 设置 studentId
	 * @param studentId
	 */
	public void setStudentId(Long studentId){
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
	 * 获取 国家奖学金
	 * @return
	 */
	public String getGjjxj(){
		return gjjxj;
	}
	
	/**
	 * 设置 国家奖学金
	 * @param gjjxj
	 */
	public void setGjjxj(String gjjxj){
		this.gjjxj = gjjxj;
	}

	/**
	 * 获取 国家励志奖学金
	 * @return
	 */
	public String getGjlzjxj(){
		return gjlzjxj;
	}
	
	/**
	 * 设置 国家励志奖学金
	 * @param gjlzjxj
	 */
	public void setGjlzjxj(String gjlzjxj){
		this.gjlzjxj = gjlzjxj;
	}

	/**
	 * 获取 国家助学金
	 * @return
	 */
	public String getGjzxj(){
		return gjzxj;
	}
	
	/**
	 * 设置 国家助学金
	 * @param gjzxj
	 */
	public void setGjzxj(String gjzxj){
		this.gjzxj = gjzxj;
	}

	/**
	 * 获取 其他社会资助
	 * @return
	 */
	public String getOther(){
		return other;
	}
	
	/**
	 * 设置 其他社会资助
	 * @param other
	 */
	public void setOther(String other){
		this.other = other;
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
		StringBuffer buf = new StringBuffer("StudentZizhu=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("xueqi=").append(getXueqi()).append(", ");
				buf.append("gjjxj=").append(getGjjxj()).append(", ");
				buf.append("gjlzjxj=").append(getGjlzjxj()).append(", ");
				buf.append("gjzxj=").append(getGjzxj()).append(", ");
				buf.append("other=").append(getOther()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}