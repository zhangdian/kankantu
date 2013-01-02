package com.bd17kaka.kankantu.exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignupException extends RuntimeException {

	private static final long serialVersionUID = -7005546686395722462L;

	private String userName;
	public SignupException(String userName) {
		super();
		this.userName = userName;
	}
	@Override
	public String toString() {
		DateFormat formater = new SimpleDateFormat();
		return "SignupExistException [userName=" + userName + ", date=" + formater.format(new Date())
				+ "]";
	}
}
