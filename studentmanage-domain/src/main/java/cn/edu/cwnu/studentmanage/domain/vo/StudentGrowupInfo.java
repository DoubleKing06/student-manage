/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain.vo;

import java.util.List;

import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentBasicInfo
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentGrowupInfo extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String name;
	private String xuehao;
	private String zhuanye;
	private String jclass;
	private Float cet4;
	private String putonghua;
	private String sanbizi;
	private String dxxx;
	private List<XueQiInfo> xueQiInfoList;

	public StudentGrowupInfo(){
		//默认无参构造方法
	}

	
}