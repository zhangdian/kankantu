package com.bd17kaka.kankantu.exception;

/**
 * 用户名已存在异常
 * @author bd17kaka
 */
public class UserNameExistException extends RuntimeException {

	private static final long serialVersionUID = -7005546686395722462L;

	private String userName;

	public UserNameExistException(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserNameExistException [userName=" + userName + "]";
	}
	
}
