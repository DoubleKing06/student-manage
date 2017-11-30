package cn.edu.cwnu.studentmanage.domain.vo;

import java.util.List;

import cn.edu.cwnu.studentmanage.domain.StudentBasicInfo;
import cn.edu.cwnu.studentmanage.domain.StudentRemark;
import cn.edu.cwnu.studentmanage.domain.StudentWeiji;

public class StudentBasicInfoVO {
	private StudentBasicInfo studentBasicInfo;
	private List<StudentWeiji> weiJiList;
	private List<StudentRemark> remarkList;
	
	
	
	
	public StudentBasicInfo getStudentBasicInfo() {
		return studentBasicInfo;
	}
	public void setStudentBasicInfo(StudentBasicInfo studentBasicInfo) {
		this.studentBasicInfo = studentBasicInfo;
	}
	public List<StudentWeiji> getWeiJiList() {
		return weiJiList;
	}
	public void setWeiJiList(List<StudentWeiji> weiJiList) {
		this.weiJiList = weiJiList;
	}
	public List<StudentRemark> getRemarkList() {
		return remarkList;
	}
	public void setRemarkList(List<StudentRemark> remarkList) {
		this.remarkList = remarkList;
	}
	
	

}
