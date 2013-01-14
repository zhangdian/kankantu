package com.bd17kaka.kankantu.po;

import java.util.Date;

/**
 * 授权信息
 */
public class SinaWeiboAuthorizeInfo implements java.io.Serializable {
	private static final long serialVersionUID = -1744081377872974319L;
	/**
	 * 用户id，微博里的id
	 */
	private String userId;
	/**
	 * 用户名称,微博名字
	 */
	private String userName;
	/**
	 * token
	 */
	private String token;

	/**
	 * 时间戳
	 */
	private String date;

	public SinaWeiboAuthorizeInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SinaWeiboAuthorizeInfo(String userId, String userName, String token, String date) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.token = token;
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "AuthorizeInfo [userId=" + userId + ", userName=" + userName
				+ ", token=" + token + ", date=" + date + "]";
	}
}
