/*
 * Copyright (c) 2017 cwnu.edu.cn All rights reserved.
 * 本软件源代码版权归----所有,未经许可不得任意复制与传播.
 */
package cn.edu.cwnu.studentmanage.domain;

import java.util.Date;
import cn.edu.cwnu.studentmanage.domain.base.BaseDomain;

/**
 * studentBasicInfo
 * @author kkliu
 * @since 2017-11-17
 */
public class StudentBasicInfo extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private String xuehao;
	private String name;
	private String minzu;
	private String zhengzhi;
	private String idNumber;
	private String jiguan;
	private String xueyuan;
	private String zhuanye;
	private String luqupici;
	private String banji;
	private String xiaoqu;
	private String gongyu;
	private String qinshihao;
	private String address;
	private String phone;
	private String qq;
	private String jiazhang1;
	private String jiazhang1Phone;
	private String jiazhang2;
	private String jiazhang2Phone;
	private Date updateTime;
	private String nianji;
	private String sex;

	public StudentBasicInfo(){
		//默认无参构造方法
	}

	/**
	 * 获取 学号
	 * @return
	 */
	public String getXuehao(){
		return xuehao;
	}
	
	/**
	 * 设置 学号
	 * @param xuehao
	 */
	public void setXuehao(String xuehao){
		this.xuehao = xuehao;
	}

	/**
	 * 获取 姓名
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 设置 姓名
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获取 民族
	 * @return
	 */
	public String getMinzu(){
		return minzu;
	}
	
	/**
	 * 设置 民族
	 * @param minzu
	 */
	public void setMinzu(String minzu){
		this.minzu = minzu;
	}

	/**
	 * 获取 政治面貌
	 * @return
	 */
	public String getZhengzhi(){
		return zhengzhi;
	}
	
	/**
	 * 设置 政治面貌
	 * @param zhengzhi
	 */
	public void setZhengzhi(String zhengzhi){
		this.zhengzhi = zhengzhi;
	}

	/**
	 * 获取 身份证号
	 * @return
	 */
	public String getIdNumber(){
		return idNumber;
	}
	
	/**
	 * 设置 身份证号
	 * @param idNumber
	 */
	public void setIdNumber(String idNumber){
		this.idNumber = idNumber;
	}

	/**
	 * 获取 籍贯
	 * @return
	 */
	public String getJiguan(){
		return jiguan;
	}
	
	/**
	 * 设置 籍贯
	 * @param jiguan
	 */
	public void setJiguan(String jiguan){
		this.jiguan = jiguan;
	}

	/**
	 * 获取 学院
	 * @return
	 */
	public String getXueyuan(){
		return xueyuan;
	}
	
	/**
	 * 设置 学院
	 * @param xueyuan
	 */
	public void setXueyuan(String xueyuan){
		this.xueyuan = xueyuan;
	}

	/**
	 * 获取 专业
	 * @return
	 */
	public String getZhuanye(){
		return zhuanye;
	}
	
	/**
	 * 设置 专业
	 * @param zhuanye
	 */
	public void setZhuanye(String zhuanye){
		this.zhuanye = zhuanye;
	}

	/**
	 * 获取 录取批次
	 * @return
	 */
	public String getLuqupici(){
		return luqupici;
	}
	
	/**
	 * 设置 录取批次
	 * @param luqupici
	 */
	public void setLuqupici(String luqupici){
		this.luqupici = luqupici;
	}

	/**
	 * 获取 班级
	 * @return
	 */
	public String getBanji(){
		return banji;
	}
	
	/**
	 * 设置 班级
	 * @param banji
	 */
	public void setBanji(String banji){
		this.banji = banji;
	}

	/**
	 * 获取 校区
	 * @return
	 */
	public String getXiaoqu(){
		return xiaoqu;
	}
	
	/**
	 * 设置 校区
	 * @param xiaoqu
	 */
	public void setXiaoqu(String xiaoqu){
		this.xiaoqu = xiaoqu;
	}

	/**
	 * 获取 公寓
	 * @return
	 */
	public String getGongyu(){
		return gongyu;
	}
	
	/**
	 * 设置 公寓
	 * @param gongyu
	 */
	public void setGongyu(String gongyu){
		this.gongyu = gongyu;
	}

	/**
	 * 获取 寝室号
	 * @return
	 */
	public String getQinshihao(){
		return qinshihao;
	}
	
	/**
	 * 设置 寝室号
	 * @param qinshihao
	 */
	public void setQinshihao(String qinshihao){
		this.qinshihao = qinshihao;
	}

	/**
	 * 获取 家庭地址
	 * @return
	 */
	public String getAddress(){
		return address;
	}
	
	/**
	 * 设置 家庭地址
	 * @param address
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * 获取 手机号码
	 * @return
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * 设置 手机号码
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/**
	 * 获取 QQ
	 * @return
	 */
	public String getQq(){
		return qq;
	}
	
	/**
	 * 设置 QQ
	 * @param qq
	 */
	public void setQq(String qq){
		this.qq = qq;
	}

	/**
	 * 获取 家长1
	 * @return
	 */
	public String getJiazhang1(){
		return jiazhang1;
	}
	
	/**
	 * 设置 家长1
	 * @param jiazhang1
	 */
	public void setJiazhang1(String jiazhang1){
		this.jiazhang1 = jiazhang1;
	}

	/**
	 * 获取 家长1电话
	 * @return
	 */
	public String getJiazhang1Phone(){
		return jiazhang1Phone;
	}
	
	/**
	 * 设置 家长1电话
	 * @param jiazhang1Phone
	 */
	public void setJiazhang1Phone(String jiazhang1Phone){
		this.jiazhang1Phone = jiazhang1Phone;
	}

	/**
	 * 获取 家长2
	 * @return
	 */
	public String getJiazhang2(){
		return jiazhang2;
	}
	
	/**
	 * 设置 家长2
	 * @param jiazhang2
	 */
	public void setJiazhang2(String jiazhang2){
		this.jiazhang2 = jiazhang2;
	}

	/**
	 * 获取 家长2电话
	 * @return
	 */
	public String getJiazhang2Phone(){
		return jiazhang2Phone;
	}
	
	/**
	 * 设置 家长2电话
	 * @param jiazhang2Phone
	 */
	public void setJiazhang2Phone(String jiazhang2Phone){
		this.jiazhang2Phone = jiazhang2Phone;
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

	/**
	 * 获取 年级
	 * @return
	 */
	public String getNianji(){
		return nianji;
	}
	
	/**
	 * 设置 年级
	 * @param nianji
	 */
	public void setNianji(String nianji){
		this.nianji = nianji;
	}

	/**
	 * 获取 性别
	 * @return
	 */
	public String getSex(){
		return sex;
	}
	
	/**
	 * 设置 性别
	 * @param sex
	 */
	public void setSex(String sex){
		this.sex = sex;
	}
	@Override
    public String toString() {
		StringBuffer buf = new StringBuffer("StudentBasicInfo=[");
				buf.append("id=").append(getId()).append(", ");
				buf.append("xuehao=").append(getXuehao()).append(", ");
				buf.append("name=").append(getName()).append(", ");
				buf.append("minzu=").append(getMinzu()).append(", ");
				buf.append("zhengzhi=").append(getZhengzhi()).append(", ");
				buf.append("idNumber=").append(getIdNumber()).append(", ");
				buf.append("jiguan=").append(getJiguan()).append(", ");
				buf.append("xueyuan=").append(getXueyuan()).append(", ");
				buf.append("zhuanye=").append(getZhuanye()).append(", ");
				buf.append("luqupici=").append(getLuqupici()).append(", ");
				buf.append("banji=").append(getBanji()).append(", ");
				buf.append("xiaoqu=").append(getXiaoqu()).append(", ");
				buf.append("gongyu=").append(getGongyu()).append(", ");
				buf.append("qinshihao=").append(getQinshihao()).append(", ");
				buf.append("address=").append(getAddress()).append(", ");
				buf.append("phone=").append(getPhone()).append(", ");
				buf.append("qq=").append(getQq()).append(", ");
				buf.append("jiazhang1=").append(getJiazhang1()).append(", ");
				buf.append("jiazhang1Phone=").append(getJiazhang1Phone()).append(", ");
				buf.append("jiazhang2=").append(getJiazhang2()).append(", ");
				buf.append("jiazhang2Phone=").append(getJiazhang2Phone()).append(", ");
				buf.append("updateTime=").append(getUpdateTime()).append(", ");
				buf.append("nianji=").append(getNianji()).append(", ");
				buf.append("sex=").append(getSex()).append(", ");
				buf.append("]");
		return buf.toString();
	}
}