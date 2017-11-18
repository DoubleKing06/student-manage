package cn.edu.cwnu.studentmanage.domain.vo;

import cn.edu.cwnu.studentmanage.domain.StudentChengji;

public class StudentChengjiVO extends StudentChengji {
	private String xuehao;//学生学号
	private String name;//学生姓名
	private String banji;//学生班级
	
	
	
	public String getBanji() {
		return banji;
	}
	public void setBanji(String banji) {
		this.banji = banji;
	}
	public String getXuehao() {
		return xuehao;
	}
	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	

}
