/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentPingjiang
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentPingjiang extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long studentId;
	private String xueqi;
	private String jiangxuejin;
	private String danxiangjiangxuejin;
	private String xueyou;
	private String tuanyou;
	private String yxdxbys;
	private String dxxx;
	private Date updateTime;

	public StudentPingjiang(){
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
	 * 获取 奖学金
	 * @return
	 */
	public String getJiangxuejin(){
		return jiangxuejin;
	}
	
	/**
	 * 设置 奖学金
	 * @param jiangxuejin
	 */
	public void setJiangxuejin(String jiangxuejin){
		this.jiangxuejin = jiangxuejin;
	}

	/**
	 * 获取 单项奖学金
	 * @return
	 */
	public String getDanxiangjiangxuejin(){
		return danxiangjiangxuejin;
	}
	
	/**
	 * 设置 单项奖学金
	 * @param danxiangjiangxuejin
	 */
	public void setDanxiangjiangxuejin(String danxiangjiangxuejin){
		this.danxiangjiangxuejin = danxiangjiangxuejin;
	}

	/**
	 * 获取 学优
	 * @return
	 */
	public String getXueyou(){
		return xueyou;
	}
	
	/**
	 * 设置 学优
	 * @param xueyou
	 */
	public void setXueyou(String xueyou){
		this.xueyou = xueyou;
	}

	/**
	 * 获取 团优
	 * @return
	 */
	public String getTuanyou(){
		return tuanyou;
	}
	
	/**
	 * 设置 团优
	 * @param tuanyou
	 */
	public void setTuanyou(String tuanyou){
		this.tuanyou = tuanyou;
	}

	/**
	 * 获取 优秀大学毕业生
	 * @return
	 */
	public String getYxdxbys(){
		return yxdxbys;
	}
	
	/**
	 * 设置 优秀大学毕业生
	 * @param yxdxbys
	 */
	public void setYxdxbys(String yxdxbys){
		this.yxdxbys = yxdxbys;
	}

	/**
	 * 获取 党校学习
	 * @return
	 */
	public String getDxxx(){
		return dxxx;
	}
	
	/**
	 * 设置 党校学习
	 * @param dxxx
	 */
	public void setDxxx(String dxxx){
		this.dxxx = dxxx;
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
		StringBuffer buf = new StringBuffer("StudentPingjiang=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("studentId=").append(getStudentId()).append(", ");
				buf.append("xueqi=").append(getXueqi()).append(", ");
				buf.append("jiangxuejin=").append(getJiangxuejin()).append(", ");
				buf.append("danxiangjiangxuejin=").append(getDanxiangjiangxuejin()).append(", ");
				buf.append("xueyou=").append(getXueyou()).append(", ");
				buf.append("tuanyou=").append(getTuanyou()).append(", ");
				buf.append("yxdxbys=").append(getYxdxbys()).append(", ");
				buf.append("dxxx=").append(getDxxx()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}