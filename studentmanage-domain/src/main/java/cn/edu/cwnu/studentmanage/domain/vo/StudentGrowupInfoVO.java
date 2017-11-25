/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain.vo;

import java.util.List;
import java.util.Map;

import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentBasicInfo
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentGrowupInfoVO extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String name;
	private String xuehao;
	private String zhuanye;
	private String banji;
	private Float cet4;
	private String putonghua;
	private String sanbizi;
	private String dxxx;
	private String nianjiCount;//年级总人数
	private String banjiCount;//班级总人数
	
	
	
	
	public String getNianjiCount() {
		return nianjiCount;
	}

	public void setNianjiCount(String nianjiCount) {
		this.nianjiCount = nianjiCount;
	}

	public String getBanjiCount() {
		return banjiCount;
	}

	public void setBanjiCount(String banjiCount) {
		this.banjiCount = banjiCount;
	}



	private Map<String,XueQiInfo> xueQiInfo;
	
	public String getBanji() {
		return banji;
	}

	public void setBanji(String banji) {
		this.banji = banji;
	}

	public Map<String, XueQiInfo> getXueQiInfo() {
		return xueQiInfo;
	}

	public void setXueQiInfo(Map<String, XueQiInfo> xueQiInfo) {
		this.xueQiInfo = xueQiInfo;
	}

	public StudentGrowupInfoVO(){
		//默认无参构造方法
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	public String getZhuanye() {
		return zhuanye;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}

	public Float getCet4() {
		return cet4;
	}

	public void setCet4(Float cet4) {
		this.cet4 = cet4;
	}

	public String getPutonghua() {
		return putonghua;
	}

	public void setPutonghua(String putonghua) {
		this.putonghua = putonghua;
	}

	public String getSanbizi() {
		return sanbizi;
	}

	public void setSanbizi(String sanbizi) {
		this.sanbizi = sanbizi;
	}

	public String getDxxx() {
		return dxxx;
	}

	public void setDxxx(String dxxx) {
		this.dxxx = dxxx;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	

	
}