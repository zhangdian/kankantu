package com.bd17kaka.kankantu.exception;

import com.bd17kaka.kankantu.utils.TimeUtils;


public class KankantuException extends Exception {

	private static final long serialVersionUID = -7005546686395722462L;
	
	private String msg;

	public KankantuException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public KankantuException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "KankantuException [time= " + TimeUtils.getCurTime() + ", msg=" + msg + "]";
	}

}
