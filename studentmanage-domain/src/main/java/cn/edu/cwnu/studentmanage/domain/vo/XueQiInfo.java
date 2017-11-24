package cn.edu.cwnu.studentmanage.domain.vo;

import java.util.List;

public class XueQiInfo {
	private String xueqi;
	private Integer zhuanyePaiming;
	private Integer zonghePaiming;
	private Integer bukaokemu;
	private String jiangxuejin;
	private String danxiangjiangxuejin;
	private String xueyou;
	private String tuanyou;
	private String yxdxbys;
	private String gjjxj;
	private String gjlzjxj;
	private String gjzxj;
	private String other;
	private List<String> otherhuojianginfo;
	
	
	
	public String getGjjxj() {
		return gjjxj;
	}
	public void setGjjxj(String gjjxj) {
		this.gjjxj = gjjxj;
	}
	public String getGjlzjxj() {
		return gjlzjxj;
	}
	public void setGjlzjxj(String gjlzjxj) {
		this.gjlzjxj = gjlzjxj;
	}
	public String getGjzxj() {
		return gjzxj;
	}
	public void setGjzxj(String gjzxj) {
		this.gjzxj = gjzxj;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

	public String getXueqi() {
		return xueqi;
	}
	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}
	public Integer getZhuanyePaiming() {
		return zhuanyePaiming;
	}
	public void setZhuanyePaiming(Integer zhuanyePaiming) {
		this.zhuanyePaiming = zhuanyePaiming;
	}
	public Integer getZonghePaiming() {
		return zonghePaiming;
	}
	public void setZonghePaiming(Integer zonghePaiming) {
		this.zonghePaiming = zonghePaiming;
	}
	public Integer getBukaokemu() {
		return bukaokemu;
	}
	public void setBukaokemu(Integer bukaokemu) {
		this.bukaokemu = bukaokemu;
	}
	public String getJiangxuejin() {
		return jiangxuejin;
	}
	public void setJiangxuejin(String jiangxuejin) {
		this.jiangxuejin = jiangxuejin;
	}
	public String getDanxiangjiangxuejin() {
		return danxiangjiangxuejin;
	}
	public void setDanxiangjiangxuejin(String danxiangjiangxuejin) {
		this.danxiangjiangxuejin = danxiangjiangxuejin;
	}
	public String getXueyou() {
		return xueyou;
	}
	public void setXueyou(String xueyou) {
		this.xueyou = xueyou;
	}
	public String getTuanyou() {
		return tuanyou;
	}
	public void setTuanyou(String tuanyou) {
		this.tuanyou = tuanyou;
	}
	public String getYxdxbys() {
		return yxdxbys;
	}
	public void setYxdxbys(String yxdxbys) {
		this.yxdxbys = yxdxbys;
	}
	public List<String> getOtherhuojianginfo() {
		return otherhuojianginfo;
	}
	public void setOtherhuojianginfo(List<String> otherhuojianginfo) {
		this.otherhuojianginfo = otherhuojianginfo;
	}
	@Override
	public String toString() {
		return "XueQiInfo [xueqi=" + xueqi + ", zhuanyePaiming=" + zhuanyePaiming + ", zonghePaiming=" + zonghePaiming
				+ ", bukaokemu=" + bukaokemu + ", jiangxuejin=" + jiangxuejin + ", danxiangjiangxuejin="
				+ danxiangjiangxuejin + ", xueyou=" + xueyou + ", tuanyou=" + tuanyou + ", yxdxbys=" + yxdxbys
				+ ", otherhuojianginfo=" + otherhuojianginfo + "]";
	}
	
	
	


}
