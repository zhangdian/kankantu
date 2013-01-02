package com.bd17kaka.kankantu.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StoreTokenException extends RuntimeException {

	private static final long serialVersionUID = -7005546686395722462L;
	
	private String time;

	public StoreTokenException(String time) {
		super();
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "StoreTokenException [time=" + time + "]";
	}
}
