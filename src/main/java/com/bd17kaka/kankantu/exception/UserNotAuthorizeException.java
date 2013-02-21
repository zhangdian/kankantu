package com.bd17kaka.kankantu.exception;

import com.bd17kaka.kankantu.utils.TimeUtils;


/**
 * 用户未授权或者授权过期
 * @author bd17kaka
 */
public class UserNotAuthorizeException extends Exception {

	private static final long serialVersionUID = -7005546686395722462L;
	
	private String msg;

	public UserNotAuthorizeException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UserNotAuthorizeException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "UserNotAuthorizeException [time= " + TimeUtils.getCurTime() + ", msg=" + msg + "]";
	}

}
