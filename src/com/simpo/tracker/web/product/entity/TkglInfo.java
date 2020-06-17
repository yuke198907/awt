package com.simpo.tracker.web.product.entity;

public class TkglInfo {
	private long id = 0;
	private long xcid = 0;
	private String tkseq = "";
	private String tkpic = "";
	private String cjsj = "";
	private String filetype = "";
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getXcid() {
		return xcid;
	}
	public void setXcid(long xcid) {
		this.xcid = xcid;
	}
	public String getTkseq() {
		return tkseq;
	}
	public void setTkseq(String tkseq) {
		this.tkseq = tkseq;
	}
	public String getTkpic() {
		return tkpic;
	}
	public void setTkpic(String tkpic) {
		this.tkpic = tkpic;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
}
