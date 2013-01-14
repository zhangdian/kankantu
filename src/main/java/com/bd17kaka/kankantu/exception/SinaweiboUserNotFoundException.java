package com.bd17kaka.kankantu.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SinaweiboUserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7005546686395722462L;

	private String uid;
	
	public SinaweiboUserNotFoundException(String uid) {
		super();
		this.uid = uid;
	}
	@Override
	public String toString() {
		DateFormat formater = new SimpleDateFormat();
		return "SignupExistException [userName=" + uid + ", date=" + formater.format(new Date())
				+ "]";
	}
}
