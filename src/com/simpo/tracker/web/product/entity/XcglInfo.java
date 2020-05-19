package com.simpo.tracker.web.product.entity;

public class XcglInfo {
	private long id = 0;
	private String xcmc = "";
	private String remark = "";
	private int zt = 0;//0-ok,1-no
	private String xcseq = "";
	private String pic = "";
	private String video = "";
	private String cjsj = "";
	private String gxsj = "";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getXcmc() {
		return xcmc;
	}
	public void setXcmc(String xcmc) {
		this.xcmc = xcmc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getZt() {
		return zt;
	}
	public void setZt(int zt) {
		this.zt = zt;
	}
	public String getXcseq() {
		return xcseq;
	}
	public void setXcseq(String xcseq) {
		this.xcseq = xcseq;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getGxsj() {
		return gxsj;
	}
	public void setGxsj(String gxsj) {
		this.gxsj = gxsj;
	}
}
