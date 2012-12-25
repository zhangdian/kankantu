package com.sohu.sendCloud.vo;

import java.io.Serializable;

/**
 * ESPVo
 * 
 * @author bd17kaka
 * 
 */
public class ESPVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * hello_id
	 */
	private int helloId;
	/**
	 * hello_esp
	 */
	private String helloEsp;
	/**
	 * status
	 */
	private int status;

	/**
	 * SB总数
	 */
	private int totalSB;

	/**
	 * FBL总数
	 */
	private int totalFBL;

	public int getTotalSB() {
		return totalSB;
	}

	public void setTotalSB(int totalSB) {
		this.totalSB = totalSB;
	}

	public int getTotalFBL() {
		return totalFBL;
	}

	public void setTotalFBL(int totalFBL) {
		this.totalFBL = totalFBL;
	}

	public ESPVo() {
		super();
	}

	public ESPVo(int helloId, String helloEsp, int status, int totalSB,
			int totalFBL) {
		super();
		this.helloId = helloId;
		this.helloEsp = helloEsp;
		this.status = status;
		this.totalSB = totalSB;
		this.totalFBL = totalFBL;
	}

	public int getHelloId() {
		return helloId;
	}

	public void setHelloId(int helloId) {
		this.helloId = helloId;
	}

	public String getHelloEsp() {
		return helloEsp;
	}

	public void setHelloEsp(String helloEsp) {
		this.helloEsp = helloEsp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ESPVo [helloId=" + helloId + ", helloEsp=" + helloEsp
				+ ", status=" + status + ", totalSB=" + totalSB + ", totalFBL="
				+ totalFBL + "]";
	}
}
